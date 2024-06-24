public class Reservation {
    private String reservationID;
    private Passenger passenger;
    private Route route;
    private Schedule schedule;
    private int totalSeatsBooked;
    private Payment payment;
    private Ticket ticket;

    public Reservation(String reservationID, Passenger passenger, Route route, Schedule schedule, int totalSeatsBooked) {
        this.reservationID = reservationID;
        this.passenger = passenger;
        this.route = route;
        this.schedule = schedule;
        this.totalSeatsBooked = totalSeatsBooked;
        processPayment();
        generateTicket();
    }

    private void processPayment() {
        double amount = totalSeatsBooked * route.getPrice();
        payment = new Payment("P" + reservationID, amount, this);
    }

    private void generateTicket() {
        ticket = new Ticket("T" + reservationID, this);
    }

    public String dispReservationDetails() {
        return "Reservation ID: " + reservationID + "\n" +
               "Passenger Name: " + passenger.getName() + "\n" +
               "Route: " + route.getOrigin() + " to " + route.getDestination() + "\n" +
               "Schedule: " + schedule.getTiming() + "\n" +
               "Total Seats Booked: " + totalSeatsBooked + "\n";
    }

    public String dispPaymentDetails() {
        return payment.generateReceipt();
    }

    public String dispTicketDetails() {
        return ticket.generateTicket();
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

    public void setPassenger(Passenger passenger) {
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
