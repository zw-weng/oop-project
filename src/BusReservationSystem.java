import javax.swing.*;
import java.util.ArrayList;

public class BusReservationSystem {
    private ArrayList<Route> routes;
    private ArrayList<Bus> buses;
    private ArrayList<Reservation> reservations;
    private ArrayList<User> users;
    private ArrayList<Admin> admins;

    public BusReservationSystem() {
        routes = new ArrayList<>();
        buses = new ArrayList<>();
        reservations = new ArrayList<>();
        users = new ArrayList<>();
        admins = new ArrayList<>();
        seedData();
    }

    private void seedData() {
        // Seed with some initial data
        routes.add(new Route("City A", "City B", 50.0));
        routes.get(0).addSchedule("08:00 AM", 40);
        routes.get(0).addSchedule("02:00 PM", 40);

        buses.add(new Bus("Bus01", "Model X", 50));
        buses.add(new Bus("Bus02", "Model Y", 60));

        users.add(new Passenger("John Doe", "password", "john@example.com", 123456789));
        admins.add(new Admin("Admin User", "adminpass", "admin@example.com", 987654321, "A001", routes, buses, reservations));
    }

    public void start() {
        String[] options = {"Admin", "Passenger"};
        int choice = JOptionPane.showOptionDialog(null, "Are you an admin or a passenger?", "Login",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            adminLogin();
        } else {
            passengerLogin();
        }
    }

    private void adminLogin() {
        String email = JOptionPane.showInputDialog("Enter email:");
        String password = JOptionPane.showInputDialog("Enter password:");

        Admin admin = authenticateAdmin(email, password);
        if (admin != null) {
            adminMenu(admin);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid credentials.");
        }
    }

    private Admin authenticateAdmin(String email, String password) {
        for (Admin admin : admins) {
            if (admin.getEmail().equals(email) && admin.getPwd().equals(password)) {
                return admin;
            }
        }
        return null;
    }

    private void adminMenu(Admin admin) {
        String[] options = {"Manage Buses", "Manage Routes", "View Bookings", "Profile", "Logout"};
        int choice;

        do {
            choice = JOptionPane.showOptionDialog(null, "Admin Menu", "Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0 -> manageBuses(admin);
                case 1 -> manageRoutes(admin);
                case 2 -> admin.viewBooking();
                case 3 -> admin.updateProfile();
            }
        } while (choice != 4);
    }

    private void manageBuses(Admin admin) {
        String[] options = {"Add Bus", "Delete Bus", "View Buses", "Back"};
        int choice;

        do {
            choice = JOptionPane.showOptionDialog(null, "Manage Buses", "Buses",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0 -> {
                    String busID = JOptionPane.showInputDialog("Enter Bus ID:");
                    String model = JOptionPane.showInputDialog("Enter Bus Model:");
                    int capacity = Integer.parseInt(JOptionPane.showInputDialog("Enter Bus Capacity:"));
                    admin.addBus(new Bus(busID, model, capacity));
                }
                case 1 -> {
                    String busID = JOptionPane.showInputDialog("Enter Bus ID to delete:");
                    admin.deleteBus(busID);
                }
                case 2 -> admin.viewBus();
            }
        } while (choice != 3);
    }

    private void manageRoutes(Admin admin) {
        String[] options = {"Add Route", "Delete Route", "View Routes", "Back"};
        int choice;

        do {
            choice = JOptionPane.showOptionDialog(null, "Manage Routes", "Routes",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0 -> {
                    String origin = JOptionPane.showInputDialog("Enter Route Origin:");
                    String destination = JOptionPane.showInputDialog("Enter Route Destination:");
                    double price = Double.parseDouble(JOptionPane.showInputDialog("Enter Route Price:"));
                    admin.addRoute(new Route(origin, destination, price));
                }
                case 1 -> {
                    String origin = JOptionPane.showInputDialog("Enter Route Origin to delete:");
                    String destination = JOptionPane.showInputDialog("Enter Route Destination to delete:");
                    admin.deleteRoute(origin, destination);
                }
                case 2 -> admin.viewRoute();
            }
        } while (choice != 3);
    }

    private void passengerLogin() {
        String email = JOptionPane.showInputDialog("Enter email:");
        String password = JOptionPane.showInputDialog("Enter password:");

        Passenger passenger = authenticatePassenger(email, password);
        if (passenger != null) {
            passengerMenu(passenger);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid credentials.");
        }
    }

    private Passenger authenticatePassenger(String email, String password) {
        for (User user : users) {
            if (user instanceof Passenger && user.getEmail().equals(email) && user.getPwd().equals(password)) {
                return (Passenger) user;
            }
        }
        return null;
    }

    private void passengerMenu(Passenger passenger) {
        String[] options = {"View Routes", "New Booking", "View Bookings", "Cancel Booking", "Profile", "Logout"};
        int choice;

        do {
            choice = JOptionPane.showOptionDialog(null, "Passenger Menu", "Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0 -> viewRoutes();
                case 1 -> passenger.newBooking(routes, reservations);
                case 2 -> passenger.viewBooking();
                case 3 -> passenger.cancelBooking();
                case 4 -> passenger.updateProfile();
            }
        } while (choice != 5);
    }

    private void viewRoutes() {
        StringBuilder sb = new StringBuilder("Available Routes:\n");
        for (Route route : routes) {
            sb.append(route.getOrigin()).append(" to ").append(route.getDestination()).append(" - ").append(route.getPrice()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public static void main(String[] args) {
        BusReservationSystem system = new BusReservationSystem();
        system.start();
    }
}
