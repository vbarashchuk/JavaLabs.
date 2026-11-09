package org.example.lr4.model;

import java.util.Objects;

public class Human {
    private final String firstName;
    private final String lastName;
    private final String patronymic;
    private final Sex sex;

    public Human(String firstName, String lastName, String patronymic, Sex sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.sex = sex;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public Sex getSex() {
        return sex;
    }

    public String getFullName() {
        return lastName + " " + firstName + " " + patronymic;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Human person = (Human) other;
        return Objects.equals(firstName, person.firstName)
                && Objects.equals(lastName, person.lastName)
                && Objects.equals(patronymic, person.patronymic)
                && sex == person.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, patronymic, sex);
    }
}