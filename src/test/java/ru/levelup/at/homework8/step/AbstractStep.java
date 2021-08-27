package ru.levelup.at.homework8.step;

import org.openqa.selenium.WebDriver;
import ru.levelup.at.java.homework8.DraftsPage;
import ru.levelup.at.java.homework8.InboxPage;
import ru.levelup.at.java.homework8.LoginPage;
import ru.levelup.at.java.homework8.SentPage;
import ru.levelup.at.java.homework8.TestPage;
import ru.levelup.at.java.homework8.TrashPage;

public abstract class AbstractStep {

    private WebDriver driver;

    protected LoginPage loginPage;
    protected DraftsPage draftsPage;
    protected InboxPage inboxPage;
    protected SentPage sentPage;
    protected TestPage testPage;
    protected TrashPage trashPage;

    protected AbstractStep(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
        draftsPage = new DraftsPage(driver);
        inboxPage = new InboxPage(driver);
        sentPage = new SentPage(driver);
        testPage = new TestPage(driver);
        trashPage = new TrashPage(driver);
    }
}
