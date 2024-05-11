package attachmentUtils;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Attachment;
import managers.CustomLogger;
import org.openqa.selenium.OutputType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Класс с методами для вложения в отчёт Allure
 */

public class AttachmentUtils {
    @Attachment(value = "PageScreenshot", type = "image/png")
    public static byte[] attachScreenshot() {
        try {
            return Selenide.screenshot(OutputType.BYTES);
        } catch (Exception exception) {
            CustomLogger.debug("Получение screenshot страницы");
        }
        return new byte[0];
    }

    @Attachment(value = "PdfScreenshot", type = "image/png")
    public static byte[] attachScreenshotPDF(byte[] bytes) {
        return bytes;
    }

    @Attachment(value = "Logs", type = "application/json", fileExtension = ".txt")
    public static byte[] getBytesAnnotationWithArgs() {
        byte[] result = null;
        try {
            result = Files.readAllBytes(Paths.get("logs/logback.log"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Attachment(value = "PageSource", type = "text/xml")
    public static byte[] attachPageSource() {
        try {
            return getWebDriver().getPageSource().getBytes();
        } catch (Exception exception) {
            CustomLogger.debug("Получение html страницы");
        }
        return new byte[0];
    }

}
