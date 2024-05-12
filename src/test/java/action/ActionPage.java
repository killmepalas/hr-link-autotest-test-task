package action;

import com.codeborne.selenide.SelenideElement;
import context.IFindAndWaitElement;
import io.qameta.allure.Step;
import managers.CustomLogger;
import org.openqa.selenium.JavascriptExecutor;

import java.io.File;
import com.codeborne.selenide.files.FileFilter;
import java.io.FileNotFoundException;
import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Класс, содержащий действия на страницы с элементами
 */


public interface ActionPage extends IFindAndWaitElement {
    @Step("Нажимаем на [{0}]")
    default void click(String key) {
        CustomLogger.debugFormat("Нажимаем на [%s]", key);
        getElementByTitle(key)
                .click();
    }

//    @Step("Нажимаем на [{0}]")
//    default void clickAllowDisabled(String key) {
//        CustomLogger.debugFormat("Нажимаем на [%s]", key);
//        getElementByTitle(key)
//                .click(ClickOptions.allowDisabled());
//    }

    @Step("Нажимаем на [{0}]")
    default void click(String key, boolean javascript) {
        CustomLogger.debugFormat("Нажимаем на [%s]", key);
        getElementByTitle(key, javascript)
                .click();
    }

    @Step("Скачиваем документ")
    default File download(String key) {
        try {
            return getElementByTitle(key)
                    .download();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Step("Скачиваем документ")
    default File download(String key, FileFilter fileFilter) {
        try {
            return getElementByTitle(key)
                    .download(fileFilter);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Не удалось скачать документ");
    }

    @Step("Нажимаем на [{0}]")
    default void clickCollection(String key, int index) {
        CustomLogger.debugFormat("Нажимаем на [%s]", key);
        getCollectionElementByTitle(key, index, true)
                .click();
    }

    @Step("Получаем текст из [{0}]")
    default String getTextCollection(String key, int index) {
        CustomLogger.debugFormat("Получаем текст из [%s]", key);
        return getCollectionElementByTitle(key, index, true)
                .getText();
    }

    @Step("Нажимаем на [{0}]")
    default void click(String pattern, String element) {
        CustomLogger.debugFormat("Нажимаем на [%s]", element);
        highlightElement($x(String.format(pattern, element)), true)
                .click();
    }

    @Step("Нажимаем на [{0}]")
    default void click(SelenideElement element) {
        CustomLogger.debugFormat("Нажимаем на [%s]", element);
        highlightElement(element, true)
                .click();
    }

    @Step("Получаем аттрибут [{1}] элемента [{0}]")
    default void getAttributeByCss(String key, String attribute) {
        CustomLogger.debugFormat("Получаем аттрибут [%s] элемента [%s]", attribute, key);
        getElementByTitle(key, false)
                .shouldHave(cssClass(attribute));
    }

    @Step("Получаем аттрибут [{1}] элемента [{0}]")
    default SelenideElement getAttributeByCss(String key) {
        CustomLogger.debugFormat("Получаем аттрибут элемента [%s]", key);
        return getElementByTitle(key, false);
    }

    @Step("Нажимаем на [{0}]")
    default void click(SelenideElement element, int seconds) {
        CustomLogger.debugFormat("Нажимаем на [%s]", element);
        highlightElement(element, true)
                .shouldBe(visible, Duration.ofSeconds(seconds)).click();
    }

    @Step("Нажимаем на [{0}]")
    default void click(String key, int seconds) {
        CustomLogger.debugFormat("Нажимаем на [%s]", key);
        getElementByTitle(key)
                .shouldBe(visible, Duration.ofSeconds(seconds)).click();
    }

    @Step("Нажимаем Enter для [{0}]")
    default void pressEnter(String key) {
        getElementByTitle(key, false)
                .pressEnter();
    }

    @Step("В [{0}] вводим текст [{1}]")
    default void setValue(String key, String text) {
        CustomLogger.debugFormat("В поле [%s] вводим значение [%s]", key, text);
        getElementByTitle(key, true)
                .setValue(text);
    }

    @Step("Устанавливаем значение {1} для элемента {0}")
    default void setValue(SelenideElement element, String text) {
        CustomLogger.debugFormat("В поле %s вводим значение %s", element, text);
        highlightElement(element, true)
                .setValue(text);
    }

    @Step("В [{0}] вводим текст [{1}]")
    default void setValue(String pattern, String text, String value) {
        CustomLogger.debugFormat("В поле [%s] вводим значение [%s]", text, value);
        highlightElement($x(String.format(pattern, text)), true)
                .setValue(value);
    }

    @Step("В [{0}] вводим текст [{1}]")
    default void setValueCollection(String key, int index, String value) {
        CustomLogger.debugFormat("В поле [%s] вводим значение [%s]", key, value);
        getCollectionElementByTitle(key, index, true)
                .setValue(value);
    }

    @Step("В [{0}] вводим текст [{1}]")
    default void sendKeys(String key, String text) {
        CustomLogger.debugFormat("В поле [%s] вводим значение [%s]", key, text);
        getElementByTitle(key, true)
                .sendKeys(text);
    }

    @Step("Получаем значение из [{0}]")
    default String getValue(String key) {
        CustomLogger.debugFormat("Получаем значение из [%s]", key);
        return getElementByTitle(key)
                .getValue();
    }

    @Step("Получаем текст из [{0}]")
    default String getText(SelenideElement element) {
        CustomLogger.debugFormat("Получаем текст из [%s]", element);
        return highlightElement(element, true)
                .getText();
    }

    @Step("Получаем текст из [{0}]")
    default String getText(String pattern, String text) {
        CustomLogger.debugFormat("Получаем текст из [%s]", text);
        return highlightElement($x(String.format(pattern, text)), true)
                .getText();
    }

    @Step("Получаем текст из [{0}]")
    default String getText(String key) {
        CustomLogger.debugFormat("Получаем текст из [%s]", key);
        return getElementByTitle(key)
                .getText();
    }

    default SelenideElement waitElement(String pattern, String element) {
        CustomLogger.debugFormat("Ожидаем элемент [%s]", element);
        return highlightElement($x(String.format(pattern, element)), false);
    }

    default SelenideElement waitElement(String key) {
        CustomLogger.debugFormat("Ожидаем появление элемента [%s]", key);
        return getElementByTitle(key, false);
    }

    default SelenideElement waitElement(SelenideElement element) {
        CustomLogger.debugFormat("Ожидаем появление элемента [%s]", element);
        return highlightElement(element, false);
    }

    default void removeElementOnPage(String xpath) {
        CustomLogger.debugFormat("Удаляем элемент [%s] на странице", xpath);
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].remove()", $x(xpath));
    }

    default void waitDisappearanceElement(String key, int seconds) {
        CustomLogger.debugFormat("Ожидаем до [%s] секунд для исчезновения элемента [%s]", seconds, key);
        getElementByTitle(key, false)
                .shouldBe(hidden, Duration.ofSeconds(seconds));
    }

    default SelenideElement waitAppearanceElement(String key, int seconds) {
        CustomLogger.debugFormat("Ожидаем до [%s] секунд для появления элемента [%s]", seconds, key);
        return getElementByTitle(key, false)
                .shouldBe(visible, Duration.ofSeconds(seconds));
    }

//    default SelenideElement waitClickableElement(String key, int seconds) {
//        CustomLogger.debugFormat("Ожидаем до [%s] секунд для появления элемента [%s]", seconds, key);
//        return getElementByTitle(key, false)
//                .shouldBe(cli, Duration.ofSeconds(seconds));
//    }

    default SelenideElement waitAppearanceElement(String pattern, String text) {
        CustomLogger.debugFormat("Ожидаем до появления элемента [%s]", text);
        return highlightElement($x(String.format(pattern, text)), false)
                .shouldBe(visible, Duration.ofSeconds(30));
    }

    default SelenideElement waitAppearanceElement(String key) {
        CustomLogger.debugFormat("Ожидаем появления элемента [%s]", key);
        return getElementByTitle(key, false)
                .shouldBe(visible, Duration.ofSeconds(30));
    }

    @Step("Ожидаем появление элемента {0}, если он существует")
    default void waitElementIfExists(SelenideElement element) {
        CustomLogger.debugFormat("Ожидаем элемент %s", element);

        if (element.exists()) {
            highlightElement(element, false)
                    .shouldBe(visible, Duration.ofSeconds(10));
        } else {
            System.out.println("Элемент не найден");
        }
    }

    @Step("Проверяем существует ли элемент {0}")
    default boolean isElementExists(String key) {
        CustomLogger.debugFormat("Ожидаем появления элемента [%s]", key);
        return getElementByTitle(key, false) != null;
    }


}
