package com.onboarding.chat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "messagetype")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageType {

    @Id
    @Comment("메세지종류 번호")
    private Long messageTypeNo;

    @Comment("메세지종류")
    private String messageType;

    @OneToMany(mappedBy = "messageType")
    @Comment("채팅메세지")
    private List<ChatMessage> chatMessages = new ArrayList<>();

}
