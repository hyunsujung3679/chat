package com.onboarding.chat.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.domain.Persistable;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "chatmessage")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage implements Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("메세지 번호")
    private Long messageNo;

    @Comment("메세지")
    @Column(columnDefinition = "TEXT")
    private String message;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "room_no")
    @Comment("채팅방")
    private ChatRoom chatRoom;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "message_type_no")
    @Comment("채팅 메세지 종류")
    private MessageType messageType;

    @Column(updatable = false)
    @Comment("생성자")
    private String insertNickname;

    @Column(updatable = false)
    @Comment("생성일자")
    private LocalDateTime insertDate;

    //==연관관계 메서드==//
    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
        chatRoom.getChatMessages().add(this);
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
        messageType.getChatMessages().add(this);
    }

    @PrePersist
    public void prePersist() {
        insertDate = LocalDateTime.now();
    }

    @Override
    public Long getId() {
        return messageNo;
    }

    @Override
    public boolean isNew() {
        return this.getInsertDate() == null;
    }

}
