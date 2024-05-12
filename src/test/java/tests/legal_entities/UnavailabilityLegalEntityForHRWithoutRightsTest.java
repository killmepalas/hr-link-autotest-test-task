package tests.legal_entities;

import io.qameta.allure.Feature;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import tests.base_test.BaseTest;
import user.UserFactory;

@Feature("Проверка недоступности юрлица, на которое у кадровика нет прав (positive)")
public class UnavailabilityLegalEntityForHRWithoutRightsTest extends BaseTest {


    @Test
    @DisplayName("Проверка отображения документов недоступного сотрудника")
    public void verifyUnavailabilityOfEmployeeDocuments(){
        baseProcess.precondition(UserFactory.withHRPermissionWithoutLegalEntityRight());
        baseProcess.closeTelegramDialog();
        documentsPage.checkDocumentsRegistryDoesNotContainDocumentsOfUnavailableEmployee("Орлов Д.");
    }

    @Test
    @DisplayName("Проверка отображения документов недоступного Юрлица")
    public void verifyUnavailabilityOfLegalEntityDocuments(){
        baseProcess.precondition(UserFactory.withHRPermissionWithoutLegalEntityRight());
        baseProcess.closeTelegramDialog();
        documentsPage.checkDocumentsRegistryDoesNotContainDocumentsOfUnavailableLegalEntity("ООО \"КОТ\"");
    }

    @Test
    @DisplayName("Проверка отображения заявлений недоступного сотрудника")
    public void verifyUnavailabilityOfEmployeeApplications(){
        baseProcess.precondition(UserFactory.withHRPermissionWithoutLegalEntityRight());
        baseProcess.closeTelegramDialog();
        baseProcess.openApplicationTab();
        applicationPage.checkApplicationRegistryDoesNotContainUnavailableApplications("Орлов Д.");

    }
}
