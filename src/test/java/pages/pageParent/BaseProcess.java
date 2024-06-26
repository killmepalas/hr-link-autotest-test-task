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
        proxyElementForLocator("Аватар пользователя", "//*[@data-qa='app-header-open-menu-actions-button']");
        proxyElementForLocator("Профиль. Кнопка 'Выйти'", "//button[@data-qa='app-header-logout-button']");
        proxyElementForLocator("Кнопка 'Войти'", "//button[@type='submit']");
        proxyElementForLocator("Значок загрузки", "//mat-spinner");
        proxyElementForLocator("Кнопка 'Отмена' окна 'Уведомления в Телеграм'", "//*[@data-qa='telegram-invitation-dlg-cancel-button']");
    }

    public static final String TEXT_LOCATOR_PATTERN = "//*[contains(text(), '%s')]";
    public static final String FILTER_TABLE_LOCATOR_PATTERN = "//*[@role='listbox']//*[contains(@role, 'option') and contains(., '%s')]";

    public void precondition() {
        authorize(UserFactory.withAdminPermission());
    }

    public void precondition(User user) {
        authorize(user);
    }

    public void postcondition(){
        exitByUserCredentials();
    }


    @Step("Производим авторизацию под пользователем {0}")
    private void authorize(User user) {
        LoginPage login = PageManager.getInstance().loadPage("Страница Авторизации. LoginPage");
        login.openLoginPage()
                .loginByUserCredentials(user);
    }


    @Step("Выход пользователя из системы")
    public LoginPage exitByUserCredentials() {
        click("Аватар пользователя");
        waitAppearanceElement("Профиль. Кнопка 'Выйти'");
        click("Профиль. Кнопка 'Выйти'");
        return PageManager.getInstance().loadPage("Страница Авторизации. LoginPage");

    }


    @Step("Закрываем окно 'Уведомления в Телеграм', если оно появилось")
    public void closeTelegramDialog() {
        if (getElementByTitle("Кнопка 'Отмена' окна 'Уведомления в Телеграм'").isEnabled())
            click("Кнопка 'Отмена' окна 'Уведомления в Телеграм'");
    }

}
