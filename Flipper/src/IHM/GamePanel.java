package IHM;

import Elements.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable{
    private List<Picture> pcs = new ArrayList<>();
    public GameTable GT; 
	private Ball ball;
	private Picture ballP; 
	private Picture flipperLP;
	private Picture flipperRP;
	//private Timer timer;
    private Image backgroundImage = new ImageIcon("./Flipper/src/Resource/background.png").getImage();

    public GamePanel(Ball ball){
        this.ball = ball;
        this.addKeyListener(new TAdapter());
        this.setFocusable(true);
        

        ballP = new Picture("balle"+this.ball.getNum());
		flipperLP = new Picture("flipperL");
		flipperLP.setX(132-15);
		flipperLP.setY(919-15);
		flipperRP = new Picture("flipperR");
		flipperRP.setX(517-145);
		flipperRP.setY(919-15);
        pcs.add(ballP);
        pcs.add(flipperLP);
        pcs.add(flipperRP);

        
        GT = new GameTable(MainWindow.WIDTH, MainWindow.HEIGHT, this.ball);


        //timer = new Timer((int)GameTable.refreshTimeMS, this);
		//timer.start();

    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), null);
        for (Picture pc : this.pcs) {
            g.drawImage(pc.getImage(),pc.getX(),pc.getY(),pc.getW(),pc.getH(),null);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    
    @Override
    public void run(){
        while(true){
            try {
                Thread.sleep(GameTable.refreshTimeMS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            GT.refresh();
            ballP.setX(GT.ball.getPositionX()-20);
            ballP.setY(GT.ball.getPositionY()-20);
            repaint();
    
            if(GT.ball.isOut()){
                System.out.println("game over");
                System.exit(0);
                break;
            }
        }
        
    }
    /*
    public void actionPerformed(ActionEvent e) { 
        
		GT.refresh();
		ballP.setX(GT.ball.getPositionX()-20);
		ballP.setY(GT.ball.getPositionY()-20);
		repaint();

        if(GT.ball.isOut()){
			
			System.out.println("game over");
            System.exit(0);
        }	
	}
    */

	private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            GT.flipperLeft.keyReleased(e);
			GT.flipperRight.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
			System.out.println("??????");
			GT.flipperLeft.keyPressed(e);
			GT.flipperRight.keyPressed(e);
        }
    }
}
