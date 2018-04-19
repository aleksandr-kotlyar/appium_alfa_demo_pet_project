import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by alexander.kotlyar on 05.04.2018. 9:15
 */
public class AppiumDriverCreator {
    private static AndroidDriver xiaomi;
    private static final String HUB_URL = "http://127.0.0.1:4723/wd/hub";


    public static AndroidDriver getXiaomiDriver() {
        if(xiaomi == null)
            createXiaomiDriver(getXiaomiAlfabankDesiredCapabilities());
        return xiaomi;
    }

    private static void createXiaomiDriver(DesiredCapabilities capabilities) {
        try {
            xiaomi = new AndroidDriver(new URL(HUB_URL), capabilities);
            xiaomi.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Unknown type device");
        }
    }

    private static DesiredCapabilities getXiaomiAlfabankDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Name");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("udid", "YOURDEVICEUDID");

        capabilities.setCapability("appPackage", "ru.alfabank.mobile.android");
        capabilities.setCapability("appWaitActivity",".presentation.feature.splash.activity.SplashActivity");
        capabilities.setCapability("appActivity", ".presentation.feature.splash.activity.SplashActivity");

//        capabilities.setCapability("automationName","UIAutomator2");
        capabilities.setCapability("noReset", "true");

        capabilities.setCapability("newCommandTimeout", 10000);
        capabilities.setCapability("systemPort", 8205);

        return capabilities;
    }
}
