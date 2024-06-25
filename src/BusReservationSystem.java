import javax.swing.*;
import java.util.ArrayList;

public class BusReservationSystem {
    private ArrayList<Route> routes;
    private ArrayList<Bus> buses;
    private ArrayList<Reservation> reservations;
    private ArrayList<Passenger> users;
    private ArrayList<Admin> admins;

    public BusReservationSystem() {
        routes = new ArrayList<>();
        buses = new ArrayList<>();
        reservations = new ArrayList<>();
        users = new ArrayList<>();
        admins = new ArrayList<>();
        seedData();
    }

    public void seedData() {
        // Seed with some initial data
        Route route1 = new Route("City A", "City B", 50.0);
        Route route2 = new Route("City B", "City C", 75.0);
        route1.addSchedule("08:00 AM", 40, 40);
        route1.addSchedule("02:00 PM", 40, 40);
        route2.addSchedule("09:00 AM", 50, 50);
        route2.addSchedule("03:00 PM", 50, 50);
        routes.add(route1);
        routes.add(route2);

        buses.add(new Bus("Bus01", "Model X", 50));
        buses.add(new Bus("Bus02", "Model Y", 60));

        Passenger passenger1 = new Passenger("John Doe", "password", "john@example.com", 123456789);
        Passenger passenger2 = new Passenger("Jane Smith", "password123", "jane@example.com", 987654321);
        users.add(passenger1);
        users.add(passenger2);

        Admin admin = new Admin("Admin User", "adminpass", "admin@example.com", 987654321, "A001", routes, buses,
                reservations, users);
        admins.add(admin);

        // Create some initial reservations
        passenger1.newBooking(routes, reservations, "City A", "City B", "08:00 AM", 2);
        passenger2.newBooking(routes, reservations, "City B", "City C", "09:00 AM", 3);
    }

    public void start() {
        String[] options = { "Admin", "Passenger" };
        int choice = showOptionDialog("Are you an admin or a passenger?", "Login", options);

        if (choice == 0) {
            adminLogin();
        } else if (choice == 1) {
            passengerLogin();
        } else {
            // Handle if user cancels the initial dialog
            showMessageDialog("Thank you for using our system!");
            System.exit(0);
        }
    }

    public void adminLogin() {
        while (true) {
            String email = showInputDialog("Enter email:");
            if (email == null) {
                start();
                return; // User cancelled, return to login page
            }
            String password = showInputDialog("Enter password:");
            if (password == null) {
                start();
                return; // User cancelled, return to login page
            }

            Admin admin = authenticateAdmin(email, password);
            if (admin != null) {
                admin.menu(this);
                return;
            } else {
                showMessageDialog("Invalid credentials. Please try again.");
            }
        }
    }

    public Admin authenticateAdmin(String email, String password) {
        for (Admin admin : admins) {
            if (admin.getEmail().equals(email) && admin.getPwd().equals(password)) {
                return admin;
            }
        }
        return null;
    }

    public void adminMenu(Admin admin) {
        String[] options = { "Manage Buses", "Manage Routes", "Manage Schedules", "View Bookings", "View Passengers",
                "Profile", "Logout" };
        int choice;

        do {
            choice = showOptionDialog("Admin Menu", "Menu", options);

            switch (choice) {
                case 0 -> manageBuses(admin);
                case 1 -> manageRoutes(admin);
                case 2 -> manageSchedules(admin);
                case 3 -> showMessageDialog(admin.viewBooking());
                case 4 -> showMessageDialog(admin.viewUser());
                case 5 -> viewOrUpdateProfile(admin);
                case 6 -> {
                    showMessageDialog("Thank you for using our system!");
                    System.exit(0);
                }
            }
        } while (choice != 6);
    }

    public void manageBuses(Admin admin) {
        String[] options = { "Add Bus", "Delete Bus", "View Buses", "Back" };
        int choice;

        do {
            choice = showOptionDialog("Manage Buses", "Buses", options);

            switch (choice) {
                case 0 -> {
                    String busID = showInputDialog("Enter Bus ID:");
                    if (busID == null)
                        return; // User cancelled
                    String model = showInputDialog("Enter Bus Model:");
                    if (model == null)
                        return; // User cancelled
                    String capacityStr = showInputDialog("Enter Bus Capacity:");
                    if (capacityStr == null)
                        return; // User cancelled
                    int capacity = Integer.parseInt(capacityStr);
                    admin.addBus(new Bus(busID, model, capacity));
                    showMessageDialog("Bus added successfully.");
                }
                case 1 -> {
                    String busID = showInputDialog("Enter Bus ID to delete:");
                    if (busID == null)
                        return; // User cancelled
                    String mesej = admin.deleteBus(busID);
                    showMessageDialog(mesej);
                }
                case 2 -> showMessageDialog(admin.viewBus());
            }
        } while (choice != 3);
    }

