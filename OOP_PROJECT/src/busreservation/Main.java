package busreservation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bus busService = new Bus();
        Reservation resService = new Reservation();

        while (true) {
            System.out.println("1. Add Bus");
            System.out.println("2. Remove Bus");
            System.out.println("3. View All Buses");
            System.out.println("4. Search Bus");
            System.out.println("5. Update Bus");
            System.out.println("6. Book Ticket");
            System.out.println("7. View Reservations");
            System.out.println("8. Cancel Reservation");
            System.out.println("9. Update Reservation");
            System.out.println("10. Check Seat Availability");  
            System.out.println("11. Exit");          
            System.out.print("Choose: ");
            

            int choice = sc.nextInt();
            sc.nextLine();

            
        switch (choice) {
            case 1: busService.addBus(); break;
            case 2: busService.removeBus(); break;
            case 3: busService.viewBuses(); break;
            case 4: busService.searchBus(); break;
            case 5: busService.updateBus(); break;
            case 6: resService.bookTicket(); break;
            case 7: resService.viewReservations(); break;
                    
            case 8: resService.cancelReservation(); break;
            case 9: resService.updateReservation(); break;
            case 10:  
            System.out.print("Enter Bus ID to Check Seats: ");
            int busId = sc.nextInt(); sc.nextLine();
             resService.checkSeatAvailability(busId);  
             break;
            case 11:
                    System.out.println("Program Finished");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice!");
             
        }

        }
    }
}


