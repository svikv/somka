package api;
import apiResources.CurrencyValues;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static apiResources.EndPoints.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static utilities.Utils.getDateAndTimeFormated;

//import com.tngtech.java.junit.dataprovider.DataProvider;
//import com.tngtech.java.junit.dataprovider.UseDataProvider;
//import org.junit.Test;

public class ApiTest {
    @Test
    public void privateBankTest() {
        CurrencyValues[] currencyValues =
                given()
                        .accept(ContentType.JSON)    // Header
                        .queryParam("json")
                        .queryParam("exchange")         //adding param to URL
                        .queryParam("coursid", 5)
                        .log().all()
                .when()
                        .get(PRIVATBANK_P24API_PUBINFO)// Example GET method
                .then()
                        .statusCode(200)
                        .log().all()
                        .extract()
                        .response().getBody().as(CurrencyValues[].class);

        for (CurrencyValues currencyElement : currencyValues) {
            System.out.println("Cur " + currencyElement.ccy + " to "
                    + currencyElement.base_ccy + " has for buy " + currencyElement.buy +
                    " and for sale " + currencyElement.sale);
        }
    }

    @Test
    public void getResponseBody(){
        given()
                .given()
                        .queryParam("CUSTOMER_ID", "68195")
                        .queryParam("PASSWORD", "1234!")
                        .queryParam("Account_No", "1")
                .when()
                        .get(RESTAPI_DEMO_GURU99)
                .then()
//                .log().body()
                .log().all();
    }

    @Test
    public void getContentType(){
        String str =
        given()
                .given()
                     .queryParam("CUSTOMER_ID", "68195")
                     .queryParam("PASSWORD", "1234!")
                     .queryParam("Account_No", "1")
                .when()
                .get(RESTAPI_DEMO_GURU99)
                .then()
                        .statusCode(200)
//                        .log().all()
                        .extract().contentType();

        System.out.println("The content type of response is:" + str);
    }

    @Test
    public void test_NumberOfCircuitsFor2017Season_ShouldBe20() {
                given()
                .when()
                      .get("http://ergast.com/api/f1/2017/circuits.json")
                .then()
                      .assertThat()
                      .body("MRData.CircuitTable.Circuits.circuitId", hasSize(20));
    }

    @Test
    public void getAllResponse() {
                given()
                .when()
                        .get("http://ergast.com/api/f1/2017/circuits.json")
                .then()
                        .log().all();
    }

    @Test
    public void test_ResponseHeaderData_ShouldBeCorrect() {
//        The example below checks that:
//        The response status code is equal to 200.
//        The response content type (telling the receiver of the response how to interpret the response body) equals "application/json."
//        The value of the response header "Content-Length" equals "4567."
                given()
                .when()
                    .get("http://ergast.com/api/f1/2017/circuits.json")
                .then()
                    .assertThat()
                    .statusCode(200)
                .and()
                    .contentType(ContentType.JSON)
                .and()
                    .header("Content-Length",equalTo("4551"));
    }

    @Test
    public void test_Md5CheckSumForTest_ShouldBe098f6bcd4621d373cade4e832627b4f6() {

        String originalText = "test";
        String expectedMd5CheckSum = "098f6bcd4621d373cade4e832627b4f6";

                given().
                    param("text",originalText).
                when().
                    get("http://md5.jsontest.com").
                then().
                    assertThat().
                    body("md5",equalTo(expectedMd5CheckSum));
    }

    @Test
    public void test_NumberOfCircuits_ShouldBe20_Parameterized() {

        String season = "2017";
        int numberOfRaces = 20;

                given().
                     pathParam("raceSeason",season).
                when().
                     get("http://ergast.com/api/f1/{raceSeason}/circuits.json").
                then().
                      assertThat().
                      body("MRData.CircuitTable.Circuits.circuitId",hasSize(numberOfRaces));
    }

//    @DataProvider
//    public static Object[][] createSeasonsAndNumberOfRaces() {
//        return new Object[][] {
//                {"2017",20},
//                {"2016",21},
//                {"1966",9}
//        };
//    }

//    When you have a test data object, you can add, remove, or update individual test cases simply by creating, deleting, or modifying the appropriate test data record.
    @DataProvider(name = "seasonsAndNumberOfRaces")
    public Object[][] createTestDataRecords() {
        return new Object[][] {
                {"2017",20},
                {"2016",21},
                {"1966",9}
        };
    }

