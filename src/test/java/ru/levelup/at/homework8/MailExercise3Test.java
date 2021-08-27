package ru.levelup.at.homework8;

import org.testng.annotations.Test;

public class MailExercise3Test extends AbstractSeleniumTest {

    @Test
    public void loginNewLetterDeleteTest() {

        actionStep.openMailLoginPage();
        actionStep.mailLogin();
        assertionStep.assertThatActualAccountEqualExpectedAccount("vasilina_levelup@mail.ru");
        actionStep.createLetter("vasilina_levelup@mail.ru",
            "Test Delete Subject", "Test Delete Text");
        actionStep.getSelfLetters();
        assertionStep.assertInboxLetter("vasilina_levelup@mail.ru",
            "Test Delete Subject", "Test Delete Text");
        actionStep.deleteSelfLetters();
        actionStep.navigateToTrashPageFromInboxPage();
        assertionStep.assertTrashLetter("vasilina_levelup@mail.ru",
            "Test Delete Subject", "Test Delete Text");
        actionStep.mailLogout();
    }

}
