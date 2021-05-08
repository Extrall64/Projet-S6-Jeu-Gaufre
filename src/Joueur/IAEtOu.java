package Joueur;

import Arbitre.InterfaceNiveau;
import Patterns.Point;

public class IAEtOu implements Joueur{
    public IAEtOu(InterfaceNiveau n) {}
    @Override
    public void informer(Point p) {}
    @Override
    public Point determineCoup() {
        return null;
    }
}