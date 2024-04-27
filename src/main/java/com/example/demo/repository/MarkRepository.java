package com.example.demo.repository;

import com.example.demo.model.Mark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkRepository extends JpaRepository<Mark,Integer> {
    Mark findMarkById(Integer id);
}
