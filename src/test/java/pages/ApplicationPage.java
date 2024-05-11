package pages;

import pages.pageParent.BaseProcess;
import pages.pageTitle.PageTitle;

import static com.codeborne.selenide.Selenide.$$x;

@PageTitle("Общая страница. Заявления. ApplicationPage")
public class ApplicationPage extends BaseProcess {
    {
        proxyCollectionElementForLocator("Таблица. Столбец 'Юрлицо'", $$x("//div[@class='documents-registry-desktop-table-row__legal-entity-cell']"));
    }
}
