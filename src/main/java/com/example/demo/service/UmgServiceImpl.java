package com.example.demo.service;

import com.example.demo.model.Office;
import com.example.demo.model.Umg;
import com.example.demo.repository.UmgRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UmgServiceImpl implements UmgService{

    @Resource
    UmgRepository umgRepository;

    @Override
    public String addUmg(Umg umg) {

        return umgRepository.save(umg)!=null?"УМГ добавлено успешно" : "УМГ не добавлено!";
    }

    @Override
    public List<Umg> getAllUmges() {
        return umgRepository.findAll();

    }

    @Override
    public String updateUmg(Integer id, Umg umg) {
//        umgRepository.getOne(id);
        Umg u = umgRepository.save(umg);
        return u != null ? "УМГ изменено успешно" : "УМГ не изменено!";
    }

    @Override
    public String deleteUmg(Integer id) {
        Optional<Umg> umg = umgRepository.findById(id);
        if (umg.isPresent()) {
            umgRepository.deleteById(umg.get().getId());
            return "УМГ удалено успешно!";
        }
        return "УМГ не удалено!";
    }
    @Override
    public Umg findUmgById(Integer id) {
//        List<Umg> umges = umgRepository.findAll();
//        for (Umg u : umges) {
//            if (u.getId().equals(id))
//                return u;
//        }
//        return null;
        return umgRepository.findUmgById(id);
    }

}
