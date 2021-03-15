package ru.job4j.accident.service.data;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.User;
import ru.job4j.accident.repository.data.AuthorityRepository;
import ru.job4j.accident.repository.data.UserRepository;
import ru.job4j.accident.service.AccidentServiceUser;

@Service
public class AccidentServiceUserData implements AccidentServiceUser {
    private final PasswordEncoder encoder;
    private final UserRepository users;
    private final AuthorityRepository authorities;

    public AccidentServiceUserData(PasswordEncoder encoder, UserRepository users,
                                   AuthorityRepository authorities) {
        this.encoder = encoder;
        this.users = users;
        this.authorities = authorities;
    }

    @Override
    public void save(User user) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorities.findByAuthority("ROLE_USER"));
        users.save(user);
    }
}
