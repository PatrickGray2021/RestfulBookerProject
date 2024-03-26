package DTO;

import com.google.gson.annotations.SerializedName;

public class BookingInfo {

    @SerializedName("firstname")
    private String firstname;
    @SerializedName("lastname")
    private String lastname;
    @SerializedName("totalprice")
    private int totalPrice;
    @SerializedName("depositpaid")
    private boolean depositPaid;
    @SerializedName("additionalneeds")
    private String additionalNeeds;
    private bookingDates bookingdates;

    public BookingInfo(String firstname, String lastname, int totalPrice, boolean depositPaid, String additionalNeeds, bookingDates bookingdates) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalPrice = totalPrice;
        this.depositPaid = depositPaid;
        this.additionalNeeds = additionalNeeds;
        this.bookingdates = bookingdates;
    }

    public BookingInfo() {
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public boolean isDepositPaid() {
        return depositPaid;
    }

    public String getAdditionalNeeds() {
        return additionalNeeds;
    }
    
    public bookingDates getBookingDates(){
      return bookingdates;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setDepositPaid(boolean depositPaid) {
        this.depositPaid = depositPaid;
    }

    public void setAdditionalNeeds(String additionalNeeds) {
        this.additionalNeeds = additionalNeeds;
    }

    public void setBookingdates(bookingDates bookingdates) {
        this.bookingdates = bookingdates;
    }

    public static class bookingDates{
        @SerializedName("checkin")
         private String checkIn;
        @SerializedName("checkout")
         private String checkOut;

        public String getCheckIn() {
            return checkIn;
        }

        public String getCheckOut() {
            return checkOut;
        }

        public void setCheckIn(String checkIn) {
            this.checkIn = checkIn;
        }

        public void setCheckOut(String checkOut) {
            this.checkOut = checkOut;
        }
    }
}