package images;

import colors.ColorHSB;
import colors.Color;

/**
 * Клас Mask представляє маску для зображення, що складається з двовимірної
 * матриці цілих чисел.
 * Маска може застосовуватися до зображення, модифікуючи його кольори.
 */
public class Mask {
    private int m, n; // Розміри маски
    private int[][] mask; // Матриця цілих чисел для маски

    /**
     * Конструктор для створення маски з вказаними розмірами.
     * 
     * @param m Кількість рядків.
     * @param n Кількість стовпців.
     */
    public Mask(int m, int n) {
        this.m = m;
        this.n = n;
        this.mask = new int[m][n];
    }

    /**
     * Встановлює значення для конкретного елемента маски.
     * 
     * @param i     Індекс рядка.
     * @param j     Індекс стовпця.
     * @param value Значення для установки (наприклад, 0 або 1).
     */
    public void setMaskValue(int i, int j, int value) {
        if (isValidIndex(i, j)) {
            mask[i][j] = value;
        }
    }

    /**
     * Повертає значення елемента маски.
     * 
     * @param i Індекс рядка.
     * @param j Індекс стовпця.
     * @return Значення елемента маски або -1, якщо індекси недійсні.
     */
    public int getMaskValue(int i, int j) {
        return isValidIndex(i, j) ? mask[i][j] : -1;
    }

    /**
     * Перевіряє, чи є індекси маски дійсними.
     * 
     * @param i Індекс рядка.
     * @param j Індекс стовпця.
     * @return true, якщо індекси дійсні, інакше false.
     */
    private boolean isValidIndex(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }

    /**
     * Застосовує маску до зображення, змінюючи яскравість кольорів, де маска має
     * значення 1.
     * 
     * @param image Зображення для застосування маски.
     */
    public void applyToImage(Image image) {
        for (int i = 0; i < m && i < image.getRows(); i++) {
            for (int j = 0; j < n && j < image.getColumns(); j++) {
                if (mask[i][j] == 1) {
                    Color originalColor = image.getPixel(i, j);
                    if (originalColor != null) {
                        ColorHSB hsbColor = originalColor.toHSB();
                        int newBrightness = Math.max(hsbColor.getB() - 20, 0); // Зменшуємо яскравість
                        ColorHSB modifiedColor = new ColorHSB(hsbColor.getH(), hsbColor.getS(), newBrightness);
                        image.setPixel(i, j, modifiedColor.toRGBA()); // Застосовуємо змінену яскравість
                    }
                }
            }
        }
    }
}
