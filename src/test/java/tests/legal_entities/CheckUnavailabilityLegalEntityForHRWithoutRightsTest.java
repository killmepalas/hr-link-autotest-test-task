package tests.legal_entities;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import tests.base_test.BaseTest;
import user.UserFactory;

@Feature("Проверка недоступности юрлица, на которое у кадровика нет прав (positive)")
public class CheckUnavailabilityLegalEntityForHRWithoutRightsTest extends BaseTest {

    @Test
    @DisplayName("Проверка отображения документов недоступного сотрудника")
    public void checkUnavailabilityOfEmployeeDocuments(){
        baseProcess.precondition(UserFactory.withHRPermissionWithoutLegalEntityRight());
        baseProcess.closeTelegramDialog();
        documentsPage.checkDocumentsRegistryDoesNotContainDocumentsOfUnavailableEmployee("Орлов Д.");
        baseProcess.postcondition();
    }

    @Test
    @DisplayName("Проверка отображения документов недоступного Юрлица")
    public void checkUnavailabilityOfLegalEntityDocuments(){
        baseProcess.precondition(UserFactory.withHRPermissionWithoutLegalEntityRight());
        baseProcess.closeTelegramDialog();
        documentsPage.checkDocumentsRegistryDoesNotContainDocumentsOfUnavailableLegalEntity("ООО \"КОТ\"");
        baseProcess.postcondition();
    }

    @Test
    @DisplayName("Проверка отображения заявлений недоступного сотрудника")
    public void checkUnavailabilityOfEmployeeApplications(){
        baseProcess.precondition(UserFactory.withHRPermissionWithoutLegalEntityRight());
        baseProcess.closeTelegramDialog();
        baseProcess.openApplicationTab();
        applicationPage.checkApplicationRegistryDoesNotContainUnavailableApplications("Орлов Д.");
        baseProcess.postcondition();
    }

    @Test
    @DisplayName("Проверка отображения недоступного сотрудника в реестре сотрудников")
    public void checkUnavailabilityOfEmployeeInEmployeeRegistry(){
        baseProcess.precondition(UserFactory.withHRPermissionWithoutLegalEntityRight());
        baseProcess.closeTelegramDialog();
        baseProcess.openEmployeeTab();
        employeesPage.checkEmployeesRegistryDoesNotContainUnavailableEmployee("Орлов Д.");
        baseProcess.postcondition();
    }

    @Test
    @DisplayName("Проверка отображения недоступного ЮЛ в реестре справочников")
    public void checkUnavailabilityOfLegalEntityInReferences(){
        baseProcess.precondition(UserFactory.withHRPermissionWithoutLegalEntityRight());
        baseProcess.closeTelegramDialog();
        baseProcess.openReferencesTab();
        referencesPage.checkReferencesRegistryDoesNotContainUnavailableLegalEntity("ООО \"КОТ\"");
        baseProcess.postcondition();
    }
}
