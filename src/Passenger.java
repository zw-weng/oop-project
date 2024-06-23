import javax.swing.*;
import java.util.ArrayList;

public class Passenger extends User {
    private ArrayList<Reservation> reservations;

    public Passenger(String name, String pwd, String email, int phoneNo) {
        super(name, pwd, email, phoneNo);
        this.reservations = new ArrayList<>();
    }

    public void newBooking(ArrayList<Route> routes, ArrayList<Reservation> allReservations) {
        String origin = JOptionPane.showInputDialog("Enter Route Origin:");
        String destination = JOptionPane.showInputDialog("Enter Route Destination:");
        String timing = JOptionPane.showInputDialog("Enter Schedule Timing:");
        int seats = Integer.parseInt(JOptionPane.showInputDialog("Enter number of seats to book:"));

        Route selectedRoute = findRoute(routes, origin, destination);
        if (selectedRoute != null) {
            Schedule selectedSchedule = findSchedule(selectedRoute, timing);
            if (selectedSchedule != null) {
                String reservationID = "R" + (allReservations.size() + 1);
                Reservation reservation = new Reservation(reservationID, this, selectedRoute, selectedSchedule, seats);
                reservation.makeReservation();
                reservations.add(reservation);
                allReservations.add(reservation);
            } else {
                JOptionPane.showMessageDialog(null, "Schedule not found.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Route not found.");
        }
    }

    public void viewBooking() {
        StringBuilder sb = new StringBuilder("Your Bookings:\n");
        for (Reservation reservation : reservations) {
            sb.append(reservation.getReservationID()).append(" - ")
              .append(reservation.getRoute().getOrigin()).append(" to ")
              .append(reservation.getRoute().getDestination()).append(", ")
              .append("Schedule: ").append(reservation.getSchedule().getTiming()).append(", ")
              .append("Seats Booked: ").append(reservation.getTotalSeatsBooked()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public void cancelBooking() {
        String reservationID = JOptionPane.showInputDialog("Enter Reservation ID to cancel:");
        Reservation reservation = findReservation(reservationID);
        if (reservation != null) {
            reservation.cancelReservation();
            reservations.remove(reservation);
        } else {
            JOptionPane.showMessageDialog(null, "Reservation not found.");
        }
    }

    private Route findRoute(ArrayList<Route> routes, String origin, String destination) {
        for (Route route : routes) {
            if (route.getOrigin().equals(origin) && route.getDestination().equals(destination)) {
                return route;
            }
        }
        return null;
    }

    private Schedule findSchedule(Route route, String timing) {
        for (Schedule schedule : route.getScheduleList()) {
            if (schedule.getTiming().equals(timing)) {
                return schedule;
            }
        }
        return null;
    }

    private Reservation findReservation(String reservationID) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationID().equals(reservationID)) {
                return reservation;
            }
        }
        return null;
    }

    // Getters and Setters
    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
    }
}
