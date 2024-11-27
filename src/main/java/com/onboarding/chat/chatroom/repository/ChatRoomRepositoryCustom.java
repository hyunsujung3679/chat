package com.onboarding.chat.chatroom.repository;

import com.onboarding.chat.entity.ChatRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChatRoomRepositoryCustom {

    Page<ChatRoom> findChatRoomList(Pageable pageable);

}
