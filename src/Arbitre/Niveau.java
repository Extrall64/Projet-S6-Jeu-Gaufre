package Arbitre;

public class Niveau implements InterfaceNiveau{
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
                T[i][j] = InterfaceNiveau.GAUFRE;
        T[0][0] = InterfaceNiveau.POISON;
    }

    public int hauteur() { return hauteur;}

    public int largeur() { return largeur;}

    public int contenu(int i, int j) { return T[i][j];}


    public boolean estJeuFini() {
        return T[0][0] == InterfaceNiveau.VIDE;
    }

    public boolean coupAutoriser(int i, int j) {
        return i >= 0 && i < hauteur && j >= 0 && j < largeur && T[i][j] != InterfaceNiveau.VIDE && !estJeuFini();
    }

    public void joue(int ligne, int colonne) {
        int i = ligne;
        int j;
        while(i<hauteur){
            j = colonne;
            while(j<largeur){
                T[i][j]=InterfaceNiveau.VIDE;
                j++;
            }
            i++;
        }
    }
}
