package ru.job4j.accident.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ACCIDENTS")
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "text", nullable = false)
    private String text;
    @Column(name = "address", nullable = false)
    private String address;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCIDENT_TYPE_ID", nullable = false)
    private AccidentType type;
    @ManyToMany(mappedBy = "accidents")
    private Set<Rule> rules = new HashSet<>();

    public Accident() {
    }

    public Accident(int id, String name, String text, String address) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.address = address;
    }

    public Accident(int id, String name, String text, String address,
                    AccidentType type, Set<Rule> rules) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.address = address;
        this.type = type;
        this.rules = rules;
    }

    public void addRule(Rule rule) {
        rules.add(rule);
        rule.getAccidents().add(this);
    }

    public void removeRule(Rule rule) {
        rules.remove(rule);
        rule.getAccidents().remove(this);
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AccidentType getType() {
        return type;
    }

    public void setType(AccidentType type) {
        this.type = type;
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public void setRules(Set<Rule> rules) {
        this.rules = rules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accident accident = (Accident) o;
        return getId() == accident.getId()
                && Objects.equals(getName(), accident.getName())
                && Objects.equals(getText(), accident.getText())
                && Objects.equals(getAddress(), accident.getAddress());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}