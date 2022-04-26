package IHM;

import Elements.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class SelectionBall extends JFrame implements ActionListener {
    private JLabel demandeBalle;
	private JLabel balle1;
	private JLabel balle2;
	private JLabel balle3;
	private JButton boutonBalle1;
	private JButton boutonBalle2;
	private JButton boutonBalle3;
	private Ball ball;
    
    public SelectionBall(){
        this.setTitle("Choix de la balle");
		this.setSize(500,500);
		// Pour placer la fenêtre au centre de l'écran
		this.setLocation(500,250);
		// Pour permettre la fermeture de la fenêtre lors de l'appui sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// Création du JPanel pour afficher les règles
		JPanel panneauBalle = new JPanel();
		panneauBalle.setBounds(0,0,500,500);
		panneauBalle.setLayout(null);
		panneauBalle.setBackground(Color.white);
		
		// affichage du texte
		demandeBalle = new JLabel();
		demandeBalle.setBounds(25,0,200,50);
		demandeBalle.setLayout(null);
		demandeBalle.setText("Choisis la balle :");
		demandeBalle.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));		
		demandeBalle.setForeground(Color.black);
		panneauBalle.add(demandeBalle); 
		
		
		// pour chaque balle, on écrit le code pour :
		// afficher l'image de la balle
		// créer un bouton qui permet de choisir cette balle
		
		// balle 1
		
		balle1 = new JLabel();
		balle1.setIcon(new ImageIcon("Flipper/src/Resource/balle1.png"));
		balle1.setBounds(100,50,100,100);
		panneauBalle.add(balle1);
		
		boutonBalle1 = new JButton("Choisir");
		boutonBalle1.setBounds(50,150,100,30);
		boutonBalle1.setLayout(null);
		boutonBalle1.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,20));		
		boutonBalle1.setForeground(Color.black);
		boutonBalle1.setBackground(Color.green);
		panneauBalle.add(boutonBalle1);
		
		
		// balle 2
		
		balle2 = new JLabel();
		balle2.setIcon(new ImageIcon("Flipper/src/Resource/balle2.png"));
		balle2.setBounds(250,50,100,100);
		panneauBalle.add(balle2);
		
		boutonBalle2 = new JButton("Choisir");
		boutonBalle2.setBounds(200,150,100,30);
		boutonBalle2.setLayout(null);
		boutonBalle2.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,20));		
		boutonBalle2.setForeground(Color.black);
		boutonBalle2.setBackground(Color.green);
		panneauBalle.add(boutonBalle2);
		
		// balle 3
		
		balle3 = new JLabel();
		balle3.setIcon(new ImageIcon("Flipper/src/Resource/balle3.png"));
		balle3.setBounds(400,50,100,100);
		panneauBalle.add(balle3);
		
		boutonBalle3 = new JButton("Choisir");
		boutonBalle3.setBounds(350,150,100,30);
		boutonBalle3.setLayout(null);
		boutonBalle3.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,20));		
		boutonBalle3.setForeground(Color.black);
		boutonBalle3.setBackground(Color.green);
		panneauBalle.add(boutonBalle3);
		
		// on ajoute des écouteurs sur les boutons des balles 1 2 et 3
		
		boutonBalle1.addActionListener(this);
		boutonBalle2.addActionListener(this);
		boutonBalle3.addActionListener(this);

		
		add(panneauBalle);
		
		this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource()==boutonBalle1){
            ball = new Ball(2, 3, 4, 5, (float)0.3, (float)0.1);
        } else if (e.getSource()==boutonBalle2){
			ball = new Ball(2, 3, 4, 5, (float)0.3, (float)0.1);
        } else if(e.getSource()==boutonBalle3){
            ball = new Ball(2, 3, 4, 5, (float)0.3, (float)0.1);
        }
		this.dispose();
		new MainWindow(ball);
		
	}
}