    @Test(dataProvider = "seasonsAndNumberOfRaces")
//    @UseDataProvider("createSeasonsAndNumberOfRaces")
    public void test_NumberOfCircuits_ShouldBe_DataDriven(String season, int numberOfRaces) {

                given().
                    pathParam("raceSeason",season).
                when().
                    get("http://ergast.com/api/f1/{raceSeason}/circuits.json").
                then().
                    assertThat().
                    body("MRData.CircuitTable.Circuits.circuitId",hasSize(numberOfRaces));
    }

    @Test
    public void test_APIWithBasicAuthentication_ShouldBeGivenAccess() {

                given().
                    auth().
                    preemptive().
                    basic("username", "password").
                when().
                    get("http://path.to/basic/secured/api").
                then().
                    assertThat().
                    statusCode(200);
    }

    @Test
    public void test_APIWithOAuth2Authentication_ShouldBeGivenAccess() {

                given().
                    auth().
                    oauth2("YOUR_AUTHENTICATION_TOKEN_GOES_HERE").
                when().
                    get("http://path.to/oath2/secured/api").
                then().
                    assertThat().
                    statusCode(200);
    }

    @Test
    public void test_ScenarioRetrieveFirstCircuitFor2017SeasonAndGetCountry_ShouldBeAustralia() {

        // First, retrieve the circuit ID for the first circuit of the 2017 season
                String circuitId =
                        given().
                        when().
                            get("http://ergast.com/api/f1/2017/circuits.json").
                        then().
                            extract().
                            path("MRData.CircuitTable.Circuits.circuitId[0]");

        System.out.println("circuitId is: " + circuitId);

        // Then, retrieve the information known for that circuit and verify it is located in Australia
        given().
                pathParam("circuitId",circuitId).
                when().
                get("http://ergast.com/api/f1/circuits/{circuitId}.json").
                then().
                assertThat().
                body("MRData.CircuitTable.Circuits.Location[0].country",equalTo("Australia"));
    }

//    Reusing checks with ResponseSpecBuilder
//    https://techbeacon.com/app-dev-testing/how-perform-api-testing-rest-assured
    ResponseSpecification checkStatusCodeAndContentType =
            new ResponseSpecBuilder().
                    expectStatusCode(200).
                    expectContentType(ContentType.JSON).
                    build();

    @Test
    public void test_NumberOfCircuits_ShouldBe20_UsingResponseSpec() {

                given().
                when().
                    get("http://ergast.com/api/f1/2017/circuits.json").
                then().
                    assertThat().
                    spec(checkStatusCodeAndContentType).
                and().
                    body("MRData.CircuitTable.Circuits.circuitId",hasSize(20));
    }



    @Test
    public void demoQATest() {
        String dateTime = getDateAndTimeFormated();

        JSONObject requestParams = new JSONObject();
        requestParams.put("FirstName", "Virender" + dateTime); // Cast
        requestParams.put("LastName", "Singh" + dateTime);
        requestParams.put("UserName", "sdimpleuser2dd20111" + dateTime);
        requestParams.put("Password", "password");
        requestParams.put("Email", "sample" + dateTime + "@gmail.com");

        ResponseBody response =
                given()
                    .contentType(ContentType.JSON)
                    .body(requestParams.toMap())
                .when()
                    .post(RESTAPI_DEMOQA_COM_CUSTOMER_REGISTER)// Example POST method
                .then()
                    .statusCode(201)
                    .log().all()
                    .extract()
                    .response().getBody();

        Assert.assertEquals("Correct Success code was returned", response.jsonPath().get("SuccessCode"), "OPERATION_SUCCESS");

    }

    @Test
    public void demoQATestAlredyRegistered() {

        JSONObject requestParams = new JSONObject();
        requestParams.put("FirstName", "Virender"); // Cast
        requestParams.put("LastName", "Singh");
        requestParams.put("UserName", "444444");
        requestParams.put("Password", "password");
        requestParams.put("Email", "sample@gmail.com");

        ResponseBody response =
                given()
                    .contentType(ContentType.JSON)
                    .body(requestParams.toMap())
                .when()
                    .post(RESTAPI_DEMOQA_COM_CUSTOMER_REGISTER)
                .then()
                    .statusCode(200)
                    .log().all()
                    .extract()
                    .response().getBody();

        Assert.assertEquals("Don`t get FaultId", response.jsonPath().get("FaultId"), "User already exists");

    }
}

