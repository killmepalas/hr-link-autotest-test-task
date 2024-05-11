package asserts;

import io.qameta.allure.Step;
import managers.CustomLogger;
import org.junit.Assert;
import attachmentUtils.AttachmentUtils;
import utils.AllureUtils;
import utils.FileExtension;
import utils.StringUtils;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Класс содержащий проверки для всех результатов
 */
public class AssertsClass {

    @Step("{1}")
    public static void assertNull(final Object actual, final String message) {
        CustomLogger.debugFormat("Актуальное значение: [%s] ожидаемое: null", actual);
        AttachmentUtils.attachScreenshot();
        AttachmentUtils.attachPageSource();
        Assert.assertNull(actual);
    };

    @Step("{1}")
    public static void assertTrue(
            final boolean actual,
            final String message
    ) {
        CustomLogger.debug(message);
        AttachmentUtils.attachScreenshot();
        AttachmentUtils.attachPageSource();
        AllureUtils.addAttachments(toBytes(actual), toBytes("true"), FileExtension.TXT);
        Assert.assertTrue(actual);
    }

    @Step("{1}")
    public static void assertFalse(
            final boolean actual,
            final String message
    ) {
        CustomLogger.debug(message);
        AttachmentUtils.attachScreenshot();
        AttachmentUtils.attachPageSource();
        AllureUtils.addAttachments(toBytes(actual), toBytes("false"), FileExtension.TXT);
        Assert.assertFalse(actual);
    }

    @Step("Проверка результата")
    public static <T> void assertEquals(final T actual, final T expect) {
        equalsAndAttachment(actual, expect);
    }

    @Step("{2}")
    public static <T> void assertEquals(final T actual, final T expect, String textAllure) {
        equalsAndAttachment(actual, expect);
    }

    @Step("{1}")
    public static void assertNotNull(final Object actual, final String message) {
        CustomLogger.debug(message);
        AttachmentUtils.attachScreenshot();
        AttachmentUtils.attachPageSource();
        notNullAndAttachment(actual);
    }

    @Step("Проверка результата")
    private static <T> void equalsAndAttachment(final T actual, final T expect) {
        CustomLogger.debugFormat("Сравниваем полученное значение [%s] с ожидаемым [%s]", actual, expect);
        AllureUtils.addAttachments(toBytes(actual), toBytes(expect), FileExtension.TXT);
        Assert.assertEquals(actual, expect);
    }

    @Step("Проверка результата на null")
    private static <T> void notNullAndAttachment(final T actual) {
        AllureUtils.addAttachments(toBytes(actual), toBytes("!=null"), FileExtension.TXT);
        Assert.assertNotNull(actual);
    }

    private static byte[] toBytes(final Object object) {
        return StringUtils.toStringOrNull(object).getBytes(StandardCharsets.UTF_8);
    }

    @Step("Проверка совпадения времени с указанной точностью")
    public static void assertEquals(final LocalDateTime actual, final LocalDateTime expect, Duration plusMinus) {
        AllureUtils.addAttachments(toBytes(actual), toBytes(expect), FileExtension.TXT);
        Assert.assertTrue(actual.isBefore(expect.plus(plusMinus)));
        Assert.assertTrue(actual.isAfter(expect.minus(plusMinus)));
    }
}


