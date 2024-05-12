package pages;

import asserts.AssertsClass;
import io.qameta.allure.Step;
import pages.pageParent.BaseProcess;
import pages.pageTitle.PageTitle;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.sleep;

@PageTitle("Кадровик. Общая страница 'Справочники'")
public class ReferencesPage extends BaseProcess {
    {
        proxyCollectionElementForLocator("Таблица. Столбец 'Название'", $$x("//*[@class='legal-entities-catalog-table-body__name-cell']"));
        proxyElementForLocator("Заголовок реестра", "//*[contains(text(), 'Справочник юрлиц')]");
    }

    @Step("Проверяем недоступность Юрлица кадровику без прав на него")
    public ReferencesPage checkReferencesRegistryDoesNotContainUnavailableLegalEntity(String entityName){
        waitAppearanceElement("Заголовок реестра");
        for (var entityNameElement: getCollectionProxy("Таблица. Столбец 'Название'")){
            AssertsClass.assertFalse(entityNameElement.getText().contains(entityName),
                    "Проверяем, что в реестре Юрлиц нет Юрлица " + entityName);
        }
        return this;
    }
}
