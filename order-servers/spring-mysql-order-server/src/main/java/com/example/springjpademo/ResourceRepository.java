package com.example.springjpademo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ResourceRepository extends JpaRepository<Resource, Integer>{

}
