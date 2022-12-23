package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScooterHomePage {
    private final String linkHomePage = "https://qa-scooter.praktikum-services.ru/";
    private WebDriver driver;

    // элемент "вопросы о важном"
    private By questionsImportant = By.xpath(".//div[text()='Вопросы о важном']");

    // элемент "аккордеона"
    private By elementAccordion;

    // текст элемента аккордеона
    private By elementTextAccordion;

    // Верхняя кнопка "заказать"
    private By topButtonOrder = By.xpath(".//button[@class='Button_Button__ra12g'] ");

    // локатор для верхней кнопки заказать
    private By bottomButtonOrder = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM'] ");

    // конструктор класса
    public ScooterHomePage(WebDriver driver) {
        this.driver = driver;
    }

    // переход на тестовую страницу
    public void openGetHomePage() {
        driver.get(linkHomePage);
    }

    // прокрутка до элемента 'вопросы о важном'
    public void scrollQuestionsImportant() {
        WebElement element = driver.findElement(questionsImportant);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    // нажать на стрелочку элемента элемента аккордеона
    public void clickAccordionArrow(int number) {
        if (number >= 1) {
            elementAccordion = By.id("accordion__heading-" + (number - 1));
        }
        driver.findElement(elementAccordion).click();
    }

    // проверка появления  текста
    public void waitTextAppear(String textAccordion, int number) {
        if (number >= 1) {
            elementTextAccordion = By.id("accordion__panel-" + (number - 1));
        }
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.textToBePresentInElementLocated(elementTextAccordion, textAccordion));
    }

    // метод для нажатия на вкрхней кнопку заказать
    public void clickTopButtonOrder() {
        driver.findElement(topButtonOrder).click();
    }

    // метод для нажатия на нижнию кнопку заказать
    public void clickBottomButtonOrder() {
        driver.findElement(bottomButtonOrder).click();
    }

    // прокрутка до нижней кнопки Заказать
    public void scrollBottomButtonOrder() {
        WebElement element = driver.findElement(bottomButtonOrder);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
}
