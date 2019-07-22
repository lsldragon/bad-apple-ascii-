/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * @author Elliot
 */
public class Image2Ascii {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public static String algorithm1(final String path) {

        final String base = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. ";
        StringBuffer result = new StringBuffer();

        try {
            BufferedImage image = ImageIO.read(new File(path));
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {

                    final int pixel = image.getRGB(x, y);
                    final int r = (pixel & 0xff0000) >> 16;
                    final int g = (pixel & 0xff00) >> 8;
                    final int b = pixel & 0xff;
                    float gray = 0.299f * r + 0.578f * g + 0.114f * b;
                    final int index = Math.round(gray * (base.length() + 1) / 255);
//                    System.out.print(index >= base.length() ? " " : String.valueOf(base.charAt(index)));

                    result.append(index >= base.length() ? " " : String.valueOf(base.charAt(index)));

                }
                result.append(LINE_SEPARATOR);
//                System.out.println();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();

    }

    public static String algorithm2(final String path) {
        final String base = "@#&$%*o!;.";
        StringBuffer result = new StringBuffer();

        try {
            BufferedImage image = ImageIO.read(new File(path));
//            System.out.println(image.getWidth() + ": " + image.getHeight());
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {

                    final int pixel = image.getRGB(x, y);
                    final int r = (pixel & 0xff0000) >> 16;
                    final int g = (pixel & 0xff00) >> 8;
                    final int b = pixel & 0xff;
                    float gray = 0.299f * r + 0.578f * g + 0.114f * b;
                    final int index = Math.round(gray * (base.length() + 1) / 255);

//                    System.out.print(index >= base.length() ? " " : String.valueOf(base.charAt(index)));
                    result.append(index >= base.length() ? " " : String.valueOf(base.charAt(index)));

                }
//                System.out.println();
                result.append(LINE_SEPARATOR);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

}
