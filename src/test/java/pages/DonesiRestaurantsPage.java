package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class DonesiRestaurantsPage extends BaseHelper
{

    public String numberOfAllRestaurants;
    public String numberOfItalianRestaurants;
    public List<WebElement> restaurantsList = new ArrayList<>();

    WebDriver driver;
    public DonesiRestaurantsPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void filterItalianRestaurants()
    {

    }

    public WebElement chooseRandomRestaurant()
    {
        return driver.findElement(By.name(""));
    }
}
