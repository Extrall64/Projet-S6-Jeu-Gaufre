package Joueur;

import Arbitre.*;
import Patterns.Point;
import java.util.*;

public class IAEtOu implements Joueur {
    InterfaceNiveau n;
    Hashtable<Integer, List<Point>> coupGagnant;
    // represente le tour de A ou de B, utilisé pour determiner l'evaluation()
    boolean estJoueurA;
    Random rand;
    public IAEtOu(InterfaceNiveau n) {
        rand = new Random();
        this.n = n;
        coupGagnant = new Hashtable<Integer, List<Point> >();
        estJoueurA = true;
    }

    @Override
    public void informer(Point p) {}

    @Override
    public Point determineCoup() {
        //s'il y a un coup gagnant renvoyé le sinon jouer un coup aleatoire
        calculJoueurA(n);
        // le coup a faire apres cette configuration (la grille courante)
        Point p = coupGagnant.get(n.hash()).get(0);
        System.out.printf("Coup IA: %d %d\n", p.x, p.y);
        return p;
    }
    boolean calculJoueurA(InterfaceNiveau config) {
        estJoueurA = true;
        if (estFeuille(config)) {
            return evaluation(config);
        }
        else {
            // enumerer tous les coups possible par A
            List<Point> C = coup(config);
            boolean valeur = false;
            coupGagnant.put( config.hash(), new ArrayList<Point>() );
            // pour chaque coup, clonner le niveau et jouer ce coup
            for (int i = 0; i < C.size(); i++) {
                InterfaceNiveau nconfig = config.clone();
                // si le joueur B perdant, alors ajouter la configuration dans coupGagnant
                nconfig.joue( C.get(i).x, C.get(i).y );
                if (calculJoueurB(nconfig)) {
                    valeur = true;
                    System.out.println("Ajouter dans coupGagnant");
                    // ajouter les coups a jouer pour cette configuration
                    coupGagnant.get(config.hash()).add( C.get(i) );
                    // return la permet de arreter de essayer les coups
                    // dés qu'on a trouver un gagnant
                    return valeur;
                }
            }
            // s'il n'y a pas un de coup dans la configuration -> choisir un aleatoire
            // eviter le coup 0, 0 tant que y a d'autre coup possible a jouer
            if (coupGagnant.get(config.hash()).size() == 0) {
                if (C.size() > 1) {
                    int coupAleatoire = rand.nextInt( C.size() -1 );
                    // obtenir par le hash de niveau, le coup a jouer
                    coupGagnant.get(config.hash()).add( C.get( coupAleatoire + 1 ) );
                } else coupGagnant.get(config.hash()).add( new Point(0, 0) );
            }
            return valeur;
        }
    }

    boolean calculJoueurB(InterfaceNiveau config) {
        estJoueurA = false;
        if (estFeuille(config)) {
            return evaluation(config);
        }
        else {
            boolean valeur = true;
            // enumer coup possible par B
            List<Point> C = coup(config);
            // pour chaque coup, clonne niveau et le jouer
            for (int i = 0; i < C.size(); i++) {
                InterfaceNiveau nconfig = config.clone();
                nconfig.joue( C.get(i).x, C.get(i).y );
                valeur = valeur && calculJoueurA(nconfig);
                // return la permet de arreter de essayer les coups
                // dés qu'on a trouver un gagnant
                if (!valeur) return valeur;
            }
            return valeur;
        }
    }
    List <Point> coup(InterfaceNiveau niv) {
        List<Point> r = new ArrayList<>();
        // enumerer l'ensemble de coup possible
        for(int i = 0; i < niv.hauteur() && niv.contenu(i, 0) != niv.VIDE; i++)
            for (int j = 0; j < niv.largeur() && niv.contenu(0, j) != niv.VIDE; j++)
                if (niv.estCoupValide(i, j)) {
                    r.add( new Point(i, j));
                }
        return r;
    }
    boolean estFeuille(InterfaceNiveau niv) {
        return niv.contenu(0, 0) == niv.VIDE;
    }

    boolean evaluation(InterfaceNiveau niv) {
        //si estJoueurA == vrai alors B a mangé le poison le dernier tour
        boolean res = estJoueurA && niv.contenu(0, 0) == niv.VIDE;
        if (res) System.out.println("Coup gagnant pour A");
        return res;
    }
}