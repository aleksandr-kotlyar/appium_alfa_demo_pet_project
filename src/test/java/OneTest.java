import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static io.appium.java_client.touch.offset.PointOption.point;
import static java.lang.Thread.sleep;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by alexander.kotlyar on 05.04.2018. 10:31
 */
public class OneTest {

    private static AndroidDriver device;
    private static final String backMenuButton = "//android.view.ViewGroup/android.widget.ImageButton";
    private List<String> instanceList = asList("4", "7", "10");


    @Before
    public void createDriver() {
        device = AppiumDriverCreator.getXiaomiDriver();
        alfaActions = new AlfaActions(device);
    }

    private AlfaActions alfaActions;

    @Test
    public void testLaunchAlfabankApplication() throws InterruptedException, IOException {
        Set<String> contextNames = device.getContextHandles();
        for (String contextName : contextNames) {
            System.out.println(contextName); //prints out something like NATIVE_APP \n WEBVIEW_1
        }

        alfaActions.openDemo();

        alfaActions.viewAllAccounts();

        List<String> balanceInApp = alfaActions.getAllAccountsBalances(instanceList);
        List<String> balanceInFile = alfaActions.readBalancesFromFile("Balance");
        for (int i = 0; i < balanceInFile.size(); i++) {
            assertThat(balanceInApp.get(i)).isEqualTo(balanceInFile.get(i));
        }

        alfaActions.openAccountsAndCards();

        alfaActions.openCard("MasterCard");

        sleep(200);
        new TouchAction(device)
                .press(point(770, 544))
                .moveTo(point(-318, 0))
                .release()
                .perform();

        sleep(200);
        device.findElementByXPath("//*[@resource-id='ru.alfabank.mobile.android:id/card_name_view'][@text='Виртуальная карта']");
        (new TouchAction(device))
                .press(point(624, 385))
                .moveTo(point(150, 8))
                .release()
                .perform();

        device.findElementByXPath(backMenuButton).click();
        device.findElementByXPath(backMenuButton).click();

        device.findElementById("ru.alfabank.mobile.android:id/all_payments_widget_payments").click();
        device.findElementByXPath("//*[@resource-id='ru.alfabank.mobile.android:id/recipient_title'][@instance='2']").click();

        device.findElementById("ru.alfabank.mobile.android:id/amount_edit_text").sendKeys("100");
        device.findElementById("ru.alfabank.mobile.android:id/perform_action").click();

        device.findElementById("ru.alfabank.mobile.android:id/cancel_confirm").click();
        device.findElementByXPath(backMenuButton).click();
        device.findElementByXPath(backMenuButton).click();


        Dimension dimensions = device.manage().window().getSize();
        int scrollHeightStart = (int) (dimensions.getHeight() * 0.8);
        int scrollHeightEnd = (int) (dimensions.getHeight() * 0.5);
        int widthScrollPoint = (int) (dimensions.getWidth() * 0.5);
        System.out.println("widthScrollPoint = " + widthScrollPoint);
        System.out.println("scrollHeightStart = " + scrollHeightStart);
        System.out.println("scrollHeightEnd = " + scrollHeightEnd);

        (new TouchAction(device))
                .press(point(widthScrollPoint, scrollHeightStart))
                .moveTo(point(0, -scrollHeightEnd))
                .release()
                .perform();
    }
}
