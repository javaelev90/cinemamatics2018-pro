
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataManager {
	
	private Map<Integer, Show> shows;
	
	public DataManager() {
		shows = new HashMap<Integer, Show>();
	}
	
	/**
	 * 
	 * @param id for the show
	 * @return the Show
	 */
	public Show getShow(Integer id) {
		return shows.get(id);
	}
	
	/**
	 * 
	 * @return all shows
	 */
	public List<Show> getShows(){
		return shows.values().stream().collect(Collectors.toList());
	}
	
	/**
	 * 
	 * @param show
	 * @param id for the show
	 * @return check if the show already exists
	 */
	public boolean createShow(Show show, Integer id) {
		if(!shows.containsKey(id)) {
			shows.put(id, show);
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param booking
	 * @param showId
	 * @return if the booking could be made
	 */
	public boolean createBooking(Booking booking, Integer showId) {
		return false;
	}
	
	/**
	 * 
	 * @param bookingId
	 * @return if booking was found
	 */
	public boolean getBooking(Integer bookingId) {
		return false;
	}
	
}