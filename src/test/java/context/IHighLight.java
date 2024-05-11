package context;

import attachmentUtils.AttachmentUtils;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import managers.CustomLogger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;
import java.util.function.Supplier;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public interface IHighLight {

    default SelenideElement highlightElement(WebElement webElement, boolean javaScript) {
        return selectElement(webElement, javaScript);
    }

    default void highlightElement(ElementsCollection elements, int index, boolean javaScript) {
        elements.asFixedIterable()
                .stream()
                .forEach(e -> selectElement(e, javaScript));
        highlightElement(elements.get(index), javaScript);
    }


    private SelenideElement selectElement(WebElement webElement, boolean javaScript) {
        Supplier<WebElement> supplier = () -> webElement;
        new WebDriverWait(getWebDriver(), Duration.ofSeconds(2)).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) Objects.requireNonNull(wd)).executeScript("return document.readyState").equals("complete"));
        if (javaScript) {
            CustomLogger.debugFormat("Ищем элемент '%s' на странице и выделяем его", supplier.get());
            $(supplier.get())
                    .scrollIntoView("{block: \"center\", inline: \"center\"}");
            executeJavaScript("arguments[0].style.border='2px solid blue'", supplier.get());
            AttachmentUtils.attachScreenshot();
            AttachmentUtils.attachPageSource();
            if (supplier.get().isDisplayed() && supplier.get().isEnabled()) {
                Selenide.executeJavaScript("arguments[0].style.border='0px'", supplier.get());
            }
        }
        return Selenide.element(supplier.get());
    }

}
