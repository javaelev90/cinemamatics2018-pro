
public class Booking {
	private int bookingId;
	private Customer myCust;
	private Show myShow;
	private Seat mySeat;
	
		
	public void setCustomer(Customer c) {
		myCust = c;
	}
	public Customer getCustomer() {
		return myCust;
	}
	
	public void setShow(Show s) {
		myShow = s;
	}
	public Show getShow() {
		return myShow;
	}
	
	public void setSeat(Seat s) {
		mySeat = s;
	}
	public Seat getSeat() {
		return mySeat;
	}
	
	public void setBookingId(int id) {
		bookingId = id;
	}
	public int getBookingId() {
		return bookingId;
	}
}
