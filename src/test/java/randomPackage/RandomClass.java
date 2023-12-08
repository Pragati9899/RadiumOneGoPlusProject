package randomPackage;

public class RandomClass {

    //  driver.findElement(new AppiumBy.ByAndroidUIAutomator("attribute(\"Value\")")).click();
       /* TouchActions action = new TouchActions(driver);
        action.scroll(element, 10, 100);
        action.perform();
        element.click();*/
      /*  new TouchAction(driver).tap(tapOptions().withElement(element(monthPicker, 200, 772))).perform();
        new TouchAction(driver).tap(tapOptions().withElement(element(datePicker, 380, 800))).perform();
        new TouchAction(driver).tap(tapOptions().withElement(element(yearPicker, 500, 800))).perform();*/

// new TouchAction(driver).tap(tapOptions().withElement(element(myElement))).perform();

// driver.findElement(MobileBy.AndroidUIAutomator("")).click();
//driver.androidUIAutomator("attribute(\"text\")")
      /*  WebElement source = driver.findElement(By.xpath("//android.view.View[@instance='0']"));
        WebElement destination = driver.findElement(By.xpath("//android.view.View[@instance='22']"));
        TouchAction action = new TouchAction((PerformsTouchActions)driver);
        System.out.println("Dragging item");
        action.longPress(source).moveTo(destination).release().perform();
        boolean bul = driver.findElementsByXPath("//android.view.View[@content-desc='24 January 2018']").isEmpty();

       */
    //check if permission dialog are displayed or not
      /*  if (signInPage.permissionpop_upIsDisplayed()) {
            if (rb.getString("PermissionDialogue").equals("While using the app")) {
                Thread.sleep(6000);
                signInPage.AcceptThePop_up1();
                Thread.sleep(4000);
                signInPage.AcceptThePop_up1();
                Thread.sleep(4000);
                signInPage.AcceptThePop_up1();
            } else if (rb.getString("PermissionDialogue").equals("Only this time")) {
                signInPage.AcceptThePop_up2();
                signInPage.AcceptThePop_up2();
                signInPage.AcceptThePop_up2();
            } else if (rb.getString("PermissionDialogue").equals("Don’t allow")) {
                signInPage.AcceptThePop_up3();
                signInPage.AcceptThePop_up3();
                signInPage.AcceptThePop_up3();
            }
        }*/
    //check if permission diauloge are displayed or not
      /*  if (signInPage.permissionpop_upIsDisplayed()) {
        if (rb.getString("PermissionDialogue").equals("While using the app")) {
            Thread.sleep(4000);
            signInPage.AcceptThePop_up1();
            Thread.sleep(2000);
            signInPage.AcceptThePop_up1();
            Thread.sleep(2000);
            signInPage.AcceptThePop_up1();
        } else if (rb.getString("PermissionDialogue").equals("Only this time")) {
            signInPage.AcceptThePop_up2();
            signInPage.AcceptThePop_up2();
            signInPage.AcceptThePop_up2();
        } else if (rb.getString("PermissionDialogue").equals("Don’t allow")) {
            signInPage.AcceptThePop_up3();
            signInPage.AcceptThePop_up3();
            signInPage.AcceptThePop_up3();
        }*/
  //  }
}
