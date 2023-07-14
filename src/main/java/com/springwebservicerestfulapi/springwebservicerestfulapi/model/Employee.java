package com.springwebservicerestfulapi.springwebservicerestfulapi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name= "gsm")
    private String gsm;
    @Column(name = "title")
    private String title;
    @Column(name = "account_id")
    private Long accountId;
    @Column(name = "role_id")
    private Long roleId;

    @OneToOne
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;
}