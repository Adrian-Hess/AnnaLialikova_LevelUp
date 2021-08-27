package ru.levelup.at.java.homework8;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractBasePage {

    private static final String BASE_URL = "https://mail.ru";

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected AbstractBasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    protected void open(String url) {
        driver.navigate().to(BASE_URL + url);
    }

}
