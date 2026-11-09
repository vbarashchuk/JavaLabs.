package org.example.lr4.controller;

import java.util.List;
import org.example.lr4.model.Group;
import org.example.lr4.model.Sex;
import org.example.lr4.model.Student;

public class GroupCreator {
    private final StudentCreator studentCreator = new StudentCreator();

    public Group create() {
        Student head = studentCreator.create("Олена", "Коваль", "Ігорівна", Sex.FEMALE);
        Student student = studentCreator.create("Андрій", "Лисенко", "Олегович", Sex.MALE);
        return new Group("СА-31", head, List.of(head, student));
    }
}
