package colors;

/**
 * Клас ColorHSB представляє колір у моделі HSB.
 * H - відтінок, S - насиченість, B - яскравість.
 */
public class ColorHSB extends Color {
    private int h, s, b;

    /**
     * Конструктор для створення кольору HSB.
     * 
     * @param h Відтінок (0-360).
     * @param s Насиченість (0-100).
     * @param b Яскравість (0-100).
     */
    public ColorHSB(int h, int s, int b) {
        this.h = clamp(h, 0, 360);
        this.s = clamp(s, 0, 100);
        this.b = clamp(b, 0, 100);
    }

    /**
     * Обмежує значення у заданих межах.
     * 
     * @param value Значення для обмеження.
     * @param min   Мінімальне значення.
     * @param max   Максимальне значення.
     * @return Обмежене значення.
     */
    private int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    /**
     * Повертає колір у форматі цілого числа.
     * 
     * @return Значення кольору як ціле число.
     */
    @Override
    public int valueInt() {
        return (h << 16) | (s << 8) | b;
    }

    /**
     * Повертає колір у форматі дійсного числа.
     * 
     * @return Значення кольору як дійсне число.
     */
    @Override
    public float valueFloat() {
        return (float) ((h / 360.0) * 0.299 + (s / 100.0) * 0.587 + (b / 100.0) * 0.114);
    }

    /**
     * Конвертує поточний колір у модель RGBA.
     * 
     * @return Колір у форматі RGBA.
     */
    @Override
    public ColorRGBA toRGBA() {
        return ColorConverter.HSBtoRGB(this);
    }

    /**
     * Повертає поточний колір у форматі HSB (сам себе).
     * 
     * @return Колір у форматі HSB.
     */
    @Override
    public ColorHSB toHSB() {
        return this;
    }

    /**
     * Конвертує поточний колір у модель CMYK.
     * 
     * @return Колір у форматі CMYK.
     */
    @Override
    public ColorCMYK toCMYK() {
        return this.toRGBA().toCMYK();
    }

    /**
     * Конвертує поточний колір у модель XYZ через проміжний формат RGBA.
     * 
     * @return Колір у форматі XYZ.
     */
    @Override
    public ColorXYZ toXYZ() {
        return this.toRGBA().toXYZ();
    }

    /**
     * Додає поточний колір до іншого кольору та повертає результат.
     * 
     * @param other Інший колір для додавання.
     * @return Новий колір після додавання.
     */
    @Override
    public Color add(Color other) {
        ColorHSB o = other.toHSB();
        int newH = (this.h + o.h) / 2;
        int newS = (this.s + o.s) / 2;
        int newB = (this.b + o.b) / 2;
        return new ColorHSB(newH, newS, newB);
    }

    /**
     * Виконує побітове об'єднання (OR) з іншим кольором.
     * 
     * @param other Інший колір для побітового OR.
     * @return Новий колір після побітового об'єднання.
     */
    @Override
    public Color or(Color other) {
        ColorHSB o = other.toHSB();
        return new ColorHSB(this.h | o.h, this.s | o.s, this.b | o.b);
    }

    /**
     * Виконує побітовий перетин (AND) з іншим кольором.
     * 
     * @param other Інший колір для побітового AND.
     * @return Новий колір після побітового перетину.
     */
    @Override
    public Color and(Color other) {
        ColorHSB o = other.toHSB();
        return new ColorHSB(this.h & o.h, this.s & o.s, this.b & o.b);
    }

    /**
     * Виконує побітову операцію XOR з іншим кольором.
     * 
     * @param other Інший колір для побітового XOR.
     * @return Новий колір після побітової операції XOR.
     */
    @Override
    public Color xor(Color other) {
        ColorHSB o = other.toHSB();
        return new ColorHSB(this.h ^ o.h, this.s ^ o.s, this.b ^ o.b);
    }

    /**
     * Повертає значення відтінку (H).
     * 
     * @return Значення відтінку.
     */
    public int getH() {
        return h;
    }

    /**
     * Повертає значення насиченості (S).
     * 
     * @return Значення насиченості.
     */
    public int getS() {
        return s;
    }

    /**
     * Повертає значення яскравості (B).
     * 
     * @return Значення яскравості.
     */
    public int getB() {
        return b;
    }
}
