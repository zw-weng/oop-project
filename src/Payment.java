import java.util.Date;

public class Payment {
    private String paymentID;
    private double amount;
    private Date paymentDate;
    private Reservation reservation;

    public Payment(String paymentID, double amount, Reservation reservation) {
        this.paymentID = paymentID;
        this.amount = amount;
        this.paymentDate = new Date();
        this.reservation = reservation;
    }

    public String generateReceipt() {
        return "Payment ID: " + paymentID + "\nReservation ID: " + reservation.getReservationID() +
                "\nPassenger: " + reservation.getPassenger().getName() + "\nAmount: RM" + amount + "\nDate: "
                + paymentDate.toString() + "\n";
    }

    // Getters and Setters
    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
