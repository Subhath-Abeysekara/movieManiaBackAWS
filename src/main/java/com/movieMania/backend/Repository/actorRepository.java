package com.movieMania.backend.Repository;

import com.movieMania.backend.Entity.actors;
import com.movieMania.backend.Entity.character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface actorRepository extends JpaRepository<actors, Long> {
}
