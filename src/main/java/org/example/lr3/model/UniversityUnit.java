package org.example.lr3.model;

import java.util.List;

public abstract class UniversityUnit<T> {
    private final String name;
    private final Human head;
    private final List<T> members;

    protected UniversityUnit(String name, Human head, List<T> members) {
        this.name = name;
        this.head = head;
        this.members = List.copyOf(members);
    }

    public String getName() {
        return name;
    }

    public Human getHead() {
        return head;
    }

    protected List<T> getMembers() {
        return members;
    }
}