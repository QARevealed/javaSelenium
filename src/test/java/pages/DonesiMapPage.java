package pages;

import helpers.BaseHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class DonesiMapPage extends BaseHelper
{

    WebDriver driver;
    public DonesiMapPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void continueFromMap()
    {

    }
}
