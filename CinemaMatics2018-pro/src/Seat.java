
public class Seat {

	private Integer bookingId;

	public Integer getBookingNr() {
		return bookingId;
	}

	public void setBookingNr(Integer bookingId) {
		this.bookingId = bookingId;
	}
	
	public boolean isOccupied() {
		return (bookingId == null) ? false : true;
	}
	
}
