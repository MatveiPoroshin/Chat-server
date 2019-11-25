package com.matv.chatserver.utils.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class PersonDto {

    private Long id;

    private String name;

    private Date lastSeen;
}
