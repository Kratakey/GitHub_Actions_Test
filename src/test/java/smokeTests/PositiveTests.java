package smokeTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositiveTests extends config.TestBase {

    @Test
    void t00001() {
    log.popupSkip();
    sideMenu.clickSignUp();
    log.forceMainPage();
    }
}