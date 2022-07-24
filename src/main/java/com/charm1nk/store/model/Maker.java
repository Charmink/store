package com.charm1nk.store.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "maker")
@Entity
public class Maker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "country", nullable = false)
    private String country;

    @OneToMany(mappedBy = "maker", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;
}
