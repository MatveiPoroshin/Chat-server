package com.matv.chatserver.message.service;

import com.matv.chatserver.chat.mapper.ChatConverter;
import com.matv.chatserver.chat.service.ChatService;
import com.matv.chatserver.message.repo.MessageRepo;
import com.matv.chatserver.utils.dto.ChatDto;
import com.matv.chatserver.utils.dto.MessageDto;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepo repo;
    private final ChatService chatService;
    private final ChatConverter converter;

    @Transactional
    public ChatDto save(Long chatId, MessageDto dto) throws NotFoundException {
        if (dto == null) {
            throw new IllegalArgumentException("Entity is null");
        }

        return chatService.postNewMessage(chatId, converter.messageDtoToEntity(dto));
    }
}
