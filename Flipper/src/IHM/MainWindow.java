package IHM;

import javax.swing.JPanel;
import Elements.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame implements KeyListener{
    public boolean isPressedL;
	public boolean isPressedJ;
    public MainWindow() {

       
        this.setTitle("Jeu du flipper");
		this.setSize(879,754);
		// Pour placer la fenêtre au centre de l'écran
		this.setLocation(500,0);
		// Pour permettre la fermeture de la fenêtre lors de l'appui sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel panneauJeu = new JPanel();
		panneauJeu.setBounds(0,0,479,754);
		panneauJeu.setLayout(null); // permet de placer manuellement les composants
		panneauJeu.setBackground(Color.black);
		
		
		JPanel panneauScore = new JPanel();
		panneauScore.setBounds(479,0,400,754);
		panneauScore.setLayout(null); // permet de placer manuellement les composants
		panneauScore.setBackground(Color.white);
		
		JLabel afficheJeu = new JLabel();
		afficheJeu.setIcon(new ImageIcon("C:/Users/camil/OneDrive/Pictures/flipperJEU.png"));
		afficheJeu.setBounds(0,0,479,754);		
		
		panneauJeu.add(afficheJeu);
		
		
		//Pour ajouter le panneau jaune à la JFrame
		add(panneauJeu);
		add(panneauScore);
		
		this.setVisible(true);

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
