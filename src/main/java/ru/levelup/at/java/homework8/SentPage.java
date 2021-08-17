package ru.levelup.at.java.homework8;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SentPage extends AbstractBasePage {

    @FindBy(css = "[href='/compose/']")
    private WebElement composeButton;

    @FindBy(css = "[href='/drafts/']")
    private WebElement draftsButton;

    @FindBy(css = "[href='/1/']")
    private WebElement testButton;

    @FindBy(xpath = "//*[contains(@class, 'llc__content')]")
    private List<WebElement> letterItems;

    @FindBy(xpath = "//*[contains(@class, 'llc__item_correspondent')]//span")
    private List<WebElement> letterContactToItems;

    @FindBy(xpath = "//*[contains(@class, 'llc__subject')]//span")
    private List<WebElement> letterSubjectItems;

    @FindBy(xpath = "//*[contains(@class, 'llc__snippet')]//span")
    private List<WebElement> letterTextItems;

    @FindBy(css = "[data-testid='whiteline-account']")
    private WebElement accountButton;

    @FindBy(xpath = "//*[text() = 'Выйти']")
    private WebElement logoutButton;


    public SentPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        open("");
    }

    public List<WebElement> getLetterItems() {
        return letterItems;
    }

    public String getLetterContactTo(int letterIndex) {
        var letterContactTo = letterContactToItems.get(letterIndex);
        return letterContactTo.getAttribute("title");
    }

    public String getLetterSubject(int letterIndex) {
        var letterSubject = letterSubjectItems.get(letterIndex);
        return letterSubject.getText();
    }

    public String getLetterText(int letterIndex) {
        var letterText = letterTextItems.get(letterIndex);
        return letterText.getText();
    }

    public DraftsPage clickDraftsButton() {
        draftsButton.click();
        return new DraftsPage(driver);
    }

    public TestPage clickTestButton() {
        testButton.click();
        return new TestPage(driver);
    }

    public void logout() {
        accountButton.click();
        logoutButton.click();
    }

}
