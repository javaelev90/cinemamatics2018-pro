import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class UserInterface {
	
	static BufferedReader con = new BufferedReader(new InputStreamReader(System.in));
	
	public void show_menu() {
		System.out.println("-----------------------------------------------------");
		System.out.println("- (1) Visa alla föreställningar för alla salonger   -");
		System.out.println("- (2) Visa alla föreställningar för specifik salong -");
		System.out.println("- (3) Skapa föreställning                           -");
		System.out.println("- (4) Boka biljett                                  -");
		System.out.println("- (5) Exit                                          -");
		System.out.println("-----------------------------------------------------");
	}
	
	// Get Int from console
	public static int inputInt() {
		int myInt = 0;
		try {
			myInt = Integer.parseInt(con.readLine());
		} catch (NumberFormatException | IOException e) {
			System.out.println("Bad input");
			myInt = inputInt();
		}

		return myInt;
	}
	
	public static String getUserInputString() {
		String input = "";
		
		try {
			input = con.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return input;
	}
	
	public static String getTheatreName() {
		System.out.println("Ange salongsnamn: ");
		return getUserInputString();
	}
	
	public static int getShowId() {
		System.out.println("Ange föreställningsid: ");
		return inputInt();
	}
	
	public static int enterMovieId() {
		System.out.println("Enter movie id");
		return inputInt();
	}
	

	// ----------------------------------------------------------------------------
	// Create a new LDT object or reinstance a new time from an existing one
	// Copying year/month/date to save input
	public static LocalDateTime readDate(LocalDateTime dateIn) {

		LocalDateTime dt;

		if (dateIn != null) {
			// Dealing with booking end here, we get an starting time in
			try {

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm");
				String ymd = formatter.format(dateIn);

				System.out.print("Input end time in format <yyyyMMdd> [HH:mm] :");

				String time = con.readLine();
				if (time.length() == 0)
					return LocalDateTime.MIN; // Special case, We deliberately input nothing

				// Did not input atleast HH:mm
				if (time.length() < 5) {
					System.out.println("You need to input at the very least a HH:mm time");
					return null;
				}

				int dateLen = "yyyyMMdd HH:mm".length();
				dateLen -= time.length();
				// Reuse datestring from start time
				dt = LocalDateTime.parse(ymd.substring(0, dateLen) + time, formatter);

				// dt = LocalDateTime.parse(ymd+time, formatter);
			} catch (IOException e) {
				// Console readline error, nothing to do if we cannot read the console
				e.printStackTrace();
				return null;
			} catch (DateTimeParseException e) {
				System.out.println("That is not a valid end time.");
				return null;
			}
		} else {
			try {
				System.out.print("Slot starts at [yyyyMMdd HH:mm] :");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm");

				String time = con.readLine();
				if (time.length() == 0)
					return LocalDateTime.MIN; // We deliberately input nothing, special case, we want to exit

				dt = LocalDateTime.parse(time, formatter);
			} catch (IOException e) {
				// Console readline error, nothing for us to do
				e.printStackTrace();
				return null;
			} catch (DateTimeParseException e) {
				System.out.println("That is not a valid date/time input.");
				return null;
			}
		}
		return dt;
	} // end ReadDate

	public static int chooseSeatCol() {
		System.out.println("Choose seat column");
		return inputInt();
	}

	public static int chooseSeatRow() {
		System.out.println("Choose seat row");
		return inputInt();
	}
	
	public static int chooseNumberOfSeats() {
		System.out.println("Choose number of seats");
		return inputInt();
	}

	public static boolean seatsTogether() {
		System.out.println("Seats together? (y/n)");
		String answer = getUserInputString();
		if(answer.equals("y")) {
			return true;
		} else if(answer.equals("n")) {
			return false;
		} else {
			System.out.println("You have to choose y or n!");
			return seatsTogether();
		}
	}

	
	
	

}
