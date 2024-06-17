import javax.swing.*;
import java.util.List;
// Git update steps
// 1. right click the folder in folder view and select "Git > Add"
// 2. right click the folder in folder view and click commit
// 3. Enter the commit message and commit the change
// 4. open the version control menu from the left icon row
// 5. Select Local > Master > right click > push
public class Main {
    public static void main(String[] args) {
        // Create the game components
        List<Location> waypoints = List.of(
                new Location(0, 250),
                new Location(300, 250),
                new Location(300, 450),
                new Location(600, 450),
                new Location(600, 250),
                new Location(900, 250)
        );
        Map map = new Map(48, 27, waypoints);
        WaveManager waveManager = new WaveManager(10, 1.0);
        Player player = new Player(100, 100);
        Game game = new Game(map, waveManager, player);

        // Create the game canvas
        GameCanvas gameCanvas = new GameCanvas(game);

        // Create the frame
        JFrame frame = new JFrame("Slimy Defense");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(gameCanvas);
        frame.setSize(1920, 1080); // Set the frame size
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
