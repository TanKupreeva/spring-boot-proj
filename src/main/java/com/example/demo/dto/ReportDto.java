package com.example.demo.dto;


import com.example.demo.model.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@Data
public class ReportDto {
    private Integer id;
    //    private String grsName;
    private Grs grs;
    private String registrationNum;

    private String date;


    private String testDate;


    //    private String officeName;


//    private Client client;



//    private String umgName;


    private List<String> fioPersons;
//    private Integer clientId;
    private List<String> oifPersons;

    private List<Condition> conditionList;
    private List<Instrument> instrumentList;
    private Set<Criteria> criteriaList;


    private List<DetailResult> detailResultList;
    private List<Result> resultList;


    private List<Person> person;
    private String scheme;


}

