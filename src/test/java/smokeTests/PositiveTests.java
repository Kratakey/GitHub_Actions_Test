package smokeTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositiveTests extends config.TestBase {

    @Test
    @Feature("Feature 1")
    @Owner("EK")
    @Story("Story 1")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Test 1")
    void t00000() {
        log.popupSkip();
        sideMenu.clickSignUp();
    }

    @Test
    @Feature("Feature 1")
    @Owner("EK")
    @Story("Story 1")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Test 2")
    void t00001() {
        log.popupSkip();
        sideMenu.clickLogIn();
    }
}