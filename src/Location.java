public class Location {
    private int x;
    private int y;
    private Grid grid;

    public Location(int x, int y) {
        this.grid = grid;
        if (grid != null) {
            grid.isValidPosition(x, y);
        }
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (grid == null || grid.isValidPosition(x, y)) {
            this.x = x;
        } else {
            System.out.println("Invalid X position");
        }
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if (grid == null || grid.isValidPosition(x, y)) {
            this.y = y;
        } else {
            System.out.println("Invalid Y position");
        }
    }

    public void move(int deltaX, int deltaY) {
        int newX = this.x + deltaX;
        int newY = this.y + deltaY;
        if (grid == null || grid.isValidPosition(newX, newY)) {
            this.x = newX;
            this.y = newY;
        } else {
            System.out.println("Invalid move");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Location location = (Location) obj;
        return x == location.x && y == location.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
