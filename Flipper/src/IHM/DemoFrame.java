package IHM;

import javax.swing.*;

public class DemoFrame extends JFrame {
    public DemoFrame() {
        this.setTitle("Demo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        

        Demo demo = new Demo();
        DemoPanel p = new DemoPanel(demo);
        this.add(p);
        this.setVisible(true);
        while(true){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            demo.setX(demo.getX()+1);
            demo.setY(demo.getY()+1);
            p.repaint();
        }
    }

    public static void main(String[] args) {
        new DemoFrame();
    }
}
