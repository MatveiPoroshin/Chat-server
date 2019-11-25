package com.matv.chatserver.person.service;

import com.matv.chatserver.chat.mapper.ChatConverter;
import com.matv.chatserver.person.domain.Person;
import com.matv.chatserver.person.repo.PersonRepo;
import com.matv.chatserver.utils.dto.PersonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepo repo;
    private final ChatConverter converter;
    private final PasswordEncoder encoder;

    public PersonDto createUser(PersonDto dto, String password) {
        if (dto == null) {
            throw new IllegalArgumentException("Entity is null");
        }

        Person newPerson = converter.personDtoToEntity(dto);
        newPerson.setPassword(encoder.encode(password));
        return converter.personToDto(repo.save(newPerson));
    }
}
