package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        List<String> listUsers = new ArrayList<>();
        listUsers.add("Monte Dunigan");
        listUsers.add("Tomi Breslin");
        listUsers.add("Livia Lacomb");
        listUsers.add("Hortensia Poore");
        listUsers.add("Madalene Schier");
        listUsers.add("Mikaela Hagedorn");
        listUsers.add("Shanice Sen");
        listUsers.add("Hilario Carmean");
        listUsers.add("Zetta Canela");
        listUsers.add("Enola Natoli");
        model.addAttribute("users", listUsers);
        return "index";
    }
}