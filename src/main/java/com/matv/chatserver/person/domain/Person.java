package com.matv.chatserver.person.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matv.chatserver.message.domain.Message;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(exclude="messages")
@Table(name = "person")
public class Person implements Serializable {

    @Id
    @GeneratedValue(generator = "person_generator")
    @SequenceGenerator(
            name = "person_generator",
            sequenceName = "person_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column(unique = true, nullable = false)
    @NonNull
    private String login;

    @Column(name = "password")
    @NonNull
    @Transient
    private String password;

    @Column
    private String name;

    @JsonIgnore
    @Column
    @OneToMany(mappedBy = "author",
            cascade = CascadeType.PERSIST)
    private Set<Message> messages;

    @JsonIgnore
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Fetch(FetchMode.SUBSELECT)
    private Set<PersonChat> personChats;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSeen;
}
