package ru.levelup.at.homework7;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractMainTest {
    private static final String URL = "https://mail.ru";

    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;

    protected void openMail(String url){
        webDriver.navigate().to(URL + url);
    }

    protected AbstractMainTest (WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
        webDriverWait = new WebDriverWait(webDriver, 15);
    }
}
