package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.HeroKUAppPage;

public class HeroKuAppTest extends BaseTest
{
    @Test
    public void loginWithValidCredentials() throws InterruptedException
    {
        String username = "tomsmith";
        String password = "SuperSecretPassword!";

        HeroKUAppPage loginPage = new HeroKUAppPage(driver);
        loginPage.login(username,password);

        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("flash-messages")));
        WebElement greenMessage = driver.findElement(By.id("flash-messages"));
        Assert.assertTrue(greenMessage.getText().contains("You logged into a secure area!"));

        WebElement logOutButton = driver.findElement(By.className("icon-signout"));
        Assert.assertTrue(logOutButton.getText().contains("Logout"));

        Thread.sleep(3000);
    }

    @Test
    public void loginWithInvalidUsername() throws InterruptedException
    {
        String username = "wrongusername";
        String password = "SuperSecretPassword!";

        HeroKUAppPage loginPage = new HeroKUAppPage(driver);
        loginPage.login(username,password);

        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("flash-messages")));
        WebElement greenMessage = driver.findElement(By.id("flash-messages"));
        Assert.assertTrue(greenMessage.getText().contains("Your username is invalid!"));

        Thread.sleep(3000);
    }

}