package ru.levelup.at.homework8;

import org.testng.annotations.Test;

public class MailExercise2Test extends AbstractSeleniumTest {

    @Test
    public void loginNewLetterInFolderTest() {

        actionStep.openMailLoginPage();
        actionStep.mailLogin();
        assertionStep.assertThatActualAccountEqualExpectedAccount("vasilina_levelup@mail.ru");
        actionStep.createLetter("vasilina_levelup@mail.ru",
            "Тест Test Subject", "Test Text");
        actionStep.navigateToSentPageFromInboxPage();
        assertionStep.assertSentLetter("vasilina_levelup@mail.ru", "Тест Test Subject", "Test Text");
        actionStep.navigateToTestPageFromSentPage();
        assertionStep.assertTestLetter("vasilina_levelup@mail.ru",
            "Тест Test Subject", "Test Text");
        actionStep.mailLogout();
    }

}
