package ru.levelup.at.homework8;

import org.testng.annotations.Test;

public class MailExercise1Test extends AbstractSeleniumTest {

    @Test
    public void loginNewLetterTest() {

        actionStep.openMailLoginPage();
        actionStep.mailLogin();
        assertionStep.assertThatActualAccountEqualExpectedAccount("vasilina_levelup@mail.ru");
        actionStep.createDraftLetter("vas.kuznetsova@gmail.com",
            "Test Subject", "Test Text");
        actionStep.navigateToDraftPageFromInboxPage();
        assertionStep.assertDraftLetter("vas.kuznetsova@gmail.com",
            "Test Subject",
            "Test Text");
        actionStep.sendDraftLetter(0);
        actionStep.navigateToSentPageFromDraftPage();
        assertionStep.assertSendLetterItems();
        actionStep.navigateToDraftPageFromSentPage();
        assertionStep.assertDraftLetterItemsIsEmpty();
        actionStep.mailLogout();
    }

}
