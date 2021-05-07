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
        joueurCourant = 1;
        fixeIA(InterfaceArbitre.JOUEUR1,InterfaceArbitre.HUMAIN);
        fixeIA(InterfaceArbitre.JOUEUR2,InterfaceArbitre.HUMAIN);
    }
    
    public void fixerInterfaceGraphique(InterfaceGraphique i) {
		ig = i;
	}

    public int joueurCourant(){
        return joueurCourant;
    }

    public void joue(int ligne,int colonne){
        if(typeIA[joueurCourant-1] == 0 && niveau.coupAutoriser(ligne,colonne)){
            niveau.joue(ligne,colonne);
            changeJoueur();
        }else{
            System.out.println("Coup non autoris� !");
        }
    }

    private void changeJoueur(){
        if(joueurCourant == InterfaceArbitre.JOUEUR1) {
        	joueurCourant = InterfaceArbitre.JOUEUR2;
        }else{
        	joueurCourant = InterfaceArbitre.JOUEUR1;
        }
        joueIA();
    }
    
    private void joueIA() {
    	if(typeIA[joueurCourant-1] != InterfaceArbitre.HUMAIN && !niveau.estJeuFini()) {
	    	Point p = joueurs[joueurCourant-1].determineCoup();
	    	niveau.joue(p.x,p.y);
	        changeJoueur();
    	}
    }

    private void fixeIA(int joueur, int typeia){
    	typeIA[joueur-1] = typeia;
        switch (typeia){
            case 0:
                joueurs[joueur-1] = new Humain();
                break;
            case 1:
            	joueurs[joueur-1] = new IAAleatoire(niveau);
                break;
            case 2:
            	joueurs[joueur-1] = new IAGagnantPerdant(niveau);
                break;
            case 3:
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
    	joueurCourant = 1;
        fixeIA(InterfaceArbitre.JOUEUR1,typeIA[0]);
        fixeIA(InterfaceArbitre.JOUEUR2,typeIA[1]);
        joueIA();
    }
    
    public void commande(String commande) {
    	switch (commande) {
    		case "NouvellePartie" :
    			nouveauNiveau();
    			break;
    		case "moins1" :
    			modifieIA(1,false);
    			break;
    		case "plus1" :
    			modifieIA(1,true);
    			break;
    		case "moins2" :
    			modifieIA(2,false);
    			break;
    		case "plus2" :
    			modifieIA(2,true);
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
    
    private void modifieIA(int j,boolean b) {
    	if(b) {
    		if(typeIA[j-1] != InterfaceArbitre.DIFFICILE) {
    			fixeIA(j,typeIA[j-1] + 1);
    			joueIA();
    		}
    	}
    	else {
    		if(typeIA[j-1] != InterfaceArbitre.HUMAIN) {
    			fixeIA(j,typeIA[j-1] - 1);
    			joueIA();
    		}
    	}
    	
    }
    
    public String etatIA(int j) {
    	if(typeIA[j-1] == InterfaceArbitre.HUMAIN) {
    		return "Humain";
    	}
    	else if(typeIA[j-1] == InterfaceArbitre.FACILE) {
    		return "Facile";
    	}
    	else if(typeIA[j-1] == InterfaceArbitre.MOYEN) {
    		return "Moyen";
    	}
    	else{
    		return "Difficile";
    	}
    }
    
    public String etatJoueur() {
    	if(niveau.estJeuFini()) {
    		return ("Joueur "+ joueurCourant  + " a gagn�!");
    	}
    	else {
    		return ("Au tour de : Joueur " + joueurCourant);
    	}
    }
    
    public int nouvelleHauteur() {
    	return nouvH;
    }
	
	public int nouvelleLargeur() {
		return nouvL;
	}
}