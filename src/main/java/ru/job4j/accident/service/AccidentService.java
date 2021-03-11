package ru.job4j.accident.service;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;

public interface AccidentService {
    Accident save(Accident accident, String[] idRules);

    Accident findById(int id);

    List<AccidentType> findAllAccidentType();

    List<Accident> findAllAccident();

    List<Rule> findAllRule();

    List<Rule> findAllRuleExclude(Accident accident);
}
