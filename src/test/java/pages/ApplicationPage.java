package pages;

import asserts.AssertsClass;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.ex.TimeoutException;
import io.qameta.allure.Step;
import managers.CustomLogger;
import pages.pageParent.BaseProcess;
import pages.pageTitle.PageTitle;
import static utils.SleepUtils.sleep;

import static com.codeborne.selenide.Selenide.$$x;

@PageTitle("Общая страница. Заявления. ApplicationPage")
public class ApplicationPage extends BaseProcess {
    {
        proxyCollectionElementForLocator("Таблица. Столбец 'Сотрудник'", $$x("//div[@class='applications-registry-table-body__employee-cell']"));
        proxyElementForLocator("Страница реестра заявлений", "//*[@class='applications-registry-table-header-row__legal-entity-cell']" );
    }

    @Step("Проверяем, что заявления недоступного сотрудника не отображаются в реестре")
    public ApplicationPage checkApplicationRegistryDoesNotContainUnavailableApplications(String employeeName) {

        waitAppearanceElement("Страница реестра заявлений",1000);
        checkColumnDoesNotContainEmployee(employeeName);

        while (elementProxyExists("Кнопка 'Загрузить ещё'")) {
            try {
                click("Кнопка 'Загрузить ещё'");
                sleep(1000);
                checkColumnDoesNotContainEmployee(employeeName);
            } catch (ElementNotFound | TimeoutException e) {
                CustomLogger.debugFormat("Кнопка 'Load More' не обнаружена, продолжаем выполнение теста.");
                break;
            }
        }

        return this;
    }

    private void checkColumnDoesNotContainEmployee(String employeeName){
        for (var employeeNameElement :  getCollectionProxy("Таблица. Столбец 'Сотрудник'")) {
            AssertsClass.assertFalse(
                    getText(employeeNameElement).contains(employeeName),
                    "Проверяем, что в реестре не отображаются заявления сотрудника " + employeeName
            );
        }
    }

}
