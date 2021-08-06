package ru.levelup.at.homework7;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractMainTest{

    @FindBy(name = "login")
    private WebElement login;

    @FindBy(css = "[data-testid='enter-password']")
    private WebElement loginBtn;

    @FindBy(name = "password")
    private WebElement pass;

    @FindBy(css = "[data-testid='login-to-mail']")
    private WebElement passBtn;

    @FindBy(css = "[data-testid='whiteline-account']")
    private WebElement accBtn;

    @FindBy(xpath = "//*[text() = 'Выйти']")
    private WebElement exitBtn;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openMail(){
        openMail("");
    }

    public void logIn(String log, String pas){
        login.sendKeys(log);
        loginBtn.click();
        pass.sendKeys(pas);
        passBtn.click();
    }

    public void logout(){
        accBtn.click();
        exitBtn.click();
    }

    public String getActualLogin(){
        return accBtn.getAttribute("aria-label");
    }
}
