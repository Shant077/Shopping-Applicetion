package com.example.demo.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "CommentsAndRate")
@Table(
        name = "commentsAndRate"
)
public class CommentsAndRate {
    @Id
    @SequenceGenerator(
            name = "CommentsAndRate_sequence",
            sequenceName = "CommentsAndRate_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "CommentsAndRate_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "comment",
            updatable = false
    )
    private String comment;

    @Column(
            name = "rate",
            updatable = false
    )
    private Integer rate;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private Users users;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Products products;

    public CommentsAndRate(Long id, String comment, Integer rate, Users users, Products products) {
        this.id = id;
        this.comment = comment;
        this.rate = rate;
        this.users = users;
        this.products = products;
    }

    public CommentsAndRate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "CommentsAndRate{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", rate=" + rate +
                ", users=" + users +
                ", products=" + products +
                '}';
    }
}
