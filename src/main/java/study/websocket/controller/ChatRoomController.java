package study.websocket.controller;

import jdk.jfr.MemoryAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.core.AbstractMessageSendingTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import study.websocket.domain.chatRoom.ChatRoom;
import study.websocket.dto.chatRoom.ChatRoomDTO;
import study.websocket.dto.chatRoom.ChatRoomResDTO;
import study.websocket.service.ChatRoomService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ChatRoomController {

    private final AbstractMessageSendingTemplate messageTemplate;
    private final ChatRoomService chatRoomService;

    /**
     * 채팅방 목록
     */
    @GetMapping("/chatrooms")
    public String chatRoomList(Model model){

        List<ChatRoomResDTO> chatRoomResDTOList = chatRoomService.getAllChatRoomList(1L);

        model.addAttribute("chatRoomResDTOList",chatRoomResDTOList);

        return "index";
    }

    /**
     * 채팅방 생성
     */
    @MessageMapping("/chatrooms")
    public void saveChatRoom(@RequestBody ChatRoomDTO chatRoomDTO){

        ChatRoom chatRoom = chatRoomService.create(chatRoomDTO, chatRoomDTO.getMasterId());
        ChatRoomResDTO chatRoomResDTO = chatRoom.toDTO(1, false);
        messageTemplate.convertAndSend("/topic/chatrooms", chatRoomResDTO);

    }




}
