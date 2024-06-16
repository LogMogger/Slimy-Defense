import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

public class ImageManager {
    private Map<String, Image> mapImages;
    private Map<String, Image> towerImages;
    private Map<String, Image> enemyImages;

    public ImageManager() {
        this.mapImages = new HashMap<>();
        this.towerImages = new HashMap<>();
        this.enemyImages = new HashMap<>();
    }

    public Image loadImage(String filePath) {
        // Logic to load an image from file
        return new ImageIcon(filePath).getImage(); // Using ImageIcon to load the image
    }

    public Image getMapImage(String mapName) {
        return mapImages.get(mapName);
    }

    public Image getTowerImage(String towerName, String state) {
        return towerImages.get(towerName + "_" + state);
    }

    public Image getEnemyImage(String enemyName) {
        return enemyImages.get(enemyName);
    }

    public void addMapImage(String mapName, String filePath) {
        mapImages.put(mapName, loadImage(filePath));
    }

    public void addTowerImage(String towerName, String state, String filePath) {
        towerImages.put(towerName + "_" + state, loadImage(filePath));
    }

    public void addEnemyImage(String enemyName, String filePath) {
        enemyImages.put(enemyName, loadImage(filePath));
    }
}
