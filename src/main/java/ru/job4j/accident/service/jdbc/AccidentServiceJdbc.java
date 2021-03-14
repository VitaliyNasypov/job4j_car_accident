package ru.job4j.accident.service.jdbc;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.jdbc.AccidentJdbcTemplate;
import ru.job4j.accident.service.AccidentService;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//@Service
public class AccidentServiceJdbc implements AccidentService {
    private final AccidentJdbcTemplate accidentJdbcTemplate;

    public AccidentServiceJdbc(AccidentJdbcTemplate accidentJdbcTemplate) {
        this.accidentJdbcTemplate = accidentJdbcTemplate;
    }

    @Override
    public Accident save(Accident accident, String[] idRules) {
        accident.setType(accidentJdbcTemplate.findByIdAccidentType(accident.getType().getId()));
        Set<Rule> ruleSet = accidentJdbcTemplate.findAllRule().stream()
                .filter(e -> Arrays.stream(idRules).anyMatch(s -> Integer.parseInt(s) == e.getId()))
                .collect(Collectors.toSet());
        accident.setRules(ruleSet);
        if (accident.getId() == 0) {
            accidentJdbcTemplate.create(accident);
        } else {
            accidentJdbcTemplate.update(accident);
        }
        return accident;
    }

    @Override
    public Accident findById(int id) {
        return accidentJdbcTemplate.findByIdAccident(id);
    }

    @Override
    public List<AccidentType> findAllAccidentType() {
        return accidentJdbcTemplate.findAllAccidentType();
    }

    @Override
    public List<Accident> findAllAccident() {
        return accidentJdbcTemplate.findAllAccident();
    }

    @Override
    public List<Rule> findAllRule() {
        return accidentJdbcTemplate.findAllRule();
    }

    @Override
    public List<Rule> findAllRuleExclude(Accident accident) {
        return accidentJdbcTemplate.findAllRule().stream().filter(e -> !accident.getRules()
                .contains(e)).collect(Collectors.toList());
    }
}
