public class Grid {
    private int width;
    private int height;
    private Cell[][] cells;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(CellType.EMPTY);
            }
        }
    }

    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public Cell getCell(int x, int y) {
        if (isValidPosition(x, y)) {
            return cells[x][y];
        }
        return null;
    }

    public void occupyCell(Location location, CellType type) {
        if (isValidPosition(location.getX(), location.getY())) {
            cells[location.getX()][location.getY()].setType(type);
        }
    }

    public void freeCell(Location location) {
        if (isValidPosition(location.getX(), location.getY())) {
            cells[location.getX()][location.getY()].setType(CellType.EMPTY);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isCellAvailable(Location location) {
        return getCell(location.getX(), location.getY()).getType() == CellType.EMPTY;
    }
}
