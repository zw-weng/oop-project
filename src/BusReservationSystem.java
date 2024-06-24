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
        int choice = showOptionDialog("Are you an admin or a passenger?", "Login", options);

        if (choice == 0) {
            adminLogin();
        } else {
            passengerLogin();
        }
    }

    private void adminLogin() {
        while (true) {
            String email = showInputDialog("Enter email:");
            if (email == null) {
                start();
            }
            String password = showInputDialog("Enter password:");
            if (password == null) {
                start();
            }

            Admin admin = authenticateAdmin(email, password);
            if (admin != null) {
                admin.menu(this);
            } else {
                showMessageDialog("Invalid credentials. Please try again.");
            }
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

    public void adminMenu(Admin admin) {
        String[] options = {"Manage Buses", "Manage Routes", "Manage Schedules", "View Bookings", "Profile", "Logout"};
        int choice;

        do {
            choice = showOptionDialog("Admin Menu", "Menu", options);

            switch (choice) {
                case 0 -> manageBuses(admin);
                case 1 -> manageRoutes(admin);
                case 2 -> manageSchedules(admin);
                case 3 -> showMessageDialog(admin.viewBooking());
                case 4 -> viewOrUpdateProfile(admin);
                case 5 -> {
                    showMessageDialog("Thank you for using our system");
                    return;
                }
            }
        } while (choice != 5);
    }

    private void manageBuses(Admin admin) {
        String[] options = {"Add Bus", "Delete Bus", "View Buses", "Back"};
        int choice;

        do {
            choice = showOptionDialog("Manage Buses", "Buses", options);

            switch (choice) {
                case 0 -> {
                    String busID = showInputDialog("Enter Bus ID:");
                    String model = showInputDialog("Enter Bus Model:");
                    int capacity = Integer.parseInt(showInputDialog("Enter Bus Capacity:"));
                    admin.addBus(new Bus(busID, model, capacity));
                    showMessageDialog("Bus added successfully.");
                }
                case 1 -> {
                    String busID = showInputDialog("Enter Bus ID to delete:");
                    admin.deleteBus(busID);
                    showMessageDialog("Bus deleted successfully.");
                }
                case 2 -> showMessageDialog(admin.viewBus());
            }
        } while (choice != 3);
    }

    private void manageRoutes(Admin admin) {
        String[] options = {"Add Route", "Delete Route", "View Routes", "Back"};
        int choice;

        do {
            choice = showOptionDialog("Manage Routes", "Routes", options);

            switch (choice) {
                case 0 -> {
                    String origin = showInputDialog("Enter Route Origin:");
                    String destination = showInputDialog("Enter Route Destination:");
                    double price = Double.parseDouble(showInputDialog("Enter Route Price:"));
                    admin.addRoute(new Route(origin, destination, price));
                    showMessageDialog("Route added successfully.");
                }
                case 1 -> {
                    String origin = showInputDialog("Enter Route Origin to delete:");
                    String destination = showInputDialog("Enter Route Destination to delete:");
                    admin.deleteRoute(origin, destination);
                    showMessageDialog("Route deleted successfully.");
                }
                case 2 -> showMessageDialog(admin.viewRoute());
            }
        } while (choice != 3);
    }

    private void manageSchedules(Admin admin) {
        String origin = showInputDialog("Enter Route Origin:");
        String destination = showInputDialog("Enter Route Destination:");
        Route route = admin.findRoute(origin, destination);

        if (route == null) {
            showMessageDialog("Route not found.");
            return;
        }

        String[] options = {"Add Schedule", "Delete Schedule", "View Schedules", "Back"};
        int choice;

        do {
            choice = showOptionDialog("Manage Schedules for route " + origin + " to " + destination, "Schedules", options);

            switch (choice) {
                case 0 -> {
                    String timing = showInputDialog("Enter Schedule Timing:");
                    int seatLimit = Integer.parseInt(showInputDialog("Enter Seat Limit:"));
                    admin.addSchedule(origin, destination, timing, seatLimit);
                    showMessageDialog("Schedule added successfully.");
                }
                case 1 -> {
                    String timing = showInputDialog("Enter Schedule Timing to delete:");
                    admin.deleteSchedule(origin, destination, timing);
                    showMessageDialog("Schedule deleted successfully.");
                }
                case 2 -> showMessageDialog(route.getScheduleDetails());
            }
        } while (choice != 3);
    }

    private void passengerLogin() {
        while (true) {
            String email = showInputDialog("Enter email:");
            if (email == null) {
                start();
            }
            String password = showInputDialog("Enter password:");
            if (password == null) {
                start();
            }

            Passenger passenger = authenticatePassenger(email, password);
            if (passenger != null) {
                passenger.menu(this);
            } else {
                showMessageDialog("Invalid credentials. Please try again.");
            }
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

    public void passengerMenu(Passenger passenger) {
        String[] options = {"View Routes", "New Booking", "View Bookings", "Cancel Booking", "Profile", "Logout"};
        int choice;

        do {
            choice = showOptionDialog("Passenger Menu", "Menu", options);

            switch (choice) {
                case 0 -> viewRoutes();
                case 1 -> newBooking(passenger);
                case 2 -> showMessageDialog(passenger.viewBooking());
                case 3 -> cancelBooking(passenger);
                case 4 -> viewOrUpdateProfile(passenger);
                case 5 -> {
                    showMessageDialog("Thank you for using our system");
                    return;
                }
            }
        } while (choice != 5);
    }

    private void viewRoutes() {
        StringBuilder sb = new StringBuilder("Available Routes:\n");
        for (Route route : routes) {
            sb.append(route.getOrigin()).append(" to ").append(route.getDestination()).append(" - ").append(route.getPrice()).append("\n");
        }
        showMessageDialog(sb.toString());
    }

    private void newBooking(Passenger passenger) {
        String origin = showInputDialog("Enter Route Origin:");
        String destination = showInputDialog("Enter Route Destination:");
        String timing = showInputDialog("Enter Schedule Timing:");
        int seats = Integer.parseInt(showInputDialog("Enter number of seats to book:"));

        passenger.newBooking(routes, reservations, origin, destination, timing, seats);
        showMessageDialog("Booking successful.");
    }

    private void cancelBooking(Passenger passenger) {
        String reservationID = showInputDialog("Enter Reservation ID to cancel:");
        passenger.cancelBooking(reservationID);
        showMessageDialog("Booking canceled.");
    }

    private void viewOrUpdateProfile(User user) {
        showMessageDialog(user.dispProfile());
        int choice = showOptionDialog("Do you want to update your profile?", "Profile", new String[]{"Yes", "No"});
        if (choice == 0) {
            updateProfile(user);
        }
    }

    private void updateProfile(User user) {
        String[] profileData = user.updateProfileData();
        String newName = showInputDialog("Enter new name:", profileData[0]);
        String newPwd = showInputDialog("Enter new password:", profileData[1]);
        String newEmail = showInputDialog("Enter new email:", profileData[2]);
        String newPhoneStr = showInputDialog("Enter new phone number:", profileData[3]);

        try {
            user.updateProfile(newName, newPwd, newEmail, newPhoneStr);
            showMessageDialog("Profile updated successfully.");
        } catch (IllegalArgumentException e) {
            showMessageDialog(e.getMessage());
        }
    }

    // Methods for interacting with JOptionPane
    public String showInputDialog(String message) {
        return JOptionPane.showInputDialog(message);
    }

    public String showInputDialog(String message, String initialSelectionValue) {
        return JOptionPane.showInputDialog(null, message, initialSelectionValue);
    }

    public void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public int showOptionDialog(String message, String title, String[] options) {
        return JOptionPane.showOptionDialog(null, message, title, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
    }

    public static void main(String[] args) {
        BusReservationSystem system = new BusReservationSystem();
        system.start();
    }
}
