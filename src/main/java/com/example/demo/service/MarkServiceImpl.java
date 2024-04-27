package com.example.demo.service;

import com.example.demo.model.Mark;
import com.example.demo.repository.MarkRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkServiceImpl implements MarkService {

    @Resource
    MarkRepository markRepository;

    @Override
    public String addMark(Mark mark) {
        return markRepository.save(mark) != null ? "Оценка добавлена успешно" : "Оценка не добавлена";
    }

    @Override
    public void deleteMark(Integer id) {
        markRepository.deleteById(id);
    }

    @Override
    public List<Mark> getAllMarks() {

            return markRepository.findAll();

        }

    @Override
    public String updateMark( Mark mark) {
        return markRepository.save(mark) != null ? "Оценка изменена успешно" : "Оценка не изменена";

    }



    @Override
    public Mark findMarkById(Integer id) {
//        List<Mark> marks = markRepository.findAll();
//        for (Mark m : marks) {
//            if (m.getId().equals(id))
//                return m;
//        }
//        return null;
        return markRepository.findMarkById(id);
    }


}
