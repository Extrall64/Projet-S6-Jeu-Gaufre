package Interface;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Arbitre.InterfaceArbitre;

public class AdaptateurSouris extends MouseAdapter {
	JeuGraphique jg;
	InterfaceArbitre arbitre;

	public AdaptateurSouris(JeuGraphique j, InterfaceArbitre a) {
		jg = j;
		arbitre = a;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int c = e.getX() / jg.largeurCase();
		int l = e.getY() / jg.hauteurCase();
		arbitre.joue(l, c);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		System.out.println("OK");
		int c = e.getX() / jg.largeurCase();
		int l = e.getY() / jg.hauteurCase();
		arbitre.surligne(l, c);
	}
}
