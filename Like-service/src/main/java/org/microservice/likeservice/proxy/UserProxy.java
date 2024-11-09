package org.microservice.likeservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE", path = "/user")
public interface UserProxy {
    @GetMapping("/auth/{id}")
    public ResponseEntity<Void> checkUserIsAuth (@PathVariable Long id);
}
