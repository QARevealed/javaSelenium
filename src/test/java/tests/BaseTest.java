package tests;

import helpers.BaseHelper;
import org.junit.After;
import org.junit.Before;


public class BaseTest extends BaseHelper
{
    @Before
    public void testInit()
    {
        driver.manage().window().maximize();
    }

    @After
    public void testTearDown() {
        driver.close();
        driver.quit();
    }

}