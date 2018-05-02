import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

// Describes a show in the theatre, Start, End and Movie

public class Show implements Comparable <Show>{
	private LocalDateTime start;
	private LocalDateTime end;
	private Movie movie;
	private Seat[][] seat = new Seat[10][25];	// The seating arrangement

	
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
	 * @return the seat
	 */
	public Seat[][] getSeat() {
		return seat;
	}
	
	/**
	 * @param seat the seat to set
	 */
	public void setSeat(Seat[][] seat) {
		this.seat = seat;
	}

}
