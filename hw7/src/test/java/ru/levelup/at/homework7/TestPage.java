package ru.levelup.at.homework7;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TestPage extends AbstractMainTest{

    @FindBy(css = "[href='/drafts/']")
    private WebElement draftsBtn;

    @FindBy(css = "[href='/compose/']")
    private WebElement compose;

    @FindBy(css = "[href='/sent/']")
    private WebElement sent;

    @FindBy(xpath = "//*[@data-type='to']//input")
    private WebElement letterContactTo;

    @FindBy(xpath = "//*[contains(@class, 'subject')]//input")
    private WebElement letterSubject;

    @FindBy(xpath = "//*[@role='textbox']")
    private WebElement letterText;

    @FindBy(css = "[title='Сохранить']")
    private WebElement saveBtn;

    @FindBy(css = "[title='Отправить']")
    private WebElement sendBtn;

    @FindBy(css = "[title='Закрыть']")
    private WebElement closeBtn;

    @FindBy(xpath = "//*[contains(@class, 'llc__subject')]//span")
    private List<WebElement> letterSubjectItems;

    @FindBy(xpath = "//*[contains(@class, 'llc__item_correspondent')]//span")
    private List<WebElement> letterContactToItems;

    @FindBy(xpath = "//*[contains(@class, 'llc__content')]")
    private List<WebElement> letterItems;

    @FindBy(xpath = "//*[contains(@class, 'llc__snippet')]//span")
    private List<WebElement> letterTextItems;

    @FindBy(css = "[data-testid='whiteline-account']")
    private WebElement accBtn;

    @FindBy(xpath = "//*[text() = 'Выйти']")
    private WebElement exitBtn;

    public TestPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openMail(){
        openMail("");
    }

    public String getLetterContactTo(int letterIndex){
        var letterContactTo = letterContactToItems.get(letterIndex);
        return letterContactTo.getAttribute("title");
    }

    public String getLetterSubject(int letterIndex){
        var letterSubject = letterSubjectItems.get(letterIndex);
        return letterSubject.getAttribute("title");
    }

    public SentPage clicksentBtn(){
        sendBtn.click();
        return new SentPage(webDriver);
    }

    public DraftsPage clickDraftsBttn(){
        draftsBtn.click();
        return new DraftsPage(webDriver);
    }

    public void logout(){
        accBtn.click();
        exitBtn.click();
    }
}
