package cubepay.com.dataproviders;

import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider
    public static Object[] getData(){

        String[] a=  {"91111111","91111112","87667",""};
        return a;
    }
}