    public void manageRoutes(Admin admin) {
        String[] options = { "Add Route", "Delete Route", "View Routes", "Back" };
        int choice;

        do {
            choice = showOptionDialog("Manage Routes", "Routes", options);

            switch (choice) {
                case 0 -> {
                    String origin = showInputDialog("Enter Route Origin:");
                    if (origin == null)
                        return; // User cancelled
                    String destination = showInputDialog("Enter Route Destination:");
                    if (destination == null)
                        return; // User cancelled
                    String priceStr = showInputDialog("Enter Route Price:");
                    if (priceStr == null)
                        return; // User cancelled
                    double price = Double.parseDouble(priceStr);
                    admin.addRoute(new Route(origin, destination, price));
                    showMessageDialog("Route added successfully.");
                }
                case 1 -> {
                    String origin = showInputDialog("Enter Route Origin to delete:");
                    if (origin == null)
                        return; // User cancelled
                    String destination = showInputDialog("Enter Route Destination to delete:");
                    if (destination == null)
                        return; // User cancelled
                    String mesej = admin.deleteRoute(origin, destination);
                    showMessageDialog(mesej);
                }
                case 2 -> showMessageDialog(admin.viewRoute());
            }
        } while (choice != 3);
    }

    public void manageSchedules(Admin admin) {
        String origin = showInputDialog("Enter Route Origin:");
        if (origin == null)
            return; // User cancelled
        String destination = showInputDialog("Enter Route Destination:");
        if (destination == null)
            return; // User cancelled
        Route route = admin.findRoute(origin, destination);

        if (route == null) {
            showMessageDialog("Route not found.");
            return;
        }

        String[] options = { "Add Schedule", "Delete Schedule", "View Schedules", "Back" };
        int choice;

        do {
            choice = showOptionDialog("Manage Schedules for route " + origin + " to " + destination, "Schedules",
                    options);

            switch (choice) {
                case 0 -> {
                    String timing = showInputDialog("Enter Schedule Timing:");
                    if (timing == null)
                        return; // User cancelled
                    String seatLimitStr = showInputDialog("Enter Seat Limit:");
                    if (seatLimitStr == null)
                        return; // User cancelled
                    int seatLimit = Integer.parseInt(seatLimitStr);
                    admin.addSchedule(origin, destination, timing, seatLimit);
                    showMessageDialog("Schedule added successfully.");
                }
                case 1 -> {
                    String timing = showInputDialog("Enter Schedule Timing to delete:");
                    if (timing == null)
                        return; // User cancelled
                    String mesej = admin.deleteSchedule(origin, destination, timing);
                    showMessageDialog(mesej);
                }
                case 2 -> showMessageDialog(route.getScheduleDetails());
            }
        } while (choice != 3);
    }

    public void passengerLogin() {
        String[] options = { "Login", "Register" };
        int choice = showOptionDialog("Do you want to login or register?", "Passenger", options);

        if (choice == 0) {
            while (true) {
                String email = showInputDialog("Enter email:");
                if (email == null) {
                    start();
                    return; // User cancelled, return to login page
                }
                String password = showInputDialog("Enter password:");
                if (password == null) {
                    start();
                    return; // User cancelled, return to login page
                }

                Passenger passenger = authenticatePassenger(email, password);
                if (passenger != null) {
                    passenger.menu(this);
                    return;
                } else {
                    showMessageDialog("Invalid credentials. Please try again.");
                }
            }
        } else if (choice == 1) {
            registerPassenger();
        } else {
            start();
        }
    }

    public void registerPassenger() {
        String name = showInputDialog("Enter your name:");
        if (name == null) {
            start();
            return; // User cancelled
        }
        String email = showInputDialog("Enter your email:");
        if (email == null) {
            start();
            return; // User cancelled
        }
        String password = showInputDialog("Enter your password:");
        if (password == null) {
            start();
            return; // User cancelled
        }
        String phoneNoStr = showInputDialog("Enter your phone number:");
        if (phoneNoStr == null) {
            start();
            return; // User cancelled
        }

        try {
            int phoneNo = Integer.parseInt(phoneNoStr);
            Passenger newPassenger = new Passenger(name, password, email, phoneNo);
            users.add(newPassenger);
            showMessageDialog("Registration successful. You can now log in.");
            passengerLogin();
        } catch (NumberFormatException e) {
            showMessageDialog("Invalid phone number. Please enter a valid number.");
            registerPassenger();
        }
    }

    public Passenger authenticatePassenger(String email, String password) {
        for (User user : users) {
            if (user instanceof Passenger && user.getEmail().equals(email) && user.getPwd().equals(password)) {
                return (Passenger) user;
            }
        }
        return null;
    }

