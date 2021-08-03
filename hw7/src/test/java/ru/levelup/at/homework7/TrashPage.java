package ru.levelup.at.homework7;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TrashPage extends AbstractMainTest{
    @FindBy(css = "[href='/drafts/']")
    private WebElement draftsBtn;

    @FindBy(css = "[href='/compose/']")
    private WebElement compose;

    @FindBy(css = "[href='/1/']")
    private WebElement testBttn;

    @FindBy(css = "[title='Отправить']")
    private WebElement sendBtn;

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

    public TrashPage(WebDriver webDriver) {
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

    public TestPage clickTestBtn(){
        testBttn.click();
        return new TestPage(webDriver);
    }

    public void logout(){
        accBtn.click();
        exitBtn.click();
    }
}
