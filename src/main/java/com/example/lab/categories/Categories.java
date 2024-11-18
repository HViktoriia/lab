package com.example.lab.categories;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categories")
@Getter
@NoArgsConstructor
@ToString
@Data
//@Builder
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long categoriesId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    @Nullable
    private String description;

    public Categories(String name, @Nullable String description) {
        this.name = name;
        this.description = description;
    }
}
