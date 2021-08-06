package ru.levelup.at.ex3;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.levelup.at.BaseTest;

public class DeleteMessageTest extends BaseTest {

    @Test
    public void openAndDeleteMsgTest() {
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
        letterText.sendKeys("Delete Text");

        driver.findElement(By.cssSelector("[title='Отправить']")).click();
        try {
            driver.findElement(By.cssSelector("[title='Закрыть']")).click();
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            driver.findElement(By.cssSelector("[title='Закрыть']")).click();
        }

        driver.findElement(By.cssSelector("[href='/inbox/']")).click();

        driver.findElement(By.xpath("//*[contains(@class, 'metathread__contain')]"))
              .click();

        String actualInboxLetterContactTo = driver
            .findElement(By.xpath("//*[contains(@class, 'llc__item_correspondent')]//span"))
            .getAttribute("title");

        String actualInboxLetterSubject = driver
            .findElement(By.xpath("//*[contains(@class, 'llc__subject')]//span"))
            .getText();

        String actualInboxLetterText = driver
            .findElement(By.xpath("//*[contains(@class, 'llc__snippet')]//span"))
            .getText();

        assertThat(actualInboxLetterContactTo).containsIgnoringCase("anya.f@internet.ru");
        assertThat(actualInboxLetterSubject).containsIgnoringCase("Test");
        assertThat(actualInboxLetterText).containsIgnoringCase("Delete Text");


        driver.findElement(By.xpath("//*[contains(@class, 'metathread__contain')]//div[@class='checkbox']"))
              .click();
        driver.findElement(By.cssSelector("[title='Удалить']")).click();
        driver.findElement(By.xpath("//*[contains(@class, 'layer__submit-button')]//span[text() = 'Очистить']"))
              .click();

        driver.findElement(By.cssSelector("[href='/trash/']")).click();
        String actualDeleteLetterContactTo = driver
            .findElement(By.xpath("//*[contains(@class, 'llc__item_correspondent')]//span"))
            .getAttribute("title");

        String actualDeleteLetterSubject = driver
            .findElement(By.xpath("//*[contains(@class, 'llc__subject')]//span"))
            .getText();

        String actualDeleteLetterText = driver
            .findElement(By.xpath("//*[contains(@class, 'llc__snippet')]//span"))
            .getText();

        assertThat(actualDeleteLetterContactTo).containsIgnoringCase("anya.f@internet.ru");
        assertThat(actualDeleteLetterSubject).containsIgnoringCase("Test");
        assertThat(actualDeleteLetterText).containsIgnoringCase("Delete Text");

        driver.findElement(By.cssSelector("[data-testid='whiteline-account']")).click();
        driver.findElement(By.xpath("//*[text() = 'Выйти']")).click();
    }
}
