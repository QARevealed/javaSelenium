package pages;

import helpers.BaseHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class DonesiHomePage extends BaseHelper
{
    WebDriver driver;
    public DonesiHomePage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void donesiComFilter(String address)
    {

    }
}
