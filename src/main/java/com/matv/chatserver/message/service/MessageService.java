package com.matv.chatserver.message.service;

import com.matv.chatserver.chat.mapper.ChatConverter;
import com.matv.chatserver.message.domain.Message;
import com.matv.chatserver.message.repo.MessageRepo;
import com.matv.chatserver.utils.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MessageService {

    private MessageRepo repo;
    private ChatConverter converter;

    @Transactional
    public MessageDto save(MessageDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Entity is null");
        }

        Message savedEntity = repo.save(converter.messageDtoToEntity(dto));
        log.info("message saved " + savedEntity.getId());
        return converter.messageToDto(savedEntity);
    }
}
