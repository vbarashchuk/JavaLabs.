package org.example.lr4.controller;

import java.util.List;
import org.example.lr4.model.Department;
import org.example.lr4.model.Human;
import org.example.lr4.model.Faculty;
import org.example.lr4.model.Sex;

public class FacultyCreator {
    private final DepartmentCreator departmentCreator = new DepartmentCreator();

    public Faculty create() {
        Human head = new Human("Дмитро", "Бондар", "Сергійович", Sex.MALE);
        return new Faculty("Інформаційних технологій", head, List.of(departmentCreator.create()));
    }
}
