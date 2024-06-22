import java.util.ArrayList;

class Schedule {
    private String timing;
    private int seatLimit;
    private int seatsBooked = 0;
    private Route route; // Reference to the Route object

    // Constructor with Route reference
    Schedule(String timing, Route route, int seatLimit) {
        this.timing = timing;
        this.route = route;
        this.seatLimit = seatLimit;
    }

    // Getters and Setters
    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public int getSeatLimit() {
        return seatLimit;
    }

    public void setSeatLimit(int seatLimit) {
        this.seatLimit = seatLimit;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public void setSeatsBooked(int seatsBooked) {
        this.seatsBooked = seatsBooked;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    // Fixed price set by the admin
    public double getPrice() {
        return 50.0; // Adjust as needed
    }

    // Static method to view all schedules
    public static void viewAllSchedules(ArrayList<Schedule> schedules) {
        System.out.println("Viewing all schedules...");
        for (int i = 0; i < schedules.size(); i++) {
            Schedule schedule = schedules.get(i);
            System.out.println((i + 1) + ". " + schedule.getTiming() +
                               " - " + schedule.getRoute().getOrigin() + " to " + schedule.getRoute().getDestination());
        }
    }

    // Get schedule details
    public String getScheduleDetails() {
        return "Timing: " + timing + ", Seats Booked: " + seatsBooked + "/" + seatLimit;
    }
}