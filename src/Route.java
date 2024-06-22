import javax.swing.*;
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

    // Display Route details
    public void dispRouteDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("Route Details:\n")
          .append("Origin: ").append(origin).append("\n")
          .append("Destination: ").append(destination).append("\n")
          .append("Price: ").append(price).append("\n")
          .append("Schedules:\n");
        
        for (Schedule schedule : scheduleList) {
            sb.append(schedule.getScheduleDetails()).append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString());
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
