package Joueur;

import Patterns.Point;

public class Humain implements Joueur{
    int i, j;
    boolean estTerminer;
    public Humain() {
        i = 0;
        j = 0;
        estTerminer = false;
    }
    // appelé par l'adaptateur souris pour faire sortir de la boucle
    // dans determiner coup
    @Override
    public void informer(int i, int j) {
        this.i = i;
        this.j = j;
        estTerminer = true;
    }
    @Override
    public Point determineCoup() {
        // attendre que estTerminer soit vraie des le clique de la souris
        while(!estTerminer) {} 
        estTerminer = false;
        return new Point(i, j);
    }
}

