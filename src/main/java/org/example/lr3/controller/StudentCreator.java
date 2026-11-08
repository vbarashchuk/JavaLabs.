package org.example.lr3.controller;

import org.example.lr3.model.Sex;
import org.example.lr3.model.Student;

public class StudentCreator {
    public Student create(String firstName, String lastName, String patronymic, Sex sex) {
        return new Student(firstName, lastName, patronymic, sex);
    }
}