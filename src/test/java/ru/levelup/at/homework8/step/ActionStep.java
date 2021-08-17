package ru.levelup.at.homework8.step;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ru.levelup.at.java.homework8.ConfProperties;

public class ActionStep extends AbstractStep {

    public ActionStep(WebDriver driver) {
        super(driver);
    }

    @Step("Open mail.ru")
    public void openMailLoginPage() {
        loginPage.open();
    }

    @Step("Login")
    public void mailLogin() {
        loginPage.login(ConfProperties.getProperty("login"),
            ConfProperties.getProperty("password"));
    }

    @Step("Create a draft letter to '{contactTo}'")
    public void createDraftLetter(String contactTo, String letterSubject, String letterText) {
        inboxPage.createDraftLetter(contactTo, letterSubject, letterText);
    }

    @Step("Create and send a letter to '{contactTo}'")
    public void createLetter(String contactTo, String letterSubject, String letterText) {
        inboxPage.createLetter(contactTo, letterSubject, letterText);
    }

    @Step("Send a draft letter")
    public void sendDraftLetter(int index) {
        draftsPage.sendLetter(0);
    }

    @Step("Get the self letters")
    public void getSelfLetters() {
        inboxPage.getSelfLetters();
    }

    @Step("Delete the self letter")
    public void deleteSelfLetters() {
        inboxPage.deleteSelfLetters();
    }

    @Step("Navigate to Sent letters")
    public void navigateToSentPageFromDraftPage() {
        sentPage = draftsPage.clickSentButton();
    }

    @Step("Navigate to Sent letters")
    public void navigateToSentPageFromInboxPage() {
        sentPage = inboxPage.clickSentButton();
    }

    @Step("Navigate to Draft letters")
    public void navigateToDraftPageFromSentPage() {
        draftsPage = sentPage.clickDraftsButton();
    }

    @Step("Navigate to Draft letters")
    public void navigateToDraftPageFromInboxPage() {
        draftsPage = inboxPage.clickDraftsButton();
    }

    @Step("Navigate to Test letters")
    public void navigateToTestPageFromSentPage() {
        testPage = sentPage.clickTestButton();
    }

    @Step("Navigate to Trash")
    public void navigateToTrashPageFromInboxPage() {
        trashPage = inboxPage.clickTrashButton();
    }

    @Step("Logout")
    public void mailLogout() {
        draftsPage.logout();
    }

}
