package Joueur;

import java.util.Random;

import Arbitre.InterfaceNiveau;
import Patterns.Point;

public class IAGagnantPerdant implements Joueur{
	InterfaceNiveau n;
    Random rand;

    public IAGagnantPerdant(InterfaceNiveau n) {
        this.n = n;
        rand = new Random();
    }

    @Override
    public void informer(Point p) {}

    /* mangable d'un coup
        1) si le rectangle depuis le point (1, 0) couvre tt le gaufre sauf poison
        2) si le rectangle depuis le point (0, 1) couvre tt le gaufre sauf poison
    */
    boolean estMangableUnCoup() {
        return !(n.contenu(0, 1) == InterfaceNiveau.GAUFRE && n.contenu(1, 0) == InterfaceNiveau.GAUFRE);
    }
    /*
        1ere etape maintenir au mieu l'impossibilité de creer un rectangle couvrant
        toutes les case de gaufre
        2eme etape garder au mieu un nombre pair de case gaufre pour l'adversaire
    */
    Point manger() {
        if (estMangableUnCoup()) {
            if (n.contenu(0, 1) == InterfaceNiveau.GAUFRE) return new Point(0, 1);
            if (n.contenu(1, 0) == InterfaceNiveau.GAUFRE) return new Point(1, 0);
        } else {

            /* si la condition est vraie: rester a l'etape 1
                - selection de point aleatoire
                - eviter que ca soit sur les bords superieurs
            */
            if (!(n.contenu(1, 1) == InterfaceNiveau.VIDE)) {
                int i = rand.nextInt(n.hauteur()-1);
                int j = rand.nextInt(n.largeur()-1);
                while (!n.estCoupValide(i+1, j+1)) {
                    i = rand.nextInt(n.hauteur()-1);
                    j = rand.nextInt(n.largeur()-1);            
                }
                return new Point(i+1, j+1);
            }
            /* passer a l'etape 2
            car presence que les bords de gaufre
            2 1 1 1 1
            1 0 0 0 0
            1 0 0 0 0
            */
            else {
                // sv: somme de case gaufre verticale
                // sh: somme de case gaufre horizontale
                int sv = 0; int sh = 0;
                for(int i = 1; i < n.hauteur(); i++)
                    sv += n.contenu(i, 0);
                for(int i = 1; i < n.largeur(); i++)
                    sh += n.contenu(0, i);
                // voir la paritée de nombre de case gaufre au bord
                // essaye de maintenir un nombre pair de case
                if ( (sh % 2 == 0 && sv % 2 != 0)) return new Point(0, sh);
                else if ( (sv % 2 == 0 && sh % 2 != 0))  return new Point(sv, 0);
                // probabilité de perdre
                else return new Point(sv, 0);
            }
        }
        return new Point(0, 0);
    }
    @Override
    public Point determineCoup() {
        return manger();
    }
}