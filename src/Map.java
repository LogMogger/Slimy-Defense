import java.util.List;

public class Map {
    private Grid grid;
    private Path path;
    private Location baseLocation;

    public Map(int width, int height, List<Location> waypoints) {
        this.grid = new Grid(width, height);
        this.path = new Path(waypoints);
        this.baseLocation = waypoints.get(waypoints.size() - 1);
    }

    public Grid getGrid() {
        return grid;
    }

    public Path getPath() {
        return path;
    }

    public boolean isLocationOnPath(Location location) {
        return path.isOnPath(location);
    }

    public boolean isLocationValid(Location location) {
        return grid.isValidPosition(location.getX(), location.getY());
    }

    public boolean isLocationAvailable(Location location) {
        return grid.isCellAvailable(location);
    }

    public void occupyLocation(Location location, CellType type) {
        grid.occupyCell(location, type);
    }

    public void freeLocation(Location location) {
        grid.freeCell(location);
    }

    public Location getBaseLocation() {
        return baseLocation;
    }
}
