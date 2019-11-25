package com.matv.chatserver.chat.service;

import com.matv.chatserver.chat.domain.Chat;
import com.matv.chatserver.chat.mapper.ChatConverter;
import com.matv.chatserver.chat.repo.ChatRepo;
import com.matv.chatserver.chat.repo.PersonChatRepo;
import com.matv.chatserver.message.domain.Message;
import com.matv.chatserver.person.domain.Person;
import com.matv.chatserver.person.domain.PersonChat;
import com.matv.chatserver.person.domain.UserRole;
import com.matv.chatserver.security.UserPrincipal;
import com.matv.chatserver.utils.dto.ChatDto;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepo chatRepo;
    private final PersonChatRepo personChatRepo;
    private final ChatConverter converter;

    public ChatDto get(Long entityId) throws NotFoundException {
        return converter.chatToDto(getChatEntity(entityId));
    }

    @Transactional
    public ChatDto save(ChatDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Entity is null");
        }

        Person owner = getCurrentSessionUser();

        Chat newChat = converter.chatDtoToEntity(dto);
        Set<PersonChat> participants = new HashSet<>();
        participants.add(new PersonChat(null, newChat, owner, UserRole.OWNER));
        newChat.setPersonChats(participants);
        return converter.chatToDto(chatRepo.save(newChat));
    }

    @Transactional
    public void delete(Long entityId) throws NotFoundException {
        Person person = getCurrentSessionUser();
        Chat entity = chatRepo.findById(entityId)
                .orElseThrow(() -> new NotFoundException("chat with id " + entityId + " not found"));

        boolean isAbleToDelete = personChatRepo.getUserRolesForPersonAndChat(person, entity)
                .contains(UserRole.OWNER);
        if (isAbleToDelete) {
            chatRepo.deleteById(entityId);
            log.info("chat deleted " + entityId);
        }
    }

    public ChatDto postNewMessage(Long entityId, Message message) throws NotFoundException {
        Chat chat = getChatEntity(entityId);
        List<Message> messages = chat.getMessages();
        messages.add(message);
        return converter.chatToDto(chatRepo.save(chat));
    }

    private Person getCurrentSessionUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((UserPrincipal) authentication.getPrincipal()).getPerson();
    }

    private Chat getChatEntity(Long entityId) throws NotFoundException {
        return chatRepo.findById(entityId)
                .orElseThrow(() -> new NotFoundException("chat with id " + entityId + " not found"));
    }
}
