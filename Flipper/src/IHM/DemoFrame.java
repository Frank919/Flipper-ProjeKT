package IHM;

import javax.swing.*;

public class DemoFrame extends JFrame {
    public DemoFrame() {
        this.setTitle("Demo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        

        Demo demo1 = new Demo("balle1");
        Demo demo2 = new Demo("background");
        DemoPanel p = new DemoPanel(demo1,demo2);
        this.add(p);
        this.setVisible(true);
        while(true){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            demo1.setX(demo1.getX()+1);
            demo1.setY(demo1.getY()+1);
            demo2.setX(demo2.getX()+2);
            demo2.setY(demo2.getY()+1);

            p.repaint();
        }
    }

    public static void main(String[] args) {
        new DemoFrame();
    }
}
