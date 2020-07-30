package com.expense.io.project.auth;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvatarRepository extends CrudRepository<Avatar, Integer> {
}
