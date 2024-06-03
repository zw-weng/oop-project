import java.util.UUID;

class Reservation {
    String id;
    Schedule schedule;

    Reservation(Schedule schedule) {
        this.id = UUID.randomUUID().toString();
        this.schedule = schedule;
        System.out.println("Reservation " + id + " created for schedule " + schedule.timing);
    }
}
