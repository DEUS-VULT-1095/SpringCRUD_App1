package ru.springcourse.dao;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.springcourse.models.Person;

import java.util.List;

@Component
public class PersonDAO {

    private final EntityManager entityManager;

    @Autowired
    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public void testNPlus1() {
        Session session = entityManager.unwrap(Session.class);

//        List<Person> people = session.createQuery("FROM Person", Person.class).getResultList();
//
//        for (Person person : people) {
//            System.out.println("Person " + person.getName() + " has: " + person.getItems());
//        }

        List<Person> people = session.createQuery("SELECT p FROM Person p LEFT OUTER JOIN FETCH p.items")
                .getResultList();

        for (Person person : people) {
            System.out.println("Person " + person.getName() + " has: " + person.getItems());
        }
    }
}
