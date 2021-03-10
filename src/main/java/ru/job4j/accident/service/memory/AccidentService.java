package ru.job4j.accident.service.memory;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.memory.AccidentMem;
import ru.job4j.accident.repository.memory.AccidentTypeMem;
import ru.job4j.accident.repository.memory.RuleMem;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccidentService {
    private final AccidentMem accidentMem;
    private final AccidentTypeMem accidentTypeMem;
    private final RuleMem ruleMem;

    public AccidentService(AccidentMem accidentMem, AccidentTypeMem accidentTypeMem, RuleMem ruleMem) {
        this.accidentMem = accidentMem;
        this.accidentTypeMem = accidentTypeMem;
        this.ruleMem = ruleMem;
    }

    public Accident save(Accident accident, String[] idRules) {
        accident.setType(accidentTypeMem.findAll().stream()
                .filter(e -> e.getId() == accident.getType().getId())
                .findFirst()
                .orElse(accidentTypeMem.findAll().get(0)));
        Set<Rule> ruleSet = ruleMem.findAll().stream()
                .filter(e -> Arrays.stream(idRules).anyMatch(s -> Integer.parseInt(s) == e.getId()))
                .collect(Collectors.toSet());
        accident.setRules(ruleSet);
        accidentMem.save(accident);
        return accident;
    }

    public Accident findById(int id) {
        return accidentMem.findById(id);
    }

    public List<Accident> findAllAccident() {
        return accidentMem.findAll();
    }

    public List<AccidentType> findAllAccidentType() {
        return accidentTypeMem.findAll();
    }

    public List<Rule> findAllRule() {
        return ruleMem.findAll();
    }

    public List<Rule> findAllRuleExclude(Accident accident) {
        return ruleMem.findAll().stream().filter(e -> !accident.getRules()
                .contains(e)).collect(Collectors.toList());
    }


}
