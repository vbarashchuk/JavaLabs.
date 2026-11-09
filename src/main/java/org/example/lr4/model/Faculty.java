package org.example.lr4.model;

import java.util.List;

public class Faculty extends UniversityUnit<Department> {
    public Faculty(String name, Human head, List<Department> departments) {
        super(name, head, departments);
    }

    public List<Department> getDepartments() {
        return getMembers();
    }
}
