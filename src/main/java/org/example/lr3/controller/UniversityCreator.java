package org.example.lr3.controller;

import java.util.List;
import org.example.lr3.model.Human;
import org.example.lr3.model.Sex;
import org.example.lr3.model.University;

public class UniversityCreator {
    private final FacultyCreator facultyCreator = new FacultyCreator();

    public University create() {
        Human head = new Human("Наталія", "Шевченко", "Вікторівна", Sex.FEMALE);
        return new University("Навчальний університет", head, List.of(facultyCreator.create()));
    }
}