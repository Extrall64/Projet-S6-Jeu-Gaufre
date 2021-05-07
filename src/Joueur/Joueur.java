package Joueur;

import java.awt.*;

// la fonction informer est pour Humain, a la clique du souris
// sinon ignorer pour les autres type de joueur
public interface Joueur {
    public void informer(int i, int j);
    public Point determineCoup();
}
