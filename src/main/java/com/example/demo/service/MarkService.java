package com.example.demo.service;

import com.example.demo.model.Mark;

import java.util.List;

public interface MarkService {
    String addMark(Mark mark);
    void deleteMark(Integer id);
    List<Mark> getAllMarks();

    String updateMark( Mark mark);

    Mark findMarkById(Integer id);
}
