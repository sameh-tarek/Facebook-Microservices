package org.microservice.userservice.repository;

import org.microservice.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends
        JpaRepository<User, Long> {
}
