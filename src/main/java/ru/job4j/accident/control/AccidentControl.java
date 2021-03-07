package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class AccidentControl {
    private final AccidentMem accidents;
    private final List<AccidentType> accidentTypes;
    private final List<Rule> rules;

    public AccidentControl(AccidentMem accidents) {
        this.accidents = accidents;
        accidentTypes = new ArrayList<>();
        accidentTypes.add(AccidentType.of(1, "Two cars"));
        accidentTypes.add(AccidentType.of(2, "Machine and man"));
        accidentTypes.add(AccidentType.of(3, "Car and bicycle"));
        rules = new ArrayList<>();
        rules.add(Rule.of(1, "Article. 1"));
        rules.add(Rule.of(2, "Article. 2"));
        rules.add(Rule.of(3, "Article. 3"));
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("accidentTypes", accidentTypes);
        model.addAttribute("rules", rules);
        return "accident/create";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        Accident accident = accidents.findById(id);
        model.addAttribute("accident", accident);
        model.addAttribute("accidentTypes", accidentTypes);
        model.addAttribute("rules", rules.stream().filter(e -> !accident.getRules()
                .contains(e)).collect(Collectors.toSet()));
        return "accident/update";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute() Accident accident, HttpServletRequest req) {
        accident.setType(accidentTypes.stream()
                .filter(e -> e.getId() == accident.getType().getId())
                .findFirst()
                .orElse(AccidentType.of(1, "Two cars")));
        String[] ids = req.getParameterValues("rulesId");
        Set<Rule> ruleSet = rules.stream()
                .filter(e -> Arrays.stream(ids).anyMatch(s -> Integer.parseInt(s) == e.getId()))
                .collect(Collectors.toSet());
        accident.setRules(ruleSet);
        accidents.create(accident);
        return "redirect:/";
    }
}
