public class Schedule {
    private String timing;
    private Route route;
    private int seatLimit;
    private int seatsAvailable;

    public Schedule(String timing, Route route, int seatLimit, int seatsAvailable) {
        this.timing = timing;
        this.route = route;
        this.seatLimit = seatLimit;
        this.seatsAvailable = seatsAvailable;
    }

    public String bookSeat(int seats) {
        if (seatsAvailable >= seats) {
            seatsAvailable -= seats;
            return "Seats booked successfully. Seats available: " + seatsAvailable;
        } else {
            return "Not enough seats available.";
        }
    }

    public String cancelSeat(int seats) {
        if (seats <= seatLimit - seatsAvailable) {
            seatsAvailable += seats;
            return "Seats canceled successfully. Seats available: " + seatsAvailable;
        } else {
            return "No seats to cancel.";
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
