package ru.job4j.accident.service.hibernate;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.hibernate.AccidentHibernate;
import ru.job4j.accident.service.AccidentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccidentServiceHibernate implements AccidentService {
    private final AccidentHibernate accidentHibernate;

    public AccidentServiceHibernate(AccidentHibernate accidentHibernate) {
        this.accidentHibernate = accidentHibernate;
    }

    @Override
    public Accident save(Accident accident, String[] idRules) {
        accident.setType(accidentHibernate.findByIdAccidentType(accident.getType().getId()));
        if (accident.getId() == 0) {
            accidentHibernate.create(accident, idRules);
        } else {
            accidentHibernate.update(accident, idRules);
        }
        return accident;
    }

    @Override
    public Accident findById(int id) {
        return accidentHibernate.findByIdAccident(id);
    }

    @Override
    public List<AccidentType> findAllAccidentType() {
        return accidentHibernate.findAllAccidentType();
    }

    @Override
    public List<Accident> findAllAccident() {
        return accidentHibernate.findAllAccident();
    }

    @Override
    public List<Rule> findAllRule() {
        return accidentHibernate.findAllRule();
    }

    @Override
    public List<Rule> findAllRuleExclude(Accident accident) {
        return accidentHibernate.findAllRule().stream().filter(e -> !accident.getRules()
                .contains(e)).collect(Collectors.toList());
    }
}
