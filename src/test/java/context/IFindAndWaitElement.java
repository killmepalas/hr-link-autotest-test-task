package context;

import com.codeborne.selenide.SelenideElement;
import managers.CustomLogger;
import org.openqa.selenium.StaleElementReferenceException;

public interface IFindAndWaitElement extends IStoreElements, IHighLight{
    /**
     * @param key ключ локатора
     * @return возвращает прокированный локатор элемента из хранилища по ключу
     */
    default SelenideElement getElementByTitle(String key, boolean javaScript) {
        CustomLogger.debugFormat("Работаем с элементом [%s]", key);
        var count = 0;
        return retry(() -> highlightElement(getElementProxy(key), javaScript), count);
    }

    default SelenideElement getElementByTitle(String key) {
        CustomLogger.debugFormat("Работаем с элементом [%s]", key);
        var count = 0;
        return retry(() -> highlightElement(getElementProxy(key), true), count);
    }

    default SelenideElement retry(IReturnSelenideElement method, int count) {
        if (count != 3) {
            try {
                return method.method();
            } catch (StaleElementReferenceException e) {
                CustomLogger.debug("Получили устаревшую ссылку на элемент StaleElementReferenceException, повторяем попытку");
                count++;
                return retry(method, count);
            }
        }
        throw new RuntimeException("Получаем исключение при получении StaleElementReferenceException");
    }

    /**
     * @param key ключ локатора
     * @return возвращает прокированный локатор элемента из хранилища по ключу
     */
    default SelenideElement getCollectionElementByTitle(String key, int index, boolean javaScript) {
        return highlightElement(getCollectionProxy(key).get(index), javaScript);
    }

    default void getCollectionElementByTitle(String key, int index) {
        highlightElement(getCollectionProxy(key), index, true);
    }

}
