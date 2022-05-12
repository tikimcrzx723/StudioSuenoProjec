package com.studiosueno.admin.user;

import org.springframework.data.repository.CrudRepository;

import com.studiosueno.common.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
