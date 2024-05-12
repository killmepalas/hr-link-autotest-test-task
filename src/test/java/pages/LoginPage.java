package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.pageParent.BaseProcess;
import pages.pageTitle.PageTitle;
import user.User;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@PageTitle("Страница Авторизации. LoginPage")
public class LoginPage extends BaseProcess {

    {
        proxyElementForLocator("Поле 'Email/Телефон'", "//input[@formcontrolname='login']");
        proxyElementForLocator("Поле 'Пароль'", "//input[@formcontrolname='password']");
        proxyElementForLocator("Кнопка 'Войти'", "//*[@data-qa='oauth-provider-buttons-login-by-oauth-provider-link']");
    }


    @Step("Открытие страницы авторизации")
    public LoginPage openLoginPage(){
        open("/login");
        getWebDriver().manage().window().maximize();
        getWebDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(2));
        return this;
    }


    @Step("Авторизация пользователем")
    public LoginPage loginByUserCredentials(User user){
        authorize(user);
        return this;
    }

    @Step("Авторизация пользователем с неверными данными")
    public void loginByUserWithWrongCredentials(User user) {
        authorize(user);
    }


    private void authorize(User user){
        sendKeys("Поле 'Email/Телефон'", user.getEmail());
        sendKeys("Поле 'Пароль'", user.getPassword());
        click("Кнопка 'Войти'");
    }

}
