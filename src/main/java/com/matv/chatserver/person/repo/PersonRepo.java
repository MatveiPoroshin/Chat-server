package com.matv.chatserver.person.repo;

import com.matv.chatserver.person.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepo extends JpaRepository<Person, Long> {

    Optional<Person> getByLogin(String login);
}
