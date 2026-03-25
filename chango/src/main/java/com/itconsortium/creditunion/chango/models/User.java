package com.itconsortium.creditunion.chango.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name", unique = true)
    private String username;

    @Builder.Default
    @Column(name = "is_active")
    private boolean active = true;

    @Column(name = "pin")
    private String pin;

    @Column(name = "msisdn")
    private String msisdn;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ElementCollection
    @CollectionTable(name = "user_approval_ids", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "approval_id")
    private Set<Long> approvalIds;
}

