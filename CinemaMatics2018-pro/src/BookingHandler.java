import java.util.ArrayList;
import java.util.List;

public class BookingHandler {
	List<Booking> myBookings;
	
	public BookingHandler() {
		myBookings = new ArrayList<>();
	}
	
	public void addBooking(Booking b) {
		myBookings.add(b);
	}
	
	public Booking getBooking(int bookingId) {
		
		for (Booking cB : myBookings) {
			if (bookingId == cB.getBookingId()) {
				return cB;
			}
		}
		// needs to be fixed....
		return null;
	}
	
	public void printAllBookings() {
	
	}
}
