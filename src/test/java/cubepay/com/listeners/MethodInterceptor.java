package cubepay.com.listeners;

import cubepay.com.utilities.ExcelUtils;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MethodInterceptor implements IMethodInterceptor {
    /* @Override
     public List<IMethodInstance> intercept(List<IMethodInstance> list, ITestContext iTestContext) {
         List<IMethodInstance> methods;
         try {
             List<Map<String, String>> mapList = ExcelUtils.readExcelData();
             methods = new ArrayList<>();
             for (IMethodInstance instance : list) {
                 for (Map<String, String> map : mapList) {
                     if (instance.getMethod().getMethodName().equalsIgnoreCase(map.get("testcasename"))) {
                         if (map.get("execute").equalsIgnoreCase("yes")) {
                             instance.getMethod().setDescription(map.get("testcasedescription"));
                             instance.getMethod().setInvocationCount(Integer.parseInt(map.get("count")));
                             methods.add(instance);
                         }
                     }
                 }
             }
         } catch (FileNotFoundException e) {
             throw new RuntimeException(e);
         }

         return methods;
     }*/
    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methodInstances, ITestContext testContext) {
        List<IMethodInstance> filteredMethods;
        try {
            List<Map<String, String>> excelData = ExcelUtils.readExcelData();
            filteredMethods = new ArrayList<>();
            for (IMethodInstance methodInstance : methodInstances) {
                for (Map<String, String> testData : excelData) {
                    if (methodInstance.getMethod().getMethodName().equalsIgnoreCase(testData.get("testcasename"))) {
                        if ("yes".equalsIgnoreCase(testData.get("execute"))) {
                            methodInstance.getMethod().setDescription(testData.get("testcasedescription"));
                            //  methodInstance.getMethod().setInvocationCount(Integer.parseInt(testData.get("count")));
                            filteredMethods.add(methodInstance);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("problem in method interceptor");
        }
        return filteredMethods;
    }
}


