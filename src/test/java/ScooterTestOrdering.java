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


    final String name;
    final String surname;
    final String address;
    final String phone;
    final String comment;
    private final int typeButton;

    public ScooterTestOrdering(String name, String surname, String address, String phone, String comment, int typeButton) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.comment = comment;
        this.typeButton = typeButton;
    }
    @Parameterized.Parameters
    public static Object[][] Parameters() {
        return new Object[][] {
                {"Осипов", "Дмитрий", "Щелково Пролетарский проспект дом 3 квартира 159", "89250914255", "Позвонить за час до доставки",1},
                {"Осипов", "Дмитрий", "Щелково Пролетарский проспект дом 3 квартира 159", "89250914255", "Позвонить за час до доставки",2}
        };
    }
    @Test
    public void checkOrderFromHeader_success() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        ScooterOrderPage scooterOrderPagePage = new ScooterOrderPage(driver);
        scooterOrderPagePage.startPage();
        scooterOrderPagePage.findCheckAndClickOrderButton(typeButton);
        scooterOrderPagePage.enterValueOrder(name, surname, address, phone, comment);
        assertTrue("ERROR", scooterOrderPagePage.createdOrder());
    }

    @After
    public void closedBrowser() {
        driver.quit();
    }
}