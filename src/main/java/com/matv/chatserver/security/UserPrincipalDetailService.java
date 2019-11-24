package com.matv.chatserver.security;

import com.matv.chatserver.person.domain.Person;
import com.matv.chatserver.person.repo.PersonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailService implements UserDetailsService {

    @Autowired
    private PersonRepo personRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Person person = personRepo.getByLogin(s)
                .orElseThrow(() -> new IllegalArgumentException("There is no such user"));
        return new UserPrincipal(person);
    }
}
