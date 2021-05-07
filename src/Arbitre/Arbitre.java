package Arbitre;

import Interface.InterfaceGraphique;
import Joueur.Humain;
import Joueur.IAAleatoire;
import Joueur.IAEtOu;
import Joueur.IAGagnantPerdant;
import Joueur.Joueur;

public class Arbitre implements InterfaceArbitre {
    InterfaceNiveau niveau;
    InterfaceGraphique ig;
    
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
    
    public void fixerInterfaceGraphique(InterfaceGraphique i) {
		ig = i;
	}

    int joueurCourant(){
        return numJoueur;
    }

    public void joue(int ligne,int colonne){
        if(niveau.coupAutoriser(ligne,colonne)){
            niveau.joue(ligne,colonne);
            changeJoueur();
        }else{
            System.out.println("Coup non autorisé !");
        }
        if(niveau.estJeuFini())
        	System.out.println("Le partie est fini : joueur " + (numJoueur%2 + 1) + " a gagner");
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
                j2 = new IAAleatoire(niveau);
            case 2:
                j2 = new IAGagnantPerdant(niveau);
            case 3:
                j2 = new IAEtOu(niveau);
            default:
                System.out.println("Type IA non reconnue");
        }

    }

    int estTypeIA(){
        return typeIAj2;
    }
    
    public void tictac() {
        ig.metAJour();
    }

}
