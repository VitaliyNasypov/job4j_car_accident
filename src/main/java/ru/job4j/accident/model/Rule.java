package ru.job4j.accident.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "RULES")
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "ACCIDENTS_RULES",
            joinColumns = @JoinColumn(name = "rule_id"),
            inverseJoinColumns = @JoinColumn(name = "accident_id")
    )
    private Set<Accident> accidents = new HashSet<>();

    public Rule() {
    }

    public static Rule of(int id, String name) {
        Rule rule = new Rule();
        rule.id = id;
        rule.name = name;
        return rule;
    }

    public void addAccident(Accident accident) {
        accidents.add(accident);
        accident.getRules().add(this);
    }

    public void removeAccident(Accident accident) {
        accidents.remove(accident);
        accident.getRules().remove(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Accident> getAccidents() {
        return accidents;
    }

    public void setAccidents(Set<Accident> accidents) {
        this.accidents = accidents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rule rule = (Rule) o;
        return getId() == rule.getId() && Objects.equals(getName(), rule.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
