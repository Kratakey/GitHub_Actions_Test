package smokeTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class PositiveTests extends config.TestBase {

    @Test
    void t00001() {
    log.popupSkip();
    sideMenu.clickSignUp();
    log.forceMainPage();
    }
}