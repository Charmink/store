package com.charm1nk.store.repository;

import com.charm1nk.store.model.Partition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartitionRepository extends JpaRepository<Partition, Long> {
}
