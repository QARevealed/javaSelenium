# javaSelenium
Default Java Selenium project

Create new java class in tests package which extends BaseTest.
BaseTest will start Chrome before each test and close it after.
Create JUnit @Test annotation and create a method bellow.
You have run test button next to method signature.
You can use variable driver (as WebDriver) and wdWait (as WebDriverWait)
which are inherited from BaseHelper.
Each page/helper class should extends BaseHelper.

for eg.

package tests;

import org.junit.Test;

public class probeClassTest extends BaseTest
{
    @Test
    public void probe()
    {
        // your code
    }
}
