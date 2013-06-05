package net.rrm.ehour.it;

import org.junit.Before;
import org.junit.Rule;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

public abstract class AbstractScenario {
    private static boolean initialized = false;
    public static RemoteWebDriver Driver;
    public static final String BASE_URL = "http://localhost:18000";

    @Rule
    public ScreenshotTestRule screenshotTestRule = new ScreenshotTestRule();

    @Before
    public void setUp() throws Exception {
        if (!initialized) {
            EhourTestApplication.start();
            Driver = new FirefoxDriver();
            Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    try {
                        Driver.quit();
                    } catch (Exception e) {

                    }
                }
            });
        }

        initialized = true;
    }

}