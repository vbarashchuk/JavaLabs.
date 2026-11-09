package org.example.lr4.model;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        UniversityUnit<?> unit = (UniversityUnit<?>) other;
        return Objects.equals(name, unit.name)
                && Objects.equals(head, unit.head)
                && Objects.equals(members, unit.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, head, members);
    }
}
