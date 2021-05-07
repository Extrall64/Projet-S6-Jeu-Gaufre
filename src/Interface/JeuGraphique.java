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
		// afficher cases
		drawable.setColor(Color.yellow);
		for (int i = 0; i < niveau.hauteur(); i++)
			for (int j = 0; j < niveau.largeur(); j++)
				if (niveau.contenu(i, j) == InterfaceNiveau.GAUFRE || niveau.contenu(i, j) == InterfaceNiveau.POISON) drawable.fillRect(j*largeurCase, i*hauteurCase, largeurCase, hauteurCase);
		// afficher case poison
		drawable.setColor(Color.green);
		drawable.fillOval(0, 0, largeurCase, hauteurCase);

		// afficher les lignes colonnes
		drawable.setColor(Color.black);
		drawable.setStroke(new BasicStroke(2));
		for (int i = 0; i <= niveau.hauteur(); i++)
			drawable.drawLine(0, i*hauteurCase, hauteur, i*hauteurCase);
		for (int i = 0; i <= niveau.largeur(); i++)
			drawable.drawLine(i*largeurCase, 0, i*largeurCase, largeur);
	}
	
	int largeurCase() {
		return largeurCase;
	}

	int hauteurCase() {
		return hauteurCase;
	}
}
