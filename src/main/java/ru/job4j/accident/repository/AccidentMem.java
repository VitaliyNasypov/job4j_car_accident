package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private static final AtomicInteger ACCIDENT_ID = new AtomicInteger(4);
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    private AccidentMem() {
        Rule one = Rule.of(1, "Article. 1");
        Rule two = Rule.of(2, "Article. 2");
        Set<Rule> rules = new HashSet<>();
        rules.add(one);
        rules.add(two);
        accidents.put(1, new Accident(1, "Name_1", "Text_1", "Address_1",
                AccidentType.of(1, "Две машины"), rules));
        accidents.put(2, new Accident(2, "Name_2", "Text_2", "Address_2",
                AccidentType.of(1, "Две машины"), rules));
        accidents.put(3, new Accident(3, "Name_3", "Text_3", "Address_3",
                AccidentType.of(3, "Машина и велосипед"), rules));
        accidents.put(4, new Accident(4, "Name_4", "Text_4", "Address_4",
                AccidentType.of(1, "Две машины"), rules));
    }

    private static final class Lazy {
        private static final AccidentMem INST = new AccidentMem();
    }

    public static AccidentMem of() {
        return Lazy.INST;
    }

    public Collection<Accident> findAll() {
        return accidents.values();
    }

    public Accident create(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(ACCIDENT_ID.incrementAndGet());
        }
        return accidents.put(accident.getId(), accident);
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }
}
