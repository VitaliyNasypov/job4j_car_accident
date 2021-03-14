package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccidentControl {
    private final AccidentService accidentService;

    public AccidentControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("accidentTypes", accidentService.findAllAccidentType());
        model.addAttribute("rules", accidentService.findAllRule());
        return "accident/create";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        Accident accident = accidentService.findById(id);
        model.addAttribute("accident", accident);
        model.addAttribute("accidentTypes", accidentService.findAllAccidentType());
        model.addAttribute("rules", accidentService.findAllRuleExclude(accident));
        return "accident/update";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute() Accident accident, HttpServletRequest req) {
        String[] idRules = req.getParameterValues("rulesId");
        accidentService.save(accident, idRules);
        return "redirect:/";
    }
}
