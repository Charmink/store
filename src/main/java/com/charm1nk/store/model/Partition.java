package com.charm1nk.store.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "partition")
public class Partition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "partition", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;
}
