package org.example.lr4.controller;

import java.util.List;
import org.example.lr4.model.Faculty;
import org.example.lr4.model.Human;
import org.example.lr4.model.University;
import org.example.lr4.model.Sex;

public class UniversityCreator {
    private final FacultyCreator facultyCreator = new FacultyCreator();

    public University create() {
        Human head = new Human("Наталія", "Шевченко", "Вікторівна", Sex.FEMALE);
        return new University("Навчальний університет", head, List.of(facultyCreator.create()));
    }
}
