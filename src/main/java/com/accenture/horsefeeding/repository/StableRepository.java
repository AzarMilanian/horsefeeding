package com.accenture.horsefeeding.repository;
import com.accenture.horsefeeding.model.Stable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StableRepository extends JpaRepository<Stable, Long> {
}
