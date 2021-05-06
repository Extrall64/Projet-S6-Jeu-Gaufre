package Arbitre;

public interface InterfaceNiveau {
    int hauteur();
    int largeur();
    boolean estJeuFini();
    boolean coupAutoriser(int i, int j);
    void joue(int ligne, int colonne);
}
