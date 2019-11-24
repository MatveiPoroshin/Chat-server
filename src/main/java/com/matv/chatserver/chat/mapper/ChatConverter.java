package com.matv.chatserver.chat.mapper;

import com.matv.chatserver.chat.domain.Chat;
import com.matv.chatserver.message.domain.Message;
import com.matv.chatserver.utils.dto.ChatDto;
import com.matv.chatserver.utils.dto.MessageDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChatConverter {

    ChatConverter INSTANCE = Mappers.getMapper(ChatConverter.class);

    ChatDto chatToDto(Chat chat);

    Chat chatDtoToEntity(ChatDto chatDto);

    MessageDto messageToDto(Message message);

    Message messageDtoToEntity(MessageDto messageDto);

    List<ChatDto> toDtoList(List<Chat> chats);

    List<Chat> toEntityList(List<ChatDto> chatDtos);

}
