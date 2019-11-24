package com.matv.chatserver.utils.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MessageDto {

    private Long id;

    private Date createAt;

    private String message;

    private PersonDto author;
}
