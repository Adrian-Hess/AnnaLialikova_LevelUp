package ru.levelup.at.java.homework8;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InboxPage extends AbstractBasePage {

    @FindBy(css = "[href='/compose/']")
    private WebElement composeButton;

    @FindBy(css = "[href='/drafts/']")
    private WebElement draftsButton;

    @FindBy(css = "[href='/sent/']")
    private WebElement sentButton;

    @FindBy(css = "[href='/1/']")
    private WebElement testButton;

    @FindBy(css = "[href='/trash/']")
    private WebElement trashButton;

    @FindBy(xpath = "//*[@data-type='to']//input")
    private WebElement letterContactToInput;

    @FindBy(xpath = "//*[contains(@class, 'subject')]//input")
    private WebElement letterSubjectInput;

    @FindBy(xpath = "//*[@role='textbox']")
    private WebElement letterTextInput;

    @FindBy(css = "[title='Сохранить']")
    private WebElement saveButton;

    @FindBy(css = "[title='Отправить']")
    private WebElement sendButton;

    @FindBy(css = "[title='Закрыть']")
    private WebElement closeButton;

    @FindBy(xpath = "//*[contains(@class, 'llc__content')]")
    private List<WebElement> letterItems;

    @FindBy(xpath = "//*[contains(@class, 'llc__item_correspondent')]//span")
    private List<WebElement> letterContactToItems;

    @FindBy(xpath = "//*[contains(@class, 'llc__subject')]//span")
    private List<WebElement> letterSubjectItems;

    @FindBy(xpath = "//*[contains(@class, 'llc__snippet')]//span")
    private List<WebElement> letterTextItems;

    @FindBy(xpath = "//*[contains(@class, 'metathread__contain')]")
    private WebElement selfLetter;

    @FindBy(xpath = "//*[contains(@class, 'metathread__contain')]//div[@class='checkbox']")
    private WebElement selfLetterCheckbox;

    @FindBy(css = "[title='Удалить']")
    private WebElement deleteButton;

    @FindBy(xpath = "//*[contains(@class, 'layer__submit-button')]//span[text() = 'Очистить']")
    private WebElement clearButton;

    @FindBy(css = "[data-testid='whiteline-account']")
    private WebElement accountButton;

    @FindBy(xpath = "//*[text() = 'Выйти']")
    private WebElement logoutButton;

    public InboxPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        open("");
    }

    public void createDraftLetter(String contactTo, String letterSubject, String letterText) {
        composeButton.click();
        letterContactToInput.sendKeys(contactTo);
        letterSubjectInput.sendKeys(letterSubject);
        letterTextInput.sendKeys(letterText);
        saveButton.click();
        closeButton.click();
    }

    public void createLetter(String contactTo, String letterSubject, String letterText) {
        composeButton.click();
        letterContactToInput.sendKeys(contactTo);
        letterSubjectInput.sendKeys(letterSubject);
        letterTextInput.sendKeys(letterText);
        sendButton.click();
        try {
            closeButton.click();
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            closeButton.click();
        }
    }

    public void getSelfLetters() {
        selfLetter.click();
    }

    public void deleteSelfLetters() {
        selfLetterCheckbox.click();
        deleteButton.click();
        clearButton.click();
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

    public SentPage clickSentButton() {
        sentButton.click();
        return new SentPage(driver);
    }

    public DraftsPage clickDraftsButton() {
        draftsButton.click();
        return new DraftsPage(driver);
    }

    public TestPage clickTestButton() {
        testButton.click();
        return new TestPage(driver);
    }

    public TrashPage clickTrashButton() {
        trashButton.click();
        return new TrashPage(driver);
    }

    public void logout() {
        accountButton.click();
        logoutButton.click();
    }


}
