package IHM;

import javax.swing.JPanel;
import Elements.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame implements KeyListener{
    public boolean isPressedL;
	public boolean isPressedJ;
    public GameTable GT;
    public GamePanel GP;
	JPanel panneauScore;
    public Ball ball;
	/**
	 * Largeur de la fenetre principale
	 */
	public static int WIDTH = 640;
	/**
	 * Hauteur de la fenetre principale
	 */
	public static int HEIGHT = 720;

    
    public MainWindow(Ball ball) {
        this.ball  = ball;
        this.setTitle("Jeu du flipper");
		this.setResizable(false);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension size = new Dimension(WIDTH, HEIGHT);
        int width = toolkit.getScreenSize().width;
        int height = toolkit.getScreenSize().height;
        this.setBounds((int) (width - size.getWidth()) / 2,
                (int) (height - size.getHeight()) / 3, (int) size.getWidth(), (int) size.getHeight());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//JPanel panneauJeu = new JPanel();
		//panneauJeu.setBounds(0,0,479,754);
		//panneauJeu.setLayout(null); // permet de placer manuellement les composants
		//panneauJeu.setBackground(Color.black);
		
		
		panneauScore = new JPanel();
		panneauScore.setBounds(480,0,400,754);
		panneauScore.setLayout(null); // permet de placer manuellement les composants
		panneauScore.setBackground(Color.white);
		
		//JLabel afficheJeu = new JLabel();
		//afficheJeu.setIcon(new ImageIcon("Flipper/src/Resource/background.jpg"));
		//afficheJeu.setBounds(0,0,479,754);		
		//panneauJeu.add(afficheJeu);
		
		
		//Pour ajouter le panneau jaune à la JFrame

		//add(panneauJeu);
		add(panneauScore);
		
		this.setVisible(true);
        //this.startGame();
		
    }
    public void startGame(){
        GT = new GameTable(640, 720, ball);
        GP = new GamePanel(GT);
        this.add(GP);
        

        while(true){
            try {
                Thread.sleep(GameTable.refreshTimeMS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
			
            GT.refresh();

            if(GT.ball.isOut()){
                break;
            }
            //break;
        }

    }
    // méthodes pour associer la touche J au fait que la manette réagit
	// On créé une méthode isPressedJ qui devient TRUE lorsqu'on appuie sur J
	// Lorsque isPressedJ devient TRUE, alors ça va déclencher le mouvement de la manette (A CODER)
	
	public boolean isPressedJ(){
		return false;
	}
	
	
	public boolean isPressedL(){
		return false;
	}
	
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_J){
			isPressedJ=true;
		}else if(e.getKeyCode()==KeyEvent.VK_L){
			isPressedL=true;
		}
	}
	
	public void keyReleased(KeyEvent e){
			
		if(e.getKeyCode()==KeyEvent.VK_J){
			isPressedJ=false;
		}else if(e.getKeyCode()==KeyEvent.VK_L){
			isPressedL=false;
		}
	}
	public void keyTyped(KeyEvent e){

	}

	
	/**
	
	public void paint(Graphics g){
		g.setColor(Color.red);
		g.drawLine(500,500,50,50);
		g.drawOval(500,500,50,50);
	} **/
}
