package com.matv.chatserver.chat.repo;

import com.matv.chatserver.chat.domain.Chat;
import com.matv.chatserver.person.domain.Person;
import com.matv.chatserver.person.domain.PersonChat;
import com.matv.chatserver.person.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonChatRepo extends JpaRepository<PersonChat, Long> {

    @Query("SELECT role FROM PersonChat pc WHERE pc.person = person AND pc.chat = chat")
    List<UserRole> getUserRolesForPersonAndChat(Person person, Chat chat);
}
