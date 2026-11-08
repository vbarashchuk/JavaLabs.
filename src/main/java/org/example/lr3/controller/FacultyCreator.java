package org.example.lr3.controller;

import java.util.List;
import org.example.lr3.model.Faculty;
import org.example.lr3.model.Human;
import org.example.lr3.model.Sex;

public class FacultyCreator {
    private final DepartmentCreator departmentCreator = new DepartmentCreator();

    public Faculty create() {
        Human head = new Human("Дмитро", "Бондар", "Сергійович", Sex.MALE);
        return new Faculty("Інформаційних технологій", head, List.of(departmentCreator.create()));
    }
}