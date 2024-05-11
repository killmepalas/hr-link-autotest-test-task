package managers;

import org.reflections.Reflections;
import pages.LoginPage;
import pages.pageParent.Page;
import pages.pageTitle.PageTitle;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PageManager {
    private static final ThreadLocal<PageManager> pInstance = ThreadLocal.withInitial(PageManager::new);
    private static final Map<String, Class> mapPage = new ConcurrentHashMap<>();
    private final Map<Class<Page>, Page> pages = new ConcurrentHashMap<>();

    public PageManager() {
    }

    static {
        var reflections = new Reflections(LoginPage.class.getPackage().getName());
        var classes = reflections.getTypesAnnotatedWith(PageTitle.class);

        classes.forEach(cls -> {
            var target = cls.getAnnotation(PageTitle.class);
            if (target != null){
                if (!mapPage.
                        containsKey(target.value())) {
                    mapPage.put(target.value(), cls);
                }
            } else System.out.println("Target is null value");

        });
    }

    public static PageManager getInstance() {
        return pInstance.get();
    }

    /**
     * Метод предназначен для загрузки страницы:
     * Если ключ страницы есть в хранилище, значит возвращаем её экземпляр (Singleton)
     * Если ключа нет - создаём новый инстанс для класса страницы и кладём её в хранилище
     *
     * @param key название страницы из аннотации @PageTitle
     * @param <T> объект возвращаемой страницы
     * @return возвращает новый экземпляр страницы из хранилища по ключу
     */
    public <T extends Page> T loadPage(String key) {
        System.out.println(key);
        if (key == "" || key == null) System.out.println("блять");
        return getPage(getPageClass(key));
    }

    private <T extends Page> Class<T> getPageClass(String key) {
        return mapPage.get(key);
    }

    private <T extends Page> T getPage(Class<T> pageClass) {
        if (pages.containsKey(pageClass)) {
            return (T) pages.get(pageClass);
        }
        T page;
        try {
            page = pageClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        pages.put((Class<Page>) pageClass, page);
        return page;
    }
}
