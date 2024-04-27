package com.example.demo.service;

import com.example.demo.dto.ReportDto;
import com.example.demo.model.Grs;
import com.example.demo.model.Umg;
import com.example.demo.repository.GrsRepository;
import com.example.demo.repository.UmgRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class GrsServiceImpl implements GrsService {
    @Resource
    GrsRepository grsRepository;

    @Resource
    UmgRepository umgRepository;

    @Override
    public String addGrs(Grs grs) {
        return grsRepository.save(grs) != null ? "ГРС добавлено успешно" : "ГРС не добавлено!";
    }

    @Override
    public List<Grs> getAllGrses() {
        return grsRepository.findAll();
    }

    @Override
    public String updateGrs(Integer id, Grs grs) {
        Grs g = findGrsById(id);
        grsRepository.save(grs);
        return g != null ? "ГРС изменено успешно" : "ГРС не изменено!";
    }

    @Override
    public String deleteGrs(Integer id) {
        Optional<Grs> grs = grsRepository.findById(id);
        if (grs.isPresent()) {
            grsRepository.deleteById(id);
            return "ГРС удалено успешно!";
        }
        return "ГРС не удалено!";
    }

    @Override
    public Grs findGrsById(Integer id) {
//        List<Grs> grses = getAllGrses();
//        for (Grs g : grses) {
//            if (g.getId().equals(id))
//                return g;
//        }
//        return null;
        return  grsRepository.findGrsById(id);
    }

    @Override
    public Set<Grs> findBy(String start) {
        Set<Grs> grsSet = new HashSet<>();
        for (Grs g : getAllGrses()) {
            if (g.getName().toLowerCase().contains(start.toLowerCase()))
                grsSet.add(g);
            if (g.getClient().getUmg().getName().toLowerCase().contains(start.toLowerCase()))
                grsSet.add(g);

        }
        return grsSet;
    }
}
