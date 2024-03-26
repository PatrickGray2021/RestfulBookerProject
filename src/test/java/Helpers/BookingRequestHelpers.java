package Helpers;

import DTO.BookingInfo;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BookingRequestHelpers {

    public static Response getResponse(String endpoint) {
        RestAssured.defaultParser = Parser.JSON;
        return given().contentType(ContentType.JSON).when().get(endpoint);
    }

    public static BookingInfo getBookingInfo(int id) {
        GsonBuilder builder = new GsonBuilder();
        return builder.create().fromJson(getResponse(String.format("/booking/%s", id)).getBody().prettyPrint(), BookingInfo.class);
    }

    public static int postRequest(String request) {
        Response response = given().contentType(ContentType.JSON).body(request).when().post("/booking");
        Assert.assertEquals(response.statusCode(), StatusCodes.OK.getId());
        return response.getBody().jsonPath().getInt("bookingid");
    }

    public static void invalidPostRequest(String request) {
        Response response = given().contentType(ContentType.JSON).body(request).when().post("/booking");
        Assert.assertEquals(response.statusCode(), StatusCodes.BAD_REQUEST.getId());
    }

    public static void updateBooking(int id, String updateValues) {
        Response response = given().contentType(ContentType.JSON)
                                   .header("Cookie", createToken())
                                   .body(updateValues)
                                   .patch(String.format("/booking/%s", id));

        Assert.assertEquals(response.statusCode(), StatusCodes.OK.getId());
    }

    public static void invalidBookingUpdate(int id, String updateValues) {
        Response response = given().contentType(ContentType.JSON)
                                   .header("Cookie", createToken())
                                   .body(updateValues)
                                   .patch(String.format("/booking/%s", id));

        Assert.assertEquals(response.statusCode(), StatusCodes.BAD_REQUEST.getId());
    }

    public static void deleteBooking(int id) {
        Response response = given().contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Cookie", createToken())
                                   .delete(String.format("/booking/%s", id));
        // bug with status code
        Assert.assertEquals(response.statusCode(), StatusCodes.CREATED.getId());
    }

    private static String createToken() {
        Map<String, String> auth = new HashMap<>();
        auth.put("username", "admin");
        auth.put("password", "password123");

        GsonBuilder gsonBuilder = new GsonBuilder();

        Response response = given().contentType(ContentType.JSON).body(gsonBuilder.create().toJson(auth)).when().post("/auth");

        Assert.assertEquals(response.statusCode(), StatusCodes.OK.getId());

        return "token=" + response.getBody().jsonPath().getString("token");
    }
}
