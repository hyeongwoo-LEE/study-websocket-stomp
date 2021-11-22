package study.websocket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.core.AbstractMessageSendingTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.broker.SimpleBrokerMessageHandler;
import org.springframework.stereotype.Controller;
import study.websocket.domain.message.Message;
import study.websocket.dto.message.MessageDTO;


@RequiredArgsConstructor
@Controller
public class MessageController {

    private final AbstractMessageSendingTemplate messageTemplate;


    /**
     * 메세지 저장
     */
    @MessageMapping(value = "/chatrooms/{chat_room_id}/messages")
    public void saveChat(MessageDTO messageDTO){

        messageTemplate.convertAndSend("/app/chatrooms/" + messageDTO.getChatRoomId(), messageDTO);
    }


    /**
     * 입장 메세지
     */

    /**
     * 퇴장 메세지
     */
}
