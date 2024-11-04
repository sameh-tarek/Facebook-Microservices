package org.microservice.feedservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "feeds")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_id")
    private Long feedId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "post_id", nullable = false)
    private Long postId;

    @Column(name = "origin_user_id", nullable = false)
    private Long originUserId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "priority")
    private int priority;
}
