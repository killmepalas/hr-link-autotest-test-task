package pages;

import asserts.AssertsClass;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import managers.CustomLogger;
import org.apache.commons.lang.ObjectUtils;
import pages.pageParent.BaseProcess;
import pages.pageTitle.PageTitle;

import static com.codeborne.selenide.Selenide.$$x;

@PageTitle("Кадровик. Общая страница. 'Документы'")
public class DocumentsPage extends BaseProcess {
    {
        proxyElementForLocator("Фильтр 'Сотрудник'", "//*[@data-qa='documents-registry-table-filter-row-employees-select']//input");
        proxyElementForLocator("Фильтр 'Юрлицо'", "//*[@data-qa='documents-registry-table-filter-row-legal-entities-select']");
        proxyCollectionElementForLocator("Таблица. Столбец 'Сотрудник'", $$x("//div[@class='documents-registry-desktop-table-row__employee-cell']"));
        proxyCollectionElementForLocator("Таблица. Столбец 'Юрлицо'", $$x("//div[@class='documents-registry-desktop-table-row__legal-entity-cell']"));
    }

    @Step("Проверяем, что в реестре не отображается документ недоступного сотрудника")
    public DocumentsPage checkDocumentsRegistryDoesNotContainDocumentsOfUnavailableEmployee(String employeeName) {
        chooseEmployeeFilter(employeeName);
        AssertsClass.assertTrue(getCollectionProxy("Таблица. Столбец 'Сотрудник'").isEmpty(), "Проверяем, что список отображаемых документов по фильтру пуст");
        return this;
    }

    @Step("Проверяем, что в реестре не отображается документ недоступного Юрлица")
    public DocumentsPage checkDocumentsRegistryDoesNotContainDocumentsOfUnavailableLegalEntity(String entityName) {
        click("Фильтр 'Юрлицо'");

        for (var entityNameElement :  getCollectionProxy("Таблица. Столбец 'Юрлицо'")) {
            AssertsClass.assertFalse(
                    getText(entityNameElement).contains(entityName),
                    "Проверяем, что в реестре не отображаются документы ЮЛ " + entityName
            );
        }


        AssertsClass.assertTrue(getCollectionProxy("Таблица. Столбец 'Юрлицо'").isEmpty(), "Проверяем, что список отображаемых документов по фильтру пуст");
        return this;
    }


    @Step("Выбор фильтра 'Юрлицо'")
    public DocumentsPage chooseEntityFilter(String entityName) {
        click("Фильтр 'Юрлицо'");
        click("(" + TEXT_LOCATOR_PATTERN + ")[1]", entityName);
        waitHiddenSpinner();
        return this;
    }

    private void chooseEmployeeFilter(String employeeName) {
        setValue("Фильтр 'Сотрудник'", employeeName);
    }

    private void chooseLegalEntityFilter() {
        click("Фильтр 'Юрлицо'");
    }

    private void waitHiddenSpinner() {
        waitAppearanceElement("Значок загрузки");
        getElementByTitle("Значок загрузки", false).should(Condition.hidden);
    }

}
