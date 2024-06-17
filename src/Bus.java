public class Bus {
    private String busID;
    private String model;
    private int capacity;

    public Bus(String busID, String model, int capacity) {
        this.busID = busID;
        this.model = model;
        this.capacity = capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getModel() {
        return model;
    }

    public String getBusID() {
        return busID;
    }

    @Override
    public String toString() {
        return "Bus ID: " + busID + ", Model: " + model + ", Capacity: " + capacity;
    }
}
