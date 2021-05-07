package Interface;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.*;

import Arbitre.InterfaceArbitre;
import Arbitre.InterfaceNiveau;
import Patterns.Point;

public class InterfaceGraphique implements Runnable{
	InterfaceNiveau jeu;
	InterfaceArbitre arbitre;
	boolean maximized;
	JFrame frame;
	JLabel joueurCourant,typeIA,IA;
	JButton nouvellePartie,moins, plus;
	JeuGraphique jg;
	

	InterfaceGraphique(InterfaceNiveau j, InterfaceArbitre a) {
		jeu = j;
		arbitre = a;
	}

	public static void demarrer(InterfaceNiveau j, InterfaceArbitre a) {
		SwingUtilities.invokeLater(new InterfaceGraphique(j, a));
	}
	
	private JLabel createLabel(String s) {
		JLabel lab = new JLabel(s);
		lab.setAlignmentX(Component.CENTER_ALIGNMENT);
		return lab;
	}
	
	private JButton createButton(String s, String c) {
		JButton but = new JButton(s);
		but.addActionListener(new AdaptateurCommande(arbitre, c));
		but.setAlignmentX(Component.CENTER_ALIGNMENT);
		but.setFocusable(false);
		return but;
	}

	public void run() {	
		frame = new JFrame("Gaufre");
		jg = new JeuGraphique(jeu);
		frame.add(jg);
		
		//affichage
		Box barreLaterale = Box.createVerticalBox();
		barreLaterale.add(createLabel("Gaufre"));
		
		barreLaterale.add(Box.createGlue());
		
		joueurCourant = createLabel("Au tour de : Joueur 1");
		barreLaterale.add(joueurCourant);
		
		barreLaterale.add(Box.createGlue());
		//bouton
		nouvellePartie = createButton("Nouvelle partie", "NouvellePartie");
		barreLaterale.add(nouvellePartie);
		
		barreLaterale.add(Box.createGlue());
		typeIA = createLabel("IA :");
		barreLaterale.add(typeIA);
		Box changeIA = Box.createHorizontalBox();
		moins = createButton("<", "moins");
		changeIA.add(moins);
		IA = createLabel("IA :");
		changeIA.add(IA);
		plus = createButton(">", "plus");
		changeIA.add(plus);
		barreLaterale.add(changeIA);
		
		barreLaterale.add(Box.createGlue());
		frame.add(barreLaterale, BorderLayout.LINE_END);
		
		jg.addMouseListener(new AdaptateurSouris(jg, arbitre));
		Timer time = new Timer(16, new AdaptateurTemps(arbitre));
		time.start();
		arbitre.fixerInterfaceGraphique(this);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 300);
		frame.setVisible(true);
	}

	public void basculePleinEcran() {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();
		if (maximized) {
			device.setFullScreenWindow(null);
			maximized = false;
		} else {
			device.setFullScreenWindow(frame);
			maximized = true;
		}
	}

	public void metAJour() {
		joueurCourant.setText(arbitre.etatJoueur());
		IA.setText(arbitre.etatIA());
		jg.repaint();
	}
	
	public void setSurligne(Point p) {
		jg.setSurligne(p);
	}
}
