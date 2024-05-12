package pages;

import io.qameta.allure.Step;
import managers.PageManager;
import org.openqa.selenium.By;
import pages.pageParent.Page;
import pages.pageTitle.PageTitle;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

@PageTitle("Блок Меню")
public class NavigationBar extends Page {

    {
        proxyElementForLocator("Меню 'Заявления'", "//*[contains(@data-qa, '-app-nav-applications-registry-link')]");
        proxyElementForLocator("Меню 'Сотрудники'", "//a[contains(@data-qa, 'app-nav-employees-registry-link')]");
        proxyElementForLocator("Меню 'Справочники'", "//*[@data-qa='hr-app-nav-catalogs-link']");
        proxyElementForLocator("Фильтры в 'Заявления'", "//applications-registry-table-filter-row");
    }

    @Step("Открытие вкладки 'Заявления'")
    public ApplicationPage openApplicationTab() {
        click("Меню 'Заявления'");
        waitAppearanceElement("Фильтры в 'Заявления'");
        return PageManager.getInstance().loadPage("Общая страница. Заявления. ApplicationPage");
    }

    @Step("Открытие вкладки 'Сотрудники'")
    public EmployeesPage openEmployeeTab() {
        click("Меню 'Сотрудники'");
        return PageManager.getInstance().loadPage("Кадровик. Общая страница 'Сотрудники'");
    }

    @Step("Открытие вкладки 'Справочники'")
    public ReferencesPage openReferencesTab() {
        click("Меню 'Справочники'");
        return PageManager.getInstance().loadPage("Кадровик. Общая страница 'Справочники'");
    }

}
