import java.time.LocalDateTime;

// Describes a show in the theatre, Start, End and Movie

public class Show {
	private LocalDateTime start;
	private LocalDateTime end;
	private Movie movie;
	
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

}
