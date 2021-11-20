package study.websocket.domain.chatRoom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {



    @Query("select cr from ChatRoom cr " +
            "join ChatJoin cj on cj.chatRoom.chatRoomId = cr.chatRoomId " +
            "where cj.user.userId = :userId")
    List<ChatRoom> getJoinChatRoomList(Long userId);

}
