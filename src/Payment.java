import java.util.Date;

public class Payment {
    private String paymentID;
    private double amount;
    private Date paymentDate;

    public Payment(String paymentID, double amount) {
        this.paymentID = paymentID;
        this.amount = amount;
        this.paymentDate = new Date();
    }

    public String generateReceipt() {
        return "Receipt:\nPayment ID: " + paymentID + "\nAmount: RM" + amount + "\nDate: " + paymentDate.toString();
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
