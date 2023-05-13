package ru.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.springcourse.models.Person;
import ru.springcourse.services.PeopleService;

import java.text.SimpleDateFormat;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        if (peopleService.findByEmail(person.getEmail()) != null) {
            errors.rejectValue("email", "", "This email is already taken");
        }

        if (person.getName().length() < 1 || !Character.isUpperCase(person.getName().codePointAt(0))) {
            errors.rejectValue("name", "", "Name should start with a capital letter");
        }

//        if (person.getDateOfBirth() == null || !formatter.format(person.getDateOfBirth()).matches("\\d{2}/\\d{2}/\\d{4}")) {
//            errors.rejectValue("dateOfBirth", "",
//                    "Date of birth should be like this format dd/mm/yyyy");
//            System.out.println("В методе");
//        }

    }
}
