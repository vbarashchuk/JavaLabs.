package org.example.lr5;

import org.h2.tools.RunScript;
import org.h2.tools.Server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public final class Run {
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) throws SQLException, IOException {
        Server tcp = Server.createTcpServer("-tcpPort", "0", "-ifNotExists").start();
        try {
            String jdbcUrl = "jdbc:h2:tcp://localhost:" + tcp.getPort() + "/mem:lr5";
            try (Connection connection = DriverManager.getConnection(jdbcUrl, DB_USER, DB_PASSWORD);
                 Scanner input = new Scanner(System.in)) {
                initializeDatabase(connection);

                Server web = Server.createWebServer("-webPort", "0").start();
                try {
                    System.out.println("Консоль H2: http://localhost:" + web.getPort());
                    System.out.println("JDBC URL: " + jdbcUrl);
                    System.out.println("Користувач: " + DB_USER + " (пароль порожній)");
                    search(new StudentRepository(connection), input);
                } finally {
                    web.stop();
                }
            }
        } finally {
            tcp.stop();
        }
    }

    private static void initializeDatabase(Connection connection) throws SQLException, IOException {
        var script = Run.class.getResourceAsStream("/database.sql");
        if (script == null) {
            throw new IOException("Ресурс database.sql відсутній.");
        }
        try (var reader = new InputStreamReader(script, StandardCharsets.UTF_8)) {
            RunScript.execute(connection, reader);
        }
    }

    private static void search(StudentRepository repository, Scanner input) throws SQLException {
        while (true) {
            System.out.print("Введіть номер місяця (1–12) або 0 для виходу: ");
            if (!input.hasNextLine()) {
                return;
            }

            int month;
            try {
                month = Integer.parseInt(input.nextLine().strip());
            } catch (NumberFormatException exception) {
                System.out.println("Введіть ціле число від 0 до 12.");
                continue;
            }
            if (month == 0) {
                return;
            }
            if (month < 1 || month > 12) {
                System.out.println("Введіть ціле число від 0 до 12.");
                continue;
            }

            var students = repository.findByBirthMonth(month);
            if (students.isEmpty()) {
                System.out.println("У цьому місяці ніхто зі студентів не народився.");
                continue;
            }
            System.out.printf("Знайдено студентів: %d%n", students.size());
            for (Student student : students) {
                System.out.printf("%d | %s %s %s | %s | № %s%n",
                        student.id(), student.lastName(), student.firstName(), student.patronymic(),
                        student.birthDate(), student.recordBookNumber());
            }
        }
    }
}
