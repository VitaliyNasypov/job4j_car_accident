package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentMem;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AccidentControl {
    private final AccidentMem accidents;
    private final List<AccidentType> accidentTypes;

    public AccidentControl(AccidentMem accidents) {
        this.accidents = accidents;
        accidentTypes = new ArrayList<>();
        accidentTypes.add(AccidentType.of(1, "Две машины"));
        accidentTypes.add(AccidentType.of(2, "Машина и человек"));
        accidentTypes.add(AccidentType.of(3, "Машина и велосипед"));
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("accidentTypes", accidentTypes);
        return "accident/create";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidents.findById(id));
        model.addAttribute("accidentTypes", accidentTypes);
        return "accident/update";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute() Accident accident) {
        accident.setType(accidentTypes.stream()
                .filter(e -> e.getId() == accident.getType().getId())
                .findFirst()
                .orElse(new AccidentType()));
        accidents.create(accident);
        return "redirect:/";
    }
}
