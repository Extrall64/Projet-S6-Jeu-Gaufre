package Joueur;

import java.awt.*;
import java.util.Random;
import Arbitre.*;

public class IAAleatoire implements Joueur{
	InterfaceNiveau n;
    Random rand;
    public IAAleatoire(InterfaceNiveau n) {
        this.n = n;
        rand = new Random();
    }
    public void informer(int i, int j) {}
    
    public Point determineCoup() {
        int i = rand.nextInt(n.hauteur());
        int j = rand.nextInt(n.largeur());
        while ( (i != 0 && j != 0) && !n.coupAutoriser(i, j)) {
            i = rand.nextInt(n.hauteur());
            j = rand.nextInt(n.largeur());            
        }
        return new Point(i, j);
    }
}
