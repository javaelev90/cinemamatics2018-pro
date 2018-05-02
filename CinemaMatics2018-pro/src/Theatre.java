
// Describes the Theatre studio where the movie is shown

public class Theatre {
	private String name;						// The studio might have a name like "Blue Room"
	private Show show;							// The show that is booked for the studio
	private Seat[][] seat = new Seat[10][25];	// The seating arrangement
	

	// Constructor
	public Theatre(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the show
	 */
	public Show getShow() {
		return show;
	}
	/**
	 * @param show the show to set
	 */
	public void setShow(Show show) {
		this.show = show;
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
