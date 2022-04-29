package IHM.Demo;

import javax.swing.*;
import java.awt.*;

public class DemoPanel extends JPanel implements Runnable {
    private Demo[] demos;
    private Image backgroundImage = new ImageIcon("Flipper/src/Resource/background.png").getImage();
    private double i;
    public DemoPanel(Demo... demos){
        this.demos = demos;
    }
    
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        
        //super.paint(g);
        
        
        g2.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), null);
        g2.rotate(Math.toRadians(i));
        for (Demo demo : this.demos) {
            g.drawImage(demo.getImage(),demo.getX(),demo.getY(),demo.getW(),demo.getH(),null);
        }
    }
    public void run() {
        while(true){
            i++;
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }
}
