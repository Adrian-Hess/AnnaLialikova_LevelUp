package ru.levelup.at.java.homework8;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractBasePage {

    @FindBy(name = "login")
    private WebElement loginInput;

    @FindBy(css = "[data-testid='enter-password']")
    private WebElement loginInputButton;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(css = "[data-testid='login-to-mail']")
    private WebElement passwordInputButton;

    @FindBy(css = "[data-testid='whiteline-account']")
    private WebElement accountButton;

    @FindBy(xpath = "//*[text() = 'Выйти']")
    private WebElement logoutButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        open("");
    }

    public void login(String login, String password) {
        loginInput.sendKeys(login);
        loginInputButton.click();
        passwordInput.sendKeys(password);
        passwordInputButton.click();
    }

    public void logout() {
        accountButton.click();
        logoutButton.click();
    }

    public String getActualLoginText() {
        return accountButton.getAttribute("aria-label");
    }

}
