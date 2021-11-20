package study.websocket.domain.join;

import lombok.*;
import study.websocket.domain.BaseEntity;
import study.websocket.domain.chatRoom.ChatRoom;
import study.websocket.domain.user.User;

import javax.persistence.*;

@Getter
@ToString(exclude = {"user","chatRoom"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ChatJoin extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long joinId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id", nullable = false)
    private ChatRoom chatRoom;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType role;

    //-----연관관계 메서드-------
    public void setChatRoom(ChatRoom chatRoom){
        this.chatRoom = chatRoom;
        chatRoom.getParticipantList().add(this);
    }
}
