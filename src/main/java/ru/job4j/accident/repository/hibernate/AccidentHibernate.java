package ru.job4j.accident.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Accident create(Accident accident, String[] idRules) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            for (String id : idRules) {
                Rule rule = session.find(Rule.class, Integer.parseInt(id));
                accident.addRule(rule);
            }
            session.save(accident);
            session.getTransaction().commit();
            return accident;
        }
    }

    public void update(Accident accident, String[] idRules) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Accident accidentUpdate = session.find(Accident.class, accident.getId());
            for (Rule rule : new ArrayList<>(accidentUpdate.getRules())) {
                accidentUpdate.removeRule(rule);
            }
            for (String id : idRules) {
                Rule rule = session.find(Rule.class, Integer.parseInt(id));
                accidentUpdate.addRule(rule);
            }
            accidentUpdate.setName(accident.getName());
            accidentUpdate.setText(accident.getText());
            accidentUpdate.setAddress(accident.getAddress());
            accidentUpdate.setType(accident.getType());
            session.update(accidentUpdate);
            session.getTransaction().commit();
        }
    }

    public List<Accident> findAllAccident() {
        try (Session session = sf.openSession()) {
            return session.createQuery("select distinct a from Accident a"
                    + " join fetch a.type"
                    + " join fetch a.rules", Accident.class)
                    .getResultList();
        }
    }

    public List<AccidentType> findAllAccidentType() {
        try (Session session = sf.openSession()) {
            return session.createQuery("select a from AccidentType a", AccidentType.class)
                    .getResultList();
        }
    }

    public List<Rule> findAllRule() {
        try (Session session = sf.openSession()) {
            return session.createQuery("select r from Rule r", Rule.class).getResultList();
        }
    }

    public Accident findByIdAccident(int id) {
        try (Session session = sf.openSession()) {
            return session.createQuery("select distinct a from Accident a"
                    + " join fetch a.type"
                    + " join fetch a.rules"
                    + " where a.id = :id", Accident.class)
                    .setParameter("id", id)
                    .getResultList().get(0);
        }
    }

    public AccidentType findByIdAccidentType(int id) {
        try (Session session = sf.openSession()) {
            return session.createQuery("select a from AccidentType a"
                    + " where a.id = :id", AccidentType.class)
                    .setParameter("id", id)
                    .getResultList().get(0);
        }
    }
}
