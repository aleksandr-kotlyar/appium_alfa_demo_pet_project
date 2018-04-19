import io.appium.java_client.android.AndroidDriver;

/**
 * Created by alexander.kotlyar on 05.04.2018. 10:34
 */
public class AppActions {
    AndroidDriver driver;
    String deviceName;

    public AppActions(AndroidDriver driver, String deviceName) {
        this.driver = driver;
        this.deviceName = deviceName;
    }
}
