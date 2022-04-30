package IHM;

import Elements.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MainWindow extends JFrame {

	private JPanel panneauScore;
	private JPanel panneauJeu;
    private GamePanel GP;
	private Ball ball;
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
		GP = new GamePanel(ball);
		this.add(GP);
		this.setVisible(true);
		new Thread(GP,"GameData").start();
		
    }

	public static void main(String[] args) throws Exception {
        //new GameRules();
        //new SelectionBall();
		EventQueue.invokeLater(() -> {
            new MainWindow(new Ball(1,2, 3, 4, 5, 1.0, 0.1)).setVisible(true);;
        });
        
        
        
    }
}
