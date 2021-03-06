package Arbitre;

public interface InterfaceNiveau {
    public static final int VIDE = 0;
    public static final int GAUFRE = 1;
    public static final int POISON = 2;

    void initialiser(int h,int l);
    int hauteur();
    int largeur();
    boolean estJeuFini();
    boolean estCoupValide(int i, int j);
    void joue(int ligne, int colonne);
    int contenu(int i, int j);
    InterfaceNiveau clone();
    int hash();
}