import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Admin extends User {
    private ArrayList<Route> routeList;
    private ArrayList<Bus> busList;
    private ArrayList<Reservation> bookingList;
    private String staffID;

    public Admin(String name, String pwd, String email, int phoneNo, String staffID) {
        super(name, pwd, email, phoneNo);
        this.staffID = staffID;
        routeList = new ArrayList<>();
        busList = new ArrayList<>();
        bookingList = new ArrayList<>();
    }

    public void addBus(Bus bus) { busList.add(bus); }
    public void deleteBus(String busID) { /* Delete bus logic */ }
    public void viewBus() { /* View bus logic */ }

    public void addRoute(Route route) { routeList.add(route); }
    public void deleteRoute(String origin, String destination) { /* Delete route logic */ }
    public void viewRoute() { /* View route logic */ }

    public void viewBooking() { /* View booking logic */ }

    @Override
    public void updateProfile() {
        super.updateProfile();
        // Additional logic specific to Admin, if any
        JOptionPane.showMessageDialog(null, "Admin profile updated successfully!", "Update Profile", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void dispProfile() {
        super.dispProfile();
        // Additional display logic specific to Admin
        JOptionPane.showMessageDialog(null, "Staff ID: " + staffID, "Profile Details", JOptionPane.INFORMATION_MESSAGE);
    }
}