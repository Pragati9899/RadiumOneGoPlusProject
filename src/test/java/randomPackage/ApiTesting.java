package Testcases;


import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.openqa.selenium.json.Json;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.path.json.JsonPath.given;

public class ApiTesting {
        public static void main(String[] args) {
            try {
                System.setProperty("javax.net.ssl.trustStore", "C:/.keystore");
                System.setProperty("javax.net.ssl.trustStorePassword", "changeit");

                // Create a URL object with the API endpoint
                URL url = new URL("https://api.g-sandbox.radiumone.io/paynow/notify/mockup");

                // Open a connection to the URL
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
               String qr_data="00020101021226560009SG.PAYNOW010120213201509555BNN1030100412230921212331520400005303702540476.05802SG5914DEV MERCHANT 56009SINGAPORE62160112087Z-00014706304CCC7";
                String encoding3 = Base64.getEncoder().encodeToString(
                        qr_data.getBytes(StandardCharsets.UTF_8));
                // Set the request method (GET, POST, etc.)
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Accept","*/*");
                connection.setRequestProperty("qr_data", encoding3);
                connection.setRequestProperty("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1cm46Zy1zYW5kYm94LmFwaS5yYWRpdW1vbmUuaW8iLCJuYW1lIjoiYXBpX2RldmljZSIsImlhdCI6MTY5NTI3NDc3NCwiZXhwIjoxNjk1ODc5NTc0LCJzY29wZSI6ImFwaTpkZXZpY2UiLCJzdWIiOiI1Y2JlNTIyYjhhODkwNDFkYjVhMTFmZmRhZWViOGRmZTdmNGUxODVjMmM5NTRlNzc0Mjk1MGIxOTM2ODJhZjQ2IiwiZGV2aWNlIjoiMTIzNDU2NyJ9.TmdhkI2UW0V-lVAz00eqLSvJg06VekognqKT5DAb-78");
                // Get the response code
                int responseCode = connection.getResponseCode();
                System.out.println("Response Code: " + responseCode);

                // Read the response data
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }

                reader.close();

                // Print the response data
                System.out.println("Response Data:\n" + response.toString());

                // Close the connection
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Test
    public void apiTesting(){
            String qr_data="00020101021226560009SG.PAYNOW010120213201509555BNN1030100412230921221315520400005303702540476.05802SG5914DEV MERCHANT 56009SINGAPORE62160112087Z-000148263041C68";
            String encoding3 = Base64.getEncoder().encodeToString(
                    qr_data.getBytes(StandardCharsets.UTF_8));
          /*  // Creating an object of RequestSpecBuilder
            RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
            // Setting Base URI
            reqBuilder.setBaseUri("https://api.g-sandbox.radiumone.io/paynow");
            // Setting Base Path
            reqBuilder.setBasePath("/notify/mockup");
            reqBuilder.setContentType(ContentType.JSON);
            // Setting pay load
           // reqBuilder.setBody();
            // Getting RequestSpecification reference using builder() method
            RequestSpecification reqSpec = reqBuilder.build();*/

            baseURI="https://api.g-sandbox.radiumone.io/paynow";
            Response validatableResponse = RestAssured.given().header("authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1cm46Zy1zYW5kYm94LmFwaS5yYWRpdW1vbmUuaW8iLCJuYW1lIjoiYXBpX2RldmljZSIsImlhdCI6MTY5NTI3NDc3NCwiZXhwIjoxNjk1ODc5NTc0LCJzY29wZSI6ImFwaTpkZXZpY2UiLCJzdWIiOiI1Y2JlNTIyYjhhODkwNDFkYjVhMTFmZmRhZWViOGRmZTdmNGUxODVjMmM5NTRlNzc0Mjk1MGIxOTM2ODJhZjQ2IiwiZGV2aWNlIjoiMTIzNDU2NyJ9.TmdhkI2UW0V-lVAz00eqLSvJg06VekognqKT5DAb-78")
                    .accept(ContentType.JSON)
                    .header("Accept-Encoding","gzip, deflate, br")
                    .queryParam("qr_data", qr_data)
                    .when()
                    .post("/notify/mockup").then()
                    .contentType(ContentType.JSON).extract().response();

            String responseBody = validatableResponse.getBody().asString();
            System.out.println(responseBody);
            JsonPath js = new JsonPath(responseBody);
            String asset = js.getString("x.message");
            System.out.println(asset);

        }
    }

