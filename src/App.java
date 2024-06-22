import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        App app = new App();
        app.login();
    }

    private List<Schedule> schedules;
    private Admin admin;
    private Passenger passenger;

    public App() {
        // Initialize schedules
        schedules = new ArrayList<>();
        schedules.add(new Schedule("10:00 AM", new Route("Start", "End"), 50)); // 50 seats available
        schedules.add(new Schedule("12:00 PM", new Route("Start", "End"), 50)); // 50 seats available
        schedules.add(new Schedule("02:00 PM", new Route("Start", "End"), 50)); // 50 seats available

        // Initialize admin (with some default credentials for simplicity)
        admin = new Admin("admin", "admin123", "admin@buscompany.com", 123456789, "A001");
    }

    // Method to handle user login
    public void login() {
        String[] options = { "Admin", "Passenger" };
        int choice = JOptionPane.showOptionDialog(null, "Login as:", "Login",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            loginAdmin();
        } else if (choice == 1) {
            loginPassenger();
        } else {
            JOptionPane.showMessageDialog(null, "Exiting the application.");
            System.exit(0);
        }
    }

    // Method to handle admin login
    public void loginAdmin() {
        String username = JOptionPane.showInputDialog("Enter admin username:");
        String password = JOptionPane.showInputDialog("Enter admin password:");

        // Add logic for validating admin credentials
        if (username.equals(admin.getName()) && password.equals(admin.getPwd())) {
            adminMenu();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid admin credentials. Exiting the application.");
            System.exit(0);
        }
    }

    // Method to handle passenger login
    public void loginPassenger() {
        String name = JOptionPane.showInputDialog("Enter your name:");
        String email = JOptionPane.showInputDialog("Enter your email:");

        // Create a new Passenger object
        passenger = new Passenger(name, email);

        passengerMenu();
    }

    // Method to handle admin menu
    public void adminMenu() {
        while (true) {
            String[] options = { "View Bus", "View Routes", "View Bookings", "Logout" };
            int choice = JOptionPane.showOptionDialog(null, "Choose an option:", "Admin Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:
                    // Logic to view bus (not implemented in the original code)
                    admin.viewBus();
                    break;
                case 1:
                    // Logic to view routes (not implemented in the original code)
                    admin.viewRoute();
                    break;
                case 2:
                    // Logic to view bookings (not implemented in the original code)
                    admin.viewBooking();
                    break;
                case 3:
                    logout();
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
            }
        }
    }

    // Method to handle passenger menu
    public void passengerMenu() {
        while (true) {
            String[] options = { "Reserve a Bus", "View Available Schedule", "Make a Payment", "View Reservations",
                    "Logout" };
            int choice = JOptionPane.showOptionDialog(null, "Choose an option:", "Passenger Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:
                    // Display all schedules with route and price
                    StringBuilder scheduleList = new StringBuilder("Schedule List:\n");
                    for (int i = 0; i < schedules.size(); i++) {
                        Schedule schedule = schedules.get(i);
                        double price = schedule.getPrice(); // Get the price using the getPrice() method
                        scheduleList.append((i + 1)).append(". ")
                                .append("Timing: ").append(schedule.timing)
                                .append(", Route: ").append(schedule.route)
                                .append(", Price: $").append(price)
                                .append("\n");
                    }
                    String input = JOptionPane.showInputDialog(scheduleList.toString() + "Choose a schedule:");
                    int scheduleChoice = Integer.parseInt(input);

                    // Create a new booking with the chosen schedule
                    passenger.newBooking(schedules.get(scheduleChoice - 1));
                    break;
                case 1:
                    // View available schedules
                    StringBuilder schedule1List = new StringBuilder("Available Schedules:\n");
                    for (int i = 0; i < schedules.size(); i++) {
                        Schedule schedule = schedules.get(i);
                        double price = schedule.getPrice(); // Get the price using the getPrice() method
                        schedule1List.append((i + 1)).append(". ")
                                .append("Timing: ").append(schedule.timing)
                                .append(", Route: ").append(schedule.route)
                                .append(", Price: $").append(price)
                                .append("\n");
                    }
                    JOptionPane.showMessageDialog(null, schedule1List.toString());
                    break;

                case 2:
                    // View reservations
                    StringBuilder reservationList = new StringBuilder("Your Reservations:\n");
                    for (int i = 0; i < passenger.bookingList.size(); i++) {
                        Reservation reservation = passenger.bookingList.get(i);
                        reservationList.append((i + 1)).append(". ")
                                .append("Schedule: ").append(reservation.schedule.timing)
                                .append(", Route: ").append(reservation.schedule.route)
                                .append(", Price: $").append(reservation.schedule.getPrice())
                                .append("\n");
                    }

                    JOptionPane.showMessageDialog(null, reservationList.toString());

                    // Check user balance
                    double currentBalance = passenger.balance;
                    JOptionPane.showMessageDialog(null, "Your current balance: $" + currentBalance);

                    // Make a payment
                    String amountStr = JOptionPane.showInputDialog("Enter the payment amount:");
                    double amount = Double.parseDouble(amountStr);
                    if (currentBalance >= amount) {
                        // Deduct the payment amount from the balance
                        passenger.balance -= amount;
                        JOptionPane.showMessageDialog(null,
                                "Payment successful! Remaining balance: $" + passenger.balance);
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient balance. Please top up your account.");
                    }
                    break;

                case 3:
                    // View reservations
                    passenger.viewReservations();
                    break;
                case 4:
                    logout();
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
            }
        }
    }

    // Method to handle user logout
    public void logout() {
        JOptionPane.showMessageDialog(null, "Logging out...");
        System.exit(0);
    }
}
