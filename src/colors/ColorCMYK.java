package colors;

/**
 * Клас ColorCMYK представляє колір у моделі CMYK.
 * C - блакитний, M - пурпуровий, Y - жовтий, K - чорний.
 */
public class ColorCMYK extends Color {
    private int c, m, y, k;

    /**
     * Конструктор для створення кольору CMYK.
     * 
     * @param c Блакитна компонента (0-100).
     * @param m Пурпурова компонента (0-100).
     * @param y Жовта компонента (0-100).
     * @param k Чорна компонента (0-100).
     */
    public ColorCMYK(int c, int m, int y, int k) {
        this.c = clamp(c);
        this.m = clamp(m);
        this.y = clamp(y);
        this.k = clamp(k);
    }

    /**
     * Метод для обмеження значень у межах 0-100.
     * 
     * @param value Значення для обмеження.
     * @return Значення в межах 0-100.
     */
    private int clamp(int value) {
        return Math.max(0, Math.min(100, value));
    }

    @Override
    public int valueInt() {
        return (c << 24) | (m << 16) | (y << 8) | k;
    }

    @Override
    public float valueFloat() {
        return (float) ((c / 100.0) * 0.299 + (m / 100.0) * 0.587 + (y / 100.0) * 0.114 + (k / 100.0) * 0.1);
    }

    @Override
    public ColorRGBA toRGBA() {
        return ColorConverter.CMYKtoRGB(this);
    }

    @Override
    public ColorHSB toHSB() {
        return this.toRGBA().toHSB();
    }

    @Override
    public ColorCMYK toCMYK() {
        return this;
    }

    /**
     * Конвертує колір CMYK в модель XYZ через проміжний RGBA.
     * 
     * @return колір у форматі XYZ.
     */
    @Override
    public ColorXYZ toXYZ() {
        return this.toRGBA().toXYZ();
    }

    @Override
    public Color add(Color other) {
        ColorCMYK o = other.toCMYK();
        int newC = (this.c + o.c) / 2;
        int newM = (this.m + o.m) / 2;
        int newY = (this.y + o.y) / 2;
        int newK = (this.k + o.k) / 2;
        return new ColorCMYK(newC, newM, newY, newK);
    }

    @Override
    public Color or(Color other) {
        ColorCMYK o = other.toCMYK();
        return new ColorCMYK(this.c | o.c, this.m | o.m, this.y | o.y, this.k | o.k);
    }

    @Override
    public Color and(Color other) {
        ColorCMYK o = other.toCMYK();
        return new ColorCMYK(this.c & o.c, this.m & o.m, this.y & o.y, this.k & o.k);
    }

    @Override
    public Color xor(Color other) {
        ColorCMYK o = other.toCMYK();
        return new ColorCMYK(this.c ^ o.c, this.m ^ o.m, this.y ^ o.y, this.k ^ o.k);
    }

    /**
     * Повертає значення блакитної компоненти.
     * 
     * @return Значення компоненти C (0-100).
     */
    public int getC() {
        return c;
    }

    /**
     * Повертає значення пурпурової компоненти.
     * 
     * @return Значення компоненти M (0-100).
     */
    public int getM() {
        return m;
    }

    /**
     * Повертає значення жовтої компоненти.
     * 
     * @return Значення компоненти Y (0-100).
     */
    public int getY() {
        return y;
    }

    /**
     * Повертає значення чорної компоненти.
     * 
     * @return Значення компоненти K (0-100).
     */
    public int getK() {
        return k;
    }
}
