package tests.legal_entities;

import io.qameta.allure.Feature;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import tests.base_test.BaseTest;
import user.UserFactory;
import utils.AllureUtils.*;
import utils.AllureUtils.Object;

@Feature("Прохождение процесса аутентификации")
public class LoginToAccountByDeletedUserTest extends BaseTest {

    @Test
    @Tag("MainUserPath")
    @Tag("Login")
    @Object("Аутентификация")
    @Actor({"Кадровик", "Рекрутер", "СБ", "Владелец системы"})
    @FunctionProperty("Прохождение процесса аутентификации через UI")
    @DisplayName("Аутентификация, в зависимости от заблокированности аккаунта (negative)")
    public void loginToAccountByDeletedUserTest(){
//        baseProcess.badPrecondition(UserFactory.withDeletedAccount());
//        loginPage.getWarningSign("Пользователь заблокирован.");
    }
}
