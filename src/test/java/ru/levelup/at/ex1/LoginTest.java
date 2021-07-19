package ru.levelup.at.ex1;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {

    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/selenium/drivers/chrome/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://mail.ru/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
    }

    @Test
    public void openAndLoginMailRuHomePageTest() {
        assertThat(driver.getTitle())
            .as("Title is not matched with the expected title")
            .isEqualTo("Mail.ru: почта, поиск в интернете, новости, игры");

        driver.findElement(By.name("login")).sendKeys("anya.f@internet.ru");

        driver.findElement(By.cssSelector("[data-testid='enter-password']")).click();

        driver.findElement(By.cssSelector("[data-testid='password-input']")).sendKeys("ioiuAa1A*OA3");

        driver.findElement(By.cssSelector("[data-testid='login-to-mail']")).click();

        assertThat("https://e.mail.ru/inbox/?back=1&afterReload=1")
            .as("Something went wrong")
            .isEqualTo("https://e.mail.ru/inbox/?back=1&afterReload=1");

        driver.findElement(By.cssSelector("[href='/compose/']")).click();

        WebElement letterContactTo = driver
            .findElement(By.xpath("//*[@data-type='to']//input"));
        letterContactTo.sendKeys("anya.f@internet.ru");

        WebElement letterSubject = driver
            .findElement(By.xpath("//*[contains(@class, 'subject')]//input"));
        letterSubject.sendKeys("Test theme");

        driver.findElement(By.cssSelector("[title='Сохранить']")).click();
        driver.findElement(By.cssSelector("[title='Закрыть']")).click();
        driver.findElement(By.cssSelector("[href='/drafts/']")).click();

        String actualLetterContactTo = driver
            .findElement(By.xpath("//*[contains(@class, 'llc__item_correspondent')]//span"))
            .getAttribute("title");

        String actualLetterSubject = driver
            .findElement(By.xpath("//*[contains(@class, 'llc__subject')]//span"))
            .getText();

        assertThat(actualLetterContactTo).isEqualTo("anya.f@internet.ru");
        assertThat(actualLetterSubject).isEqualTo("Test theme");

        driver.findElement(By.xpath("//*[contains(@class, 'llc__content')]"))
              .click();
        driver.findElement(By.cssSelector("[title='Отправить']")).click();
        try {
            driver.findElement(By.cssSelector("[title='Закрыть']")).click();
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            driver.findElement(By.cssSelector("[title='Закрыть']")).click();
        }

        driver.findElement(By.cssSelector("[href='/sent/']")).click();
        List<WebElement> sentItems = driver
            .findElements(By.xpath("//*[contains(@class, 'llc__content')]"));
        assertThat(sentItems).hasSizeGreaterThan(0);

        driver.findElement(By.cssSelector("[href='/drafts/']")).click();
        List<WebElement> draftEmptyItems = driver
            .findElements(By.xpath("//*[contains(@class, 'dataset__empty')]"));
        assertThat(draftEmptyItems).hasSizeGreaterThan(0);

        driver.findElement(By.cssSelector("[data-testid='whiteline-account']")).click();

        driver.findElement(By.xpath("//*[text() = 'Выйти']")).click();
    }


    @AfterMethod()
    public void tearDown() {
        driver.quit();
    }
}
