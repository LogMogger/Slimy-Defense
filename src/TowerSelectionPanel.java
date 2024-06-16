import java.util.ArrayList;
import java.util.List;

public class TowerSelectionPanel {
    private List<Tower> towers;

    public TowerSelectionPanel() {
        towers = new ArrayList<>();
        // Add tower types to the selection panel
        towers.add(new Archer());
        towers.add(new Cannon());
        towers.add(new Gladiator());
        towers.add(new Catapult());
        towers.add(new Bomber());
        towers.add(new FireMage());
    }

    public void displayTowerOptions() {
        for (Tower tower : towers) {
            System.out.println("Tower: " + tower.getClass().getSimpleName() + " - Cost: " + tower.getUpgradeCost());
        }
    }

    public Tower selectTower(String towerName) {
        for (Tower tower : towers) {
            if (tower.getClass().getSimpleName().equalsIgnoreCase(towerName)) {
                return tower;
            }
        }
        return null;
    }
}

