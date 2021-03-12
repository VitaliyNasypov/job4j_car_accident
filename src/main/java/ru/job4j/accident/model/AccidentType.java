package ru.job4j.accident.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ACCIDENT_TYPES")
public class AccidentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Accident> accidents;

    public AccidentType() {
    }

    public static AccidentType of(int id, String name) {
        AccidentType type = new AccidentType();
        type.id = id;
        type.name = name;
        return type;
    }

    public void addAccident(Accident accident) {
        if (accidents == null) {
            accidents = new HashSet<>();
        }
        accidents.add(accident);
        accident.setType(this);
    }

    public void removeAccident(Accident accident) {
        if (accidents != null) {
            accidents.remove(accident);
            accident.setType(null);
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccidentType that = (AccidentType) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
