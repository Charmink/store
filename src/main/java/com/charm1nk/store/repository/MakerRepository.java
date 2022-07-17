package com.charm1nk.store.repository;

import com.charm1nk.store.model.Maker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MakerRepository extends JpaRepository<Maker, Long> {
    Optional<Maker> findMakerByNameContainsIgnoreCase(String name);
}
