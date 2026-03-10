package com.secondbrain.auth.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Data
@Entity
@Table(name = "user_settings")
public class UserSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "module_order", columnDefinition = "TEXT")
    private String moduleOrder;

    @Column(name = "hidden_modules", columnDefinition = "TEXT")
    private String hiddenModules;
}
