package Arbitre;

import Joueur.*;

public class Arbitre implements InterfaceArbitre {
    InterfaceNiveau niveau;
    int numJoueur;
    Joueur j1;
    Joueur j2;
    int typeIAj2;

    public Arbitre(InterfaceNiveau n){
        niveau = n;
        numJoueur = 1;
        j1 = new Humain();
        j2 = new Humain();
        typeIAj2 = 0;
    }

    int joueurCourant(){
        return numJoueur;
    }

    void joue(int ligne,int colonne){
        if(niveau.coupAutoriser(ligne,colonne)){
            joue(ligne,colonne);
            changeJoueur();
        }else{
            System.out.println("Coup non autorisé !");
        }
    }

    void changeJoueur(){
        if(numJoueur == 1) numJoueur = 2;
        else numJoueur = 1;
    }

    void changeIA(int typeIA){
        typeIAj2 = typeIA;
        switch (typeIAj2){
            case 0:
                j2 = new Humain();
            case 1:
                j2 = new IAAleatoire();
            case 2:
                j2 = new IAGagnantPerdant();
            case 3:
                j2 = new IAEtOu();
            default:
                System.out.println("Type IA non reconnue");
        }

    }

    int estTypeIA(){
        return typeIAj2;
    }
}
