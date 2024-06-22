import javax.swing.JOptionPane;

class Payment {
    double amount;
    Passenger passenger;

    Payment(double amount, Passenger passenger) {
        this.amount = amount;
        this.passenger = passenger;
    }

    void processPayment() {
        if (passenger.balance >= amount) {
            // Deduct the payment amount from the balance
            passenger.balance -= amount;
            JOptionPane.showMessageDialog(null, "Payment successful! Remaining balance: $" + passenger.balance);
        } else {
            JOptionPane.showMessageDialog(null, "Insufficient balance. Please top up your account.");
        }
    }
}
