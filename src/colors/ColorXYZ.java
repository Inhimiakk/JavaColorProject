package colors;

/**
 * Клас ColorXYZ представляє колір у моделі XYZ.
 * X, Y, Z — значення кольору в системі XYZ.
 */
public class ColorXYZ extends Color {
    private float x, y, z;

    /**
     * Конструктор для створення кольору XYZ.
     * 
     * @param x Компонента X (0-100).
     * @param y Компонента Y (0-100).
     * @param z Компонента Z (0-100).
     */
    public ColorXYZ(float x, float y, float z) {
        this.x = clamp(x);
        this.y = clamp(y);
        this.z = clamp(z);
    }

    /**
     * Метод для обмеження значень у межах 0-100.
     * 
     * @param value Значення для обмеження.
     * @return Обмежене значення.
     */
    private float clamp(float value) {
        return Math.max(0, Math.min(100, value));
    }

    /**
     * Повертає колір у вигляді цілого числа, комбінуючи X, Y, Z.
     * 
     * @return Значення кольору як ціле число.
     */
    @Override
    public int valueInt() {
        return ((int) x << 16) | ((int) y << 8) | (int) z;
    }

    /**
     * Повертає колір у вигляді дійсного числа.
     * 
     * @return Значення кольору як дійсне число.
     */
    @Override
    public float valueFloat() {
        return x * 0.299f + y * 0.587f + z * 0.114f;
    }

    /**
     * Конвертує колір XYZ у модель RGBA.
     * 
     * @return Колір у форматі RGBA.
     */
    @Override
    public ColorRGBA toRGBA() {
        return ColorConverter.XYZtoRGB(this);
    }

    /**
     * Конвертує колір XYZ у модель HSB.
     * 
     * @return Колір у форматі HSB.
     */
    @Override
    public ColorHSB toHSB() {
        return this.toRGBA().toHSB();
    }

    /**
     * Конвертує колір XYZ у модель CMYK.
     * 
     * @return Колір у форматі CMYK.
     */
    @Override
    public ColorCMYK toCMYK() {
        return this.toRGBA().toCMYK();
    }

    /**
     * Повертає поточний колір у форматі XYZ (сам себе).
     * 
     * @return Колір у форматі XYZ.
     */
    @Override
    public ColorXYZ toXYZ() {
        return this;
    }

    /**
     * Додає поточний колір до іншого кольору.
     * 
     * @param other Інший колір для додавання.
     * @return Новий колір після додавання.
     */
    @Override
    public Color add(Color other) {
        ColorXYZ o = other.toXYZ();
        float newX = (this.x + o.x) / 2;
        float newY = (this.y + o.y) / 2;
        float newZ = (this.z + o.z) / 2;
        return new ColorXYZ(newX, newY, newZ);
    }

    /**
     * Побітові операції не підтримуються для системи XYZ.
     * 
     * @param other Інший колір.
     * @throws UnsupportedOperationException Завжди викидає виняток.
     */
    @Override
    public Color or(Color other) {
        throw new UnsupportedOperationException("Побітові операції не підтримуються для системи XYZ.");
    }

    /**
     * Побітові операції не підтримуються для системи XYZ.
     * 
     * @param other Інший колір.
     * @throws UnsupportedOperationException Завжди викидає виняток.
     */
    @Override
    public Color and(Color other) {
        throw new UnsupportedOperationException("Побітові операції не підтримуються для системи XYZ.");
    }

    /**
     * Побітові операції не підтримуються для системи XYZ.
     * 
     * @param other Інший колір.
     * @throws UnsupportedOperationException Завжди викидає виняток.
     */
    @Override
    public Color xor(Color other) {
        throw new UnsupportedOperationException("Побітові операції не підтримуються для системи XYZ.");
    }

    /**
     * Повертає значення компоненти X.
     * 
     * @return Значення компоненти X.
     */
    public float getX() {
        return x;
    }

    /**
     * Повертає значення компоненти Y.
     * 
     * @return Значення компоненти Y.
     */
    public float getY() {
        return y;
    }

    /**
     * Повертає значення компоненти Z.
     * 
     * @return Значення компоненти Z.
     */
    public float getZ() {
        return z;
    }
}
