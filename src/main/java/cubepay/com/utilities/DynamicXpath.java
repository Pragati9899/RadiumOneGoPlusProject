package cubepay.com.utilities;

import org.openqa.selenium.By;

public class DynamicXpath {

    private DynamicXpath(){}

    public static By constructXpath(String xpath,String replacingString){
        return By.xpath(String.format(xpath,replacingString));
    }
    public static By constructXpath(String xpath,String replacingString1,String replacingString2){
        return By.xpath(String.format(xpath,replacingString1,replacingString2));
    }
}
