package Joueur;

import Patterns.Point;

// la fonction joueParInterfaceGraphique est pour Humain, quand la souris est cliquée
// sinon ignoré pour les autres types de joueur
public interface Joueur {
    public void informer(Point p);
    public Point determineCoup();
}
