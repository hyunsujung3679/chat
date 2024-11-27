package com.onboarding.chat.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.domain.Persistable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chatroom")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom implements Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("채팅방 번호")
    private Long roomNo;

    @Comment("제목")
    private String title;

    @OneToMany(mappedBy = "chatRoom")
    @Comment("채팅메세지")
    private List<ChatMessage> chatMessages = new ArrayList<>();

    @Column(updatable = false)
    @Comment("생성자")
    private String insertNickname;

    @Column(updatable = false)
    @Comment("생성일자")
    private LocalDateTime insertDate;

    @PrePersist
    public void prePersist() {
        insertDate = LocalDateTime.now();
    }

    @Override
    public Long getId() {
        return roomNo;
    }

    @Override
    public boolean isNew() {
        return this.getInsertDate() == null;
    }

}
