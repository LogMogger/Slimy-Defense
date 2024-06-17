import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {
    private GameCanvas gameCanvas;
    private Game game;

    public GameFrame(Game game) {
        this.game = game;
        this.gameCanvas = new GameCanvas(game);
        setupFrame();
    }

    private void setupFrame() {
        setTitle("Slimy Defense");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(gameCanvas);

        setupMenu();
    }
// DO NOT TOUCH - logan
    private void setupMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Menu");
        JMenuItem mainMenuItem = new JMenuItem("Main Menu");
        mainMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameCanvas.setCurrentScreen("MainMenu");
            }
        });
        menu.add(mainMenuItem);

        JMenuItem mapSelectionItem = new JMenuItem("Map Selection");
        mapSelectionItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameCanvas.setCurrentScreen("MapSelection");
            }
        });
        menu.add(mapSelectionItem);

        JMenuItem howToPlayItem = new JMenuItem("How to Play");
        howToPlayItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameCanvas.setCurrentScreen("HowToPlay");
            }
        });
        menu.add(howToPlayItem);

        JMenuItem creditsItem = new JMenuItem("Credits");
        creditsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameCanvas.setCurrentScreen("Credits");
            }
        });
        menu.add(creditsItem);

        menuBar.add(menu);
        setJMenuBar(menuBar);
    }
}
