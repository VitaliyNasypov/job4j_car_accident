package ru.job4j.accident.repository.memory;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentTypeMem {
    private final Map<Integer, AccidentType> accidentTypes = new ConcurrentHashMap<>();

    public AccidentTypeMem() {
        accidentTypes.put(1, AccidentType.of(1, "Two cars"));
        accidentTypes.put(2, AccidentType.of(2, "Car and man"));
        accidentTypes.put(3, AccidentType.of(3, "Car and bicycle"));
    }

    public List<AccidentType> findAll() {
        return new ArrayList<>(accidentTypes.values());
    }
}
