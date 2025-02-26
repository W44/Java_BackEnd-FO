package com.example.demo.Interfaces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Models.User;
import java.util.Optional;

@Repository
public interface IUsersDBRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}

