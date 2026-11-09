package org.example.lr4.model;

import java.util.List;

public class University extends UniversityUnit<Faculty> {
    public University(String name, Human head, List<Faculty> faculties) {
        super(name, head, faculties);
    }

    public List<Faculty> getFaculties() {
        return getMembers();
    }
}
