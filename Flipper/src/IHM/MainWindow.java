package IHM;

import javax.swing.JPanel;
import Elements.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame {
    public boolean isPressedL;
	public boolean isPressedJ;
    //private GameTable GT;
    //private GamePanel GP;
	private JPanel panneauScore;
	private JPanel panneauJeu;
    private Ball ball;
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
			this.ball  = new Ball(1,100, 3, 4, 5, (float)0.3, (float)0.1);
		}
		if(n==2){
			this.ball  = new Ball(2,2, 3, 4, 5, (float)0.3, (float)0.1);
		}
		if(n==3){
			this.ball  = new Ball(3,2, 3, 4, 5, (float)0.3, (float)0.1);
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
		
        this.startGame();
		
    }
	
    public void startGame(){
        GameTable GT = new GameTable(480, 720, this.ball);
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
    // méthodes pour associer la touche J au fait que la manette réagit
	// On créé une méthode isPressedJ qui devient TRUE lorsqu'on appuie sur J
	// Lorsque isPressedJ devient TRUE, alors ça va déclencher le mouvement de la manette (A CODER)
	
	
	public static void main(String[] args) {
        new MainWindow(1);
    
	}
	
	/**
	
	public void paint(Graphics g){
		g.setColor(Color.red);
		g.drawLine(500,500,50,50);
		g.drawOval(500,500,50,50);
	} **/
}
