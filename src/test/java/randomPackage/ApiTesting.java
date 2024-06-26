package randomPackage;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static io.restassured.RestAssured.baseURI;

public class ApiTesting {
    public static void main(String[] args) {
        try {
            System.setProperty("javax.net.ssl.trustStore", "C:/.keystore");
            System.setProperty("javax.net.ssl.trustStorePassword", "changeit");

            // Create a URL object with the API endpoint
            URL url = new URL("https://api.g-sandbox.radiumone.io/paynow/notify/mockup");

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            String qr_data = "00020101021226560009SG.PAYNOW010120213201509555BNN1030100412230921212331520400005303702540476.05802SG5914DEV MERCHANT 56009SINGAPORE62160112087Z-00014706304CCC7";
            String encoding3 = Base64.getEncoder().encodeToString(
                    qr_data.getBytes(StandardCharsets.UTF_8));
            // Set the request method (GET, POST, etc.)
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "*/*");
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
    public void apiTesting() {
        String qr_data = "00020101021226560009SG.PAYNOW010120213201509555BNN10301004122310111840355204000053037025405110.05802SG5914DEV MERCHANT 56009SINGAPORE6216011208NJ-0001VZ16304BBE2";
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

        baseURI = "https://api.g-sandbox.radiumone.io/paynow";
        String validatableResponse = RestAssured.given().header("authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1cm46Zy1zYW5kYm94LmFwaS5yYWRpdW1vbmUuaW8iLCJuYW1lIjoiYXBpX2RldmljZSIsImlhdCI6MTY5NzAxMzM3MiwiZXhwIjoxNjk3NjE4MTcyLCJzY29wZSI6ImFwaTpkZXZpY2UiLCJzdWIiOiI1Y2JlNTIyYjhhODkwNDFkYjVhMTFmZmRhZWViOGRmZTdmNGUxODVjMmM5NTRlNzc0Mjk1MGIxOTM2ODJhZjQ2IiwiZGV2aWNlIjoiMTIzNDU2NzgifQ.-I9THdfOy1zZac6wgyv3fWnUBj3Lj4YKAJmR3zxLJJw")
                .accept(ContentType.JSON)
                .queryParam("qr_data", qr_data)
                .when()
                .post("/notify/mockup").then()
                .log().all()
                .contentType(ContentType.JSON).extract().response().asString();

        System.out.println(validatableResponse);
          /*  JsonPath js = new JsonPath(responseBody);
            String asset = js.getString("x.message");
            System.out.println(asset);*/

    }
    //        try {
//            System.setProperty("javax.net.ssl.trustStore", "C:/.keystore");
//            System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
//
//            // Create a URL object with the API endpoint
//            URL url = new URL("https://api.g-sandbox.radiumone.io/paynow/notify/mockup");
//
//            // Open a connection to the URL
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            String encoding3 = Base64.getEncoder().encodeToString(
//                    content.getBytes(StandardCharsets.UTF_8));
//            // Set the request method (GET, POST, etc.)
//            connection.setRequestMethod("POST");
////            connection.setRequestProperty("Accept","*/*");
//            connection.setRequestProperty("qr_data", encoding3);
//            connection.setRequestProperty("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1cm46Zy1zYW5kYm94LmFwaS5yYWRpdW1vbmUuaW8iLCJuYW1lIjoiYXBpX2RldmljZSIsImlhdCI6MTY5NTY0Mjg2NywiZXhwIjoxNjk2MjQ3NjY3LCJzY29wZSI6ImFwaTpkZXZpY2UiLCJzdWIiOiI1Y2JlNTIyYjhhODkwNDFkYjVhMTFmZmRhZWViOGRmZTdmNGUxODVjMmM5NTRlNzc0Mjk1MGIxOTM2ODJhZjQ2IiwiZGV2aWNlIjoiMTIzNDU2NyJ9.3InLCdoSuA45Gw7IYjpKBAqX1n68tHoSKD9n5ncbezs");
//            connection.setRequestProperty(" Accept-Encoding","gzip, deflate, br");
//            connection.setRequestProperty("Connection","keep-alive");
//            // Get the response code
//            int responseCode = connection.getResponseCode();
//            System.out.println("Response Code: " + responseCode);
//            String responseMess = connection.getResponseMessage();
//            System.out.println("Response: " + responseMess);
//            // Read the response data
//            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String inputLine;
//            StringBuilder response = new StringBuilder();
//
//            while ((inputLine = reader.readLine()) != null) {
//                response.append(inputLine);
//            }
//
//            reader.close();
//
//            // Print the response data
//            System.out.println("Response Data:\n" + response.toString());
//
//            // Close the connection
//            connection.disconnect();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

}

