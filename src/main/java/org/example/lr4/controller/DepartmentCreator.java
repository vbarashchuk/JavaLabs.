package org.example.lr4.controller;

import java.util.List;
import org.example.lr4.model.Group;
import org.example.lr4.model.Human;
import org.example.lr4.model.Department;
import org.example.lr4.model.Sex;

public class DepartmentCreator {
    private final GroupCreator groupCreator = new GroupCreator();

    public Department create() {
        Human head = new Human("Ірина", "Мельник", "Петрівна", Sex.FEMALE);
        return new Department("Системного аналізу", head, List.of(groupCreator.create()));
    }
}
