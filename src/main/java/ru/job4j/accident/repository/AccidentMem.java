package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem() {
        accidents.put(1, new Accident(1, "Name_1", "Text_1", "Address_1"));
        accidents.put(2, new Accident(2, "Name_2", "Text_2", "Address_2"));
        accidents.put(3, new Accident(3, "Name_3", "Text_3", "Address_3"));
        accidents.put(4, new Accident(4, "Name_4", "Text_4", "Address_4"));
    }

    public Map<Integer, Accident> findAll() {
        return accidents;
    }
}
