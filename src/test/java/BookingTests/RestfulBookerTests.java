package BookingTests;

import DTO.BookingInfo;
import Helpers.BookingRequestHelpers;
import Helpers.ValidationHelpers;
import com.google.gson.GsonBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class RestfulBookerTests extends FrameworkInitializer {

    @Test
    public void CreateBookingRequestTests() {
        BookingInfo.bookingDates test = new BookingInfo.bookingDates();
        test.setCheckIn("2022-01-01");
        test.setCheckOut("2022-01-05");

        BookingInfo postBookingRequest = new BookingInfo("David",
                                                         "gray",
                                                         234,
                                                         true,
                                                         "Breakfast + lunch",
                                                         test);

        GsonBuilder gsonBuilder = new GsonBuilder();

        int id = BookingRequestHelpers.postRequest(gsonBuilder.create().toJson(postBookingRequest));

        BookingInfo verifyPost = BookingRequestHelpers.getBookingInfo(id);

        ValidationHelpers.verifyNewBooking(verifyPost, postBookingRequest);

        BookingRequestHelpers.invalidPostRequest(" ");
    }

    @Test
    public void CreateAndDeleteBookingTest() {
        BookingInfo.bookingDates bookingDates = new BookingInfo.bookingDates();
        bookingDates.setCheckIn("2022-03-01");
        bookingDates.setCheckOut("2022-03-02");

        BookingInfo postBookingRequest = new BookingInfo("Patrick",
                                                         "John",
                                                         89,
                                                         true,
                                                         "Breakfast + lunch",
                                                         bookingDates);

        GsonBuilder gsonBuilder = new GsonBuilder();

        int id = BookingRequestHelpers.postRequest(gsonBuilder.create().toJson(postBookingRequest));

        BookingInfo verifyPost = BookingRequestHelpers.getBookingInfo(id);

        ValidationHelpers.verifyNewBooking(verifyPost, postBookingRequest);

        BookingRequestHelpers.deleteBooking(id);

        ValidationHelpers.verifyBookingIsDeleted(id);
    }

    @Test
    public void CreateAndUpdateExistingBooking(){
        BookingInfo.bookingDates bookingDates = new BookingInfo.bookingDates();
        bookingDates.setCheckIn("2022-04-01");
        bookingDates.setCheckOut("2022-04-10");

        BookingInfo postBookingRequest = new BookingInfo("Dom",
                                                         "Bill",
                                                         560,
                                                         true,
                                                         "Breakfast + lunch",
                                                         bookingDates);

        GsonBuilder gsonBuilder = new GsonBuilder();

        int id = BookingRequestHelpers.postRequest(gsonBuilder.create().toJson(postBookingRequest));

        BookingInfo verifyPost = BookingRequestHelpers.getBookingInfo(id);

        ValidationHelpers.verifyNewBooking(verifyPost, postBookingRequest);

        Map<String,String> updateObject = new HashMap<>();
        updateObject.put("firstname","Dominic");
        updateObject.put("lastname","Gray");

        BookingRequestHelpers.updateBooking(id,gsonBuilder.create().toJson(updateObject));

        verifyPost = BookingRequestHelpers.getBookingInfo(id);

        Assert.assertEquals(verifyPost.getFirstname(), updateObject.get("firstname"));
        Assert.assertEquals(verifyPost.getLastname(), updateObject.get("lastname"));

        BookingRequestHelpers.invalidBookingUpdate(id,"foo");

        verifyPost = BookingRequestHelpers.getBookingInfo(id);

        Assert.assertEquals(verifyPost.getTotalPrice(), postBookingRequest.getTotalPrice());
    }
}