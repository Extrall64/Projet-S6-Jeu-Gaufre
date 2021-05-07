import Arbitre.Arbitre;
import Arbitre.InterfaceArbitre;
import Arbitre.InterfaceNiveau;
import Arbitre.Niveau;
import Global.Configuration;
import Interface.InterfaceGraphique;

public class gaufre {
    public static void main(String [] args) {
		int l = Configuration.instance().largeur();
		int h = Configuration.instance().hauteur();
		InterfaceNiveau n = new Niveau(h,l);
		InterfaceArbitre a = new Arbitre(n);
		InterfaceGraphique.demarrer(n,a);
    }
}
