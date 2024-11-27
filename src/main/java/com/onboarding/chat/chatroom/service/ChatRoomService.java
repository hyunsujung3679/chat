package com.onboarding.chat.chatroom.service;

import com.onboarding.chat.chatroom.repository.ChatRoomRepository;
import com.onboarding.chat.entity.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public Page<ChatRoom> findChatRoomList(Pageable pageable) {
        return chatRoomRepository.findChatRoomList(pageable);
    }
}
