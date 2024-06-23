public class Ticket {
    private String ticketID;
    private Reservation reservation;

    public Ticket(String ticketID, Reservation reservation) {
        this.ticketID = ticketID;
        this.reservation = reservation;
    }

    public String generateTicket() {
        return "Ticket:\nTicket ID: " + ticketID + "\nReservation ID: " + reservation.getReservationID() +
               "\nPassenger: " + reservation.getPassenger().getName() +
               "\nRoute: " + reservation.getRoute().getOrigin() + " to " + reservation.getRoute().getDestination() +
               "\nSchedule: " + reservation.getSchedule().getTiming() +
               "\nTotal Seats Booked: " + reservation.getTotalSeatsBooked();
    }

    // Getters and Setters
    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
