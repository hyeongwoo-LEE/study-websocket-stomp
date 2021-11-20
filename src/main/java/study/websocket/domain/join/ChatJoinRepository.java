package study.websocket.domain.join;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import study.websocket.domain.chatRoom.ChatRoom;
import study.websocket.domain.user.User;

import java.util.List;

public interface ChatJoinRepository extends JpaRepository<ChatJoin, Long> {

    ChatJoin findByChatRoom(ChatRoom chatRoom);

    @Modifying(clearAutomatically = true)
    @Query("delete from ChatJoin cj " +
            "where cj.chatRoom.chatRoomId=:chatRoomId AND cj.user.userId=:userId")
    void delete(Long chatRoomId, Long userId);

    @Query("select cj, u from ChatJoin cj " +
            "left join User u on u.userId = cj.user.userId " +
            "where cj.chatRoom.chatRoomId = :chatRoomId")
    List<Object[]> getParticipantList(Long chatRoomId);

    boolean existsByChatRoomAndUser(ChatRoom chatRoom, User user);

    @Query("select cj from ChatJoin cj " +
            "left join fetch cj.user " +
            "where cj.chatRoom.chatRoomId = :chatRoomId and cj.user.userId = :userId")
    ChatJoin findByChatRoomIdAndUserId(Long chatRoomId, Long userId);
}
