package ru.mephi.boot.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mephi.boot.beans.Roles;
import ru.mephi.boot.dao.models.UserEntity;
import ru.mephi.boot.dao.repositories.RolesRepository;
import ru.mephi.boot.dao.repositories.UserRepository;
import ru.mephi.boot.exeptions.MyRuntimeException;

import java.util.Collections;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(UserEntity userEntity) {
        if (userRepository.findByUsername(userEntity.getUsername()) != null
                || userEntity.getUsername().equals("anonymousUser")) {
            throw new MyRuntimeException("Пользователь с таким username уже зарегестрирован!");
        }
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRoles(rolesRepository.findBySymbolicNameIn(Collections.singleton(Roles.USER.name())));
        userRepository.save(userEntity);
    }

}
