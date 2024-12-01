package images;

import colors.Color;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Клас Image представляє зображення, що складається з матриці кольорів.
 */
public class Image {
    private int m, n; // Розміри зображення: m - кількість рядків, n - кількість стовпців
    private Color[][] pixels; // Матриця кольорів зображення

    /**
     * Конструктор для створення порожнього зображення з вказаними розмірами.
     * 
     * @param m Кількість рядків.
     * @param n Кількість стовпців.
     */
    public Image(int m, int n) {
        this.m = m;
        this.n = n;
        this.pixels = new Color[m][n]; // Ініціалізація матриці кольорів
    }

    /**
     * Ініціалізує зображення заданим кольором.
     * 
     * @param color Кольорова модель для заповнення зображення.
     */
    public void fill(Color color) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                pixels[i][j] = color;
            }
        }
    }

    /**
     * Встановлює колір для конкретного пікселя.
     * 
     * @param i     Індекс рядка.
     * @param j     Індекс стовпця.
     * @param color Колір для установки.
     */
    public void setPixel(int i, int j, Color color) {
        if (isValidPixel(i, j)) {
            pixels[i][j] = color;
        }
    }

    /**
     * Повертає колір пікселя у вказаних координатах.
     * 
     * @param i Індекс рядка.
     * @param j Індекс стовпця.
     * @return Колір пікселя або null, якщо координати недійсні.
     */
    public Color getPixel(int i, int j) {
        return isValidPixel(i, j) ? pixels[i][j] : null;
    }

    /**
     * Перевіряє, чи є індекси пікселя дійсними.
     * 
     * @param i Індекс рядка.
     * @param j Індекс стовпця.
     * @return true, якщо індекси дійсні, інакше false.
     */
    private boolean isValidPixel(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }

    /**
     * Записує зображення у файл як текст, де кожен піксель представлений у вигляді
     * цілого значення.
     * 
     * @param fileName Ім'я файлу для збереження.
     */
    public void saveAsText(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    writer.write(String.format("%d ", pixels[i][j].valueInt()));
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Помилка запису у файл: " + e.getMessage());
        }
    }

    /**
     * Виводить зображення на консоль, представляючи кожен піксель у вигляді цілого
     * значення.
     */
    public void display() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(pixels[i][j].valueInt() + " ");
            }
            System.out.println();
        }
    }

    /**
     * Повертає кількість рядків у зображенні.
     * 
     * @return Кількість рядків.
     */
    public int getRows() {
        return m;
    }

    /**
     * Повертає кількість стовпців у зображенні.
     * 
     * @return Кількість стовпців.
     */
    public int getColumns() {
        return n;
    }
}
