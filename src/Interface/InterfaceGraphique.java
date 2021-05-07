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
	JLabel joueurCourant,joueur1,joueur2,IA1,IA2,dimension,d1,d2;
	JButton nouvellePartie,moins1, plus1,moins2, plus2,addH,addL,subH,subL;
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
		
		
		//bouton
		
		
		barreLaterale.add(Box.createGlue());
		joueur1 = createLabel("Joueur 1 :");
		barreLaterale.add(joueur1);
		Box changeIA1 = Box.createHorizontalBox();
		moins1 = createButton("<", "moins1");
		changeIA1.add(moins1);
		IA1 = createLabel("IA :");
		changeIA1.add(IA1);
		plus1 = createButton(">", "plus1");
		changeIA1.add(plus1);
		barreLaterale.add(changeIA1);
		
		barreLaterale.add(Box.createGlue());
		joueur2 = createLabel("Joueur 2 :");
		barreLaterale.add(joueur2);
		Box changeIA2 = Box.createHorizontalBox();
		moins2 = createButton("<", "moins2");
		changeIA2.add(moins2);
		IA2 = createLabel("IA :");
		changeIA2.add(IA2);
		plus2 = createButton(">", "plus2");
		changeIA2.add(plus2);
		barreLaterale.add(changeIA2);
		
		//dimension
		barreLaterale.add(Box.createGlue());
		
		dimension = createLabel("Dimension :");
		barreLaterale.add(dimension);
		
		Box dim1 = Box.createHorizontalBox();
		subH = createButton("<", "subH");
		dim1.add(subH);
		d1 = createLabel("IA :");
		dim1.add(d1);
		addH = createButton(">", "addH");
		dim1.add(addH);
		barreLaterale.add(dim1);
		
		Box dim2 = Box.createHorizontalBox();
		subL = createButton("<", "subL");
		dim2.add(subL);
		d2 = createLabel("IA :");
		dim2.add(d2);
		addL = createButton(">", "addL");
		dim2.add(addL);
		barreLaterale.add(dim2);
		

		barreLaterale.add(Box.createGlue());
		nouvellePartie = createButton("Nouvelle partie", "NouvellePartie");
		barreLaterale.add(nouvellePartie);
		
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
		IA1.setText(arbitre.etatIA(1));
		IA2.setText(arbitre.etatIA(2));
		d1.setText(""+ arbitre.nouvelleHauteur());
		d2.setText(""+ arbitre.nouvelleLargeur());
		jg.repaint();
	}
	
	public void setSurligne(Point p) {
		jg.setSurligne(p);
	}
}
