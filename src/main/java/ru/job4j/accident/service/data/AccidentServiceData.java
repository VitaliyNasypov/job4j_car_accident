package ru.job4j.accident.service.data;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.data.AccidentRepository;
import ru.job4j.accident.repository.data.AccidentTypeRepository;
import ru.job4j.accident.repository.data.RuleRepository;
import ru.job4j.accident.service.AccidentService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccidentServiceData implements AccidentService {
    private final AccidentRepository accidentRepository;
    private final AccidentTypeRepository accidentTypeRepository;
    private final RuleRepository ruleRepository;

    public AccidentServiceData(AccidentRepository accidentRepository,
                               AccidentTypeRepository accidentTypeRepository,
                               RuleRepository ruleRepository) {
        this.accidentRepository = accidentRepository;
        this.accidentTypeRepository = accidentTypeRepository;
        this.ruleRepository = ruleRepository;
    }

    @Override
    @Transactional
    public Accident save(Accident accident, String[] idRules) {
        accident.setType(accidentTypeRepository
                .findById(accident
                        .getType()
                        .getId())
                .orElse(new AccidentType()));
        if (accident.getId() != 0) {
            Accident accidentUpdate = accidentRepository.findById(accident.getId()).orElse(new Accident());
            for (Rule rule : new ArrayList<>(accidentUpdate.getRules())) {
                accidentUpdate.removeRule(rule);
            }
            accidentUpdate.setName(accident.getName());
            accidentUpdate.setText(accident.getText());
            accidentUpdate.setAddress(accident.getAddress());
            accidentUpdate.setType(accident.getType());
            accident = accidentUpdate;
        }
        for (String id : idRules) {
            Rule rule = ruleRepository.findById(Integer.parseInt(id)).orElse(new Rule());
            accident.addRule(rule);
        }
        return accidentRepository.save(accident);
    }

    @Override
    public Accident findById(int id) {
        return accidentRepository.findById(id).orElse(new Accident());
    }

    @Override
    public List<AccidentType> findAllAccidentType() {
        List<AccidentType> accidentTypesList = new ArrayList<>();
        accidentTypeRepository.findAll().forEach(accidentTypesList::add);
        return accidentTypesList;
    }

    @Override
    public List<Accident> findAllAccident() {
        return (List<Accident>) accidentRepository.findAll();
    }

    @Override
    public List<Rule> findAllRule() {
        List<Rule> ruleList = new ArrayList<>();
        ruleRepository.findAll().forEach(ruleList::add);
        return ruleList;
    }

    @Override
    public List<Rule> findAllRuleExclude(Accident accident) {
        return findAllRule().stream().filter(e -> !accident.getRules()
                .contains(e)).collect(Collectors.toList());
    }
}
