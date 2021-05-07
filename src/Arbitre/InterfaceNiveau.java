package Arbitre;

public interface InterfaceNiveau {
	public static final int VIDE = 0;
    public static final int GAUFRE = 1;
    public static final int POISON = 2;
    
    public void initialiser();
    int hauteur();
    int largeur();
    boolean estJeuFini();
    boolean coupAutoriser(int i, int j);
    void joue(int ligne, int colonne);
	public int contenu(int i, int j);
}
