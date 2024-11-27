package com.onboarding.chat.chatroom.controller;

import com.onboarding.chat.chatroom.service.ChatRoomService;
import com.onboarding.chat.common.dto.ChatRoomDto;
import com.onboarding.chat.common.request.InsertChatRoomReq;
import com.onboarding.chat.common.response.CommonResponse;
import com.onboarding.chat.common.response.InsertChatRoomRes;
import com.onboarding.chat.entity.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chatroom")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @GetMapping("/{roomNo}")
    public String chatRoomForm(@PathVariable String roomNo,
                               @RequestParam String nickname,
                               Model model) {
        ChatRoomDto chatRoom = chatRoomService.findByRoomNo(Long.valueOf(roomNo));

        model.addAttribute("roomNo", roomNo);
        model.addAttribute("roomTitle", chatRoom.getTitle());
        model.addAttribute("nickname", nickname);

        return "chat/chatRoomForm";
    }

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

    @PostMapping
    public ResponseEntity<CommonResponse> insertChatRoom(@RequestBody InsertChatRoomReq insertChatRoomReq) {
        ChatRoomDto chatRoomDto = chatRoomService.insertChatRoom(insertChatRoomReq);
        InsertChatRoomRes response = new InsertChatRoomRes();
        response.setChatRoomDto(chatRoomDto);
        return ResponseEntity.ok(CommonResponse.success(response));
    }

}
