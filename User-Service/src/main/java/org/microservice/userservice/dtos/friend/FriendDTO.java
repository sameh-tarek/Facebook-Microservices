package org.microservice.userservice.dtos.friend;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.microservice.userservice.entity.User;
import org.microservice.userservice.entity.enums.FriendshipStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendDTO {
    private Long friendId;
    private User friendUser;
    private FriendshipStatus status;
    private LocalDateTime createdAt;
}
