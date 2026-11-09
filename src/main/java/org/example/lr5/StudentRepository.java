package org.example.lr5;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class StudentRepository {
    private final Connection connection;

    public StudentRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Student> findByBirthMonth(int month) throws SQLException {
        String sql = """
                SELECT id, last_name, first_name, patronymic, birth_date, record_book_number
                FROM students
                WHERE EXTRACT(MONTH FROM birth_date) = ?
                ORDER BY last_name, first_name, id
                """;

        List<Student> students = new ArrayList<>();
        try (var statement = connection.prepareStatement(sql)) {
            statement.setInt(1, month);
            try (var rows = statement.executeQuery()) {
                while (rows.next()) {
                    students.add(new Student(
                            rows.getInt("id"),
                            rows.getString("last_name"),
                            rows.getString("first_name"),
                            rows.getString("patronymic"),
                            rows.getObject("birth_date", LocalDate.class),
                            rows.getString("record_book_number")
                    ));
                }
            }
        }
        return students;
    }
}
