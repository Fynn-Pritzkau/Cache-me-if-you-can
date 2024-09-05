package de.bbk.cachemeifyoucan.repository;

import de.bbk.cachemeifyoucan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
    User findByUsername(String username);
}