package org.example.lr3.controller;

import java.util.List;
import org.example.lr3.model.Department;
import org.example.lr3.model.Human;
import org.example.lr3.model.Sex;

public class DepartmentCreator {
    private final GroupCreator groupCreator = new GroupCreator();

    public Department create() {
        Human head = new Human("Ірина", "Мельник", "Петрівна", Sex.FEMALE);
        return new Department("Системного аналізу", head, List.of(groupCreator.create()));
    }
}