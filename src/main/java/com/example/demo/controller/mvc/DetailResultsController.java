package com.example.demo.controller.mvc;

import com.example.demo.model.Criteria;
import com.example.demo.model.DetailResult;
import com.example.demo.model.Report;
import com.example.demo.model.Result;
import com.example.demo.service.CriteriaService;
import com.example.demo.service.DetailResultsService;
import com.example.demo.service.ReportService;
import com.example.demo.service.ResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/detail_results")
public class DetailResultsController {

    Logger logger = LoggerFactory.getLogger(DetailResultsController.class);

    @Autowired
    DetailResultsService detailResultsService;
    @Autowired
    ResultService resultService;

    @Autowired
    CriteriaService criteriaService;

    @GetMapping("/show/{id}")
    public String getShow(@PathVariable(name = "id") Integer id, Model model) {
        logger.info("get all detail results");


        Result result = resultService.findResultById(id);
        model.addAttribute("result", result);
        Report report = result.getReport();
        model.addAttribute("report",report);
        List<DetailResult> detailResults = detailResultsService.getAllDetailResultsByResultId(result.getId());

        Set<Criteria> criteria = detailResultsService.findByIdResult(id);
        double green = 0;
        double red = 0;
        for (Criteria c : criteria) {
            green = Double.valueOf(c.getAcceptable().getMarkTo());
            red = Double.valueOf(c.getUnacceptable().getMarkFrom());
        }

        model.addAttribute("green", green);
        model.addAttribute("red", red);

        model.addAttribute("criterias", detailResultsService.findByIdResult(id));
        model.addAttribute("detailResults", detailResults);
        model.addAttribute("number", result.getNumberN());
        model.addAttribute("t", result.getQuantityT());
        model.addAttribute("listV", detailResultsService.listV(id));
        model.addAttribute("listP", detailResultsService.listP(id));
        model.addAttribute("listO", detailResultsService.listO(id));

        return "show_detail_result";
    }


    @GetMapping("/new/{id}")
    public String getShowForm(@PathVariable(name = "id") Integer id, Model model) {
        logger.info("show form for new detail result");
        Result result = resultService.findResultById(id);
        model.addAttribute("result", result);
        model.addAttribute("report", result.getReport());
        DetailResult detailResult = new DetailResult();
        model.addAttribute("criterias", criteriaService.getAllCriterias());
        model.addAttribute("detailResult", detailResult);
        model.addAttribute("number", result.getNumberN());
        model.addAttribute("t", result.getQuantityT());

        List<Double> listV = new ArrayList<>();
        for (int i = 0; i < result.getQuantityT(); i++) {
            listV.add(null);
        }
        model.addAttribute("listV", listV);

        List<Double> listP = new ArrayList<>();
        for (int i = 0; i < result.getQuantityT(); i++) {
            listP.add(null);
        }
        model.addAttribute("listP", listP);

        List<Double> listO = new ArrayList<>();
        for (int i = 0; i < result.getQuantityT(); i++) {
            listO.add(null);
        }
        model.addAttribute("listO", listO);
        logger.info("Show form for edit detailresult {}", detailResult);
        return "new_detail_result";
    }


    @PostMapping("/new/{id}")
    public String submitForm(@ModelAttribute("detail_result") DetailResult detailResult,
                             @PathVariable(name = "id") Integer idResult,
                             @RequestParam("listV") List<Double> listV,
                             @RequestParam("listP") List<Double> listP,
                             @RequestParam("listO") List<Double> listO,
                             @RequestParam("criteria") List<Criteria> c,
                             RedirectAttributes redirectAttributes) {
        logger.info("Adding detailResult {}", detailResult);


        int t = resultService.findResultById(idResult).getQuantityT();
        for (int i = 0; i < t; i++) {
            DetailResult dr = new DetailResult();
            dr.setT(i + 1);
            dr.setV(listV.get(i));
            dr.setP(listP.get(i));
            dr.setO(listO.get(i));
            dr.setCriteria(c.get(0));
            dr.setResult(resultService.findResultById(idResult));
            detailResultsService.addDetailResults(dr);
        }


//        String msg = detailResultsService.addDetailResults(detailResult);
//        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/detail_results/show/{id}";
    }



//
//    @GetMapping("/edit/{id}")
//    public String editDetailResult(@PathVariable(name = "id") Integer id, Model model) {
//        Result result = resultService.findResultById(id);
//        model.addAttribute("result", result);
//        Report report = result.getReport();
//        model.addAttribute("report",report);
//        List<DetailResult> detailResults = detailResultsService.getAllDetailResultsByResultId(result.getId());
//
//        Set<Criteria> criteria = detailResultsService.findByIdResult(id);
//        double green = 0;
//        double red = 0;
//        for (Criteria c : criteria) {
//            green = Double.valueOf(c.getAcceptable().getMarkTo());
//            red = Double.valueOf(c.getUnacceptable().getMarkFrom());
//        }
//
//        model.addAttribute("green", green);
//        model.addAttribute("red", red);
//
//        model.addAttribute("criterias", criteriaService.getAllCriterias());
//        model.addAttribute("detailResults", detailResults);
//        model.addAttribute("number", result.getNumberN());
//        model.addAttribute("t", result.getQuantityT());
//        model.addAttribute("listV", detailResultsService.listV(id));
//        model.addAttribute("listP", detailResultsService.listP(id));
//        model.addAttribute("listO", detailResultsService.listO(id));
//
//        return "edit_detail_result";
//    }
//
//    @PostMapping("/edit/{id}")
//
//        public String updateDetailResult(@ModelAttribute("detail_result") DetailResult detailResult,
//                @PathVariable(name = "id") Integer idResult,
//                @RequestParam("listV") List<Double> listV,
//                @RequestParam("listP") List<Double> listP,
//                @RequestParam("listO") List<Double> listO,
//                @RequestParam("criteria") List<Criteria> c,
//                RedirectAttributes redirectAttributes) {
//
//
//            int t = resultService.findResultById(idResult).getQuantityT();
//            for (int i = 0; i < t; i++) {
//                DetailResult dr = new DetailResult();
//                dr.setT(i + 1);
//                dr.setV(listV.get(i));
//                dr.setP(listP.get(i));
//                dr.setO(listO.get(i));
//                dr.setCriteria(c.get(0));
//                dr.setResult(resultService.findResultById(idResult));
//                detailResultsService.updateDetailResults(????, dr);
//            }

//
////        String msg = detailResultsService.addDetailResults(detailResult);
////        redirectAttributes.addFlashAttribute("msg", msg);
//            return "redirect:/detail_results/show/{id}";
//    }

}