package managers;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


public class DriverManager {
    public static final boolean browser = System.getenv("BROWSER") != null;

    public static String init() {
        final var capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        if (browser) {
            switch (System.getenv("BROWSER")) {
                case "chrome":
                    return chromeSetup(capabilities);
                case "firefox":
                    return firefoxSetup(capabilities);
                default:
                    throw new IllegalStateException("Проверьте передаваемые параметры браузера");
            }
        }
        return chromeSetup(capabilities);
    }

    public static String chromeSetup(DesiredCapabilities capabilities) {
        CustomLogger.debug("Инициализация и запуск Chrome");
        var optionsChrome = new ChromeOptions();
        optionsChrome.setPageLoadStrategy(PageLoadStrategy.EAGER);
        capabilities.setCapability(ChromeOptions.CAPABILITY, optionsChrome);
        capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        Configuration.browserCapabilities = capabilities;

        return "chrome";
    }

    public static String firefoxSetup(DesiredCapabilities capabilities) {
        CustomLogger.debug("Инициализация и запуск Firefox");
        final var optionsFirefox = new FirefoxOptions();
        final var profileFirefox = new FirefoxProfile();
        optionsFirefox.setProfile(profileFirefox);
        optionsFirefox.setPageLoadStrategy(PageLoadStrategy.EAGER);
        capabilities.merge(optionsFirefox);
        Configuration.browserCapabilities = capabilities;
        return "firefox";
    }


}
