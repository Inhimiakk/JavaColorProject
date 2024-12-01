package test;

import org.junit.Test;
import static org.junit.Assert.*;
import colors.*;

/**
 * Клас для тестування функціональності перетворень між кольоровими
 * моделями RGB та XYZ.
 */
public class ColorXYZTest {

    /**
     * Тестує перетворення кольору з моделі RGB у модель XYZ.
     * 
     * Перевіряється, чи правильно обчислюються компоненти X, Y, Z.
     */
    @Test
    public void testRGBtoXYZ() {
        ColorRGBA rgb = new ColorRGBA(255, 0, 0, 255);
        ColorXYZ xyz = ColorConverter.RGBtoXYZ(rgb);

        assertEquals("Неправильне значення компоненти X", 41.24, xyz.getX(), 0.1);
        assertEquals("Неправильне значення компоненти Y", 21.26, xyz.getY(), 0.1);
        assertEquals("Неправильне значення компоненти Z", 1.93, xyz.getZ(), 0.1);
    }

    /**
     * Тестує перетворення кольору з моделі XYZ у модель RGB.
     * 
     * Перевіряється, чи правильно обчислюються компоненти R, G, B, A.
     */
    @Test
    public void testXYZtoRGB() {
        ColorXYZ xyz = new ColorXYZ(41.24f, 21.26f, 1.93f);
        ColorRGBA rgb = ColorConverter.XYZtoRGB(xyz);

        assertEquals("Неправильне значення компоненти R", 255, rgb.getR());
        assertEquals("Неправильне значення компоненти G", 0, rgb.getG());
        assertEquals("Неправильне значення компоненти B", 0, rgb.getB());
        assertEquals("Неправильне значення компоненти A", 255, rgb.getA());
    }
}
