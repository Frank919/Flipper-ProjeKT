package IHM;

import javax.swing.JPanel;
import Elements.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MainWindow extends JFrame implements ActionListener,KeyListener{

    //private GameTable GT;
    //private GamePanel GP;
	private JPanel panneauScore;
	private JPanel panneauJeu;
	private GameTable GT; 
    private GamePanel GP;

	private Ball ball;
	private Picture ballP; 
	private Picture flipperLP;
	private Picture flipperRP;
	private Timer timer;
	private Timer timer2;
	/**
	 * Largeur de la fenetre principale
	 */
	public static final int WIDTH = 640;
	/**
	 * Hauteur de la fenetre principale
	 */
	public static final int HEIGHT = 1000;

    
    public MainWindow(Ball ball) {
		
		
        this.ball  = ball;
        this.setTitle("Jeu du flipper");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension size = new Dimension(WIDTH, HEIGHT);
        int width = toolkit.getScreenSize().width;
        int height = toolkit.getScreenSize().height;
        this.setBounds(
			(int) (width - size.getWidth()) / 2,
            (int) (height - size.getHeight()) / 3, 
			(int) size.getWidth(), 
			(int) size.getHeight()
		);
		
		
		
		panneauJeu = new JPanel();
			panneauJeu.setBounds(0,0,WIDTH,HEIGHT);
			panneauJeu.setLayout(null); // permet de placer manuellement les composants
			//panneauJeu.setBackground(Color.black);
		
		
		panneauScore = new JPanel();
			panneauScore.setBounds(GameTable.width,0,WIDTH - GameTable.width,HEIGHT);
			panneauScore.setLayout(null); // permet de placer manuellement les composants
			panneauScore.setBackground(Color.white);
			panneauJeu.add(panneauScore);
		
		
		ballP = new Picture("balle"+this.ball.getNum());
		flipperLP = new Picture("flipperL");
		flipperLP.setX(132-15);
		flipperLP.setY(919-15);
		flipperRP = new Picture("flipperR");
		flipperRP.setX(517-145);
		flipperRP.setY(919-15);

		GP = new GamePanel(ballP,flipperLP,flipperRP);
		GP.setBounds(0, 0, GameTable.width, GameTable.height);
		panneauJeu.add(GP);

        GT = new GameTable(WIDTH, HEIGHT, this.ball);

		
        this.add(GP);
		this.addKeyListener(this);
        this.setVisible(true);

		timer = new Timer((int)GameTable.refreshTimeMS, this);
		//timer2 = new Timer((int)GameTable.refreshTimeMS, this);
		timer.start();
		//timer2.start();
    }
	
	
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_J){
			GT.flipperLeft.rotateUp();
			System.out.println("jjjjj");
		}
		if(e.getKeyCode()==KeyEvent.VK_L){
			GT.flipperRight.rotateUp();
			System.out.println("LLLL");
		}
	}
	public void keyReleased(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_J){
			GT.flipperLeft.rotateDown();
		}
		if(e.getKeyCode()==KeyEvent.VK_L){
			GT.flipperRight.rotateDown();
		}
		timer.start();
	}
	public void keyTyped(KeyEvent e){
		
	}
	public void actionPerformed(ActionEvent e) { 
		if (e.getSource() == timer){
			
			GT.refresh();
			ballP.setX(GT.ball.getPositionX()-20);
			ballP.setY(GT.ball.getPositionY()-20);
			GP.repaint();

            if(GT.ball.isOut()){
				this.dispose();
				System.out.println("game over");
                System.exit(0);
            }
		}
		
	}
	
}
