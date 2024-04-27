package com.example.demo.dto;

import lombok.Data;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@Data
public class InstrumentDto {
    private String name;
    private String model;

    private String factoryNumber;

    private String certificateOfVerification;

    private Date validUntil;
    private List<String> filtr= Arrays.asList("Наименование средства измерения","Тип (марка) средства измерения",
            "Заводской номер","Номер свидетельства о поверке (калибровке)","Срок действия до даты");



}
