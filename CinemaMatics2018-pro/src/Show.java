import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

// Describes a show in the theatre, Start, End and Movie

public class Show implements Comparable <Show>{
	private static int ID_COUNTER = 1;
	private Integer id; 
	private LocalDateTime start;
	private LocalDateTime end;
	private Movie movie;
	private Booking[][] bookings = new Booking[5][10];	// The seating arrangement

	public Show() {
		id = ID_COUNTER;
		ID_COUNTER++;
		
	}
	
	public Show(LocalDateTime start, LocalDateTime end, Movie movie) {
		this.start = start;
		this.end = end;
		this.movie = movie;
		
		id = ID_COUNTER;
		ID_COUNTER++;
	}
	
	//Make this object sortable in an arraylist
	@Override
	public int compareTo(Show ob) {
		if(this.start.isEqual(ob.getStart()))
			return 0;
		
		if(this.start.isBefore(ob.getStart()))
			return -1;

		return 1;
	}
	
	public long getDuration() {
		return this.getStart().until(this.getEnd(), ChronoUnit.MINUTES);
	}
	
	public Booking[][] getBookings() {
		return bookings;
	}

	public boolean checkOverlap(LocalDateTime chkTime) {
		return start.isBefore(chkTime) && chkTime.isBefore(end);
	}
	
	/**
	 * @return the start
	 */
	public LocalDateTime getStart() {
		return start;
	}
	
	/**
	 * @param start the start to set
	 */
	public void setStart(LocalDateTime start) {
		this.start = start;
	}
	
	/**
	 * @return the end
	 */
	public LocalDateTime getEnd() {
		return end;
	}
	
	/**
	 * @param end the end to set
	 */
	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
	
	/**
	 * @return the movie
	 */
	public Movie getMovie() {
		return movie;
	}
	
	/**
	 * @param movie the movie to set
	 */
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	

	/**
	 * 
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 
	 * @param show is
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	 @Override
	 public String toString() {
		 return "ID:" + this.id  + " Namn:" + this.movie.getName() + " Start:" + this.start.toString() + " Slut:" + this.end.toString(); 
	 }
	
	public void showAvailableSeats() {
		for(int row = 0; row < bookings.length; row++) {
			for(int col = 0; col < bookings[row].length; col++) {
				
				System.out.print((bookings[row][col]!=null)?"X":"O");
			}
			System.out.println("");
		}
	}

}
