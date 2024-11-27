package com.onboarding.chat.common.dto;

import com.onboarding.chat.entity.ChatRoom;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatRoomDto {

    private Long roomNo;
    private String title;
    private String insertNickname;
    private LocalDateTime insertDate;

    public static ChatRoomDto from(ChatRoom chatRoom) {
        ChatRoomDto dto = new ChatRoomDto();
        dto.setRoomNo(chatRoom.getRoomNo());
        dto.setTitle(chatRoom.getTitle());
        dto.setInsertNickname(chatRoom.getInsertNickname());
        dto.setInsertDate(chatRoom.getInsertDate());
        return dto;
    }

}
