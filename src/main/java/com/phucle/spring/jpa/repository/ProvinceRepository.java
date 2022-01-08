package com.phucle.spring.jpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.phucle.spring.jpa.entity.Province;

public interface ProvinceRepository extends JpaRepository<Province, Integer> {
}
