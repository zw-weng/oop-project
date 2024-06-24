import java.util.ArrayList;

public class Passenger extends User {
    private ArrayList<Reservation> reservations;

    public Passenger(String name, String pwd, String email, int phoneNo) {
        super(name, pwd, email, phoneNo);
        this.reservations = new ArrayList<>();
    }

    public String newBooking(ArrayList<Route> routes, ArrayList<Reservation> allReservations, String origin,
            String destination, String timing, int seats) {
        Route selectedRoute = findRoute(routes, origin, destination);
        if (selectedRoute != null) {
            Schedule selectedSchedule = findSchedule(selectedRoute, timing);
            if (selectedSchedule != null) {
                String bookingStatus = selectedSchedule.bookSeat(seats);
                if (bookingStatus.startsWith("Seats booked successfully")) {
                    String reservationID = "R" + (allReservations.size() + 1);
                    Reservation reservation = new Reservation(reservationID, this, selectedRoute, selectedSchedule,
                            seats);
                    reservations.add(reservation);
                    allReservations.add(reservation);
                    return bookingStatus;
                } else {
                    return bookingStatus;
                }
            } else {
                return "Schedule at " + timing + " not found for route " + origin + " to " + destination + ".";
            }
        } else {
            return "Route from " + origin + " to " + destination + " not found.";
        }
    }

    public String viewBooking() {
        StringBuilder sb = new StringBuilder();
        for (Reservation reservation : reservations) {
            sb.append(reservation.dispReservationDetails()).append("\n");
        }
        return sb.toString();
    }

    public String viewPayment() {
        StringBuilder sb = new StringBuilder();
        sb.append("Your Payments:\n");
        for (Reservation reservation : reservations) {
            sb.append(reservation.dispPaymentDetails()).append("\n");
        }
        return sb.toString();
    }

    public String viewTicket() {
        StringBuilder sb = new StringBuilder();
        sb.append("Your Tickets:\n");
        for (Reservation reservation : reservations) {
            sb.append(reservation.dispTicketDetails()).append("\n");
        }
        return sb.toString();
    }

    public String cancelBooking(String reservationID, ArrayList<Reservation> allReservations) {
        Reservation reservation = findReservation(reservationID);
        if (reservation != null) {
            Schedule schedule = reservation.getSchedule();
            int seatsToCancel = reservation.getTotalSeatsBooked();
            String cancelStatus = schedule.cancelSeat(seatsToCancel);
            if (cancelStatus.startsWith("Seats canceled successfully")) {
                reservations.remove(reservation);
                allReservations.remove(reservation);
                return cancelStatus;
            } else {
                return cancelStatus;
            }
        } else {
            return "Reservation with ID " + reservationID + " not found.";
        }
    }

    public String viewRoutes(ArrayList<Route> routes) {
        StringBuilder sb = new StringBuilder("Available Routes:\n");
        for (Route route : routes) {
            sb.append(route.getOrigin()).append(" to ").append(route.getDestination()).append(" - ")
                    .append(route.getPrice()).append("\n");
        }
        return sb.toString();
    }

    public String viewSchedule(Route route) {
        return route.getScheduleDetails();
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

    @Override
    public void menu(BusReservationSystem system) {
        system.passengerMenu(this);
    }

    // Getters and Setters
    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
    }
}
