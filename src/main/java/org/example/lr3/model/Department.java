package org.example.lr3.model;

import java.util.List;

public class Department extends UniversityUnit<Group> {
    public Department(String name, Human head, List<Group> groups) {
        super(name, head, groups);
    }

    public List<Group> getGroups() {
        return getMembers();
    }
}