package com.springwebservicerestfulapi.springwebservicerestfulapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Benim Pet nesnem", description = "Benim Pet" )
public class Employee {

    @ApiModelProperty(value = "")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "")
    @Column(name = "first_name")
    private String firstName;

    @ApiModelProperty(value = "")
    @Column(name = "last_name")
    private String lastName;

    @ApiModelProperty(value = "")
    @Column(name = "email")
    private String email;

    @ApiModelProperty(value = "")
    @Column(name= "gsm")
    private String gsm;

    @ApiModelProperty(value = "")
    @Column(name = "title")
    private String title;

    @ApiModelProperty(value = "")
    @Column(name = "account_id")
    private Long accountId;

    @ApiModelProperty(value = "")
    @Column(name = "role_id")
    private Long roleId;

    @OneToOne
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;
}