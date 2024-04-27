package com.example.demo.repository;

import com.example.demo.model.Client;
import com.example.demo.model.Umg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UmgRepository extends JpaRepository<Umg,Integer> {
    Umg findUmgById(Integer umgId);
}
