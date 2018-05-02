
public class Seat {

	private Integer bookingId;
	private Integer rowId;
	private Integer colId;

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	
	public boolean isOccupied() {
		return (bookingId == null) ? false : true;
	}

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public Integer getColId() {
		return colId;
	}

	public void setColId(Integer colId) {
		this.colId = colId;
	}
	
	
	
}
