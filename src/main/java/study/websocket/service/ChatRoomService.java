package study.websocket.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.websocket.domain.chatRoom.ChatRoom;
import study.websocket.domain.chatRoom.ChatRoomRepository;
import study.websocket.domain.join.ChatJoin;
import study.websocket.domain.join.ChatJoinRepository;
import study.websocket.domain.join.RoleType;
import study.websocket.domain.user.User;
import study.websocket.dto.chatRoom.ChatRoomDTO;
import study.websocket.dto.chatRoom.ChatRoomResDTO;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatJoinRepository chatRoomJoinRepository;

    /**
     * 채팅방 생성
     */
    @Transactional
    public ChatRoom create(ChatRoomDTO chatRoomDTO, Long principalId){

        ChatRoom chatRoom = ChatRoom.builder()
                .user(User.builder().userId(principalId).build())
                .title(chatRoomDTO.getTitle())
                .build();

        chatRoomRepository.save(chatRoom);

        ChatJoin chatRoomJoin = ChatJoin.builder()
                .user(User.builder().userId(principalId).build())
                .role(RoleType.MASTER)
                .build();

        chatRoomJoin.setChatRoom(chatRoom);

        chatRoomJoinRepository.save(chatRoomJoin);

        return chatRoom;
    }

    /**
     * 채팅방 삭제
     */
    @Transactional
    public void remove(Long chatRoomId){

        chatRoomRepository.deleteById(chatRoomId);
    }

    /**
     * 전체 채팅방 목록
     */
    @Transactional(readOnly = true)
    public List<ChatRoomResDTO> getAllChatRoomList(Long principalId){

        List<ChatRoom> allChatRoomList = chatRoomRepository.findAll(Sort.by(Sort.Direction.ASC, "chatRoomId"));

        List<ChatRoomResDTO> chatRoomResDTOList = allChatRoomList.stream().map(chatRoom -> {

            //참여 상태 체크
            for (ChatJoin chatRoomJoin : chatRoom.getParticipantList()) {
                if (chatRoomJoin.getUser().getUserId().equals(principalId)) {

                    return chatRoom.toDTO(chatRoom.getParticipantList().size(), true);
                }
            }

            return chatRoom.toDTO(chatRoom.getParticipantList().size(), false);



        }).collect(Collectors.toList());

        return chatRoomResDTOList;
    }

    /**
     * 참여중인 채팅방 목록
     */
    @Transactional(readOnly = true)
    public List<ChatRoomResDTO> getJoinChatRoomList(Long principalId){

        //내가 참여한 채팅방 리스트
        List<ChatRoom> joinChatRoomList = chatRoomRepository.getJoinChatRoomList(principalId);

        List<ChatRoomResDTO> chatRoomResDTOList =
                joinChatRoomList.stream().map(chatRoom ->
                        chatRoom.toDTO(chatRoom.getParticipantList().size(), true))
                        .collect(Collectors.toList());

        return chatRoomResDTOList;
    }


}
