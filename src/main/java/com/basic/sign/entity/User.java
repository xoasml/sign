package com.basic.sign.domain.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "user_info")
public class User {

    @Id
    @Column(name = "id")
    private String userId;

    @Column(name = "password")
    private String password;

}
