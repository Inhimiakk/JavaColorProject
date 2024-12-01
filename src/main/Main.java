// src/main/Main.java
// Ізвекова, компмат 1, 01.12.2024 22:27
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import colors.ColorRGBA;
import images.Image;
import images.Mask;

/**
 * Головний клас Main для запуску програми.
 * Демонструє роботу з класами Color, Image та Mask.
 * 
 * <p>
 * Програма дозволяє працювати з кольоровими зображеннями та
 * застосовувати до них маски, використовуючи дані, введені вручну або
 * зчитані з файлу.
 * </p>
 * 
 * @author Ізвекова
 * @version 1.0
 * @since 01.12.2024
 */
public class Main {

    /**
     * Конструктор за замовчуванням для класу Main.
     * Клас містить основний метод для запуску програми.
     */
    public Main() {
        // Конструктор залишається порожнім
    }

    /**
     * Головний метод для запуску програми.
     * 
     * @param args аргументи командного рядка (не використовуються).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Виберіть режим роботи:");
        System.out.println("1. Ручне введення");
        System.out.println("2. Введення з тестового файлу");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Споживає новий рядок після вибору

        if (choice == 1) {
            manualInputMode(scanner);
        } else if (choice == 2) {
            testFileMode();
        } else {
            System.out.println("Невірний вибір. Завершення програми.");
        }

        scanner.close();
    }

    /**
     * Режим ручного введення з консолі.
     * 
     * <p>
     * Користувач вводить розміри зображення, колір для заповнення
     * зображення та значення для маски. Програма демонструє результати
     * після застосування маски.
     * </p>
     * 
     * @param scanner Scanner для зчитування даних з консолі.
     */
    private static void manualInputMode(Scanner scanner) {
        System.out.println("Введіть розміри зображення (m n):");
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        Image image = new Image(m, n);

        System.out.println("Введіть колір (RGBA) для заповнення зображення:");
        int r = scanner.nextInt();
        int g = scanner.nextInt();
        int b = scanner.nextInt();
        int a = scanner.nextInt();
        ColorRGBA color = new ColorRGBA(r, g, b, a);
        image.fill(color);

        System.out.println("Введіть розміри маски (повинні відповідати розмірам зображення):");
        int maskRows = scanner.nextInt();
        int maskCols = scanner.nextInt();
        Mask mask = new Mask(maskRows, maskCols);

        System.out.println("Заповніть маску (0 або 1 для кожного елемента):");
        for (int i = 0; i < maskRows; i++) {
            for (int j = 0; j < maskCols; j++) {
                int value = scanner.nextInt();
                mask.setMaskValue(i, j, value);
            }
        }

        System.out.println("Застосування маски до зображення...");
        mask.applyToImage(image);

        System.out.println("Результат після застосування маски:");
        image.display();

        System.out.println("Введіть ім'я файлу для збереження результату:");
        String fileName = "resources/" + scanner.next(); // Додаємо "resources/" до імені файлу
        image.saveAsText(fileName);
        System.out.println("Зображення збережено у файл " + fileName);
    }

    /**
     * Режим роботи з тестовим файлом.
     * 
     * <p>
     * Програма читає дані з файлу <code>NZ_test.dat</code>, що міститься
     * в папці <code>resources</code>. Після обробки маски результати
     * зберігаються у файл.
     * </p>
     */
    private static void testFileMode() {
        try (Scanner fileScanner = new Scanner(new File("resources/NZ_test.dat"))) { // Читання з папки resources
            System.out.println("Читання даних з файлу resources/NZ_test.dat...");

            // Читання розмірів зображення
            int m = fileScanner.nextInt();
            int n = fileScanner.nextInt();
            Image image = new Image(m, n);

            // Читання кольору для заповнення зображення
            int r = fileScanner.nextInt();
            int g = fileScanner.nextInt();
            int b = fileScanner.nextInt();
            int a = fileScanner.nextInt();
            ColorRGBA color = new ColorRGBA(r, g, b, a);
            image.fill(color);

            // Читання розмірів маски
            int maskRows = fileScanner.nextInt();
            int maskCols = fileScanner.nextInt();
            Mask mask = new Mask(maskRows, maskCols);

            // Читання значень маски
            for (int i = 0; i < maskRows; i++) {
                for (int j = 0; j < maskCols; j++) {
                    int value = fileScanner.nextInt();
                    mask.setMaskValue(i, j, value);
                }
            }

            // Застосування маски до зображення
            System.out.println("Застосування маски до зображення...");
            mask.applyToImage(image);

            // Виведення результату на консоль
            System.out.println("Результат після застосування маски:");
            image.display();

            // Читання імені файлу для збереження
            String fileName = "resources/" + fileScanner.next(); // Додаємо "resources/" до імені файлу
            image.saveAsText(fileName);
            System.out.println("Зображення збережено у файл " + fileName);

        } catch (FileNotFoundException e) {
            System.out.println("Файл resources/NZ_test.dat не знайдено. Будь ласка, переконайтеся, що файл існує.");
        } catch (Exception e) {
            System.out.println("Помилка під час читання даних з файлу: " + e.getMessage());
        }
    }
}
