package org.microservice.likeservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "POST-SERVICE", path = "/post")
public interface PostProxy {
    @GetMapping("/posts/{id}/existing")
    public boolean checkPostIsExist (@PathVariable Long id);
}
