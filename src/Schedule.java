import java.util.List;

class Schedule {
    String timing;
    Route route;
    int seatLimit;
    int seatsBooked = 0;

    Schedule(String timing, Route route, int seatLimit) {
        this.timing = timing;
        this.route = route;
        this.seatLimit = seatLimit;
    }

    // Fixed price set by the admin
    double getPrice() {
        return 50.0; // Adjust as needed
    }

    static void viewAllSchedules(List<Schedule> schedules) {
        System.out.println("Viewing all schedules...");
        for (int i = 0; i < schedules.size(); i++) {
            System.out.println((i + 1) + ". " + schedules.get(i).timing);
        }
    }
}
