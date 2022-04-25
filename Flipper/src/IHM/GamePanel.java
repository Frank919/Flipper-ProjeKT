package IHM;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private Pictures drawings;
    
    public GamePanel(Pictures drawings){
        this.drawings = drawings;
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Image image = new ImageIcon("src/Resource/ackground.jpg").getImage();
        g.drawImage(image, 0, 0, 479,754,null);

    }
}
