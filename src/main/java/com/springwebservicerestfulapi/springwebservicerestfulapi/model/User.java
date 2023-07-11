package com.springwebservicerestfulapi.springwebservicerestfulapi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name="password")
    private String password;
    @Column(name = "role_id")
    private Long roleId;

    @OneToOne
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;
}