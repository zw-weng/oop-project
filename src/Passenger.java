import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

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
            // Calculate the total price based on the route (you can add a price field to
            // the Route class)
            double totalPrice = schedule.getPrice(); // Adjust this based on your actual implementation

            // Display reservation details
            String message = "You are reserving a bus for:\n" +
                    "Route: " + schedule.route + "\n" +
                    "Date and Time: " + schedule.timing + "\n" +
                    "Total Price: $" + totalPrice;

            int confirmation = JOptionPane.showConfirmDialog(null, message, "Confirm Reservation",
                    JOptionPane.YES_NO_OPTION);

            if (confirmation == JOptionPane.YES_OPTION) {
                // Create the reservation and deduct the balance
                Reservation reservation = new Reservation(schedule);
                bookingList.add(reservation);
                schedule.seatsBooked++;
                balance -= totalPrice;
                JOptionPane.showMessageDialog(null, "Reservation successful!");
            } else {
                JOptionPane.showMessageDialog(null, "Reservation canceled.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Sorry, this schedule is full. Please choose another schedule.");
        }
    }

    void viewReservations() {
        StringBuilder reservationList = new StringBuilder("Your reservations:\n");
        for (Reservation reservation : bookingList) {
            reservationList.append("Reservation ID: ").append(reservation.id)
                    .append(", Schedule: ").append(reservation.schedule.timing)
                    .append("\n");
        }

        // Display reservations in both terminal and JOptionPane
        System.out.println(reservationList);
        JOptionPane.showMessageDialog(null, reservationList.toString(), "Your Reservations",
                JOptionPane.INFORMATION_MESSAGE);
    }

}
