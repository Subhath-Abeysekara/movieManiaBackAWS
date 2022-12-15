package com.movieMania.backend.Repository;

import com.movieMania.backend.Entity.logins;
import com.movieMania.backend.Entity.request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface requestRepository extends JpaRepository<request, Long> {

    List<request> findByMultiCode(String code);

    Optional<request> findByCode(String code);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM request v WHERE v.requestId = :id")
    void deleteByRequestId(Long id);
}
