package ru.levelup.at.homework8.step;

import static org.assertj.core.api.Assertions.assertThat;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class AssertionStep extends AbstractStep {

    public AssertionStep(WebDriver driver) {
        super(driver);
    }

    @Step("Account should be equal to '{expectedText}'")
    public void assertThatActualAccountEqualExpectedAccount(String expectedText) {
        assertThat(loginPage.getActualLoginText()).isEqualTo(expectedText);
    }

    @Step("Draft letter should be to '{contactTo}', with Subject - '{letterSubject}' and Text - '{letterText}'")
    public void assertDraftLetter(String contactTo, String letterSubject, String letterText) {
        assertThat(draftsPage.getLetterContactTo(0)).containsIgnoringCase(contactTo);
        assertThat(draftsPage.getLetterSubject(0)).containsIgnoringCase(letterSubject);
        assertThat(draftsPage.getLetterText(0)).containsIgnoringCase(letterText);
    }

    @Step("Sent letter should be to '{contactTo}', with Subject - '{letterSubject}' and Text - '{letterText}'")
    public void assertSentLetter(String contactTo, String letterSubject, String letterText) {
        assertThat(sentPage.getLetterContactTo(0)).containsIgnoringCase(contactTo);
        assertThat(sentPage.getLetterSubject(0)).containsIgnoringCase(letterSubject);
        assertThat(sentPage.getLetterText(0)).containsIgnoringCase(letterText);
    }

    @Step("Test letter should be to '{contactTo}', with Subject - '{letterSubject}' and Text - '{letterText}'")
    public void assertTestLetter(String contactTo, String letterSubject, String letterText) {
        assertThat(testPage.getLetterContactTo(0)).containsIgnoringCase(contactTo);
        assertThat(testPage.getLetterSubject(0)).containsIgnoringCase(letterSubject);
        assertThat(testPage.getLetterText(0)).containsIgnoringCase(letterText);
    }

    @Step("Incoming letter should be to '{contactTo}', with Subject - '{letterSubject}' and Text - '{letterText}'")
    public void assertInboxLetter(String contactTo, String letterSubject, String letterText) {
        assertThat(inboxPage.getLetterContactTo(0)).containsIgnoringCase(contactTo);
        assertThat(inboxPage.getLetterSubject(0)).containsIgnoringCase(letterSubject);
        assertThat(inboxPage.getLetterText(0)).containsIgnoringCase(letterText);
    }

    @Step("Deleted letter should be to '{contactTo}', with Subject - '{letterSubject}' and Text - '{letterText}'")
    public void assertTrashLetter(String contactTo, String letterSubject, String letterText) {
        assertThat(trashPage.getLetterContactTo(0)).containsIgnoringCase(contactTo);
        assertThat(trashPage.getLetterSubject(0)).containsIgnoringCase(letterSubject);
        assertThat(trashPage.getLetterText(0)).containsIgnoringCase(letterText);
    }

    @Step("Sent letters should be more than 0")
    public void assertSendLetterItems() {
        assertThat(sentPage.getLetterItems()).hasSizeGreaterThan(0);
    }

    @Step("Draft letters should be empty")
    public void assertDraftLetterItemsIsEmpty() {
        assertThat(draftsPage.getDraftEmptyItems()).hasSizeGreaterThan(0);
    }

}
