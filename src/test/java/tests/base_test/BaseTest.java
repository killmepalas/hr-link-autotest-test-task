package tests.base_test;

import attachmentUtils.ScreenShootAndAttachExtension;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import managers.CustomLogger;
import managers.DriverManager;
import managers.PageManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.*;
import pages.pageParent.BaseProcess;
import pages.pageParent.Page;
import utils.AllureUtils.Layer;
import utils.PropertyReader;

import java.io.File;

import static attachmentUtils.AttachmentUtils.getBytesAnnotationWithArgs;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

@ExtendWith({ScreenShootAndAttachExtension.class})
@Layer("ui")
public class BaseTest {
    public static final String DOMAIN = System.getenv("DOMAIN");

    public BaseProcess baseProcess = loadPage("Base process");
    public LoginPage loginPage = loadPage("Страница Авторизации. LoginPage");
    public DocumentsPage documentsPage = loadPage("Кадровик. Общая страница. 'Документы'");

    public ApplicationPage applicationPage = loadPage("Общая страница. Заявления. ApplicationPage");
    public EmployeesPage employeesPage = loadPage("Кадровик. Общая страница 'Сотрудники'");
    public ReferencesPage referencesPage = loadPage("Кадровик. Общая страница 'Справочники'");


    static {
        Configuration.baseUrl = DOMAIN == null ? PropertyReader.getProperty("hr-link.url") : DOMAIN.equals(" ") ? PropertyReader.getProperty("hr-link.url") : DOMAIN;
        Configuration.timeout = 30000;
        Configuration.fileDownload = FileDownloadMode.FOLDER;
        Configuration.browser = DriverManager.init();
    }

    public <T extends Page> T loadPage(String key) {
        return PageManager
                .getInstance()
                .loadPage(key);
    }

    @AfterEach
    public void close() {
        closeWebDriver();
        getBytesAnnotationWithArgs();

    }

    public void cleanUp() {
        try {
            File downloadsDir = new File("build" + File.separator + "downloads");
            FileUtils.deleteDirectory(downloadsDir);

            if (!downloadsDir.exists()) {
                CustomLogger.debugFormat("Директория 'downloads' успешно удалена.");
            } else {
                CustomLogger.debugFormat("Не удалось полностью удалить директорию 'downloads'.");
            }
        } catch (Exception e) {
            CustomLogger.debugFormat(e.getMessage());
        }
    }



}
