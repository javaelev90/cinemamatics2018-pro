
public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		UserInterface ui = new UserInterface();
		Cinema myCinema = new Cinema("Sandrews");
		Theatre s1 = new Theatre("Salong1");
		Theatre s2 = new Theatre("Salong2");
		Theatre s3 = new Theatre("Salong3");
		Theatre s4 = new Theatre("Salong4");
		
		
		myCinema.addTheatre(s1);
		myCinema.addTheatre(s2);
		myCinema.addTheatre(s3);
		myCinema.addTheatre(s4);
		
		
		while(true) {
			
			ui.show_menu();
			Integer choice = UserInterface.inputInt();
			
			switch(choice) {
			case 1:
				System.out.println("Choice "+choice+" please");
				break;
			case 2:
				System.out.println("Choice "+choice+" please");
				break;
			case 3:
				System.out.println("Choice "+choice+" please");
				break;
			case 4:
				System.out.println("Choice "+choice+" please");
				break;
			default:
				System.out.println("Ogiltigt val");
			}
		}
	}
}
