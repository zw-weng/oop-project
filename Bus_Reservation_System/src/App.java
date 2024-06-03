import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Program is starting");
        for (int i = 0; i < 5; i++) {
            Thread.sleep(100); 
            System.out.print(".");
        }
        System.out.println();
        System.out.println("Please enter your information:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        Passenger passenger = new Passenger(name, email);

        // Create some schedules
        List<Schedule> schedules = new ArrayList<>();
        schedules.add(new Schedule("10:00 AM", new Route("Start", "End"), 50)); // 50 seats available
        schedules.add(new Schedule("12:00 PM", new Route("Start", "End"), 50)); // 50 seats available
        schedules.add(new Schedule("02:00 PM", new Route("Start", "End"), 50)); // 50 seats available

        while (true) {
            System.out.println("Please choose an option:");
            System.out.println("1. Reserve a bus");
            System.out.println("2. View available schedule");
            System.out.println("3. Make a payment");
            System.out.println("4. View your reservations");
            System.out.println("5. Exit");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    // Display all schedules
                    System.out.println("Schedule List:");
                    for (int i = 0; i < schedules.size(); i++) {
                        System.out.println((i + 1) + ". " + schedules.get(i).timing);
                    }

                    System.out.println("Choose a schedule:");
                    int scheduleChoice = scanner.nextInt();

                    // Create a new booking with the chosen schedule
                    passenger.newBooking(schedules.get(scheduleChoice - 1));
                    System.out.println("Press Enter to return to the main menu.");
                    scanner.nextLine(); // Consume newline left-over
                    scanner.nextLine(); // Wait for user to press Enter
                    break;
                case 2:
                    Schedule.viewAllSchedules(schedules);
                    System.out.println("Press Enter to return to the main menu.");
                    scanner.nextLine(); // Consume newline left-over
                    scanner.nextLine(); // Wait for user to press Enter
                    break;
                case 3:
                    Payment payment = new Payment(100.0, passenger);
                    payment.processPayment();
                    break;
                case 4:
                    passenger.viewReservations();
                    System.out.println("Press Enter to return to the main menu.");
                    scanner.nextLine(); // Consume newline left-over
                    scanner.nextLine(); // Wait for user to press Enter
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
