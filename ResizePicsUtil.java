import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ResizePicsUtil {

    public static void zoomBySize(String filePath, String des) throws Exception {

        int width = 120;
        int height = 90;

        BufferedImage img = ImageIO.read(new File(filePath));
        Image _img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics = image.createGraphics();
        graphics.drawImage(_img, 0, 0, null);
        graphics.dispose();

        OutputStream out = new FileOutputStream(des);
        ImageIO.write(image, "png", out);


    }
}
