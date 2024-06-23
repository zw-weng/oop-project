import javax.swing.*;

public class Reservation {
    private String reservationID;
    private User passenger;
    private Route route;
    private Schedule schedule;
    private int totalSeatsBooked;
    private Payment payment;
    private Ticket ticket;

    public Reservation(String reservationID, User passenger, Route route, Schedule schedule, int totalSeatsBooked) {
        this.reservationID = reservationID;
        this.passenger = passenger;
        this.route = route;
        this.schedule = schedule;
        this.totalSeatsBooked = totalSeatsBooked;
    }

    public void makeReservation() {
        if (schedule.getSeatsAvailable() >= totalSeatsBooked) {
            schedule.bookSeat(totalSeatsBooked);
            processPayment();
            generateTicket();
            JOptionPane.showMessageDialog(null, "Reservation successful for " + totalSeatsBooked + " seats.");
        } else {
            JOptionPane.showMessageDialog(null, "Not enough seats available.");
        }
    }

    public void cancelReservation() {
        schedule.cancelSeat(totalSeatsBooked);
        JOptionPane.showMessageDialog(null, "Reservation canceled for " + totalSeatsBooked + " seats.");
    }

    public void dispReservationDetails() {
        String details = "Reservation ID: " + reservationID + "\n" +
                         "Passenger Name: " + passenger.getName() + "\n" +
                         "Route: " + route.getOrigin() + " to " + route.getDestination() + "\n" +
                         "Schedule: " + schedule.getTiming() + "\n" +
                         "Total Seats Booked: " + totalSeatsBooked + "\n" +
                         "Payment Details: \n" + payment.generateReceipt() + "\n" +
                         "Ticket Details: \n" + ticket.generateTicket();
        JOptionPane.showMessageDialog(null, details);
    }

    private void processPayment() {
        double amount = totalSeatsBooked * route.getPrice();
        payment = new Payment("P" + reservationID, amount);
        payment.processPayment();
    }

    private void generateTicket() {
        ticket = new Ticket("T" + reservationID, this);
        JOptionPane.showMessageDialog(null, ticket.generateTicket());
    }

    // Getters and Setters
    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }

    public User getPassenger() {
        return passenger;
    }

    public void setPassenger(User passenger) {
        this.passenger = passenger;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public int getTotalSeatsBooked() {
        return totalSeatsBooked;
    }

    public void setTotalSeatsBooked(int totalSeatsBooked) {
        this.totalSeatsBooked = totalSeatsBooked;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
