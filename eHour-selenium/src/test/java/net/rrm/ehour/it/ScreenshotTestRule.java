package net.rrm.ehour.it;

import org.apache.commons.io.FileUtils;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

class ScreenshotTestRule implements MethodRule {
    public Statement apply(final Statement statement, final FrameworkMethod frameworkMethod, final Object o) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    statement.evaluate();
                } catch (Throwable t) {
                    captureScreenshot(frameworkMethod.getName());
                    throw t;
                }
            }

            public void captureScreenshot(String fileName) {
                try {
                    File screenshot = ((TakesScreenshot) AbstractScenario.Driver).getScreenshotAs(OutputType.FILE);
                    FileUtils.copyFile(screenshot, new File("/tmp/screenshot-" + fileName + ".png"));
                } catch (Exception e) {
                    // No need to crash the tests if the screenshot fails
                }
            }
        };
    }
}