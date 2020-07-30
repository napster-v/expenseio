package com.expense.io.project.auth;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<AppUser, Integer> {
    Optional<AppUser> findByUsername(String username);

    AppUser getByUsername(String username);
}
