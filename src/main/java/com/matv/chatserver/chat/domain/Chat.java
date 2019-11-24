package com.matv.chatserver.chat.domain;

import com.matv.chatserver.message.domain.Message;
import com.matv.chatserver.person.domain.PersonChat;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(generator = "chat_generator")
    @SequenceGenerator(
            name = "chat_generator",
            sequenceName = "chat_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column(unique = true)
    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Fetch(FetchMode.SUBSELECT)
    private Set<PersonChat> personChats;

    @OneToMany(mappedBy = "chat",
            cascade = CascadeType.PERSIST)
    private List<Message> messages;

}
