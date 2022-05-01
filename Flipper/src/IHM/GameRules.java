package IHM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * @author Camille CECCHI
 * @Description: La fenêtre à afficher les regles
 * @date 01/05/2022
 */
public class GameRules extends JFrame implements ActionListener{
    // Les Widgets à déclarer en dehors du constructeur
	private JLabel consigne1;
	private JLabel consigne11;
	private JLabel consigne2;
	private JLabel consigne21;
	private JLabel consigne3;
	private JLabel consigne4;
	
	private JLabel lettreJ;
	private JLabel lettreL;

	private JTextField saisirPseudo;
	
	private JButton boutonCommencer;
	 
    public GameRules(){
		// Création de la fenêtre principale JFrame		
		this.setTitle("Règles du jeu du flipper");

		//Centrer la fenêtre sur l'écran
		Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension size = new Dimension(800, 800);
        int width = toolkit.getScreenSize().width;
        int height = toolkit.getScreenSize().height;
        this.setBounds(
			(int) (width - size.getWidth()) / 2,
            (int) (height - size.getHeight()) / 3, 
			(int) size.getWidth(), 
			(int) size.getHeight()
		);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
			// Création du JPanel pour afficher les règles
		JPanel panneauRegles = new JPanel();
		panneauRegles.setBounds(0,0,800,800);
		panneauRegles.setLayout(null);
		panneauRegles.setBackground(Color.black);
		
			// pour demander le pseudo à l'utilisateur
		JLabel demandePseudo = new JLabel();
		demandePseudo.setBounds(200,0,150,50);
		demandePseudo.setLayout(null);
		demandePseudo.setText("Pseudo :");
		demandePseudo.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));		
		demandePseudo.setForeground(Color.white);
		panneauRegles.add(demandePseudo);
		
			// pour entrer son pseudo
		saisirPseudo = new JTextField();
		saisirPseudo.setBounds(400,0,200,50);
		saisirPseudo.setLayout(null);
		saisirPseudo.setBackground(Color.white);
		panneauRegles.add(saisirPseudo);
		
			// affichage du texte qui donne les règles du jeu
		consigne1 = new JLabel();
		consigne1.setBounds(325,70,300,50);
		consigne1.setLayout(null);
		consigne1.setText("But du jeu :");
		consigne1.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));		
		consigne1.setForeground(Color.white);
		panneauRegles.add(consigne1);
		
		consigne11 = new JLabel();
		consigne11.setBounds(250,150,500,50);
		consigne11.setLayout(null);
		consigne11.setText("Aies le plus grand score !");
		consigne11.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));		
		consigne11.setForeground(Color.white);
		panneauRegles.add(consigne11);
		
		consigne2 = new JLabel(); // NE S'AFFICHE PAS
		consigne2.setBounds(130,220,700,50);
		consigne2.setLayout(null);
		consigne2.setText("Pour jouer, tu as besoin de seulement 2 touches :");
		consigne2.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));		
		consigne2.setForeground(Color.white);
		panneauRegles.add(consigne2);
		
		
		lettreJ = new JLabel();
		lettreJ.setIcon(new ImageIcon("./Flipper/src/Resource/J.png"));
		lettreJ.setBounds(200,350,100,100);
		panneauRegles.add(lettreJ);
		
		consigne21 = new JLabel();
		consigne21.setBounds(350,375,400,50);
		consigne21.setLayout(null);
		consigne21.setText(": pour le cote gauche");
		consigne21.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));		
		consigne21.setForeground(Color.white);
		panneauRegles.add(consigne21);
		
		lettreL = new JLabel();
		lettreL.setIcon(new ImageIcon("./Flipper/src/Resource/L.png"));
		lettreL.setBounds(200,450,100,100);
		panneauRegles.add(lettreL);
		
		consigne3 = new JLabel();
		consigne3.setBounds(350,475,400,50);
		consigne3.setLayout(null);
		consigne3.setText(": pour le cote droit");
		consigne3.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));		
		consigne3.setForeground(Color.white);
		panneauRegles.add(consigne3);
		
		consigne4 = new JLabel();
		consigne4.setBounds(150,600,450,50);
		consigne4.setLayout(null);
		consigne4.setText("A toi de jouer !");
		consigne4.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));		
		consigne4.setForeground(Color.white);
		panneauRegles.add(consigne4);
		
		boutonCommencer = new JButton("Commencer");
		boutonCommencer.setBounds(400,600,200,50);
		boutonCommencer.setLayout(null);
		boutonCommencer.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));		
		boutonCommencer.setForeground(Color.white);
		boutonCommencer.setBackground(Color.green);
		panneauRegles.add(boutonCommencer);
		
		// on ajoute le ActionListener sur le bouton Commencer
		// quand on clique sur Commencer, on ferme la page des règles du jeu
		// et le JPanel du choix des balles s'ouvre (voir méthode actionPerformed juste en dessous)
		
		boutonCommencer.addActionListener(this);
		
		
		//Pour ajouter le panneauRegles à la JFrame
		add(panneauRegles);
	
	
	
		this.setVisible(true);
	}
	
	// méthode qui permet de passer à la page des CHOIX BALLE lorsqu'on clique sur le bouton COMMENCER
	

    public void actionPerformed(ActionEvent e){
        this.dispose(); // permet d'enlever la page
        new SelectionBall();
    }
}
