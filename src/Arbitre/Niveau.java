package Arbitre;

public class Niveau {
    public static final int VIDE = 0;
    public static final int GAUFRE = 1;
    public static final int POISON = 2;
    int [][] T;
    int hauteur, largeur;

    public Niveau(int h, int l) {
        hauteur = h;
        largeur = l;
        T = new int[h][l];
        initialiser();
    }
    public void initialiser() {
        for(int i = 0; i < hauteur; i++)
            for(int j = 0; j < largeur; j++)
                T[i][j] = GAUFRE;
        T[0][0] = POISON;
    }

    public int hauteur() { return hauteur;}

    public int largeur() { return largeur;}

    public boolean estJeuFini() {
        return T[0][0] == VIDE;
    }

    public boolean coupAutoriser(int i, int j) {
        return i >= 0 && i < hauteur && j >= 0 && j < largeur && T[i][j] != VIDE;
    }

    public void joue(int ligne, int colonne) {
        int i = ligne;
        int j;
        while(i<largeur){
            j = colonne;
            while(j<hauteur){
                T[i][j]=VIDE;
                j++;
            }
            i++;
        }
    }
}
