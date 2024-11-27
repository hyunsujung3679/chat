package com.onboarding.chat.chatroom.service;

import com.onboarding.chat.chatroom.repository.ChatRoomRepository;
import com.onboarding.chat.common.dto.ChatRoomDto;
import com.onboarding.chat.common.request.InsertChatRoomReq;
import com.onboarding.chat.entity.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomDto findByRoomNo(Long roomNo) {
        ChatRoom chatRoom = chatRoomRepository.findById(roomNo)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채팅방입니다."));

        return ChatRoomDto.from(chatRoom);  // 앞서 만든 변환 메서드 사용
    }

    public Page<ChatRoom> findChatRoomList(Pageable pageable) {
        return chatRoomRepository.findChatRoomList(pageable);
    }

    public ChatRoomDto insertChatRoom(InsertChatRoomReq insertChatRoomReq) {
        ChatRoom chatRoom = chatRoomRepository.save(new ChatRoom(insertChatRoomReq.getTitle(), insertChatRoomReq.getNickname()));
        return ChatRoomDto.from(chatRoom);
    }
}
