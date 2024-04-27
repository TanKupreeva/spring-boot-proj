package com.example.demo.repository;

import com.example.demo.model.Grs;
import com.example.demo.service.GrsService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrsRepository extends JpaRepository<Grs,Integer> {
    Grs findGrsById(Integer id);
}
