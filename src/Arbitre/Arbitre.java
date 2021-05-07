package Arbitre;

import java.awt.Point;

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
        changeIA(1);
    }
    
    public void fixerInterfaceGraphique(InterfaceGraphique i) {
		ig = i;
	}

    public int joueurCourant(){
        return numJoueur;
    }

    public void joue(int ligne,int colonne){
        if(niveau.coupAutoriser(ligne,colonne) && !(numJoueur == 2 && typeIAj2 > 0)){
            niveau.joue(ligne,colonne);
            tictac();
            changeJoueur();
            if(numJoueur == 2 && typeIAj2 > 0 && !niveau.estJeuFini()) {
            	Point p = j2.determineCoup();
            	System.out.println("IA a joue : en x : "+p.x + " en y: "+p.y);
            	niveau.joue(p.y,p.x);
            	tictac();
                changeJoueur();
            }
        }else{
            System.out.println("Coup non autorisé !");
        }
        if(niveau.estJeuFini())
        	System.out.println("Le partie est fini : joueur " + numJoueur + " a gagner");
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
                break;
            case 1:
                j2 = new IAAleatoire(niveau);
                break;
            case 2:
                j2 = new IAGagnantPerdant(niveau);
                break;
            case 3:
                j2 = new IAEtOu(niveau);
                break;
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
    
    public void surligne(int l, int c) {
    	ig.setSurligne(new Point(l,c));
    	ig.metAJour();
    }
    
    private void nouveauNiveau() {
    	niveau.initialiser();
        numJoueur = 1;
        j1 = new Humain();
        changeIA(typeIAj2);  	
    }
    
    public void commande(String commande) {
    	switch (commande) {
    		case "NouvellePartie" :
    			nouveauNiveau();
    			break;
    		default :
    			
    	}
    }

}
