package colors;


/**
 * Абстрактний клас Color, який представляє основну модель кольору.
 * Містить абстрактні методи для конверсії та представлення кольору
 * як цілого та дійсного числа, а також методи для операцій між кольорами.
 */
public abstract class Color {

    /**
     * Базовий конструктор для абстрактного класу Color.
     */
    public Color() {
        // Абстрактний клас не має конкретної реалізації
    }
    
    /**
     * Перетворює колір в ціле число.
     * 
     * @return значення кольору як ціле число.
     */
    public abstract int valueInt();

    /**
     * Перетворює колір в дійсне число.
     * 
     * @return значення кольору як дійсне число.
     */
    public abstract float valueFloat();

    /**
     * Конвертує поточний колір в RGBA.
     * 
     * @return колір у форматі RGBA.
     */
    public abstract ColorRGBA toRGBA();

    /**
     * Конвертує поточний колір в HSB.
     * 
     * @return колір у форматі HSB.
     */
    public abstract ColorHSB toHSB();

    /**
     * Конвертує поточний колір в CMYK.
     * 
     * @return колір у форматі CMYK.
     */
    public abstract ColorCMYK toCMYK();

    /**
     * Конвертує поточний колір в XYZ.
     * 
     * @return колір у форматі XYZ.
     */
    public abstract ColorXYZ toXYZ();

    /**
     * Додає поточний колір з іншим кольором і повертає результат як новий колір.
     * 
     * @param other інший колір для додавання.
     * @return новий колір після додавання.
     */
    public abstract Color add(Color other);

    /**
     * Виконує побітове об'єднання (OR) з іншим кольором і повертає результат як
     * новий колір.
     * 
     * @param other інший колір для побітового OR.
     * @return новий колір після побітового об'єднання.
     */
    public abstract Color or(Color other);

    /**
     * Виконує побітовий перетин (AND) з іншим кольором і повертає результат як
     * новий колір.
     * 
     * @param other інший колір для побітового AND.
     * @return новий колір після побітового перетину.
     */
    public abstract Color and(Color other);

    /**
     * Виконує побітову операцію XOR з іншим кольором і повертає результат як новий
     * колір.
     * 
     * @param other інший колір для побітового XOR.
     * @return новий колір після побітової операції XOR.
     */
    public abstract Color xor(Color other);
}
