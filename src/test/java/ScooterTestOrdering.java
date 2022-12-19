import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.cssSelector;
import pageobjects.ScooterOrderPage;

import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class ScooterTestOrdering {

    WebDriver driver = new ChromeDriver();

    final By buttonAddress;
    final String name;
    final String family;
    final String address;
    final String numberPhone;
    final String comment;

    public ScooterTestOrdering(By buttonAddress, String name, String family, String address, String numberPhone, String comment) {
        this.buttonAddress = buttonAddress;
        this.name = name;
        this.family = family;
        this.address = address;
        this.numberPhone = numberPhone;
        this.comment = comment;
    }
    @Parameterized.Parameters
    public static Object[][] Parameters() {
        return new Object[][] {
                {cssSelector("div.Header_Nav__AGCXC > button.Button_Button__ra12g"), "Иванов", "Александр", "Москва Волгоградский проспект дом 32 квартира 19", "89955306411", "Позвонить за час до доставки"},
                {cssSelector("div.Home_RoadMap__2tal_ > div.Home_FinishButton__1_cWm > button"), "Иванов", "Александр", "Москва Волгоградский проспект дом 32 квартира 19", "89955306411", "Позвонить за час до доставки"}
        };
    }
    @Test
    public void checkOrderFromHeader_success() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        ScooterOrderPage scooterOrderPagePage = new ScooterOrderPage(driver);
        scooterOrderPagePage.startPage();
        scooterOrderPagePage.findCheckAndClickOrderButton(buttonAddress);
        scooterOrderPagePage.userName(name);
        scooterOrderPagePage.userSurname(family);
        scooterOrderPagePage.userAddress(address);
        scooterOrderPagePage.userPhone(numberPhone);
        scooterOrderPagePage.metroStation();
        scooterOrderPagePage.metroStationChoice();
        scooterOrderPagePage.nextPageButton();
        scooterOrderPagePage.calendarDate();
        scooterOrderPagePage.rentalDuration();
        scooterOrderPagePage.scooterColour();
        scooterOrderPagePage.userComment(comment);
        scooterOrderPagePage.orderButton();
        scooterOrderPagePage.confirmButton();
        assertTrue("ERROR", scooterOrderPagePage.CreatedOrder());
    }

    @After
    public void closedBrowser() {
        driver.quit();
    }
}
