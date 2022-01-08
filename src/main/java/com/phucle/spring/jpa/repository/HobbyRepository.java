package com.phucle.spring.jpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.phucle.spring.jpa.entity.Hobby;



public interface HobbyRepository extends JpaRepository<Hobby, Integer> {
}
