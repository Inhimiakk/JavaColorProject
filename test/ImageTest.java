package test;

import org.junit.Test;
import static org.junit.Assert.*;

import colors.ColorRGBA;
import images.Image;

/**
 * Клас для тестування функціональності класу Image.
 */
public class ImageTest {

    /**
     * Тестує метод заповнення зображення одним кольором.
     * 
     * Перевіряється, чи всі пікселі зображення заповнюються вказаним кольором.
     */
    @Test
    public void testFillImage() {
        Image image = new Image(2, 2);
        ColorRGBA color = new ColorRGBA(100, 150, 200, 255);
        image.fill(color);

        assertEquals("Піксель (0,0) має неправильний колір", color, image.getPixel(0, 0));
        assertEquals("Піксель (1,1) має неправильний колір", color, image.getPixel(1, 1));
    }

    /**
     * Тестує метод встановлення кольору окремого пікселя.
     * 
     * Перевіряється, чи змінюється колір пікселя у вказаних координатах,
     * і чи залишаються інші пікселі незмінними.
     */
    @Test
    public void testSetPixel() {
        Image image = new Image(2, 2);
        ColorRGBA color1 = new ColorRGBA(100, 150, 200, 255);
        ColorRGBA color2 = new ColorRGBA(255, 0, 0, 255);

        image.fill(color1);
        image.setPixel(1, 1, color2);

        assertEquals("Піксель (1,1) має неправильний колір", color2, image.getPixel(1, 1));
        assertEquals("Піксель (0,0) має неправильний колір після змін", color1, image.getPixel(0, 0));
    }

    /**
     * Тестує поведінку методу отримання пікселя при зверненні
     * до недійсних координат.
     * 
     * Перевіряється, чи повертається null для координат за межами зображення.
     */
    @Test
    public void testInvalidPixelAccess() {
        Image image = new Image(2, 2);
        assertNull("Піксель (-1,0) не повинен існувати", image.getPixel(-1, 0)); // Координати за межами
        assertNull("Піксель (0,3) не повинен існувати", image.getPixel(0, 3)); // Координати за межами
    }
}
