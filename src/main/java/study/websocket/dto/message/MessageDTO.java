package study.websocket.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.websocket.domain.message.MessageType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

    private Long chatRoomId;

    private String sender;

    private MessageType messageType;

    private String message;

}
