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

    @Builder.Default
    @Column(name = "is_reset")
    private boolean isReset = false;

    @Builder.Default
    @Column(name = "is_updated")
    private boolean isUpdated = false;

    @Column(name = "pin", length = 64)
    private String pin;

    @Column(name = "msisdn")
    private String msisdn;
}

