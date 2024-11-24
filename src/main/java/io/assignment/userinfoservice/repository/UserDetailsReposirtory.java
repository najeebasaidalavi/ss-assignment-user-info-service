package io.assignment.userinfoservice.repository;

import io.assignment.userinfoservice.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDetailsReposirtory extends JpaRepository<UserDetails, Long> {

    @Query("Select UD from UserDetails UD where UD.user.id = :userId")
    List<UserDetails> findByUserId(long userId);
}
