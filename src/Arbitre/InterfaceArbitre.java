package Arbitre;

import Interface.InterfaceGraphique;

public interface InterfaceArbitre {

	void joue(int l, int c);

	void tictac();

	void fixerInterfaceGraphique(InterfaceGraphique interfaceGraphique);

	int joueurCourant();
	
	public void commande(String commande);
	
	public void surligne(int l, int c);
	
	public String etatJoueur();
	
	public String etatIA();

}
