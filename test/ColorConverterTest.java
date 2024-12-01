package test;

import org.junit.Test;
import static org.junit.Assert.*;

import colors.ColorRGBA;
import colors.ColorConverter;
import colors.ColorHSB;

/**
 * Клас для тестування функцій конвертації між кольоровими моделями.
 */
public class ColorConverterTest {

    /**
     * Тестує метод RGBtoHSB для перевірки конвертації кольору з моделі RGB у HSB.
     * 
     * <p>
     * Очікується, що червоний колір RGB (255, 0, 0, 255) буде правильно
     * перетворений у HSB з відтінком 0, насиченістю 100 і яскравістю 100.
     * </p>
     */
    @Test
    public void testRGBtoHSB() {
        ColorRGBA rgbColor = new ColorRGBA(255, 0, 0, 255);
        ColorHSB hsbColor = ColorConverter.RGBtoHSB(rgbColor);

        assertEquals("Неправильний відтінок (H)", 0, hsbColor.getH());
        assertEquals("Неправильна насиченість (S)", 100, hsbColor.getS());
        assertEquals("Неправильна яскравість (B)", 100, hsbColor.getB());
    }

    /**
     * Тестує метод HSBtoRGB для перевірки конвертації кольору з моделі HSB у RGB.
     * 
     * <p>
     * Очікується, що зелений колір HSB (120, 100, 100) буде правильно
     * перетворений у RGB з компонентами R=0, G=255, B=0 та альфа-каналом A=255.
     * </p>
     */
    @Test
    public void testHSBtoRGB() {
        ColorHSB hsbColor = new ColorHSB(120, 100, 100);
        ColorRGBA rgbColor = ColorConverter.HSBtoRGB(hsbColor);

        assertEquals("Неправильна червона компонента (R)", 0, rgbColor.getR());
        assertEquals("Неправильна зелена компонента (G)", 255, rgbColor.getG());
        assertEquals("Неправильна синя компонента (B)", 0, rgbColor.getB());
        assertEquals("Неправильний альфа-канал (A)", 255, rgbColor.getA());
    }
}
