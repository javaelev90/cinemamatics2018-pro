
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class DataManager {
	
	private Map<String, Theatre> theatres;
	private Map<Integer, Booking> bookings;
	
	public DataManager() {
		theatres = new TreeMap<String, Theatre>();
		bookings = new TreeMap<Integer, Booking>();
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
		if(theatres.containsKey(theatreName)) {
			Theatre theatre = theatres.get(theatreName);
			theatre.addShow(show);
			return true;
		}
		return false;
	}
	
	
	public boolean saveBooking(Booking booking,Integer row, Integer col, Integer showId, String theatreName) {
		if(theatres.containsKey(theatreName)) {
			Theatre theatre = theatres.get(theatreName);
			Show show = theatre.getShow(showId);
//			Booking[][] bkings = show.getBookings();
			show.getBookings()[row][col] = booking;
			bookings.put(booking.getBookingId(), booking);
			return true;
//			if(bkings[row][col] == null) {
//				bkings[row][col] = booking;
//				
//			}
		}
		return false;
	}
	
	public Theatre getTheatreForShow(Integer showId) {
		for(Theatre theatre : getTheatres()) {
			for(Show show : theatre.getAllShows()) {
				if(show.getId() == showId) {
					return theatre;
				}
			}
		}
		return null;
	}
	
	public Booking getBooking(Integer bookingId) {
		
		return bookings.get(bookingId);
	}
	
}