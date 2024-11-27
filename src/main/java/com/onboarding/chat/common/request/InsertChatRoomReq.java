package com.onboarding.chat.common.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InsertChatRoomReq {

    private String title;
    private String nickname;

}
