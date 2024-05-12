package pages;

import asserts.AssertsClass;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import pages.pageParent.BaseProcess;
import pages.pageTitle.PageTitle;

import static com.codeborne.selenide.Selenide.$$x;

@PageTitle("Кадровик. Общая страница. 'Документы'")
public class DocumentsPage extends BaseProcess {
    {
        proxyElementForLocator("Фильтр 'Сотрудник'", "//*[@data-qa='documents-registry-table-filter-row-employees-select']//input");
        proxyElementForLocator("Фильтр 'Юрлицо'", "//*[@data-qa='documents-registry-table-filter-row-legal-entities-select']");
        proxyCollectionElementForLocator("Таблица. Столбец 'Сотрудник'", $$x("//div[@class='documents-registry-desktop-table-row__employee-cell']"));
        proxyCollectionElementForLocator("Таблица. Столбец 'Юрлицо'", $$x("//*[contains(@class,'documents-registry-desktop-table-row__legal-entity-cell')]"));
    }

    @Step("Проверяем, что в реестре не отображается документ недоступного сотрудника")
    public DocumentsPage checkDocumentsRegistryDoesNotContainDocumentsOfUnavailableEmployee(String employeeName) {
        checkEmployeeInFilter(employeeName);
        checkEmployeeInDocuments(employeeName);
        return this;
    }

    @Step("Проверяем, что в реестре не отображается недоступное Юрлицо")
    public DocumentsPage checkDocumentsRegistryDoesNotContainDocumentsOfUnavailableLegalEntity(String entityName) {
       checkLegalEntityInFilter(entityName);
       checkLegalEntityInDocuments(entityName);
       return this;
    }

    private void checkEmployeeInFilter(String employeeName){
        click("Фильтр 'Сотрудник'");
        AssertsClass.assertFalse(elementProxyExists("(" + FILTER_TABLE_LOCATOR_PATTERN + ")[1]", employeeName), "Проверяем, есть ли в фильтре недоступный сотрудник");
    }

    private void checkEmployeeInDocuments(String employeeName){
        for (var entityNameElement :  getCollectionProxy("Таблица. Столбец 'Сотрудник'")) {
            AssertsClass.assertFalse(
                    getText(entityNameElement).contains(employeeName),
                    "Проверяем, что в реестре не отображаются документы недоступного сотрудника " + employeeName
            );
        }
    }

    private void checkLegalEntityInFilter(String legalEntityName){
        click("Фильтр 'Юрлицо'");
        AssertsClass.assertFalse(elementProxyExists("(" + FILTER_TABLE_LOCATOR_PATTERN + ")[1]", legalEntityName), "Проверяем, есть ли в фильтре недоступное ЮЛ");
    }

    private void checkLegalEntityInDocuments(String legalEntityName){
        for (var entityNameElement :  getCollectionProxy("Таблица. Столбец 'Юрлицо'")) {
            String nameOfEntity = getText(entityNameElement);
            AssertsClass.assertFalse(
                    nameOfEntity.contains(legalEntityName),
                    "Проверяем, что в реестре не отображаются документы ЮЛ " + legalEntityName
            );
        }
    }

    private void waitHiddenSpinner() {
        waitAppearanceElement("Значок загрузки");
        getElementByTitle("Значок загрузки", false).should(Condition.hidden);
    }

}
