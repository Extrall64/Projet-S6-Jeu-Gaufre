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
    boolean auMoinsUnVideAutourDuPoison() {
        return (n.contenu(0, 1) == InterfaceNiveau.VIDE || n.contenu(1, 0) == InterfaceNiveau.VIDE);
    }
    /*
        1ere etape maintenir au mieu l'impossibilité de creer un rectangle couvrant
        toutes les case de gaufre
        2eme etape garder au mieu un nombre pair de case gaufre pour l'adversaire
    */
    Point mangerGagnantOuNonPerdant() {
        if (auMoinsUnVideAutourDuPoison()) {
            // Coup gagnant 1
            if (n.contenu(0, 1) == InterfaceNiveau.GAUFRE) return new Point(0, 1);
            // Coup gagnant 2
            if (n.contenu(1, 0) == InterfaceNiveau.GAUFRE) return new Point(1, 0);
            // Coup perdant
            return new Point(0,0);
        }
        // Coup non perdant

        /* Cas 1: la case (1,1) n'a pas encore été jouée:
                - selection de point aleatoire
                - eviter que ca soit sur les bords superieurs
                -valide avec rejet
        */
        if (n.contenu(1, 1) == InterfaceNiveau.GAUFRE) {
            int i,j;
            do {
                i = 1 + rand.nextInt(n.hauteur() - 1);
                j = 1 + rand.nextInt(n.largeur() - 1);
            } while (!n.estCoupValide(i,j));
            return new Point(i, j);
        }

        /* Cas 2 c'est a dire presence des bords de gaufre seuls, par exemple
            2 1 1 1 0
            1 0 0 0 0
            1 0 0 0 0
            sv: somme de case gaufre verticale
            sh: somme de case gaufre horizontale
            voir la paritée de nombre de case gaufre au bord
            essaye de maintenir un nombre pair de case
            NB: on essaie de faire mieux qu'on coup non perdant ...
        */
        int sv = 0; int sh = 0;
        for(int i = 1; i < n.hauteur(); i++)
            sv += n.contenu(i, 0);
        for(int j = 1; j < n.largeur(); j++)
            sh += n.contenu(0, j);
        if ( (sh % 2 == 0 && sv % 2 != 0)) return new Point(0, sh);
        if ( (sv % 2 == 0 && sh % 2 != 0))  return new Point(sv, 0);
        // On pourrait perde
        return new Point(sv, 0);
    }

    @Override
    public Point determineCoup() {
        return mangerGagnantOuNonPerdant();
    }
}