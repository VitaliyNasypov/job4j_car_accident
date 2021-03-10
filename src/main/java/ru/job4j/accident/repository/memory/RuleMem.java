package ru.job4j.accident.repository.memory;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class RuleMem {
    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>();

    public RuleMem() {
        rules.put(1, Rule.of(1, "Article. 1"));
        rules.put(2, Rule.of(2, "Article. 2"));
        rules.put(3, Rule.of(3, "Article. 3"));
    }

    public List<Rule> findAll() {
        return new ArrayList<>(rules.values());
    }
}
