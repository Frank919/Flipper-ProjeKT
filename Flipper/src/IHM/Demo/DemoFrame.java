package IHM.Demo;

import javax.swing.*;
import Elements.*;
import IHM.*;

public class DemoFrame extends JFrame {
    private Ball ball = new Ball(1,2, 3, 4, 5, (float)0.3, (float)0.1);
    public DemoFrame() {
        this.setTitle("Demo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(640,1000);
        
        start();
        //startGame();
        
    }
    public void startGame(){
        GameTable GT = new GameTable(640, 1000,ball );
		Picture ball = new Picture("balle"+this.ball.getNum());
        GamePanel GP = new GamePanel(ball);

		ball.setX(GT.ball.getPositionX());
		ball.setY(GT.ball.getPositionY());
        this.add(GP);
        this.setVisible(true);

        while(true){
            try {
                Thread.sleep(GameTable.refreshTimeMS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            GT.refresh();
			ball.setX(GT.ball.getPositionX());
			ball.setY(GT.ball.getPositionY());
			GP.repaint();
            

            if(GT.ball.isOut()){
				this.dispose();
				System.out.println("game over");
                System.exit(0);
                break;
            }
        }
    }
    public void start(){
        Demo demo1 = new Demo("balle1");
        Demo demo2 = new Demo("balle2");
        DemoPanel p = new DemoPanel(demo1,demo2);
        new Thread(p).start();
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
