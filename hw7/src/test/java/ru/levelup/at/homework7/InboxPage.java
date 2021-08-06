package ru.levelup.at.homework7;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InboxPage extends AbstractMainTest {

    @FindBy(css = "[href='/drafts/']")
    private WebElement draftsBtn;

    @FindBy(css = "[href='/trash/']")
    private WebElement trashBtn;

    @FindBy(css = "[href='/compose/']")
    private WebElement compose;

    @FindBy(css = "[href='/sent/']")
    private WebElement sent;

    @FindBy(css = "[href='/1/']")
    private WebElement testBttn;

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

    @FindBy(css = "[title='Удалить']")
    private WebElement deleteBtn;

    @FindBy(xpath = "//*[contains(@class, 'layer__submit-button')]//span[text() = 'Очистить']")
    private WebElement clearBtn;

    @FindBy(xpath = "//*[contains(@class, 'metathread__contain')]")
    private WebElement selfLetter;

    @FindBy(xpath = "//*[contains(@class, 'metathread__contain')]//div[@class='checkbox']")
    private WebElement selfLetterCheckbox;

    @FindBy(xpath = "//*[contains(@class, 'llc__subject')]//span")
    private List<WebElement> letterSubjectItems;

    @FindBy(xpath = "//*[contains(@class, 'llc__item_correspondent')]//span")
    private List<WebElement> letterContactToItems;

    @FindBy(xpath = "//*[contains(@class, 'llc__content')]")
    private List<WebElement> letterItems;

    @FindBy(xpath = "//*[contains(@class, 'llc__snippet')]//span")
    private List<WebElement> letterTextItems;

    @FindBy(xpath = "//*[contains(@class, 'dataset__empty')]")
    private List<WebElement> emptyItems;

    @FindBy(css = "[data-testid='whiteline-account']")
    private WebElement accBtn;

    @FindBy(xpath = "//*[text() = 'Выйти']")
    private WebElement exitBtn;

    public InboxPage(WebDriver webDriver) {
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

    public void getSelfLetters(){
        selfLetter.click();
    }

    public void deleteSelfLetters(){
        selfLetterCheckbox.click();
        deleteBtn.click();
        clearBtn.click();
    }

    public void createLetter(String contactTo, String Subj, String Text){
        compose.click();
        letterContactTo.sendKeys(contactTo);
        letterSubject.sendKeys(Subj);
        letterText.sendKeys(Text);
        saveBtn.click();
        closeBtn.click();
    }

    public void createDraft(String contactTo, String Subj, String Text){
        compose.click();
        letterContactTo.sendKeys(contactTo);
        letterSubject.sendKeys(Subj);
        letterText.sendKeys(Text);
        sendBtn.click();
        try{
            closeBtn.click();
        } catch (org.openqa.selenium.StaleElementReferenceException ex){
            closeBtn.click();
        }
    }

    public SentPage clicksentBtn(){
        sendBtn.click();
        return new SentPage(webDriver);
    }

    public TrashPage clickTrashBtn(){
        trashBtn.click();
        return new TrashPage(webDriver);
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
