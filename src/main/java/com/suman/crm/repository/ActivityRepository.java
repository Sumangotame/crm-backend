package com.suman.crm.repository;

import com.suman.crm.model.Activity;
import com.suman.crm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByOwner(User owner);
}
