package pages.pageParent;

import io.qameta.allure.Step;
import managers.PageManager;
import pages.LoginPage;
import pages.NavigationBar;
import pages.pageTitle.PageTitle;
import user.User;
import user.UserFactory;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@PageTitle("Base process")
public class BaseProcess extends NavigationBar {
    {
        proxyElementForLocator("Аватар пользователя", "//span[@class='p-avatar-text ng-star-inserted']");
        proxyElementForLocator("Профиль. Кнопка 'Выйти'", "//span[text() = 'Выйти']");
        proxyElementForLocator("Кнопка 'Войти'", "//button[@type='submit']");
    }

    public void precondition() {
        authorize(UserFactory.withAdminPermission());
    }

    public void precondition(User user) {
        authorize(user);
    }

    public void badPrecondition() {
        badAuthorize(UserFactory.withAdminPermission());
    }

    public void badPrecondition(User user) {
        badAuthorize(user);
    }

    @Step("Производим авторизацию под пользователем {0}")
    private void authorize(User user) {
        LoginPage login = PageManager.getInstance().loadPage("Страница Авторизации. LoginPage");
        login.openLoginPage()
                .loginByUserCredentials(user);
    }

    @Step("Проверяем, что после введения нового пароля, переходим в ЛК сотрудника")
    public void checkEnterInPersonalAccount() {
        waitElement("Аватар пользователя").isEnabled();
    }

    @Step("Производим авторизацию под пользователем {0}")
    private void badAuthorize(User user) {
        LoginPage login = PageManager.getInstance().loadPage("Страница Авторизации. LoginPage");
        login.openLoginPage()
                .loginByUserWithWrongCredentials(user);
    }

    public static final String TEXT_LOCATOR_PATTERN = "//*[contains(text(), '%s')]";

    @Step("Выход пользователя из системы")
    public LoginPage exitByUserCredentials() {
        click("Аватар пользователя");
        click("Профиль. Кнопка 'Выйти'");
        return PageManager.getInstance().loadPage("Страница Авторизации. LoginPage");
    }

    @Step("Открываем меню аватарки")
    public BaseProcess openAvatarIcon() {
        click("Аватар пользователя");
        return this;
    }

}
