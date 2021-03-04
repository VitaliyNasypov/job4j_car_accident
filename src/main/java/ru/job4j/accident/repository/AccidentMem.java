package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private static final AtomicInteger ACCIDENT_ID = new AtomicInteger(4);
    private static final Map<Integer, Accident> ACCIDENTS = new ConcurrentHashMap<>();

    public AccidentMem() {
        ACCIDENTS.put(1, new Accident(1, "Name_1", "Text_1", "Address_1"));
        ACCIDENTS.put(2, new Accident(2, "Name_2", "Text_2", "Address_2"));
        ACCIDENTS.put(3, new Accident(3, "Name_3", "Text_3", "Address_3"));
        ACCIDENTS.put(4, new Accident(4, "Name_4", "Text_4", "Address_4"));
    }

    public Map<Integer, Accident> findAll() {
        return ACCIDENTS;
    }

    public Accident create(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(ACCIDENT_ID.incrementAndGet());
        }
        return ACCIDENTS.put(accident.getId(), accident);
    }
}
