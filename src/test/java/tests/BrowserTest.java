package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.GoogleHomePage;

public class BrowserTest extends BaseTest
{
    @Test
    public void googleSearchTest() throws InterruptedException
    {
        // navigate to Google HP
        driver.get("https://www.google.com/");
        // enter "QA Revealed" in search field
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("QA Revealed");
        // click on Google Search button
        wdWait.until(ExpectedConditions.elementToBeClickable(By.name("btnK")));
        WebElement searchButton = driver.findElement(By.name("btnK"));
        searchButton.click();

        // "QA Revealed is shown on result page

        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("res")));
        WebElement results = driver.findElement(By.id("res"));
        Assert.assertTrue(results.getText().contains("QA Revealed"));

        Thread.sleep(3000);

    }


    @Test
    public void yahooSearchTest() throws InterruptedException
    {
        // navigate to Yahoo HP
        driver.get("https://www.yahoo.com/");
        // enter "QA Revealed" in search field
        WebElement searchField = driver.findElement(By.id("ybar-sbq"));
        searchField.sendKeys("QA Revealed");
        // click on magnifier glass icon
        WebElement searchButton = driver.findElement(By.id("ybar-search"));
        searchButton.click();

        // "QA Revealed is shown on result page
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("results")));
        WebElement results = driver.findElement(By.id("results"));
        Assert.assertTrue(results.getText().contains("QA Revealed"));

        Thread.sleep(3000);

    }

    @Test
    public void googleSearchTestPOM() throws InterruptedException
    {

        String term = "QA Revealed";

        GoogleHomePage homepage = new GoogleHomePage(driver);
        homepage.googleSearch(term);

        // "QA Revealed is shown on result page
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("res")));
        WebElement results = driver.findElement(By.id("res"));
        Assert.assertTrue(results.getText().contains(term));

        Thread.sleep(3000);
    }

}