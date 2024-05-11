package attachmentUtils;

import com.codeborne.selenide.junit5.ScreenShooterExtension;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ScreenShootAndAttachExtension extends ScreenShooterExtension {
    @Override
    public void afterTestExecution(ExtensionContext context) {
        try {
            failureStep(context);
        } catch (Throwable e) {
            //Ignore
        }
    }

    private void failureStep(ExtensionContext context) throws Throwable {
        super.afterTestExecution(context);
        if (context.getExecutionException().isPresent()) {
            AttachmentUtils.attachScreenshot();
            AttachmentUtils.attachPageSource();
            throw context.getExecutionException().get();//Thrown to make @Step failed
        }
    }
}
