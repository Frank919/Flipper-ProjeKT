package IHM;

import Elements.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
/**
 * @author Chenglai FANG
 * @Description: Le panneau à déssiner les images
 * @date 01/05/2022
 */
public class GamePanel extends JPanel implements Runnable{
    private List<Picture> pcs = new ArrayList<>();
    public GameTable GT; 
	private Ball ball;
	private Picture ballP; 
	private Picture flipperLP;
	private Picture flipperRP;
    private Image backgroundImage = new ImageIcon("./Flipper/src/Resource/background.png").getImage();
    
    /**
     * 
     * @param ball la balle choisie
     */
    public GamePanel(Ball ball){
        this.ball = ball;
        this.addKeyListener(new TAdapter());
        this.setFocusable(true);
        
        //Ajouter dans un ArrayList pcs tous les images en mouvement
        ballP = new Picture("balle"+this.ball.getNum());
		flipperLP = new Picture("flipperL");
		flipperLP.setX(132-15);
		flipperLP.setY(919-35);
		flipperRP = new Picture("flipperR");
		flipperRP.setX(517-145);
		flipperRP.setY(919-35);
        pcs.add(ballP);
        pcs.add(flipperLP);
        pcs.add(flipperRP);

        //Initialiser tous les obstacles
        //Initialiser le back end
        GT = new GameTable(MainWindow.WIDTH, MainWindow.HEIGHT, this.ball);

        

    }
    //Déssiner les images
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), null);
        for (Picture pc : this.pcs) {
            g2d.drawImage(pc.getImage(),pc.getX(),pc.getY(),pc.getW(),pc.getH(),null);
        }

        Toolkit.getDefaultToolkit().sync();
        //System.out.println("paint success");
    }

    //Actualisation
    @Override
    public void run(){
        while(true){
            try {
                Thread.sleep(GameTable.refreshTimeMS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Mettre à jour le back end
            GT.refresh();
            //Mettre à jour le front end
            ballP.setX(GT.ball.getPositionX()-20);
            ballP.setY(GT.ball.getPositionY()-20);
            //REdéssiner ce panneau
            this.repaint();
            //Condition à laquelle s'arrete le jeu
            if(GT.ball.isOut()){
                System.out.println("game over");
                //System.exit(0);
                new SelectionBall();
                break;
            }
        }
    }

    //Passer les touches aux flippers
    
	private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            GT.flipperLeft.keyReleased(e);
			GT.flipperRight.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
			GT.flipperLeft.keyPressed(e);
			GT.flipperRight.keyPressed(e);
        }
    }
    
}
