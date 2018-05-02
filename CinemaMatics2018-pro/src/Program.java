import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean done = true;
		List<Movie> movies = new ArrayList<Movie>();
		DataManager dataManager = new DataManager();
		UserInterface ui = new UserInterface();
		Cinema myCinema = new Cinema("Sandrews");
		Theatre s1 = new Theatre("Salong1");
		Theatre s2 = new Theatre("Salong2");
		Theatre s3 = new Theatre("Salong3");
		Theatre s4 = new Theatre("Salong4");
		
		Movie m1 = new Movie();
		m1.setId(1);
		m1.setName("Terminator");
		m1.setDescription("I'll be back");
		
		Movie m2 = new Movie();
		m2.setId(2);
		m2.setName("Scarface");
		m2.setDescription("Say hello to my....");
		movies.add(m1);
		movies.add(m2);
		
		dataManager.addTheatre(s1);
		dataManager.addTheatre(s2);
		dataManager.addTheatre(s3);
		dataManager.addTheatre(s4);

		Show show1 = new Show(LocalDateTime.now(), LocalDateTime.now(), m1);
		dataManager.createShow(show1, "Salong1");
//		myCinema.addTheatre(s1);
//		myCinema.addTheatre(s2);
//		myCinema.addTheatre(s3);
//		myCinema.addTheatre(s4);
		
		
		while(!done) {
			
			ui.show_menu();
			Integer choice = UserInterface.inputInt();
			
			switch(choice) {
			case 1:
				System.out.println("Choice "+choice+" please");
				
				for (Theatre cT : dataManager.getTheatres()) {
					for(Show show : cT.getAllShows()) {
						System.out.println(show.toString());
					}
				}
				
				break;
			case 2:
				System.out.println("Choice "+choice+" please");
				String theatreName = UserInterface.getTheatreName();
				Theatre cT = dataManager.getTheatre(theatreName);
				for(Show show : cT.getAllShows()) {
					System.out.println(show.toString());
				}

				break;
			case 3:
				Show show = new Show();
				System.out.println("Choice "+choice+" please");
				System.out.println("MOVIES: ");
				for(Movie movie : movies) {
					System.out.println(movie.toString());
				}
				int movieId = UserInterface.enterMovieId();
				for(Movie movie : movies) {
					if(movie.getId() == movieId) {
						Movie showMovie = movie;
						show.setMovie(showMovie);
						break;
					}
				}
				for (Theatre cT2 : dataManager.getTheatres()) {
					
				}
				LocalDateTime startTime = UserInterface.readDate(null);
				LocalDateTime endTime = UserInterface.readDate(startTime);
				show.setStart(startTime);
				show.setEnd(endTime);
				dataManager.createShow(show, "Salong1");
				break;
			case 4:
				System.out.println("Choice "+choice+" please");
				for (Theatre cT3 : dataManager.getTheatres()) {
					
					for(Show show3 : cT3.getAllShows()) {
						System.out.println(show3.toString());
						show3.showAvailableSeats();
					}
				}
				int showId = UserInterface.getShowId();
				
				Theatre theatre = dataManager.getTheatreForShow(showId);
				Show show2 = theatre.getShow(showId);
				Booking booking = new Booking();
				booking.setShow(show2);
				System.out.println(dataManager.saveBooking(booking, 2, 2, showId, theatre.getName()));
				break;
			case 5:
				done = true;
				break;
			default:
				System.out.println("Ogiltigt val");
			}
		}
	}
}
