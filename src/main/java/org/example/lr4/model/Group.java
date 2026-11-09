package org.example.lr4.model;

import java.util.List;

public class Group extends UniversityUnit<Student> {
    public Group(String name, Human head, List<Student> students) {
        super(name, head, students);
    }

    public List<Student> getStudents() {
        return getMembers();
    }
}
