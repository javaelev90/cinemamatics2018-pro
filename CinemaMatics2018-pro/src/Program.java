
public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		UserInterface ui = new UserInterface();
		
		while(true) {
			
			ui.show_menu();
			Integer choice = UserInterface.inputInt();
			
			switch(choice) {
			case 1:
				System.out.println("Choice "+choice+" please");
				break;
				
			default:
				System.out.println("Hello world!");
			}
		}
	}
}
