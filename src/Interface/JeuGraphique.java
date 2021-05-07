package Interface;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import Arbitre.InterfaceNiveau;
import Arbitre.Niveau;

public class JeuGraphique extends JComponent {
	int largeur, hauteur,largeurCase,hauteurCase;
	Graphics2D drawable;
	InterfaceNiveau niveau;

	public JeuGraphique(InterfaceNiveau in) {
		niveau = in;
	}

	@Override
	public void paintComponent(Graphics g) {
		drawable = (Graphics2D) g;

		largeur = getSize().width;
		hauteur = getSize().height;
		largeurCase = largeur/niveau.largeur();
		hauteurCase = hauteur/niveau.hauteur();

		drawable.clearRect(0, 0, largeur, hauteur); //efface tout
		tracerNiveau();

	}
	
	void tracerNiveau() {
		int margeX = largeurCase/5;
		int margeY = hauteurCase/5;
		// afficher cases
		drawable.setColor(Color.yellow);
		for (int i = 0; i < niveau.hauteur(); i++)
			for (int j = 0; j < niveau.largeur(); j++)
				if (niveau.contenu(i, j) == InterfaceNiveau.GAUFRE || niveau.contenu(i, j) == InterfaceNiveau.POISON) drawable.fillRect(j*largeurCase, i*hauteurCase, largeurCase, hauteurCase);
		// afficher case poison
		drawable.setColor(Color.green);
		drawable.fillOval(margeX/2, margeY/2, largeurCase-margeX, hauteurCase-margeY);

		// afficher les lignes colonnes
		drawable.setColor(Color.black);
		drawable.setStroke(new BasicStroke(2));
		for (int i = 0; i < niveau.hauteur() - 1; i++)
			drawable.drawLine(0, (i+1)*hauteurCase, largeur, (i+1)*hauteurCase);
		for (int i = 0; i < niveau.largeur() - 1; i++)
			drawable.drawLine((i+1)*largeurCase, 0, (i+1)*largeurCase, hauteur);
	}
	
	int largeurCase() {
		return largeurCase;
	}

	int hauteurCase() {
		return hauteurCase;
	}
}
