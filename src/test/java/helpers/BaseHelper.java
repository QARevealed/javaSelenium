package helpers;

import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import slack.Slack;
import slack.SlackBlocksMessageBuilder;
import slack.SlackChannel;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

public class BaseHelper
{
    protected static WebDriver driver = new ChromeDriver();
    protected static WebDriverWait wdWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    protected static JavascriptExecutor js = (JavascriptExecutor) driver;
    SlackBlocksMessageBuilder slackMessageBuilder = new SlackBlocksMessageBuilder();
    protected SoftAssert softAssertion = new SoftAssert();
    protected void assertResult(String result, String assertText)
    {
        if (!result.contains(assertText))
        {
            slackMessageBuilder.setTitle(":x: " + assertText + " :x:");
            slackMessageBuilder.addMessageContent("Expected to find ", assertText);
            slackMessageBuilder.setUrl(driver.getCurrentUrl());
            slackMessageBuilder.addMessageContent("On website ", driver.getCurrentUrl());
            System.out.println(slackMessageBuilder.getMessage());
            Slack.postSlackBlocksMessage(slackMessageBuilder.getMessage(), SlackChannel.QARevealed);

            Assert.fail("There is no " + assertText + " in the result.");
        }
    }

    public static WebElement returnElementAttValue (String attributeName, String attributeValue)
    {
        String selector = "[" + attributeName + "=" + attributeValue + "]";
    WebElement element = driver.findElement(By.cssSelector(selector));
    return element;
    }


}
