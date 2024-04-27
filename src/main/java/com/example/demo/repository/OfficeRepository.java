package com.example.demo.repository;

import com.example.demo.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeRepository extends JpaRepository<Office,Integer> {
    Office findOfficeById(Integer id);
}
