package com.matv.chatserver.message.controller;

import com.matv.chatserver.message.service.MessageService;
import com.matv.chatserver.utils.dto.ChatDto;
import com.matv.chatserver.utils.dto.MessageDto;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/{id}")
    ChatDto postMessage(@RequestBody MessageDto dto, @PathVariable Long id) throws NotFoundException {
        return messageService.save(id, dto);
    }
}
