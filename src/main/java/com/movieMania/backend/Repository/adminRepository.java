package com.movieMania.backend.Repository;

import com.movieMania.backend.Entity.admin;
import com.movieMania.backend.Entity.logins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface adminRepository extends JpaRepository<admin, Integer> {

    admin findByUsername(String username);
    Optional<admin> findByEmail(String email);
    @Transactional
    @Modifying
    @Query("update admin A set A.username=:username where A.username=:oldUsername")
    void setUsername(String oldUsername, String username);
    @Query("SELECT A.email FROM admin A")
    List<String> getAdminMails();
    @Transactional
    @Modifying
    @Query("update admin A set A.password=:password where A.password=:oldPassword")
    void setPassword(String oldPassword, String password);
}
