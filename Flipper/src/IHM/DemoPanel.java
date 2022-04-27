package IHM;

import javax.swing.*;
import java.awt.*;

public class DemoPanel extends JPanel {
    private Demo[] demos;
    private Image backgroundImage = new ImageIcon("Flipper/src/Resource/background.png").getImage();

    public DemoPanel(Demo... demos){
        this.demos = demos;
    }
    
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), null);
        for (Demo demo : this.demos) {
            g.drawImage(demo.getImage(),demo.getX(),demo.getY(),demo.getW(),demo.getH(),null);
        }
    }
}
