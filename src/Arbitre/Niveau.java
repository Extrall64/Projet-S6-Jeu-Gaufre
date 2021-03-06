package Arbitre;

import java.util.Arrays;

public class Niveau implements InterfaceNiveau{
    int [][] T;
    int hauteur, largeur;

    public Niveau(int h, int l) {
        hauteur = h;
        largeur = l;
        T = new int[h][l];
        initialiser(h,l);
    }
    public void initialiser(int h,int l) {
        hauteur = h;
        largeur = l;
        T = new int[h][l];
        for(int i = 0; i < hauteur; i++)
            for(int j = 0; j < largeur; j++)
                T[i][j] = GAUFRE;
        T[0][0] = POISON;
    }

    public int hauteur() { return hauteur;}

    public int largeur() { return largeur;}

    public int contenu(int i, int j) { return T[i][j];}


    public boolean estJeuFini() {
        return T[0][0] == VIDE;
    }

    public boolean estCoupValide(int i, int j) {
        return i >= 0 && i < hauteur && j >= 0 && j < largeur && T[i][j] != VIDE && !estJeuFini();
    }

    public void joue(int ligne, int colonne) {
        int i = ligne;
        int j;
        while(i<hauteur){
            j = colonne;
            while(j<largeur){
                T[i][j]=VIDE;
                j++;
            }
            i++;
        }
    }
    public InterfaceNiveau clone() {
        Niveau clone = new Niveau(hauteur, largeur);
        clone.T = new int[hauteur][largeur];
        for (int i = 0; i < hauteur; i++)
            for(int j = 0; j < largeur; j++)
                clone.T[i][j] = T[i][j];
        return (InterfaceNiveau) clone;
    }
    public int hash() {
        return Arrays.deepHashCode(T);
    }
}