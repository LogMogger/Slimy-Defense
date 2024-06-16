import java.util.List;

public class Path {
    private List<Location> waypoints;

    public Path(List<Location> waypoints) {
        this.waypoints = waypoints;
    }

    public Location getStart() {
        return waypoints.get(0); // Assuming the first waypoint is the start
    }

    public Location getNextLocation(Location currentLocation, double speed) {
        // Simplified logic to move to the next waypoint
        int currentIndex = waypoints.indexOf(currentLocation);
        if (currentIndex < waypoints.size() - 1) {
            return waypoints.get(currentIndex + 1);
        }
        return currentLocation; // Stay at the last waypoint if at the end
    }

    public boolean isOnPath(Location location) {
        return waypoints.contains(location);
    }
}
