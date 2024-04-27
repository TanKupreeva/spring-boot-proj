package com.example.demo.dto;

import com.example.demo.model.Mark;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriteriaDto {
    private Integer id;
    private String position;
    private String parameter;
    private List<Mark> marks;
    private List<String> list=Arrays.asList("отлично","хорошо","допустимо","требует принятия мер","недопустимо");

}
