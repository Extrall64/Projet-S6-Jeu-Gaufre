package Joueur;

import Arbitre.InterfaceNiveau;
import Patterns.Point;

public class IAEtOu implements Joueur{
    public IAEtOu(InterfaceNiveau n) {}
    @Override
    public void informer(int i, int j) {}
    @Override
    public Point determineCoup() {
        return null;
    }
}