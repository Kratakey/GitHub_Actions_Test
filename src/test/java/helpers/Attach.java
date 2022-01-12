package helpers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.google.common.io.Files;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.logging.LogType.BROWSER;

public class Attach {
    @Attachment(value = "{attachName}", type = "text/plain")
    public static String attachAsText(String attachName, String message) {
        return message;
    }

    @Attachment(value = "Page source", type = "text/plain")
    public static byte[] pageSource() {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] screenshotAs(String attachName) {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static void browserConsoleLogs() {
        if (!Configuration.browser.equals("safari")) {
            attachAsText(
                    "Browser console logs",
                    String.join("\n", Selenide.getWebDriverLogs(BROWSER))
            );
        }
    }

//    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
//    public static String addVideo() {
//        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
//                + getVideoUrl(getSessionId())
//                + "' type='video/mp4'></video></body></html>";
//    }

    public static void addVideo() {
        try {
            InputStream is = null;
            Thread.sleep(1000);
            for (int i = 0; i < 10; i++) {
                try {
                    is = getVideoUrl(getSessionId()).openStream();
                    i = 10;
                } catch (FileNotFoundException e) {
                    Thread.sleep(1000);
                }
            }
            Allure.addAttachment("Video", "video/mp4", is, "mp4");
        } catch (Exception e) {
            System.out.println("attachAllureVideo");
            e.printStackTrace();
        }
    }

    public static URL getVideoUrl(String sessionId) {
        String videoUrl = "https://kratakey.github.io//video/" + sessionId + ".mp4";

        try {
            return new URL(videoUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getSessionId() {
        return ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
    }
}