package com.matv.chatserver.message.repo;

import com.matv.chatserver.message.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
