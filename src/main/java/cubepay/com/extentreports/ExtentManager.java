package cubepay.com.extentreports;

import com.aventstack.extentreports.ExtentTest;


public class ExtentManager {
    private ExtentManager(){}

    private static final ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

     static ExtentTest getExtentTest(){
        return extentTestThreadLocal.get();
    }
     static void setExtentTest(ExtentTest test){
         extentTestThreadLocal.set(test);
    }

     static void unload(){
         extentTestThreadLocal.remove();
    }
}
