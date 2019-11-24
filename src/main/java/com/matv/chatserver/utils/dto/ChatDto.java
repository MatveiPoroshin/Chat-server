package com.matv.chatserver.utils.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChatDto {

    private Long id;
    private String name;
    private List<MessageDto> messages;
}
