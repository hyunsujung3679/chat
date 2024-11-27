package com.onboarding.chat.chatroom.controller;

import com.onboarding.chat.chatroom.service.ChatRoomService;
import com.onboarding.chat.entity.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/chatroom")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @GetMapping("/list")
    public String chatRoomListForm(
            @RequestParam String nickname,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {

        if (nickname == null) {
            return "redirect:/login";
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "roomNo"));
        Page<ChatRoom> chatRoomPage = chatRoomService.findChatRoomList(pageable);

        model.addAttribute("nickname", nickname);
        model.addAttribute("chatRoomList", chatRoomPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", chatRoomPage.getTotalPages());
        model.addAttribute("totalElements", chatRoomPage.getTotalElements());
        model.addAttribute("size", size);

        return "chat/chatRoomListForm";
    }

}
