package context;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public interface IStoreElements extends IElementStorage<SelenideElement> {
    /**
     * @return возвращает ссылку на интерфейс для работы с хранилищем
     */
    IElementStorage<SelenideElement> getLocatorStorage();

    /**
     * @param key ключ локатора
     * @return возвращает проксированный локатор элемента из хранилища по ключу
     */
    @Override
    default SelenideElement getElementProxy(String key) {
        return getLocatorStorage().getElementProxy(key);
    }

    /**
     * @param key ключ локатора
     * @return возвращает проксированный локатор элемента из хранилища для коллекций по ключу
     */
    @Override
    default ElementsCollection getCollectionProxy(String key) {
        return getLocatorStorage().getCollectionProxy(key);
    }

    /**
     * Добавляем ключ локатора и локатор элемента в хранилище
     *
     * @param key   ключ локатора
     * @param xpath локатор элемента
     */
    @Override
    default void proxyElementForLocator(String key, String xpath) {
        getLocatorStorage().proxyElementForLocator(key, xpath);
    }

    /**
     * Добавляем ключ локатора и локатор элемента в хранилище
     *
     * @param key   ключ локатора
     * @param pattern шаблон xpath
     * @param text текст для паттерна
     */
    @Override
    default void proxyElementForLocator(String key, String pattern, String text) {
        getLocatorStorage().proxyElementForLocator(key, String.format(pattern, text));
    }

    /**
     * Добавляем ключ локатора и локатор элемента в хранилище для коллекций
     *
     * @param key   ключ локатора
     * @param collection локатор элемента
     */
    @Override
    default void proxyCollectionElementForLocator(String key, ElementsCollection collection) {
        getLocatorStorage().proxyCollectionElementForLocator(key, collection);
    }

    /**
     * @param key название локатора элемента
     * @return проверяет содержится ли локатор элемнета в хранилище
     */
    @Override
    default boolean elementProxyExists(String key) {
        return getLocatorStorage().elementProxyExists(key);
    }

    /**
     * @param key название локатора элемента
     * @return проверяет содержится ли локатор элемнета в хранилище для коллекций
     */
    @Override
    default boolean collectionElementProxyExists(String key) {
        return getLocatorStorage().collectionElementProxyExists(key);
    }

}
