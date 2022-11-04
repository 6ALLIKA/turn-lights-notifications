package com.bashka.turnlightsnotifications.dao;

import com.bashka.turnlightsnotifications.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}