    public void passengerMenu(Passenger passenger) {
        String[] options = { "View Routes", "View Schedule", "New Booking", "View Bookings", "Cancel Booking",
                "Profile", "Logout" };
        int choice;

        do {
            choice = showOptionDialog("Passenger Menu", "Menu", options);

            switch (choice) {
                case 0 -> showMessageDialog(passenger.viewRoutes(routes));
                case 1 -> viewSchedule(passenger);
                case 2 -> newBooking(passenger);
                case 3 -> viewBooking(passenger);
                case 4 -> cancelBooking(passenger);
                case 5 -> viewOrUpdateProfile(passenger);
                case 6 -> {
                    showMessageDialog("Thank you for using our system!");
                    System.exit(0);
                }
            }
        } while (choice != 6);
    }

    public void viewSchedule(Passenger passenger) {
        String origin = showInputDialog("Enter Route Origin:");
        if (origin == null)
            return; // User cancelled
        String destination = showInputDialog("Enter Route Destination:");
        if (destination == null)
            return; // User cancelled
        Route route = findRoute(origin, destination);
        if (route == null) {
            showMessageDialog("Route not found.");
        } else {
            showMessageDialog(passenger.viewSchedule(route));
        }
    }

    public Route findRoute(String origin, String destination) {
        for (Route route : routes) {
            if (route.getOrigin().equals(origin) && route.getDestination().equals(destination)) {
                return route;
            }
        }
        return null;
    }

    public void viewBooking(Passenger passenger) {
        String[] options = { "Payment Receipt", "Ticket", "Back" };
        int choice;

        do {
            choice = showOptionDialog(passenger.viewBooking(), "Reservations", options);

            switch (choice) {
                case 0 -> {
                    showMessageDialog(passenger.viewPayment());
                }
                case 1 -> {
                    showMessageDialog(passenger.viewTicket());
                }
            }
        } while (choice != 2);
    }

    public void newBooking(Passenger passenger) {
        String origin = showInputDialog("Enter Route Origin:");
        if (origin == null)
            return; // User cancelled
        String destination = showInputDialog("Enter Route Destination:");
        if (destination == null)
            return; // User cancelled
        String timing = showInputDialog("Enter Schedule Timing:");
        if (timing == null)
            return; // User cancelled
        String seatsStr = showInputDialog("Enter number of seats to book:");
        if (seatsStr == null)
            return; // User cancelled
        int seats = Integer.parseInt(seatsStr);

        showMessageDialog(passenger.newBooking(routes, reservations, origin, destination, timing, seats));
        String[] options = { "Continue with payment", "Back" };
        int choice;

        do {
            choice = showOptionDialog("Route: " + origin + " to " + destination + "\n" +
                    "Schedule: " + timing + "\n" +
                    "Total Seats Booked: " + seatsStr, "Payment", options);

            switch (choice) {
                case 0 -> {
                    showMessageDialog("Booking successful.");
                    return;
                }
            }
        } while (choice != 1);
    }

    public void cancelBooking(Passenger passenger) {
        String reservationID = showInputDialog("Enter Reservation ID to cancel:");
        if (reservationID == null)
            return; // User cancelled
        showMessageDialog(passenger.cancelBooking(reservationID, reservations));
        showMessageDialog("Booking canceled. Please refund it at the counter.");
    }

    public void viewOrUpdateProfile(User user) {
        showMessageDialog(user.dispProfile());
        int choice = showOptionDialog("Do you want to update your profile?", "Profile", new String[] { "Yes", "No" });
        if (choice == 0) {
            updateProfile(user);
        }
    }

    public void updateProfile(User user) {
        String[] profileData = user.updateProfileData();
        String newName = showInputDialog("Enter new name:", profileData[0]);
        if (newName == null)
            return; // User cancelled
        String newPwd = showInputDialog("Enter new password:", profileData[1]);
        if (newPwd == null)
            return; // User cancelled
        String newEmail = showInputDialog("Enter new email:", profileData[2]);
        if (newEmail == null)
            return; // User cancelled
        String newPhoneStr = showInputDialog("Enter new phone number:", profileData[3]);
        if (newPhoneStr == null)
            return; // User cancelled

        if (user instanceof Admin) {
            Admin admin = (Admin) user;
            String newStaffID = showInputDialog("Enter new staff ID:", admin.getStaffID());
            if (newStaffID == null)
                return; // User cancelled
            admin.setStaffID(newStaffID);
        }

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
        return JOptionPane.showOptionDialog(null, message, title, JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
    }

    public static void main(String[] args) {
        BusReservationSystem system = new BusReservationSystem();
        system.start();
    }
}
