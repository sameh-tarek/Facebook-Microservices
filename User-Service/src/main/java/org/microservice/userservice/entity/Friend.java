package org.microservice.userservice.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.microservice.userservice.entity.enums.FriendshipStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "friends")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_id")
    private Long friendId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "friend_user_id", nullable = false)
    private User friendUser;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private FriendshipStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Friend(User user, User friendUser, FriendshipStatus status) {
        this.user = user;
        this.friendUser = friendUser;
        this.status = status;
    }
}
