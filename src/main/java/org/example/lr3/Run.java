package org.example.lr3;

import org.example.lr3.controller.UniversityCreator;
import org.example.lr3.model.Department;
import org.example.lr3.model.Faculty;
import org.example.lr3.model.Group;
import org.example.lr3.model.Student;
import org.example.lr3.model.University;
import org.example.lr3.model.UniversityUnit;

public class Run {
    public static void main(String[] args) {
        University university = createTypycalUniversity();
        printUniversity(university);
    }

    public static University createTypycalUniversity() {
        return new UniversityCreator().create();
    }

    private static void printUniversity(University university) {
        printUnit("", "Університет", university, "ректор");

        for (Faculty faculty : university.getFaculties()) {
            printUnit("  ", "Факультет", faculty, "декан");

            for (Department department : faculty.getDepartments()) {
                printUnit("    ", "Кафедра", department, "завідувач");

                for (Group group : department.getGroups()) {
                    printUnit("      ", "Група", group, "староста");

                    for (Student student : group.getStudents()) {
                        System.out.println("        Студент: " + student.getFullName());
                    }
                }
            }
        }
    }

    private static void printUnit(String indent, String label, UniversityUnit<?> unit, String role) {
        System.out.printf("%s%s: %s (%s — %s)%n",
                indent, label, unit.getName(), role, unit.getHead().getFullName());
    }
}