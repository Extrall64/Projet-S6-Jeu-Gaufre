package Joueur;

import Patterns.Point;

public class Humain implements Joueur{
    Point p;
    boolean aEteJoueParInterface;
    public Humain() {
        Point p = new Point(0,0);
        aEteJoueParInterface = false;
    }
    // appelé par l'adaptateur souris pour faire sortir de la boucle
    // dans determinerCoup
    @Override
    public void informer(Point p) {
        this.p = p;
        aEteJoueParInterface = true;
    }
    @Override
    public Point determineCoup() {
        // attendre que estTerminer soit vraie des le clique de la souris
        while(!aEteJoueParInterface) {}
        aEteJoueParInterface = false;
        return p;
    }
}

