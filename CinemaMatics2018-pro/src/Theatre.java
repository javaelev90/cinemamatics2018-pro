
// Describes the Theatre studio where the movie is shown

public class Theatre {
	private String name;						// The studio might have a name like "Blue Room"
	private Show show;							// The show that is booked for the studio
	

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
}
