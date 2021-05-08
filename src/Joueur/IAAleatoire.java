package Joueur;


import java.util.Random;
import Arbitre.*;
import Patterns.Point;

public class IAAleatoire implements Joueur{
	InterfaceNiveau n;
    Random rand;
    public IAAleatoire(InterfaceNiveau n) {
        this.n = n;
        rand = new Random();
    }
    public void informer(Point p) {}
    
    public Point determineCoup() {
        int i,j;
        do {
            i = rand.nextInt(n.hauteur());
            j = rand.nextInt(n.largeur());
        }
        while (!n.estCoupValide(i, j));
        return new Point(i, j);
    }
}
