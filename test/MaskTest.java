package test;

import org.junit.Test;
import static org.junit.Assert.*;

import colors.ColorRGBA;
import images.Image;
import images.Mask;

/**
 * Клас для тестування функціональності класу Mask.
 */
public class MaskTest {

    /**
     * Тестує методи установки та отримання значення маски.
     * 
     * Перевіряється, чи значення маски встановлюються та повертаються правильно.
     */
    @Test
    public void testSetAndGetMaskValue() {
        Mask mask = new Mask(2, 2);
        mask.setMaskValue(0, 0, 1);
        mask.setMaskValue(1, 1, 0);

        assertEquals("Значення маски (0,0) не співпадає з очікуваним", 1, mask.getMaskValue(0, 0));
        assertEquals("Значення маски (1,1) не співпадає з очікуваним", 0, mask.getMaskValue(1, 1));
    }

    /**
     * Тестує метод застосування маски до зображення.
     * 
     * Перевіряється, чи впливає маска на зображення у правильних пікселях,
     * залишаючи інші пікселі незмінними.
     */
    @Test
    public void testApplyMaskToImage() {
        Image image = new Image(2, 2);
        Mask mask = new Mask(2, 2);
        ColorRGBA color = new ColorRGBA(100, 150, 200, 255);

        image.fill(color);
        mask.setMaskValue(0, 0, 1); // Застосовуємо маску до одного пікселя

        mask.applyToImage(image);

        // Перевіряємо, що маска вплинула на піксель
        assertNotEquals(
                "Маска не вплинула на піксель (0,0)",
                color.valueInt(),
                image.getPixel(0, 0).valueInt());
        assertEquals(
                "Маска вплинула на піксель (1,1), де не повинна була",
                color.valueInt(),
                image.getPixel(1, 1).valueInt());
    }
}
