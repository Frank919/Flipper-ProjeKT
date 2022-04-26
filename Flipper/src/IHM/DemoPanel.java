package IHM;

import javax.swing.*;
import java.awt.*;

public class DemoPanel extends JPanel {
    private Demo[] demos;

    //public DemoPanel(){
        
    //
    public DemoPanel(Demo... demos){
        this.demos = demos;
    }
    
    public void paint(Graphics g){
        super.paint(g);
        for (Demo demo : this.demos) {
            g.drawImage(demo.getImage(),demo.getX(),demo.getY(),demo.getW(),demo.getH(),null);
        }
    }
}
