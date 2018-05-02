
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataManager {
	
	private Map<String, Theatre> theatres;
	private Map<Integer, Booking> bookings;
	
	public DataManager() {
		theatres = new HashMap<String, Theatre>();
		bookings = new HashMap<Integer, Booking>();
	}
	
	
	public Theatre getTheatre(String name) {
		return theatres.get(name);
	}
	
	
	public List<Theatre> getTheatres(){
		return theatres.values().stream().collect(Collectors.toList());
	}
	
	public boolean addTheatre(Theatre theatre) {
		if(!theatres.containsKey(theatre.getName())) {
			theatres.put(theatre.getName(), theatre);
			return true;
		}
		return false;
	}
	
	public boolean createShow(Show show, String theatreName) {
		if(!theatres.containsKey(theatreName)) {
			Theatre theatre = theatres.get(theatreName);
			theatre.addShow(show);
			return true;
		}
		return false;
	}
	
	
	public boolean createBooking(Booking booking, Integer showId, String theatreName) {
		if(theatres.containsKey(theatreName)) {
			Theatre theatre = theatres.get(theatreName);
			Show show = theatre.getShow(showId);
			Seat[][] seats = show.getSeat();
			Seat seat = seats[booking.getSeat().getRowId()][booking.getSeat().getColId()];
			if(!seat.isOccupied()) {
				seat.setBookingId(booking.getBookingId());
				bookings.put(booking.getBookingId(), booking);
				return true;
			}
		}
		return false;
	}
	
	
	public Booking getBooking(Integer bookingId) {
		
		return bookings.get(bookingId);
	}
	
}