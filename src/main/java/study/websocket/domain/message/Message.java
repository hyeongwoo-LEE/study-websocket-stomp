package study.websocket.domain.message;


import lombok.*;
import org.springframework.lang.Nullable;
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
public class Message extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long message_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id", nullable = false)
    private ChatRoom chatRoom;

    @Builder.Default
    @Column(nullable = false)
    private String message = "";

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MessageType type;

}
