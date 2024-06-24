import java.util.ArrayList;

public class Admin extends User {
    private ArrayList<Route> routeList;
    private ArrayList<Bus> busList;
    private ArrayList<Reservation> bookingList;
    private ArrayList<Passenger> passengerList;
    private String staffID;

    public Admin(String name, String pwd, String email, int phoneNo, String staffID,
                 ArrayList<Route> routeList, ArrayList<Bus> busList, ArrayList<Reservation> bookingList, ArrayList<Passenger> passengerList) {
        super(name, pwd, email, phoneNo);
        this.staffID = staffID;
        this.routeList = routeList;
        this.busList = busList;
        this.bookingList = bookingList;
        this.passengerList = passengerList;
    }

    @Override
    public String dispProfile() {
        return super.dispProfile() + "\n" + "Staff ID: " + staffID;
    }

    @Override
    public void updateProfile(String newName, String newPwd, String newEmail, String newPhoneStr) {
        super.updateProfile(newName, newPwd, newEmail, newPhoneStr);
        if (newPhoneStr != null && !newPhoneStr.trim().isEmpty()) {
            setStaffID(newPhoneStr);
        }
    }

    public void addBus(Bus bus) {
        busList.add(bus);
    }

    public void deleteBus(String busID) {
        busList.removeIf(bus -> bus.getBusID().equals(busID));
    }

    public String viewBus() {
        StringBuilder sb = new StringBuilder("Buses:\n");
        for (Bus bus : busList) {
            sb.append(bus.toString()).append("\n");
        }
        return sb.toString();
    }

    public void addRoute(Route route) {
        routeList.add(route);
    }

    public void deleteRoute(String origin, String destination) {
        routeList.removeIf(route -> route.getOrigin().equals(origin) && route.getDestination().equals(destination));
    }

    public String viewRoute() {
        StringBuilder sb = new StringBuilder("Routes:\n");
        for (Route route : routeList) {
            sb.append("Origin: ").append(route.getOrigin())
              .append(", Destination: ").append(route.getDestination())
              .append(", Price: RM").append(route.getPrice()).append("\n");
        }
        return sb.toString();
    }

    public String viewBooking() {
        StringBuilder sb = new StringBuilder("Bookings:\n");
        for (Reservation reservation : bookingList) {
            sb.append(reservation.getReservationID()).append(" - ")
              .append(reservation.getPassenger().getName()).append(", ")
              .append("Route: ").append(reservation.getRoute().getOrigin()).append(" to ").append(reservation.getRoute().getDestination()).append("\n");
        }
        return sb.toString();
    }

    public String viewUser() {
        StringBuilder sb = new StringBuilder("Passengers:\n");
        for (Passenger user : passengerList) {
            sb.append(user.getName()).append(" - Email: ")
              .append(user.getEmail()).append(", Phone No: ")
              .append(user.getPhoneNo()).append("\n\n");
        }
        return sb.toString();
    }

    public Route findRoute(String origin, String destination) {
        for (Route route : routeList) {
            if (route.getOrigin().equals(origin) && route.getDestination().equals(destination)) {
                return route;
            }
        }
        return null;
    }

    @Override
    public void menu(BusReservationSystem system) {
        system.adminMenu(this);
    }

    public void addSchedule(String origin, String destination, String timing, int seatLimit) {
        Route route = findRoute(origin, destination);
        if (route != null) {
            route.addSchedule(timing, seatLimit);
        }
    }

    public void deleteSchedule(String origin, String destination, String timing) {
        Route route = findRoute(origin, destination);
        if (route != null) {
            route.deleteSchedule(timing);
        }
    }

    // Getters and setters
    public ArrayList<Route> getRouteList() {
        return routeList;
    }

    public void setRouteList(ArrayList<Route> routeList) {
        this.routeList = routeList;
    }

    public ArrayList<Bus> getBusList() {
        return busList;
    }

    public void setBusList(ArrayList<Bus> busList) {
        this.busList = busList;
    }

    public ArrayList<Reservation> getBookingList() {
        return bookingList;
    }

    public void setBookingList(ArrayList<Reservation> bookingList) {
        this.bookingList = bookingList;
    }

    public ArrayList<Passenger> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(ArrayList<Passenger> passengerList) {
        this.passengerList = passengerList;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }
}
