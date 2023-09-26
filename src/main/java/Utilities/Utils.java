package PageObjects;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Utils {
    public static AndroidDriver driver;
    public static String decodeQRCode(BufferedImage qrCodeImage) throws NotFoundException {

        LuminanceSource source = new BufferedImageLuminanceSource(qrCodeImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
           // result = new MultiFormatReader().decodeWithState(bitmap);
           // result = .decode(bitmap);
        com.google.zxing.Result result =  new MultiFormatReader().decode(bitmap);
     //   Bitmap resize = Bitmap.createScaledBitmap(srcBitmap, dstWidth,dstHeight,false);


        return Objects.requireNonNull(result).getText();
    }
    public BufferedImage generateImage(WebElement element, File screenshot) throws IOException {
        BufferedImage fullImage = ImageIO.read(screenshot);
        Point imageLocation = element.getLocation();

        int qrCodeImageWidth = element.getSize().getWidth();
        int qrCodeImageHeight = element.getSize().getHeight();

        int pointXPosition = imageLocation.getX();
        int pointYPosition = imageLocation.getY();

        BufferedImage qrCodeImage = fullImage.getSubimage(pointXPosition, pointYPosition, qrCodeImageWidth, qrCodeImageHeight);
        ImageIO.write(qrCodeImage, "png", screenshot);

        return qrCodeImage;
    }
}
