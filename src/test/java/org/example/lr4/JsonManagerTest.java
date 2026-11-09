package org.example.lr4;

import org.example.lr4.model.Department;
import org.example.lr4.model.Faculty;
import org.example.lr4.model.Group;
import org.example.lr4.model.Human;
import org.example.lr4.model.Sex;
import org.example.lr4.model.Student;
import org.example.lr4.model.University;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class JsonManagerTest {

    @Test
    void savesAndRestoresUniversity(@TempDir Path tempDir) throws IOException {
        University original = createUniversity();
        Path file = tempDir.resolve("university.json");
        JsonManager manager = new JsonManager();

        manager.write(file, original);
        University restored = manager.read(file);

        assertNotSame(original, restored);
        assertEquals(original, restored);
    }

    private University createUniversity() {
        Human rector = new Human("Наталія", "Шевченко", "Вікторівна", Sex.FEMALE);
        return new University("Навчальний університет", rector, List.of(
                createFaculty("Інформаційні технології", "ІТ"),
                createFaculty("Економіка", "ЕК")
        ));
    }

    private Faculty createFaculty(String name, String code) {
        Human dean = new Human("Дмитро", "Бондар", "Сергійович", Sex.MALE);
        return new Faculty(name, dean, List.of(
                createDepartment("Кафедра 1", code + "-1"),
                createDepartment("Кафедра 2", code + "-2")
        ));
    }

    private Department createDepartment(String name, String code) {
        Human head = new Human("Ірина", "Мельник", "Петрівна", Sex.FEMALE);
        return new Department(name, head, List.of(
                createGroup(code + "-А"),
                createGroup(code + "-Б")
        ));
    }

    private Group createGroup(String name) {
        Student leader = new Student("Олена", "Коваль", "Ігорівна", Sex.FEMALE);
        Student student = new Student("Андрій", "Лисенко", "Олегович", Sex.MALE);
        return new Group(name, leader, List.of(leader, student));
    }
}