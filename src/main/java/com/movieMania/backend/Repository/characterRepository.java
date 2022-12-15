package com.movieMania.backend.Repository;

import com.movieMania.backend.Entity.character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface characterRepository extends JpaRepository<character, Long> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM character v WHERE v.id = :id")
    void deleteByCharacterId(Long id);
}
