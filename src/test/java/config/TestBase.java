package config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.LogIn;
import pages.SideMenu;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    private static final WebDriverConfig config =
            ConfigFactory.create(WebDriverConfig.class, System.getProperties());

    public String
            urlBase = config.getBaseUrl(),
            urlLogin = urlBase + "auth/login",
            urlLogOut = urlBase + "auth/login?logout=",
            urlServicePublish = urlBase + "service/publish",
            urlOrders = urlBase + "my-orders/inbox",
            urlUserRegistration = urlBase + "auth/registration",
            urlProfile = urlBase + "profile",
            urlClientDetails = "client-details",
            urlProfessionalProfile = urlBase + "professional";

    public static SideMenu sideMenu = new SideMenu();
    public static LogIn log = new LogIn();

    @BeforeAll
    public static void init() {
        Configuration.browser = config.getBrowserName();
        if (!config.getBrowserVersion().equals("")) {
            Configuration.browserVersion = config.getBrowserVersion();
        }
        Configuration.browserSize = config.getBrowserSize();
        if (!config.getRemote().equals("")) {
            Configuration.remote = config.getRemote();
        }
        Configuration.timeout = config.getTimeout();
        Configuration.headless = config.getHeadless();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        Configuration.browserCapabilities = capabilities;
        capabilities.setCapability("enableVNC", config.getVNC());
        capabilities.setCapability("enableVideo", config.getVideo());
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeEach
    public void setupConfig() {
        open(urlBase);
    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}