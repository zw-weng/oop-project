import java.util.ArrayList;

public class Admin extends User {
    private ArrayList<Route> routeList;
    private ArrayList<Bus> busList;
    private ArrayList<Reservation> bookingList;
    private ArrayList<Passenger> passengerList;
    private String staffID;

    public Admin(String name, String pwd, String email, int phoneNo, String staffID,
            ArrayList<Route> routeList, ArrayList<Bus> busList, ArrayList<Reservation> bookingList,
            ArrayList<Passenger> passengerList) {
        super(name, pwd, email, phoneNo);
        this.staffID = staffID;
        this.routeList = routeList;
        this.busList = busList;
        this.bookingList = bookingList;
        this.passengerList = passengerList;
    }

    public void updateProfile(String newName, String newPwd, String newEmail, String newPhoneStr, String newStaffID) {
        super.updateProfile(newName, newPwd, newEmail, newPhoneStr);

        if (newStaffID != null && !newStaffID.trim().isEmpty()) {
            setStaffID(newStaffID);
        }
    }

    @Override
    public String[] updateProfileData() {
        String[] data = super.updateProfileData();
        if (data.length == 4) {
            String[] updatedData = new String[5];
            System.arraycopy(data, 0, updatedData, 0, data.length);
            updatedData[4] = staffID;
            return updatedData;
        }
        return data;
    }

    @Override
    public String dispProfile() {
        return super.dispProfile() + "\n" + "Staff ID: " + staffID;
    }

    public void addBus(Bus bus) {
        busList.add(bus);
    }

    public String deleteBus(String busID) {
        boolean removed = busList.removeIf(bus -> bus.getBusID().equals(busID));

        if (!removed) {
            return "Bus not found for ID: " + busID + ".";
        } else {
            return "Bus deleted successfully.";
        }
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

    public String deleteRoute(String origin, String destination) {
        boolean removed = routeList
                .removeIf(route -> route.getOrigin().equals(origin) && route.getDestination().equals(destination));

        if (!removed) {
            return "Route not found for origin: " + origin + " and destination: " + destination;
        } else {
            return "Route deleted successfully.";
        }
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
        StringBuilder sb = new StringBuilder("Current Bookings:\n");
        for (Reservation reservation : bookingList) {
            sb.append(reservation.dispReservationDetails()).append("\n");
        }
        return sb.toString();
    }

    public String viewUser() {
        StringBuilder sb = new StringBuilder("Passenger Info:\n");
        for (Passenger user : passengerList) {
            sb.append(user.getName()).append(" - Email: ")
                    .append(user.getEmail()).append(", Phone No: ")
                    .append(user.getPhoneNo()).append("\n");
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
            route.addSchedule(timing, seatLimit, seatLimit);
        }
    }

    public String deleteSchedule(String origin, String destination, String timing) {
        Route route = findRoute(origin, destination);
        if (route != null) {
            boolean scheduleDeleted = route.deleteSchedule(timing);
            if (scheduleDeleted) {
                return "Schedule deleted successfully.";
            } else {
                return "Schedule not found for departure time: " + timing + ".";
            }
        }
        return "";
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
