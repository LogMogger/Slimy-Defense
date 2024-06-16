public class TowerPanel {
    private Tower selectedTower;

    public void updatePanel(Tower tower) {
        this.selectedTower = tower;
        displayTowerInfo();
    }

    public void displayTowerInfo() {
        System.out.println("Tower Level: " + selectedTower.getLevel());
        System.out.println("Damage: " + selectedTower.getDamage());
        System.out.println("Fire Rate: " + selectedTower.getFireRate());
        System.out.println("Range: " + selectedTower.getRange());
        System.out.println("Splash AOE: " + selectedTower.getSplashAOE());
        System.out.println("Upgrade Cost: " + selectedTower.getUpgradeCost());
        System.out.println("Can Detect Hidden: " + selectedTower.canDetectHidden());
    }

    public void upgradeTower() {
        selectedTower.upgrade();
        displayTowerInfo();
    }
}
