import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

/**
 * Created by alexander.kotlyar on 08.04.2018. 9:35
 */
public class AlfaActions {

    AlfaActions(AndroidDriver driver) {
        device = driver;
    }


    private static AndroidDriver device;
    private static final String xpathBalance = "//*[@resource-id='ru.alfabank.mobile.android:id/widget_account_item_balance'][@instance='%s']";


    public void openCard(String card) {
        device.findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + card + "\")").click();
    }

    public void openAccountsAndCards() {
        device.findElementById("ru.alfabank.mobile.android:id/cards_widget_text").click();
    }

    public List<String> readBalancesFromFile(String fileName) throws IOException {
        String file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getFile()).getAbsolutePath();
        return Files.readAllLines(Paths.get(file));
    }

    public List<String> getAllAccountsBalances(List<String> balanceInstances) {
        List<String> balanceInApplication = new ArrayList<>();
        for (String instance : balanceInstances) {
            balanceInApplication.add(device.findElementByXPath(format(xpathBalance, instance)).getText());
        }
        return balanceInApplication;
    }

    public void viewAllAccounts() {
        device.findElementById("ru.alfabank.mobile.android:id/banking_account_item_header").click();
    }

    public void openDemo() {
        device.findElementById("ru.alfabank.mobile.android:id/sign_up_demo").click();
    }

}
