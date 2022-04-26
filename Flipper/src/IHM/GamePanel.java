package IHM;

import javax.swing.*;


import java.awt.*;

public class GamePanel extends JPanel {
    private Picture[] pcs;
    private Image backgroundImage = new ImageIcon("Flipper/src/Resource/background.png").getImage();

    public GamePanel(Picture... pcs){
        this.pcs = pcs;
    }
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), null);
        for (Picture pc : this.pcs) {
            g.drawImage(pc.getImage(),pc.getX(),pc.getY(),pc.getW(),pc.getH(),null);
        }
        
    }
}
