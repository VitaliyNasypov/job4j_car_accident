package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.User;
import ru.job4j.accident.service.AccidentServiceUser;

@Controller
public class RegControl {
    private final AccidentServiceUser accidentServiceUser;

    public RegControl(AccidentServiceUser accidentServiceUser) {
        this.accidentServiceUser = accidentServiceUser;
    }

    @PostMapping("/reg")
    public String save(@ModelAttribute User user) {
        accidentServiceUser.save(user);
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String reg(@ModelAttribute Accident accident) {
        return "reg";
    }
}
