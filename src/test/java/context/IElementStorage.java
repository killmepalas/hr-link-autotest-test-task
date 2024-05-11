package context;

import com.codeborne.selenide.ElementsCollection;

public interface IElementStorage<T> {
    /**
     * @param key ключ локатора
     * @return возвращает проксированный локатор элемента по ключу
     */
    T getElementProxy(String key);

    /**
     * @param key ключ локатора
     * @return возвращает проксированный локатор элемента по ключу из хранилища для коллекций
     */
    ElementsCollection getCollectionProxy(String key);

    /**
     * Метод для добавления ключа и локатора элемента в хранилище
     *
     * @param key   ключ локатора
     * @param xpath локатор элемента
     */
    void proxyElementForLocator(String key, String xpath);


    /**
     * Метод для добавления ключа и локатора элемента в хранилище
     *
     * @param key   ключ локатора
     * @param xpath локатор элемента
     */
    void proxyElementForLocator(String key, String pattern, String xpath);

    /**
     * Метод для добавления ключа и локатора элемента в хранилище для коллекций
     *
     * @param key   ключ локатора
     * @param collection локатор элемента
     */
    void proxyCollectionElementForLocator(String key, ElementsCollection collection);

    /**
     * @param key название локатора элемента
     * @return проверяет содержится ли локатор элемнета в хранилище
     */
    boolean elementProxyExists(String key);

    /**
     * @param key название локатора элемента
     * @return проверяет содержится ли локатор элемнета в хранилище для коллекций
     */
    boolean collectionElementProxyExists(String key);

}
