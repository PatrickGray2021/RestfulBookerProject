package BookingTests;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import org.testng.annotations.BeforeSuite;

public class FrameworkInitializer {

    @BeforeSuite
    public void frameworkSetUp() {
        RestAssured.config = RestAssuredConfig.config().sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }
}