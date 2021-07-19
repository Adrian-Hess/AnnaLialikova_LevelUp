package ru.levelup.at.ex2;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.Set;
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

public class TestMessageTest {
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
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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

        WebElement actualLogin = driver.findElement(By.cssSelector("[data-testid='whiteline-account']"));
        String actualLoginText = actualLogin.getAttribute("aria-label");

        assertThat(actualLoginText).isEqualTo("anya.f@internet.ru");

        driver.findElement(By.cssSelector("[href='/compose/']")).click();

        WebElement letterContactTo = driver
            .findElement(By.xpath("//*[@data-type='to']//input"));
        letterContactTo.sendKeys("anya.f@internet.ru");

        WebElement letterSubject = driver
            .findElement(By.xpath("//*[contains(@class, 'subject')]//input"));
        letterSubject.sendKeys("Test");

        WebElement letterText = driver
            .findElement(By.xpath("//*[@role='textbox']"));
        letterText.sendKeys("Test Text");

        driver.findElement(By.cssSelector("[title='Отправить']")).click();
        try {
            driver.findElement(By.cssSelector("[title='Закрыть']")).click();
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            driver.findElement(By.cssSelector("[title='Закрыть']")).click();
        }

        driver.findElement(By.cssSelector("[href='/sent/']")).click();

        String actualSentLetterContactTo = driver
            .findElement(By.xpath("//*[contains(@class, 'llc__item_correspondent')]//span"))
            .getAttribute("title");

        String actualSentLetterSubject = driver
            .findElement(By.xpath("//*[contains(@class, 'llc__subject')]//span"))
            .getText();

        String actualSentLetterText = driver
            .findElement(By.xpath("//*[contains(@class, 'llc__snippet')]//span"))
            .getText();

        assertThat(actualSentLetterContactTo).isEqualTo("anya.f@internet.ru");
        assertThat(actualSentLetterSubject).isEqualTo("Self: Test");
        assertThat(actualSentLetterText).isEqualTo("Test Text");


        driver.findElement(By.cssSelector("[href='/1/']")).click();
        String actualFolderLetterContactTo = driver
            .findElement(By.xpath("//*[contains(@class, 'llc__item_correspondent')]//span"))
            .getAttribute("title");

        String actualFolderLetterSubject = driver
            .findElement(By.xpath("//*[contains(@class, 'llc__subject')]//span"))
            .getText();

        String actualFolderLetterText = driver
            .findElement(By.xpath("//*[contains(@class, 'llc__snippet')]//span"))
            .getText();

        assertThat(actualFolderLetterContactTo).containsIgnoringCase("anya.f@internet.ru");
        assertThat(actualFolderLetterSubject).containsIgnoringCase("Test");
        assertThat(actualFolderLetterText).containsIgnoringCase("Test Text");
        driver.findElement(By.cssSelector("[data-testid='whiteline-account']")).click();

        driver.findElement(By.partialLinkText("Выйти")).click();
    }
    }