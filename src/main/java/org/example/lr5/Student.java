package org.example.lr5;

import java.time.LocalDate;

public record Student(int id, String lastName, String firstName, String patronymic,
                      LocalDate birthDate, String recordBookNumber) {
}
