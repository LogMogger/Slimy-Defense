public class Cell {
    private boolean isOccupied;
    private CellType type;

    public Cell(CellType type) {
        this.type = type;
        this.isOccupied = false;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }



}
