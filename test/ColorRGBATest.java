package test;

import org.junit.Test;
import static org.junit.Assert.*;

import colors.ColorRGBA;

/**
 * Клас для тестування функціональності класу ColorRGBA.
 */
public class ColorRGBATest {

    /**
     * Тестує конструктор ColorRGBA та методи-гетери.
     * Перевіряє, чи правильно ініціалізуються компоненти R, G, B, A.
     */
    @Test
    public void testConstructorAndGetters() {
        ColorRGBA color = new ColorRGBA(100, 150, 200, 255);
        assertEquals("Неправильна червона компонента (R)", 100, color.getR());
        assertEquals("Неправильна зелена компонента (G)", 150, color.getG());
        assertEquals("Неправильна синя компонента (B)", 200, color.getB());
        assertEquals("Неправильний альфа-канал (A)", 255, color.getA());
    }

    /**
     * Тестує метод valueInt().
     * Перевіряє, чи правильно колір перетворюється у ціле число.
     */
    @Test
    public void testValueInt() {
        ColorRGBA color = new ColorRGBA(255, 0, 0, 255);
        int expected = (255 << 24) | (0 << 16) | (0 << 8) | 255;
        assertEquals("valueInt() повертає неправильне значення", expected, color.valueInt());
    }

    /**
     * Тестує метод valueFloat().
     * Перевіряє, чи правильно колір нормалізується до дійсного числа.
     */
    @Test
    public void testValueFloat() {
        ColorRGBA color = new ColorRGBA(255, 255, 255, 255);
        assertEquals("valueFloat() повертає неправильне значення", 1.0, color.valueFloat(), 0.01);
    }

    /**
     * Тестує побітові операції OR та AND.
     * Перевіряє, чи правильно виконуються побітові об'єднання та перетин.
     */
    @Test
    public void testBitwiseOperations() {
        ColorRGBA color1 = new ColorRGBA(255, 0, 0, 255);
        ColorRGBA color2 = new ColorRGBA(0, 255, 0, 255);

        ColorRGBA resultOr = (ColorRGBA) color1.or(color2);
        assertEquals("Побітове OR повертає неправильну червону компоненту (R)", 255, resultOr.getR());
        assertEquals("Побітове OR повертає неправильну зелену компоненту (G)", 255, resultOr.getG());
        assertEquals("Побітове OR повертає неправильну синю компоненту (B)", 0, resultOr.getB());
        assertEquals("Побітове OR повертає неправильний альфа-канал (A)", 255, resultOr.getA());

        ColorRGBA resultAnd = (ColorRGBA) color1.and(color2);
        assertEquals("Побітове AND повертає неправильну червону компоненту (R)", 0, resultAnd.getR());
        assertEquals("Побітове AND повертає неправильну зелену компоненту (G)", 0, resultAnd.getG());
        assertEquals("Побітове AND повертає неправильну синю компоненту (B)", 0, resultAnd.getB());
        assertEquals("Побітове AND повертає неправильний альфа-канал (A)", 255, resultAnd.getA());
    }

    /**
     * Тестує додавання кольорів (середнє значення кожної компоненти).
     * Перевіряє, чи правильно обчислюється результат.
     */
    @Test
    public void testAddColors() {
        ColorRGBA color1 = new ColorRGBA(255, 0, 0, 255);
        ColorRGBA color2 = new ColorRGBA(0, 255, 0, 255);

        ColorRGBA result = (ColorRGBA) color1.add(color2);
        assertEquals("Додавання кольорів повертає неправильну червону компоненту (R)", 127, result.getR());
        assertEquals("Додавання кольорів повертає неправильну зелену компоненту (G)", 127, result.getG());
        assertEquals("Додавання кольорів повертає неправильну синю компоненту (B)", 0, result.getB());
        assertEquals("Додавання кольорів повертає неправильний альфа-канал (A)", 255, result.getA());
    }
}
