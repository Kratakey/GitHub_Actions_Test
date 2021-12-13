package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.fail;

public class LogIn extends config.TestBase {

    public void refreshPage() {
        Selenide.refresh();
    }

    public void openMainPage() {
        open(urlBase);
    }

    public void forceMainPage() {
        if (!$("body").exists()) {
            open(urlBase);
            return;
        }
        sleep(500);
        String currentUrl = WebDriverRunner.url();
        if (!currentUrl.equals(urlBase)) {
            open(urlBase);
        }
    }

    public void logoClick() {
        $$("ion-item").filter(visible).get(0).scrollIntoView(false).click();
    }

    public void popupSkip() {
        sleep(500);
        if (!Configuration.browser.equals("safari")) {
            $("ion-alert").pressEscape();
        } else {
            $("ion-alert").$("button", 1).click();
            sleep(500);
            $("app-on-map-popover").$("ion-row ion-col ion-button", 1).click();
        }
        sleep(500);
    }
}