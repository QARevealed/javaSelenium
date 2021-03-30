package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class DonesiMealsPage extends BaseHelper
{
    WebDriver driver;
    public DonesiMealsPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (className = "shop-profile-menu-list")
    WebElement mealsListHolder;

    public List<WebElement> mealsList;

    public void createMealsList()
    {
        // wdWait.until(ExpectedConditions.visibilityOf(mealsListHolder));
        mealsList = driver.findElements(By.className("shop-profile-menu-list-item"));
    }

    public WebElement chooseRandomMeal()
    {
        Random rnd = new Random();
        int randomNumber = rnd.nextInt(mealsList.size());
        System.out.println("Random number:"+randomNumber+ " from "+mealsList.size());
        while((mealsList.get(randomNumber).getText().contains("umak") ||
                mealsList.get(randomNumber).getText().contains("dresing") ||
                mealsList.get(randomNumber).getText().contains("Parmezan")) )
        {randomNumber = rnd.nextInt(mealsList.size());
            System.out.println(randomNumber);}
        return mealsList.get(randomNumber);
    }
}
