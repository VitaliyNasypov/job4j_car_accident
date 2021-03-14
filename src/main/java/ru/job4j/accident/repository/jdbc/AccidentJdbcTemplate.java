package ru.job4j.accident.repository.jdbc;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

//@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident create(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(
                            "INSERT INTO accidents (name, text, address, accident_type_id) "
                                    + "VALUES (?, ?, ?, ?)",
                            new String[]{"id"});
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        accident.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        for (Rule rule : accident.getRules()) {
            jdbc.update("INSERT INTO accidents_rules (accident_id, rule_id) VALUES (?, ?)",
                    accident.getId(),
                    rule.getId());
        }
        return accident;
    }

    public void update(Accident accident) {
        jdbc.update(
                "UPDATE accidents SET name = ?, text = ?, address = ?, accident_type_id = ? "
                        + "WHERE id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId());
        jdbc.update("DELETE FROM accidents_rules WHERE accident_id = ?", accident.getId());
        for (Rule rule : accident.getRules()) {
            jdbc.update("INSERT INTO accidents_rules (accident_id, rule_id) VALUES (?, ?)",
                    accident.getId(),
                    rule.getId());
        }
    }

    public List<Accident> findAllAccident() {
        return jdbc.query("SELECT * FROM accidents",
                (resultSet, row) -> {
                    Accident accident = new Accident(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("text"),
                            resultSet.getString("address")
                    );
                    accident.setType(findByIdAccidentType(resultSet.getInt("accident_type_id")));
                    accident.setRules(findByIdRule(accident.getId()));
                    return accident;
                });
    }

    public List<AccidentType> findAllAccidentType() {
        return jdbc.query("SELECT * FROM accident_types",
                new BeanPropertyRowMapper<>(AccidentType.class));
    }

    public List<Rule> findAllRule() {
        return jdbc.query("SELECT * FROM rules",
                new BeanPropertyRowMapper<>(Rule.class));
    }

    public Accident findByIdAccident(int id) {
        return jdbc.query("SELECT * FROM accidents WHERE id=?", (resultSet, i) -> {
            Accident accident = new Accident(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("text"),
                    resultSet.getString("address")
            );
            accident.setType(findByIdAccidentType(resultSet.getInt("accident_type_id")));
            accident.setRules(findByIdRule(accident.getId()));
            return accident;
        }, id).stream().findAny().orElse(new Accident());
    }

    public AccidentType findByIdAccidentType(int id) {
        return jdbc.query("SELECT * FROM accident_types WHERE id=?",
                new BeanPropertyRowMapper<>(AccidentType.class), id)
                .stream().findAny().orElse(new AccidentType());
    }

    private Set<Rule> findByIdRule(int id) {
        return new HashSet<>(
                jdbc.query("SELECT * FROM rules JOIN accidents_rules "
                                + "ON (rules.id = accidents_rules.rule_id) "
                                + "WHERE accidents_rules.accident_id = ?",
                        new BeanPropertyRowMapper<>(Rule.class), id));
    }
}
