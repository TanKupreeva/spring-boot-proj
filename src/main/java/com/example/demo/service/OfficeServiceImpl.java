package com.example.demo.service;

import com.example.demo.model.Instrument;
import com.example.demo.model.Office;
import com.example.demo.repository.OfficeRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OfficeServiceImpl implements OfficeService {

    @Resource
    private OfficeRepository officeRepository;


    @Override
    public String addOffice(Office office) {

        return officeRepository.save(office)!=null? "Служба добавлена успешно" : "Служба не добавлена!";
    }

    @Override
    public List<Office> getAllOffices() {
        return officeRepository.findAll();

    }

    @Override
    public String updateOffice(Integer id, Office office) {
        officeRepository.getOne(id);
        Office o = officeRepository.save(office);
        return o != null ? "Служба изменена успешно" : "Служба не изменена!";
    }

    @Override
    public String deleteOffice(Integer officeId) {
        Optional<Office> office = officeRepository.findById(officeId);
        if (office.isPresent()) {
            officeRepository.deleteById(office.get().getId());
            return "Служба удалена успешно!";
        }
        return "Служба не удалена!";
    }
@Override
    public Office findOfficeById(Integer id) {
//        List<Office> offices = officeRepository.findAll();
//        for (Office o : offices) {
//            if (o.getId().equals(id))
//                return o;
//        }
//        return null;
    return officeRepository.findOfficeById(id);
    }

}
