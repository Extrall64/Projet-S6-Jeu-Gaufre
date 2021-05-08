package Arbitre;

import Interface.InterfaceGraphique;
import Joueur.Humain;
import Joueur.IAAleatoire;
import Joueur.IAEtOu;
import Joueur.IAGagnantPerdant;
import Joueur.Joueur;
import Patterns.Point;


public class Arbitre implements InterfaceArbitre {
	int nouvL,nouvH;
    InterfaceNiveau niveau;
    InterfaceGraphique ig;
    
    int joueurCourant;
    
    Joueur[] joueurs;
    int[] typeIA;

    public Arbitre(InterfaceNiveau n){
    	typeIA = new int[2];
    	joueurs = new Joueur[2];
        niveau = n;
        nouvL = niveau.largeur();
        nouvH = niveau.hauteur();
        joueurCourant = JOUEUR1;
        fixeIA(JOUEUR1,HUMAIN);
        fixeIA(JOUEUR2,HUMAIN);
    }
    
    public void fixerInterfaceGraphique(InterfaceGraphique i) {
		ig = i;
	}

    public int joueurCourant(){
        return joueurCourant;
    }

    public void joueHumain(int ligne, int colonne){
        if(typeIA[joueurCourant-1] == HUMAIN && niveau.estCoupValide(ligne,colonne)){
            niveau.joue(ligne,colonne);
            changeJoueur();
        }else{
            System.out.println("Coup non autoris� !");
        }
    }

    private void changeJoueur(){
        if(joueurCourant == JOUEUR1) {
        	joueurCourant = JOUEUR2;
        }else{
        	joueurCourant = JOUEUR1;
        }
        joueIA();
    }
    
    private void joueIA() {
    	if(typeIA[joueurCourant-1] != HUMAIN && !niveau.estJeuFini()) {
	    	Point p = joueurs[joueurCourant-1].determineCoup();
	    	niveau.joue(p.x,p.y);
	        changeJoueur();

    	}
    }

    private void fixeIA(int joueur, int typeia){
    	typeIA[joueur-1] = typeia;
        switch (typeia){
			case HUMAIN:
                joueurs[joueur-1] = new Humain();
                break;
			case FACILE:
            	joueurs[joueur-1] = new IAAleatoire(niveau);
                break;
			case MOYEN:
            	joueurs[joueur-1] = new IAGagnantPerdant(niveau);
                break;
			case DIFFICILE:
            	joueurs[joueur-1] = new IAEtOu(niveau);
                break;
            default:
                System.out.println("Type IA non reconnue");
        }
    }
    
    public void tictac() {
        ig.metAJour();
    }
    
    public void surligne(int l, int c) {
    	ig.setSurligne(new Point(l,c));
    	ig.metAJour();
    }
    
    private void nouveauNiveau() {
    	niveau.initialiser(nouvH,nouvL);
    	joueurCourant = JOUEUR1;
        fixeIA(JOUEUR1,typeIA[JOUEUR1-1]);
        fixeIA(JOUEUR2,typeIA[JOUEUR2-1]);
        joueIA();
    }
    
    public void commande(String commande) {
    	switch (commande) {
    		case "NouvellePartie" :
    			nouveauNiveau();
    			break;
    		case "moins1" :
    			modifieIA(JOUEUR1,false);
    			break;
    		case "plus1" :
    			modifieIA(JOUEUR1,true);
    			break;
    		case "moins2" :
    			modifieIA(JOUEUR2,false);
    			break;
    		case "plus2" :
    			modifieIA(JOUEUR2,true);
    			break;
    		case "addH":
    			nouvH++;
    			break;
    		case "addL":
    			nouvL++;
    			break;
    		case "subH":
    			if(nouvH>2) {nouvH--;}
    			break;
    		case "subL":
    			if(nouvL>2) {nouvL--;}
    			break;
    		default :
    			
    	}
    }
    
    private void modifieIA(int joueur,boolean plus) {
    	if(plus) {
    		if(typeIA[joueur-1] != DIFFICILE) {
    			fixeIA(joueur,typeIA[joueur-1] + 1);
    			joueIA();
    		}
    	}
    	else {
    		if(typeIA[joueur-1] != HUMAIN) {
    			fixeIA(joueur,typeIA[joueur-1] - 1);
    			joueIA();
    		}
    	}
    }
    
    public String etatIA(int joueur) {
    	if(typeIA[joueur-1] == HUMAIN)
    		return "Humain";
    	if(typeIA[joueur-1] == FACILE)
    		return "Facile";
    	if(typeIA[joueur-1] == MOYEN)
    		return "Moyen";
    	return "Difficile";
    }
    
    public String etatJoueur() {
    	if(niveau.estJeuFini())
    		return ("Joueur "+ joueurCourant  + " a gagn�!");
    	return ("Au tour de : Joueur " + joueurCourant);
    }
    
    public int nouvelleHauteur() {
    	return nouvH;
    }
	
	public int nouvelleLargeur() {
		return nouvL;
	}
}