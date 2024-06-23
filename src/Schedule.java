import javax.swing.*;

public class Schedule {
    private String timing;
    private Route route;
    private int seatLimit;
    private int seatsAvailable;

    public Schedule(String timing, Route route, int seatLimit) {
        this.timing = timing;
        this.route = route;
        this.seatLimit = seatLimit;
        this.seatsAvailable = seatLimit;
    }

    public void bookSeat(int seats) {
        if (seatsAvailable >= seats) {
            seatsAvailable -= seats;
            JOptionPane.showMessageDialog(null, "Seats booked successfully. Seats available: " + seatsAvailable);
        } else {
            JOptionPane.showMessageDialog(null, "Not enough seats available.");
        }
    }

    public void cancelSeat(int seats) {
        if (seats <= seatLimit - seatsAvailable) {
            seatsAvailable += seats;
            JOptionPane.showMessageDialog(null, "Seats canceled successfully. Seats available: " + seatsAvailable);
        } else {
            JOptionPane.showMessageDialog(null, "No seats to cancel.");
        }
    }

    public String getScheduleDetails() {
        return "Timing: " + timing + ", Seats Available: " + seatsAvailable + "/" + seatLimit;
    }

    // Getters and Setters
    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public int getSeatLimit() {
        return seatLimit;
    }

    public void setSeatLimit(int seatLimit) {
        this.seatLimit = seatLimit;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }
}
