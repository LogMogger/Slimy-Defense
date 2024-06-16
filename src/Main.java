import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create the game components
        List<Location> waypoints = List.of(
                new Location(0, 250, null),
                new Location(300, 250, null),
                new Location(300, 450, null),
                new Location(600, 450, null),
                new Location(600, 250, null),
                new Location(900, 250, null)
        );
        Map map = new Map(48, 27, waypoints);
        WaveManager waveManager = new WaveManager(10, 1.0f);
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
