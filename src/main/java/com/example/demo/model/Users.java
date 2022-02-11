package com.example.demo.model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Users")
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "user_email_unique", columnNames = "email")
        }
)
public class Users {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "user_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;


    @Column(
            name = "surName",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String surName;


    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;


    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String password;


    @Column(
            name = "isAdmin",
            nullable = true,
            columnDefinition = "BOOLEAN"
    )
    private Boolean isAdmin;

    @Column(
            name = "block",
            nullable = true,
            columnDefinition = "BOOLEAN"
    )
    private Boolean block;


    public Users(String name, String surName, String email, String password, Boolean isAdmin, Boolean block) {
        this.name = name;
        this.surName = surName;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.block = block;
    }

    public Users() {

    }

    public Boolean getBlock() {
        return block;
    }

    public void setBlock(Boolean block) {
        this.block = block;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                ", block=" + block +
                '}';
    }
}
