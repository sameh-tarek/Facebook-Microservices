package org.microservice.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.microservice.userservice.entity.User;
import org.microservice.userservice.exception.RecordNotFoundException;
import org.microservice.userservice.repository.UserRepository;
import org.microservice.userservice.service.AuthService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    @Override
    public void checkUserIsAuth(Long id) {
        //TODO: check Auth userId from JWT is equal id or Not

        log.info("checking user is Authorized to make this Action or Not");
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("This User with id " + id + " Doesn't Exist!"));
    }
}
