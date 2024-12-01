package colors;

/**
 * Клас ColorConverter надає методи для конверсії між кольоровими моделями.
 */
public class ColorConverter {
    /**
     * Конструктор за замовчуванням для класу ColorConverter.
     * Надає утилітарні методи для конверсії кольорових моделей.
     */
    public ColorConverter() {
        // Конструктор залишається порожнім
    }

    /**
     * Конвертує колір із моделі RGB у HSB.
     * 
     * @param colorRGB Колір у форматі RGBA.
     * @return Колір у форматі HSB.
     */
    public static ColorHSB RGBtoHSB(ColorRGBA colorRGB) {
        float r = colorRGB.getR() / 255.0f;
        float g = colorRGB.getG() / 255.0f;
        float b = colorRGB.getB() / 255.0f;

        float max = Math.max(r, Math.max(g, b));
        float min = Math.min(r, Math.min(g, b));
        float delta = max - min;

        float h = 0;
        if (delta != 0) {
            if (max == r) {
                h = (g - b) / delta % 6;
            } else if (max == g) {
                h = (b - r) / delta + 2;
            } else {
                h = (r - g) / delta + 4;
            }
            h *= 60;
            if (h < 0)
                h += 360;
        }

        float s = max == 0 ? 0 : (delta / max) * 100;
        float brightness = max * 100;

        return new ColorHSB((int) h, (int) s, (int) brightness);
    }

    /**
     * Конвертує колір із моделі RGB у CMYK.
     * 
     * @param colorRGB Колір у форматі RGBA.
     * @return Колір у форматі CMYK.
     */
    public static ColorCMYK RGBtoCMYK(ColorRGBA colorRGB) {
        float r = colorRGB.getR() / 255.0f;
        float g = colorRGB.getG() / 255.0f;
        float b = colorRGB.getB() / 255.0f;

        float k = 1 - Math.max(r, Math.max(g, b));
        float c = (1 - r - k) / (1 - k);
        float m = (1 - g - k) / (1 - k);
        float y = (1 - b - k) / (1 - k);

        return new ColorCMYK(
                (int) (c * 100),
                (int) (m * 100),
                (int) (y * 100),
                (int) (k * 100));
    }

    /**
     * Конвертує колір із моделі HSB у RGB.
     * 
     * @param colorHSB Колір у форматі HSB.
     * @return Колір у форматі RGBA.
     */
    public static ColorRGBA HSBtoRGB(ColorHSB colorHSB) {
        float h = colorHSB.getH();
        float s = colorHSB.getS() / 100.0f;
        float b = colorHSB.getB() / 100.0f;

        int r = 0, g = 0, bl = 0;
        int i = (int) Math.floor(h / 60) % 6;
        float f = h / 60 - i;
        float p = b * (1 - s);
        float q = b * (1 - f * s);
        float t = b * (1 - (1 - f) * s);

        switch (i) {
            case 0 -> {
                r = (int) (b * 255);
                g = (int) (t * 255);
                bl = (int) (p * 255);
            }
            case 1 -> {
                r = (int) (q * 255);
                g = (int) (b * 255);
                bl = (int) (p * 255);
            }
            case 2 -> {
                r = (int) (p * 255);
                g = (int) (b * 255);
                bl = (int) (t * 255);
            }
            case 3 -> {
                r = (int) (p * 255);
                g = (int) (q * 255);
                bl = (int) (b * 255);
            }
            case 4 -> {
                r = (int) (t * 255);
                g = (int) (p * 255);
                bl = (int) (b * 255);
            }
            case 5 -> {
                r = (int) (b * 255);
                g = (int) (p * 255);
                bl = (int) (q * 255);
            }
        }

        return new ColorRGBA(r, g, bl, 255);
    }

    /**
     * Конвертує колір із моделі CMYK у RGB.
     * 
     * @param colorCMYK Колір у форматі CMYK.
     * @return Колір у форматі RGBA.
     */
    public static ColorRGBA CMYKtoRGB(ColorCMYK colorCMYK) {
        float c = colorCMYK.getC() / 100.0f;
        float m = colorCMYK.getM() / 100.0f;
        float y = colorCMYK.getY() / 100.0f;
        float k = colorCMYK.getK() / 100.0f;

        int r = (int) ((1 - c) * (1 - k) * 255);
        int g = (int) ((1 - m) * (1 - k) * 255);
        int b = (int) ((1 - y) * (1 - k) * 255);

        return new ColorRGBA(r, g, b, 255);
    }

    /**
     * Конвертує колір із моделі RGB у XYZ.
     * 
     * @param colorRGB Колір у форматі RGBA.
     * @return Колір у форматі XYZ.
     */
    public static ColorXYZ RGBtoXYZ(ColorRGBA colorRGB) {
        float r = colorRGB.getR() / 255.0f;
        float g = colorRGB.getG() / 255.0f;
        float b = colorRGB.getB() / 255.0f;

        float x = r * 0.4124564f + g * 0.3575761f + b * 0.1804375f;
        float y = r * 0.2126729f + g * 0.7151522f + b * 0.0721750f;
        float z = r * 0.0193339f + g * 0.1191920f + b * 0.9503041f;

        return new ColorXYZ(x * 100, y * 100, z * 100);
    }

    /**
     * Конвертує колір із моделі XYZ у RGB.
     * 
     * @param colorXYZ Колір у форматі XYZ.
     * @return Колір у форматі RGBA.
     */
    public static ColorRGBA XYZtoRGB(ColorXYZ colorXYZ) {
        float x = colorXYZ.getX() / 100.0f;
        float y = colorXYZ.getY() / 100.0f;
        float z = colorXYZ.getZ() / 100.0f;

        float r = x * 3.2404542f - y * 1.5371385f - z * 0.4985314f;
        float g = -x * 0.9692660f + y * 1.8760108f + z * 0.0415560f;
        float b = x * 0.0556434f - y * 0.2040259f + z * 1.0572252f;

        int ri = Math.min(255, Math.max(0, Math.round(r * 255)));
        int gi = Math.min(255, Math.max(0, Math.round(g * 255)));
        int bi = Math.min(255, Math.max(0, Math.round(b * 255)));

        return new ColorRGBA(ri, gi, bi, 255);
    }
}
