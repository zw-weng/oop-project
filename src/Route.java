import java.util.ArrayList;

public class Route {
    private String origin;
    private String destination;
    private double price;
    private ArrayList<Schedule> scheduleList;

    public Route(String origin, String destination, double price) {
        this.origin = origin;
        this.destination = destination;
        this.price = price;
        this.scheduleList = new ArrayList<>();
    }

    // Add a schedule to the route
    public void addSchedule(String timing, int seatLimit) {
        Schedule schedule = new Schedule(timing, this, seatLimit);
        scheduleList.add(schedule);
    }

    // Delete a schedule from the route
    public void deleteSchedule(String timing) {
        scheduleList.removeIf(schedule -> schedule.getTiming().equals(timing));
    }

    // Get schedule details
    public String getScheduleDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("Schedules for route ").append(origin).append(" to ").append(destination).append(":\n");
        
        for (Schedule schedule : scheduleList) {
            sb.append(schedule.getScheduleDetails()).append("\n");
        }

        return sb.toString();
    }

    // Getters and Setters
    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ArrayList<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(ArrayList<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }
}
