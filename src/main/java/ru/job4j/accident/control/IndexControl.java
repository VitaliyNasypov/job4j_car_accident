package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.hibernate.AccidentServiceHibernate;

@Controller
public class IndexControl {
    private final AccidentService accidentService;

    public IndexControl(AccidentServiceHibernate accidentServiceHibernate) {
        this.accidentService = accidentServiceHibernate;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", accidentService.findAllAccident());
        return "index";
    }
}