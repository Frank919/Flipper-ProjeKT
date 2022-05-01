package IHM;

import Elements.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Chenglai FANG
 * @Description: La fenêtre principale du jeu
 * @date 01/05/2022
 */
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
	/**
	 * Construire la fenêtre principale
	 * @param ball
	 * 		Une balle choisie précédement
	 */
    public MainWindow(Ball ball) {
		this.ball  = ball;

		//Établir le JFrame
        this.setTitle("Jeu du flipper");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);

		//Centrer la fenêtre sur l'écran
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

		//Initialiser le panneau aux images
		GP = new GamePanel(ball);
		this.add(GP);
		this.setVisible(true);

		//Commencer le Thread à actualiser tous les éléments sur le panneau
		new Thread(GP,"GameData").start();
		
		
    }

	
}
