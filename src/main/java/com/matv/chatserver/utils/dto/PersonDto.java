package com.matv.chatserver.utils.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PersonDto {

    private Long id;

    private String name;

    private Date lastSeen;
}
