package com.onboarding.chat.chatroom.repository;

import com.onboarding.chat.entity.ChatRoom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.onboarding.chat.entity.QChatRoom.chatRoom;

public class ChatRoomRepositoryCustomImpl implements ChatRoomRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ChatRoomRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ChatRoom> findChatRoomList(Pageable pageable) {
        List<ChatRoom> results = queryFactory
                .selectFrom(chatRoom)
                .orderBy(chatRoom.roomNo.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = queryFactory
                .select(chatRoom.count())
                .from(chatRoom)
                .fetchOne();

        return new PageImpl<>(results, pageable, count != null ? count : 0L);
    }

}
