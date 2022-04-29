package IHM;

import javax.swing.JPanel;
import Elements.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame implements Runnable {
    public boolean isPressedL;
	public boolean isPressedJ;
    //private GameTable GT;
    //private GamePanel GP;
	private JPanel panneauScore;
	private JPanel panneauJeu;
    private Ball ball;
	private GameTable GT; 
	private Picture ballP; 
    private GamePanel GP; 
	/**
	 * Largeur de la fenetre principale
	 */
	public static final int WIDTH = 640;
	/**
	 * Hauteur de la fenetre principale
	 */
	public static final int HEIGHT = 1000;

    
    public MainWindow(int n) {
		if(n==1){
			this.ball  = new Ball(1,100, 3, 4, 5, 0.3, 0.5);
		}
		if(n==2){
			this.ball  = new Ball(2,2, 3, 4, 5, 0.3, 0.5);
		}
		if(n==3){
			this.ball  = new Ball(3,2, 3, 4, 5, 0.3, 0.5);
		}
        //this.ball  = ball;
        this.setTitle("Jeu du flipper");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		//this.setSize(480,720);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension size = new Dimension(WIDTH, HEIGHT);
        int width = toolkit.getScreenSize().width;
        int height = toolkit.getScreenSize().height;
        this.setBounds((int) (width - size.getWidth()) / 2,
                (int) (height - size.getHeight()) / 3, (int) size.getWidth(), (int) size.getHeight());
		
		
		
		/*panneauJeu = new JPanel();
			panneauJeu.setBounds(0,0,WIDTH,HEIGHT);
			panneauJeu.setLayout(null); // permet de placer manuellement les composants
			panneauJeu.setBackground(Color.black);
		
		
		panneauScore = new JPanel();
			panneauScore.setBounds(GameTable.width,0,WIDTH - GameTable.width,HEIGHT);
			panneauScore.setLayout(null); // permet de placer manuellement les composants
			panneauScore.setBackground(Color.white);
			panneauJeu.add(panneauScore);
		*/
		GT = new GameTable(WIDTH, HEIGHT, this.ball);
		ballP = new Picture("balle"+this.ball.getNum());
        GP = new GamePanel(ballP);
        //this.startGame();
		this.addKeyListener(
			new KeyListener(){
				public void keyPressed(KeyEvent e){
					if(e.getKeyCode() == KeyEvent.VK_J){
						System.out.println("JJJJJJJ");
						GT.flipperLeft.rotateUp();
					}
					if(e.getKeyCode() == KeyEvent.VK_L){
						System.out.println("LLLLLLL");
						GT.flipperRight.rotateUp();
					}
				}
				public void keyTyped(KeyEvent e){}
				public void keyReleased(KeyEvent e){
					if(e.getKeyCode() == KeyEvent.VK_J){
						GT.flipperLeft.rotateDown();
					}
					if(e.getKeyCode() == KeyEvent.VK_L){
						GT.flipperRight.rotateDown();
					}
				}
			}
		);
		
    }
	
    public void startGame(){
        //GameTable GT = new GameTable(WIDTH, HEIGHT, this.ball);
		//Picture ballP = new Picture("balle"+this.ball.getNum());
        //GamePanel GP = new GamePanel(ballP);

		ballP.setX(GT.ball.getPositionX()-20);
		ballP.setY(GT.ball.getPositionY()-20);
        this.add(GP);
        this.setVisible(true);

        while(true){
            try {
                Thread.sleep(GameTable.refreshTimeMS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            GT.refresh();
			ballP.setX(GT.ball.getPositionX());
			ballP.setY(GT.ball.getPositionY());
			GP.repaint();

            if(GT.ball.isOut()){
				this.dispose();
				System.out.println("game over");
                System.exit(0);
                break;
            }
        }
    }
    
	
	public void run(){
		ballP.setX(GT.ball.getPositionX()-20);
		ballP.setY(GT.ball.getPositionY()-20);
        this.add(GP);
        this.setVisible(true);

        while(true){
            try {
                Thread.sleep(GameTable.refreshTimeMS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            GT.refresh();
			ballP.setX(GT.ball.getPositionX());
			ballP.setY(GT.ball.getPositionY());
			GP.repaint();

            if(GT.ball.isOut()){
				this.dispose();
				System.out.println("game over");
                System.exit(0);
                break;
			}
		}
	}
}
