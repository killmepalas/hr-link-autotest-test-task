package utils;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.LabelAnnotation;

import java.lang.annotation.*;

public class AllureUtils {

    @Documented
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.TYPE})
    @LabelAnnotation(name = "layer")
    public @interface Layer {
        String value();
    }

    @Documented
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.TYPE})
    @LabelAnnotation(name = "actor")
    public @interface Actor {
        String[] value();
    }

    @Documented
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.TYPE})
    @LabelAnnotation(name = "object")
    public @interface Object {
        String value();
    }

    @Documented
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.TYPE})
    @LabelAnnotation(name = "action")
    public @interface Action {
        String value();
    }

    @Documented
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.TYPE})
    @LabelAnnotation(name = "function_property")
    public @interface FunctionProperty {
        String value();
    }

    public static void addAttachments(
            final byte[] actual,
            final byte[] expected,
            final FileExtension attachmentExtension
    ) {
        final AllureLifecycle lifecycle = Allure.getLifecycle();
        lifecycle.addAttachment(
                "Актуально",
                attachmentExtension.getMimeType(),
                attachmentExtension.getFormat(),
                actual
        );
        lifecycle.addAttachment(
                "Ожидаемо",
                attachmentExtension.getMimeType(),
                attachmentExtension.getFormat(),
                expected
        );
        lifecycle.stopStep();
    }
}
