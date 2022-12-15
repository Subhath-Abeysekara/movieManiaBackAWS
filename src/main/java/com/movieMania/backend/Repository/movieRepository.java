package com.movieMania.backend.Repository;

import com.movieMania.backend.Entity.logins;
import com.movieMania.backend.Entity.movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface movieRepository extends JpaRepository<movie, Long> {
    List<movie> findByCategory(String category);
    List<movie> findByNameLike(String name);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM movie v WHERE v.movieId = :id")
    void deleteByMovieId(Long id);
}
