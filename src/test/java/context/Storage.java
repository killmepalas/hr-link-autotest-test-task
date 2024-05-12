package context;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import managers.CustomLogger;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Storage implements IElementStorage<SelenideElement> {

    private final Map<String, SelenideElement> map = new HashMap<>();
    private final Map<String, ElementsCollection> mapCollection = new HashMap<>();

    /**
     * @param key ключ локатора
     * @return возвращает проксированный локатор элемента и хранилища
     */
    @Override
    public SelenideElement getElementProxy(String key) {
        var locator = map.get(key);
        CustomLogger.debugFormat("По локатору %s", locator);
        return locator;
    }

    /**
     * @param key ключ локатора
     * @return возвращает проксированный локатор элемента и хранилища
     */
    @Override
    public ElementsCollection getCollectionProxy(String key) {
        var locator = mapCollection.get(key);
        CustomLogger.debugFormat("По локатору %s", locator);
        return locator;
    }

    /**
     * @param key   название локатора, который будет использоваться как ключ для его нахождения
     * @param locator локатор элемента
     */
    @Override
    public void proxyElementForLocator(String key, String locator) {
        if (!elementProxyExists(key)) {
            map.put(key, locator.contains("//") ? $x(locator) : $(locator));
        }
    }

    @Override
    public void proxyElementForLocator(String key, String pattern, String text) {
        if (!elementProxyExists(key)) {
            map.put(key, $x(String.format(pattern, text)));
        }
    }

    /**
     * @param key        название локатора, который будет использоваться как ключ для его нахождения
     * @param collection локатор элемента
     */
    @Override
    public void proxyCollectionElementForLocator(String key, ElementsCollection collection) {
        if (!collectionElementProxyExists(key)) {
            mapCollection.put(key, collection);
        }
    }

    /**
     * @param key название локатора элемента
     * @return проверяет содержится ли локатор элемнета в хранилище
     */
    @Override
    public boolean elementProxyExists(String key) {
        return map.containsKey(key);
    }

    /**
     * @param pattern паттерн локатора элемента
     * @param text название локатора элемента
     * @return проверяет содержится ли локатор элемнета в хранилище
     */
    @Override
    public boolean elementProxyExists(String pattern, String text) {
        return map.containsKey($x(String.format(pattern, text)));
    }

    /**
     * @param key название локатора элемента
     * @return проверяет содержится ли локатор элемнета в хранилище
     */

    @Override
    public boolean collectionElementProxyExists(String key) {
        return mapCollection.containsKey(key);
    }
}
