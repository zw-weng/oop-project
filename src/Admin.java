import javax.swing.*;
import java.util.ArrayList;

public class Admin extends User {
    private ArrayList<Route> routeList;
    private ArrayList<Bus> busList;
    private ArrayList<Reservation> bookingList;
    private String staffID;

    public Admin(String name, String pwd, String email, int phoneNo, String staffID,
                 ArrayList<Route> routeList, ArrayList<Bus> busList, ArrayList<Reservation> bookingList) {
        super(name, pwd, email, phoneNo);
        this.staffID = staffID;
        this.routeList = routeList;
        this.busList = busList;
        this.bookingList = bookingList;
    }

    @Override
    public void updateProfile() {
        super.updateProfile();

        String newStaffID = JOptionPane.showInputDialog("Enter new staff ID:", staffID);
        if (newStaffID != null && !newStaffID.trim().isEmpty()) {
            setStaffID(newStaffID);
        }

        JOptionPane.showMessageDialog(null, "Admin profile updated successfully.");
    }

    @Override
    public void dispProfile() {
        String profile = "Name: " + getName() + "\n" +
                         "Email: " + getEmail() + "\n" +
                         "Phone No: " + getPhoneNo() + "\n" +
                         "Staff ID: " + staffID;
        JOptionPane.showMessageDialog(null, profile);
    }

    public void addBus(Bus bus) {
        busList.add(bus);
        JOptionPane.showMessageDialog(null, "Bus added successfully.");
    }

    public void deleteBus(String busID) {
        busList.removeIf(bus -> bus.getBusID().equals(busID));
        JOptionPane.showMessageDialog(null, "Bus deleted successfully.");
    }

    public void viewBus() {
        StringBuilder sb = new StringBuilder("Buses:\n");
        for (Bus bus : busList) {
            sb.append(bus.getBusID()).append(" - ").append(bus.getModel()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public void addRoute(Route route) {
        routeList.add(route);
        JOptionPane.showMessageDialog(null, "Route added successfully.");
    }

    public void deleteRoute(String origin, String destination) {
        routeList.removeIf(route -> route.getOrigin().equals(origin) && route.getDestination().equals(destination));
        JOptionPane.showMessageDialog(null, "Route deleted successfully.");
    }

    public void viewRoute() {
        StringBuilder sb = new StringBuilder("Routes:\n");
        for (Route route : routeList) {
            sb.append("Origin: ").append(route.getOrigin())
              .append(", Destination: ").append(route.getDestination())
              .append(", Price: ").append(route.getPrice()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public void viewBooking() {
        StringBuilder sb = new StringBuilder("Bookings:\n");
        for (Reservation reservation : bookingList) {
            sb.append(reservation.getReservationID()).append(" - ")
              .append(reservation.getPassenger().getName()).append(", ")
              .append("Route: ").append(reservation.getRoute().getOrigin()).append(" to ").append(reservation.getRoute().getDestination()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // Getters and setters
    public ArrayList<Route> getRouteList() {
        return routeList;
    }

    public void setRouteList(ArrayList<Route> routeList) {
        this.routeList = routeList;
    }

    public ArrayList<Bus> getBusList() {
        return busList;
    }

    public void setBusList(ArrayList<Bus> busList) {
        this.busList = busList;
    }

    public ArrayList<Reservation> getBookingList() {
        return bookingList;
    }

    public void setBookingList(ArrayList<Reservation> bookingList) {
        this.bookingList = bookingList;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }
}