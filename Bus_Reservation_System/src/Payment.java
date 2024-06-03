class Payment {
    double amount;
    Passenger passenger;

    Payment(double amount, Passenger passenger) {
        this.amount = amount;
        this.passenger = passenger;
    }

    void processPayment() {
        if (passenger.balance >= amount) {
            passenger.balance -= amount;
            System.out.println("Payment of " + amount + " processed. Remaining balance: " + passenger.balance);
        } else {
            System.out.println("Insufficient balance. Please top up your account.");
        }
    }
}
