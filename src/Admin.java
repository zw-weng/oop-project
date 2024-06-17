import java.util.*;
import javax.swing.*;

public class Admin extends User {
    private ArrayList<Reservation> bookingList = new ArrayList<>();
    private ArrayList<Bus> busList = new ArrayList<>();
    private ArrayList<Route> routeList = new ArrayList<>();

    public Admin(String id, String name, String pwd, String email, int phoneNo) {
        super(id, name, pwd, email, phoneNo);
    }

    // Bus management
    public void addBus(Bus bus) {
        busList.add(bus);
        JOptionPane.showMessageDialog(null, "Bus added.");
    }

    public void deleteBus(String busID) {
        busList.removeIf(bus -> bus.getBusID().equals(busID));
        JOptionPane.showMessageDialog(null, "Bus deleted.");
    }

    public void viewBus() {
        StringBuilder buses = new StringBuilder();
        for (Bus bus : busList) {
            buses.append(bus.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, buses.toString());
    }

    // Route management
    public void addRoute(Route route) {
        routeList.add(route);
        JOptionPane.showMessageDialog(null, "Route added.");
    }

    public void deleteRoute(String origin, String destination) {
        routeList.removeIf(route -> route.getOrigin().equals(origin) && route.getDestination().equals(destination));
        JOptionPane.showMessageDialog(null, "Route deleted.");
    }

    public void viewRoutes() {
        StringBuilder routes = new StringBuilder();
        for (Route route : routeList) {
            routes.append("Origin: ").append(route.getOrigin()).append(", Destination: ").append(route.getDestination()).append(", Price: ").append(route.getPrice()).append("\n");
        }
        JOptionPane.showMessageDialog(null, routes.toString());
    }

    // Schedule management
    public void addSchedule(Route route, Schedule schedule) {
        route.addSchedule(schedule);
        JOptionPane.showMessageDialog(null, "Schedule added.");
    }

    public void deleteSchedule(Route route, Schedule schedule) {
        route.removeSchedule(schedule);
        JOptionPane.showMessageDialog(null, "Schedule deleted.");
    }

    public void viewSchedules(Route route) {
        StringBuilder schedules = new StringBuilder();
        for (Schedule schedule : route.getSchedules()) {
            schedules.append("Timing: ").append(schedule.getTiming()).append("\n");
        }
        JOptionPane.showMessageDialog(null, schedules.toString());
    }

    // Reservation management (view only)
    public void viewReservations() {
        StringBuilder reservations = new StringBuilder();
        for (Reservation reservation : bookingList) {
            reservations.append("Reservation ID: ").append(reservation.getReservationID()).append("\n")
                    .append("Route: ").append(reservation.getRoute().getOrigin()).append(" to ").append(reservation.getRoute().getDestination()).append("\n")
                    .append("Schedule: ").append(reservation.getSchedule().getTiming()).append("\n")
                    .append("Seat No: ").append(reservation.getSeatNo()).append("\n")
                    .append("Price: ").append(reservation.getPrice()).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, reservations.toString());
    }

    public ArrayList<Bus> getBusList() {
        return busList;
    }

    public ArrayList<Route> getRouteList() {
        return routeList;
    }

    public ArrayList<Reservation> getBookingList() {
        return bookingList;
    }
}