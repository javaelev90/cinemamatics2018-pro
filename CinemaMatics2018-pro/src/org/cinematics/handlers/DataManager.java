package org.cinematics.handlers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.cinematics.db.DbQueryHelper;
import org.cinematics.model.Booking;
import org.cinematics.model.Movie;
import org.cinematics.model.Show;
import org.cinematics.model.Theatre;
/**
 * This class should be used to store all data that the cinema program needs
 * 
 *
 */
public class DataManager {
	
	private Map<String, Theatre> theatres;
	private Map<Integer, Booking> bookings;
	private Set<Movie> movies;
	
	public DataManager() {
		theatres = new TreeMap<String, Theatre>();
		bookings = new TreeMap<Integer, Booking>();
		movies = new TreeSet<Movie>(Comparator.comparing(Movie::getName));
	}
	
	public Theatre getTheatre(String name) {
		return theatres.get(name);
	}
	
	public Set<Movie> getAllMovies(){
		String queryMovies = "SELECT * FROM cinema.movies;";
		ResultSet result = DbQueryHelper.executePreparedStatementQuery(queryMovies).get();
		try {
			while(result.next()) {
				Movie movie = new Movie();
				movie.setId(result.getInt("id"));
				movie.setName(result.getString("name"));
				movie.setDescription(result.getString("description"));
				movies.add(movie);
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
		
		return movies;
	}
	
	public boolean addMovie(Movie movie) {
		String queryMovies = "SELECT * FROM cinema.movies WHERE name = ?;";
		if(doesEntryExist(queryMovies, movie.getName())) {
			return false;
		}
		String insertMovieString = "INSERT INTO cinema.movies (name, description) VALUES (?,?);";
		long result = DbQueryHelper.executePreparedStatementUpdate(insertMovieString, movie.getName(), movie.getDescription());
		return result > 0;
	}
	
	
	public List<Theatre> getTheatres(){
		return theatres.values().stream().collect(Collectors.toList());
	}
	
	public boolean addTheatre(Theatre theatre) {
		String queryTheatres = "SELECT * FROM cinema.theatres WHERE name = ?;";
		if(doesEntryExist(queryTheatres, theatre.getName())) {
			return false;
		}
		String insertTheatreString = "INSERT INTO cinema.theatres (name, seat_rows, seat_cols) VALUES (?,?,?);";
		long result = DbQueryHelper.executePreparedStatementUpdate(insertTheatreString, theatre.getName(), theatre.SEAT_ROWS, theatre.SEAT_COLS);
		return result > 0;
	}
	
	public boolean addShowToTheatre(Show show, String theatreName) {
		if(theatres.containsKey(theatreName)) {
			Theatre theatre = theatres.get(theatreName);
			theatre.addShow(show);
			return true;
		}
		return false;
	}
	
	
	public boolean saveBooking(Booking booking, Integer row, Integer col, Integer showId, String theatreName) {
		if(theatres.containsKey(theatreName)) {
			Theatre theatre = theatres.get(theatreName);
			Show show = theatre.getShow(showId);
			show.getBookings()[row][col] = booking;
			bookings.put(booking.getBookingId(), booking);
			return true;
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
	
	private boolean doesEntryExist(String query, Object... values) {
		ResultSet resultSet = DbQueryHelper.executePreparedStatementQuery(query, values).get();
		try {
			if(resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
		return false;
	}
}