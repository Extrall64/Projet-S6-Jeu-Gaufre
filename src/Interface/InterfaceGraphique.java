package Interface;

import javax.swing.*;
import java.awt.*;

import Arbitre.InterfaceArbitre;
import Arbitre.InterfaceNiveau;
import Arbitre.Niveau;

public class InterfaceGraphique implements Runnable{
	InterfaceNiveau jeu;
	InterfaceArbitre arbitre;
	boolean maximized;
	JFrame frame;
	JeuGraphique jg;

	InterfaceGraphique(InterfaceNiveau j, InterfaceArbitre a) {
		jeu = j;
		arbitre = a;
	}

	public static void demarrer(InterfaceNiveau j, InterfaceArbitre a) {
		SwingUtilities.invokeLater(new InterfaceGraphique(j, a));
	}

	public void run() {	
		frame = new JFrame("Gaufre");
		jg = new JeuGraphique(jeu);
		frame.add(jg);
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
		jg.repaint();
	}	
}
