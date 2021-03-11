package ru.job4j.accident.repository.memory;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private static final AtomicInteger ACCIDENT_ID = new AtomicInteger(4);
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public AccidentMem() {
        AccidentTypeMem accidentTypeMem = new AccidentTypeMem();
        RuleMem ruleMem = new RuleMem();
        Set<Rule> addRules = new HashSet<>();
        addRules.add(ruleMem.findAll().get(0));
        addRules.add(ruleMem.findAll().get(2));
        accidents.put(1, new Accident(1, "Name_1", "Text_1", "Address_1",
                accidentTypeMem.findAll().get(0), addRules));
        accidents.put(2, new Accident(2, "Name_2", "Text_2", "Address_2",
                accidentTypeMem.findAll().get(0), addRules));
        accidents.put(3, new Accident(3, "Name_3", "Text_3", "Address_3",
                accidentTypeMem.findAll().get(2), addRules));
        accidents.put(4, new Accident(4, "Name_4", "Text_4", "Address_4",
                accidentTypeMem.findAll().get(2), addRules));
    }

    public List<Accident> findAll() {
        return new ArrayList<>(accidents.values());
    }

    public Accident create(Accident accident) {
        accident.setId(ACCIDENT_ID.incrementAndGet());
        return accidents.put(accident.getId(), accident);
    }

    public Accident update(Accident accident) {
        return accidents.put(accident.getId(), accident);
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }
}
