package org.example;

import java.util.Random;
import java.util.Scanner;

public class MatrixLab {
    private static final int MAX_SIZE = 20;
    private static final int RANDOM_MIN = 1;
    private static final int RANDOM_MAX = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = readInt(scanner, "Висота (1–20): ", 1, MAX_SIZE);
        int columns = readInt(scanner, "Ширина (1–20): ", 1, MAX_SIZE);
        System.out.println("1 — ручне введення, 2 — випадкове заповнення");
        int mode = readInt(scanner, "Оберіть спосіб: ", 1, 2);

        int[][] matrix = new int[rows][columns];
        fillMatrix(matrix, scanner, mode);
        printMatrix(matrix);
        printStatistics(matrix);
        scanner.close();
    }

    private static int readInt(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                if (number >= min && number <= max) {
                    return number;
                }
            } else {
                scanner.next();
            }
            System.out.println("Введіть ціле число від " + min + " до " + max + ".");
        }
    }

    private static void fillMatrix(int[][] matrix, Scanner scanner, int mode) {
        Random random = new Random();
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (mode == 1) {
                    String prompt = "Елемент [" + (row + 1) + ", " + (col + 1) + "]: ";
                    matrix[row][col] = readInt(scanner, prompt, Integer.MIN_VALUE, Integer.MAX_VALUE);
                } else {
                    matrix[row][col] = RANDOM_MIN + random.nextInt(RANDOM_MAX - RANDOM_MIN + 1);
                }
            }
        }
    }

    private static void printMatrix(int[][] matrix) {
        System.out.println("Матриця:");
        for (int[] row : matrix) {
            for (int number : row) {
                System.out.printf("%12d", number);
            }
            System.out.println();
        }
    }

    private static void printStatistics(int[][] matrix) {
        int min = matrix[0][0];
        int max = matrix[0][0];
        long sum = 0;
        double logSum = 0;
        boolean hasZero = false;

        for (int[] row : matrix) {
            for (int number : row) {
                min = Math.min(min, number);
                max = Math.max(max, number);
                sum += number;
                if (number == 0) {
                    hasZero = true;
                } else if (number > 0) {
                    logSum += Math.log(number);
                }
            }
        }

        int count = matrix.length * matrix[0].length;
        System.out.println("Мінімальний елемент: " + min);
        System.out.println("Максимальний елемент: " + max);
        System.out.printf("Середнє арифметичне: %.4f%n", (double) sum / count);

        if (min < 0) {
            System.out.println("Середнє геометричне обчислюється лише для невід’ємних елементів.");
        } else {
            double geometricMean = hasZero ? 0 : Math.exp(logSum / count);
            System.out.printf("Середнє геометричне: %.4f%n", geometricMean);
        }
    }
}