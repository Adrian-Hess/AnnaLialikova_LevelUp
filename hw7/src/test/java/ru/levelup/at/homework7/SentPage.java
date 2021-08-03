package ru.levelup.at.homework7;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SentPage extends AbstractMainTest{

    @FindBy(css = "[href='/drafts/']")
    private WebElement draftsBtn;

    @FindBy(css = "[href='/compose/']")
    private WebElement compose;

    @FindBy(css = "[href='/sent/']")
    private WebElement sent;

    @FindBy(css = "[href='/1/']")
    private WebElement testBttn;

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

    protected SentPage(WebDriver webDriver) {
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

    public String getLetterText(int letterIndex){
        var letterText = letterTextItems.get(letterIndex);
        return letterText.getText();
    }

    public TestPage clickTestBtn(){
        testBttn.click();
        return new TestPage(webDriver);
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
