package com.example.auth.entity;

import com.example.core.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role extends BaseEntity {


    @Column(nullable = false, unique = true, length = 50)
    private String name; // ROLE_USER, ROLE_ADMIN
}
