package pages;

import asserts.AssertsClass;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.ex.TimeoutException;
import io.qameta.allure.Step;
import managers.CustomLogger;
import pages.pageParent.BaseProcess;
import pages.pageTitle.PageTitle;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static utils.SleepUtils.sleep;

@PageTitle("Кадровик. Общая страница 'Сотрудники'")
public class EmployeesPage extends BaseProcess {
    {
        proxyCollectionElementForLocator("Таблица. Столбец 'ФИО'", $$x("//*[@data-qa='registry-row-employee-link']"));
        proxyElementForLocator("Фильтр 'ФИО'", "//*[@data-qa='registry-header-name-input']");
        proxyElementForLocator("Таблица. Список сотрудников", "//employees-registry-row");
        proxyCollectionElementForLocator("Таблица. Список сотрудников", $$x("//employees-registry-row"));
    }

    private static final String EMPLOYEE_PATTERN = "//employees-registry-row//a[.='%s']";
    private static final String LABEL_PATTERN = "//employees-registry-row//a[.='%s']//parent::div//parent::employees-registry-row//label";

    @Step("Проверяем, что в реестре не отображается недоступный сотрудник")
    public EmployeesPage checkEmployeesRegistryDoesNotContainUnavailableEmployee(String employeeName) {
        setValue("Фильтр 'ФИО'", employeeName);
//        waitHiddenSpinner();
        sleep(1000);
        checkColumnDoesNotContainEmployee(employeeName);
        return this;
    }

    private void checkColumnDoesNotContainEmployee(String employeeName){
        for (var employeeNameElement :  getCollectionProxy("Таблица. Список сотрудников")) {
            AssertsClass.assertFalse(employeeNameElement.getText().contains(employeeName),
                    "Проверяем, что в реестре сотрудников нет сотрудника " + employeeName);

        }
    }

    private void waitHiddenSpinner() {
        waitAppearanceElement("Значок загрузки");
        getElementByTitle("Значок загрузки", false).should(Condition.hidden);
    }
}
