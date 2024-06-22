import java.util.ArrayList;
import java.util.List;

class Passenger {
    String name;
    String email;
    List<Reservation> bookingList = new ArrayList<>();
    double balance = 500.0; // Initial balance

    Passenger(String name, String email) {
        this.name = name;
        this.email = email;
    }

    void newBooking(Schedule schedule) {
        if (schedule.seatsBooked < schedule.seatLimit) {
            Reservation reservation = new Reservation(schedule);
            bookingList.add(reservation);
            schedule.seatsBooked++;
            System.out.println("New booking has been created.");
        } else {
            System.out.println("Sorry, this schedule is full. Please choose another schedule.");
        }
    }

    void viewReservations() {
        System.out.println("Your reservations:");
        for (Reservation reservation : bookingList) {
            System.out.println("Reservation ID: " + reservation.id + ", Schedule: " + reservation.schedule.timing);
        }
    }

    
}
