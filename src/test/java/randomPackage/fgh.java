package randomPackage;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;


public class fgh {
    public static void main(String[] args) {

    try {
        baseURI = "https://api.g-sandbox.radiumone.io";
String  validatableResponse = RestAssured.given().header("authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1cm46Zy1zYW5kYm94LmFwaS5yYWRpdW1vbmUuaW8iLCJuYW1lIjoiYXBpX2RldmljZSIsImlhdCI6MTY5NzAxMzM3MiwiZXhwIjoxNjk3NjE4MTcyLCJzY29wZSI6ImFwaTpkZXZpY2UiLCJzdWIiOiI1Y2JlNTIyYjhhODkwNDFkYjVhMTFmZmRhZWViOGRmZTdmNGUxODVjMmM5NTRlNzc0Mjk1MGIxOTM2ODJhZjQ2IiwiZGV2aWNlIjoiMTIzNDU2NzgifQ.-I9THdfOy1zZac6wgyv3fWnUBj3Lj4YKAJmR3zxLJJw")
              //  .header("Accept-Encoding", "gzip, deflate, br")
                .queryParam("type", "paynow_ref")
                .queryParam("id", "08JB-0001LEA")
                .log().all()
                .when()
                .get("/receipt/qr").then()
             .contentType(ContentType.JSON)
              //  .contentType("application/json")

                .extract().response().asString();
        System.out.println(validatableResponse);
       /* System.out.println(validatableResponse.getHeader("Server"));
        System.out.println(validatableResponse.getBody());*/
      /*  Object response = "{\"responseStr\":" + validatableResponse + "}";
        System.out.println("Response :"+response);*/
       /* JSONObject jsonObject = new JSONObject(response);
        JSONArray jsonArray = (JSONArray)jsonObject.get("responseStr");
        for(int i=0;i<jsonArray.length();i++) {
            JSONObject jsonObject1 = (JSONObject)jsonArray.getJSONObject(i);
        }*/
    }catch (Exception e) {
        e.printStackTrace();

    }
}
  /* @Test(dataProvider = "getExcelData1", dataProviderClass = DataProvidersExcel.class)
     public void TC_001_loginWith_RegsisteredNumber(String mobileno) {

         lp.setEnterMobileNoField(mobileno).clickSignInBtn();

         if (Objects.equals(mobileno, "92222222")) {
             String[] a = {"1", "2", "3", "4", "5", "6"};
             lp.setVerificationCode(a);

             if (lp.checkSecuritySettingIsDisplayed()) {
                 lp.setEnterPasswordField(ReadConfigFile.getValue("Password"))
                         .setReEnterPasswordField(ReadConfigFile.getValue("Password"))
                         .clickSubmitBtn();
             }
             Assertions.assertThat(mp.isUserNameDisplayed()).isTrue();

         } else if (Objects.equals(mobileno, "9887") || Objects.equals(mobileno, "")) {
             Assertions.assertThat(lp.isErrorMessageDisplayedForInvalidNumber()).isTrue();
         } else if (Objects.equals(mobileno, "92123456")) {
             Assertions.assertThat(lp.isErrorMessageDisplayedForUnregisteredNum()).isTrue();
         } else {
             Assertions.fail("Login test has been failed");
         }
     }
 */
  /*   ObjectMapper mapper = new ObjectMapper();
   String jsonString = mapper.writeValueAsString(validatableResponse);
        System.out.println(jsonString);*/
}
