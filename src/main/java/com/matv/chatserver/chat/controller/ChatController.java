package com.matv.chatserver.chat.controller;

import com.matv.chatserver.chat.service.ChatService;
import com.matv.chatserver.utils.dto.ChatDto;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @GetMapping(value = "{id}")
    public ChatDto getChatById(@PathVariable Long id) throws NotFoundException {
        return chatService.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ChatDto addChat(@RequestBody ChatDto chatDto) {
        return chatService.save(chatDto);
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteChat(@PathVariable Long id) throws NotFoundException {
        chatService.delete(id);
    }
}
