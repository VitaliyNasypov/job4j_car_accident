package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Map;

@Service
public class AccidentService {
    private final static AccidentMem am = new AccidentMem();

    public Map<Integer, Accident> findAll() {
        return am.findAll();
    }
}
