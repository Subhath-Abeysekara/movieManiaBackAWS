package com.movieMania.backend.Repository;

import com.movieMania.backend.Entity.logins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface loginRepository extends JpaRepository<logins, Integer> {

    @Transactional
    @Modifying
    @Query("update logins A set A.loginCount=:loginCount where A.id=:id")
    void setCount(int loginCount, int id);
    Optional<logins> findByIpAddress(String ip);

}
