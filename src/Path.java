import java.util.List;

public class Path {
    private List<Location> waypoints;

    public Path(List<Location> waypoints) {
        this.waypoints = waypoints;
    }

    public Location getStart() {
        return waypoints.get(0);
    }

    public Location getNextLocation(Location currentLocation, double speed) {
        int currentIndex = waypoints.indexOf(currentLocation);
        if (currentIndex < waypoints.size() - 1) {
            return waypoints.get(currentIndex + 1);
        }
        return currentLocation;
    }

    public boolean isOnPath(Location location) {
        return waypoints.contains(location);
    }
}
