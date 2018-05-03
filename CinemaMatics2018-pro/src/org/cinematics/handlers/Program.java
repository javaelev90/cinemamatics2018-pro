package org.cinematics.handlers;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.cinematics.exceptions.OutOfSeatingBoundsException;
import org.cinematics.model.Booking;
import org.cinematics.model.Movie;
import org.cinematics.model.Seat;
import org.cinematics.model.Show;
import org.cinematics.model.Theatre;

public class Program {

	public static void main(String[] args) {
		boolean done = false;
		List<Movie> movies = new ArrayList<Movie>();
		DataManager dataManager = new DataManager();
		UserInterface ui = new UserInterface();
		setup(movies, dataManager);
		
		while(!done) {
			
			ui.show_menu();
			Integer choice = UserInterface.inputInt();
			
			switch(choice) {
			case 1:
				viewAllShows(dataManager);	
				break;
			case 2:
				viewAllShowInTheatre(dataManager);
				break;
			case 3:
				createShow(movies, dataManager);
				break;
			case 4:
				
				break;
			case 5:
				
				break;
			case 6:
				makeBooking(dataManager, choice); 
				break;
			case 7:
				done = true;
				break;
			default:
				System.out.println("Ogiltigt val");
			}
		}
	}

	public static void setup(List<Movie> movies, DataManager dataManager) {
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
		
		dataManager.addTheatre(new Theatre("Salong1"));
		dataManager.addTheatre(new Theatre("Salong2"));
		dataManager.addTheatre(new Theatre("Salong3"));
		dataManager.addTheatre(new Theatre("Salong4"));

		Show show = new Show(LocalDateTime.now(), LocalDateTime.now(), m1);
		dataManager.createShow(show, "Salong1");
	}

	public static void makeBooking(DataManager dataManager, Integer choice) {
		boolean doneWithBooking = false; 
		while(!doneWithBooking) {
			//Choose show
			System.out.println("Choice "+choice+" please");
			for (Theatre cT : dataManager.getTheatres()) {
				
				for(Show show : cT.getAllShows()) {
					System.out.println(show.toString());
					show.showAllSeats();
				}
			}
			int showId = UserInterface.getShowId();
			
			Theatre theatre = dataManager.getTheatreForShow(showId);
			if(theatre == null) {
				System.out.println("No such show id");
				break;
			}
			Show show = theatre.getShow(showId);

			int numberOfSeats = UserInterface.chooseNumberOfSeats();

			if(UserInterface.seatsTogether()) {
				System.out.println("Choose starting seat:");
				int startingRow = UserInterface.chooseSeatRow();
				if(startingRow == -1) return;
				int startingCol = UserInterface.chooseSeatCol();
				if(startingCol == -1) return;
				Seat[] seats = show.getSeats(startingRow, startingCol, numberOfSeats);
				try {
					if(show.areSeatsAvailable(seats)) {
						Booking booking = new Booking();
						booking.setShow(show);
						for(Seat currentSeat : seats) {
							dataManager.saveBooking(booking, currentSeat.row, currentSeat.col, show.getId(), theatre.getName());
						}
						show.showTickets(booking);
						break;
					} else {
						System.out.println("Those seats are not available");
						continue;
					}
				} catch (OutOfSeatingBoundsException e) {
					System.out.println("You can't sit on the floor, duuh.");
					continue;
				}
				
			} else {
				List<Seat> seats = new ArrayList<Seat>();
				while(!(seats.size() == numberOfSeats)) {
					int startingRow = UserInterface.chooseSeatRow();
					if(startingRow == -1) return;
					int startingCol = UserInterface.chooseSeatCol();
					if(startingCol == -1) return;
					try {
						Seat seat = new Seat(startingRow, startingCol);
						if(seats.contains(seat)) {
							System.out.println("You have already selected that seat");
							continue;
						}
						if(show.isSeatAvailable(startingRow, startingCol)) {
							seats.add(seat);
						} else {
							System.out.println("That seat is not available");
							continue;
						}
					} catch (OutOfSeatingBoundsException e) {
						System.out.println("You can't sit on the floor, duuh.");
						continue;

					}
				}
				Booking booking = new Booking();
				booking.setShow(show);
				for(Seat currentSeat : seats) {
					dataManager.saveBooking(booking, currentSeat.row, currentSeat.col, show.getId(), theatre.getName());
				}
				System.out.println("Booking succeeded");
//				show.showAllSeats();
				show.showTickets(booking);
			}
			doneWithBooking = true;
		}
	}

	public static void createShow(List<Movie> movies, DataManager dataManager) {
		Show show = new Show();
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
		System.out.println("Theatres: ");
		
		List<Theatre> theatres = dataManager.getTheatres();
		
		for (Theatre cT : theatres) {
			System.out.println(cT.getName());
		}
		String chosenTheatre = UserInterface.getTheatreName();
		Theatre theatre = dataManager.getTheatre(chosenTheatre);
		if(theatre == null) {
			System.out.println("That theatre does not exist.");
			return;
		}
		System.out.println("Leave blank to exit");
		LocalDateTime startTime = UserInterface.readDate(null);
		if(startTime.equals(LocalDateTime.MIN) ) {
			return;
		}
		LocalDateTime endTime = UserInterface.readDate(startTime);
		if(endTime.equals(LocalDateTime.MIN)) {
			return;
		}
		List<Show> shows = theatre.getAllShows();
		List<Show> overlappingShows = new ArrayList<Show>();
		for(Show currentShow : shows) {
			if(currentShow.checkOverlap(startTime) || currentShow.checkOverlap(endTime)) {
				overlappingShows.add(currentShow);
			}
		}
		if(!overlappingShows.isEmpty()) {
			System.out.println("Show is overlapping with :");
			overlappingShows.forEach(System.out::println);
			return;
		}

		show.setStart(startTime);
		show.setEnd(endTime);
		dataManager.createShow(show, chosenTheatre);
	}

	public static void viewAllShowInTheatre(DataManager dataManager) {
		String theatreName = UserInterface.getTheatreName();
		Theatre cT = dataManager.getTheatre(theatreName);
		
		if(cT == null) {
			System.out.println("That theatre does not exist.");
			return;
		}
		if(cT.getAllShows().isEmpty()) {
			System.out.println("There are no shows");
			return;
		}
		for(Show show : cT.getAllShows()) {
			System.out.println(show.toString());
		}
		
	}

	public static void viewAllShows(DataManager dataManager) {
		System.out.println("All shows:");
		for (Theatre cT : dataManager.getTheatres()) {
			System.out.println("-"+cT.toString());
			for(Show show : cT.getAllShows()) {
				System.out.println("---"+show.toString());
			}
			System.out.println("");
		}
	}
	
	
}
