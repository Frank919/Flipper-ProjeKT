package IHM;

import javax.swing.*;
import java.awt.*;

public class DemoPanel extends JPanel {
    private Demo demo;

    public DemoPanel(){
        
    }
    public DemoPanel(Demo demo){
        this.demo = demo;
    }
    public void paint(Graphics g){
        super.paint(g);
        
        g.drawImage(demo.getImage(),demo.getX(),demo.getY(),100,100,null);
        

    }
}
