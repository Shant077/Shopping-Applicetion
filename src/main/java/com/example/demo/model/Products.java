package com.example.demo.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Products")
@Table(
        name = "products"

)
public class Products {
    @Id
    @SequenceGenerator(
            name = "products_sequence",
            sequenceName = "products_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "products_sequence"
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
            name = "price",
            nullable = false,
            columnDefinition = "INTEGER"
    )
    private Long price;

    @Column(
            name = "rate",
            nullable = false,
            columnDefinition = "INTEGER"
    )
    private Integer rate;

    @Column(
            name = "description",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String description;

    @Column(
            name = "pictureUrl",
            nullable = false
    )
    private String pictureUrl;


    public Products(Long id, String name, Long price, Integer rate, String description, String pictureUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rate = rate;
        this.description = description;
        this.pictureUrl = pictureUrl;
    }

    public Products() {
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", rate=" + rate +
                ", description='" + description + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                '}';
    }

}
