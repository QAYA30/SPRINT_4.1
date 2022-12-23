import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobjects.ScooterHomePage;


@RunWith(Parameterized.class)
public class ScooterTestAccordion {

    public ScooterTestAccordion(int number, String message) {
        this.number = number;
        this.message = message;
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
    }

    //Добавление полей
    private final int number;
    private final String message;

    @Parameterized.Parameters(name = "порядковый номер = {0}, сообщение = {1}")
    public static Object[][] getCredentials() {

        //Генерация порядкового номера и сообщения
        return new Object[][]{
                {1, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {2, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {3, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {4, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {5, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {6, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {7, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {8, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    private WebDriver driver;

    @Test
    public void checkClickedOpensText() {

        // драйвер Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments();
        driver = new ChromeDriver(options);

        // объект класса главной страницы
        ScooterHomePage objHomePage = new ScooterHomePage(driver);
        objHomePage.openGetHomePage();
        objHomePage.scrollQuestionsImportant();
        objHomePage.clickAccordionArrow(number);
        objHomePage.waitTextAppear(message, number);
    }

    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}
