package com.example.lab.product;

import com.example.lab.categories.Categories;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long product_id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "category_id", nullable = false)
    private Categories bookCategory;

    @Column(name = "book_description", nullable = false)
    private String book_description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    public Product(String title, String author, Categories book_category, String book_description, Double price) {
        this.title = title;
        this.author = author;
        this.bookCategory = book_category;
        this.book_description = book_description;
        this.price = price;
    }

    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
}
