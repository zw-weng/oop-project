class Route {
    String start;
    String end;

    Route(String start, String end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "Route from " + start + " to " + end;
    }
}
