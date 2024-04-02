package cubepay.com.basetest;
import cubepay.com.driver.Driver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest {
    @BeforeMethod
    public void setUpTest() throws Exception {
        Driver.initDriver();
    }

    @AfterMethod
    public void tearDownTest(){
        Driver.quitDriver();
    }
}
