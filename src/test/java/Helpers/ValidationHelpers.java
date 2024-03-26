package Helpers;

import DTO.BookingInfo;
import io.restassured.response.Response;
import org.testng.Assert;

public class ValidationHelpers {

    public static void verifyNewBooking(BookingInfo expect, BookingInfo actual) {

        Assert.assertEquals(expect.getFirstname(), actual.getFirstname());
        Assert.assertEquals(expect.getLastname(), actual.getLastname());
        Assert.assertTrue(expect.isDepositPaid());
        Assert.assertEquals(expect.getBookingDates().getCheckIn(), actual.getBookingDates().getCheckIn());
    }

    public static void verifyBookingIsDeleted(int id) {

        Response response = BookingRequestHelpers.getResponse(String.format("/booking/%s", id));

        Assert.assertEquals(response.statusCode(), StatusCodes.NOT_FOUND.getId());
    }
}
