package colors;

/**
 * Клас ColorRGBA представляє колір у моделі RGBA.
 * Містить компоненти червоного, зеленого, синього та альфа-каналу.
 */
public class ColorRGBA extends Color {
    private int r, g, b, a;

    /**
     * Конструктор для створення кольору RGBA.
     * 
     * @param r Червона компонента (0-255).
     * @param g Зелена компонента (0-255).
     * @param b Синя компонента (0-255).
     * @param a Непрозорість (0-255).
     */
    public ColorRGBA(int r, int g, int b, int a) {
        this.r = clamp(r);
        this.g = clamp(g);
        this.b = clamp(b);
        this.a = clamp(a);
    }

    /**
     * Метод для обмеження значень у межах 0-255.
     * 
     * @param value Значення для обмеження.
     * @return Обмежене значення.
     */
    private int clamp(int value) {
        return Math.max(0, Math.min(255, value));
    }

    /**
     * Повертає колір як ціле число, комбінуючи R, G, B, A.
     * 
     * @return Значення кольору як ціле число.
     */
    @Override
    public int valueInt() {
        return (r << 24) | (g << 16) | (b << 8) | a;
    }

    /**
     * Повертає колір як дійсне значення.
     * 
     * @return Значення кольору як дійсне число.
     */
    @Override
    public float valueFloat() {
        return (float) ((r / 255.0) * 0.299 + (g / 255.0) * 0.587 + (b / 255.0) * 0.114);
    }

    /**
     * Повертає поточний колір у форматі RGBA (сам себе).
     * 
     * @return Колір у форматі RGBA.
     */
    @Override
    public ColorRGBA toRGBA() {
        return this;
    }

    /**
     * Конвертує поточний колір у модель HSB.
     * 
     * @return Колір у форматі HSB.
     */
    @Override
    public ColorHSB toHSB() {
        return ColorConverter.RGBtoHSB(this);
    }

    /**
     * Конвертує поточний колір у модель CMYK.
     * 
     * @return Колір у форматі CMYK.
     */
    @Override
    public ColorCMYK toCMYK() {
        return ColorConverter.RGBtoCMYK(this);
    }

    /**
     * Конвертує поточний колір у модель XYZ.
     * 
     * @return Колір у форматі XYZ.
     */
    @Override
    public ColorXYZ toXYZ() {
        float r = this.r / 255.0f;
        float g = this.g / 255.0f;
        float b = this.b / 255.0f;

        // Гамма-корекція
        r = (r > 0.04045f) ? (float) Math.pow((r + 0.055) / 1.055, 2.4) : (r / 12.92f);
        g = (g > 0.04045f) ? (float) Math.pow((g + 0.055) / 1.055, 2.4) : (g / 12.92f);
        b = (b > 0.04045f) ? (float) Math.pow((b + 0.055) / 1.055, 2.4) : (b / 12.92f);

        // Конверсія в XYZ
        float x = r * 0.4124564f + g * 0.3575761f + b * 0.1804375f;
        float y = r * 0.2126729f + g * 0.7151522f + b * 0.0721750f;
        float z = r * 0.0193339f + g * 0.1191920f + b * 0.9503041f;

        return new ColorXYZ(x * 100, y * 100, z * 100);
    }

    /**
     * Додає поточний колір до іншого кольору.
     * 
     * @param other Інший колір для додавання.
     * @return Новий колір після додавання.
     */
    @Override
    public Color add(Color other) {
        ColorRGBA o = other.toRGBA();
        int newR = (this.r + o.r) / 2;
        int newG = (this.g + o.g) / 2;
        int newB = (this.b + o.b) / 2;
        int newA = (this.a + o.a) / 2;
        return new ColorRGBA(newR, newG, newB, newA);
    }

    /**
     * Виконує побітове об'єднання (OR) з іншим кольором.
     * 
     * @param other Інший колір для побітового OR.
     * @return Новий колір після побітового об'єднання.
     */
    @Override
    public Color or(Color other) {
        ColorRGBA o = other.toRGBA();
        return new ColorRGBA(this.r | o.r, this.g | o.g, this.b | o.b, this.a | o.a);
    }

    /**
     * Виконує побітовий перетин (AND) з іншим кольором.
     * 
     * @param other Інший колір для побітового AND.
     * @return Новий колір після побітового перетину.
     */
    @Override
    public Color and(Color other) {
        ColorRGBA o = other.toRGBA();
        return new ColorRGBA(this.r & o.r, this.g & o.g, this.b & o.b, this.a & o.a);
    }

    /**
     * Виконує побітову операцію XOR з іншим кольором.
     * 
     * @param other Інший колір для побітового XOR.
     * @return Новий колір після побітової операції XOR.
     */
    @Override
    public Color xor(Color other) {
        ColorRGBA o = other.toRGBA();
        return new ColorRGBA(this.r ^ o.r, this.g ^ o.g, this.b ^ o.b, this.a ^ o.a);
    }

    /**
     * Повертає значення червоної компоненти (R).
     * 
     * @return Значення червоної компоненти.
     */
    public int getR() {
        return r;
    }

    /**
     * Повертає значення зеленої компоненти (G).
     * 
     * @return Значення зеленої компоненти.
     */
    public int getG() {
        return g;
    }

    /**
     * Повертає значення синьої компоненти (B).
     * 
     * @return Значення синьої компоненти.
     */
    public int getB() {
        return b;
    }

    /**
     * Повертає значення непрозорості (A).
     * 
     * @return Значення непрозорості.
     */
    public int getA() {
        return a;
    }
}
