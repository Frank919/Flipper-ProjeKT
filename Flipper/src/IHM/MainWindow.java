package IHM;

import javax.swing.JPanel;
import Elements.*;

public class MainWindow extends JPanel{
    
    public MainWindow() throws InterruptedException{
        GameTable GT = new GameTable(720, 640);
        GT.startGame();
        
    }
}
