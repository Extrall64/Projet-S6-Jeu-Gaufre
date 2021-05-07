package Arbitre;

import Interface.InterfaceGraphique;

public interface InterfaceArbitre {
	public static final int HUMAIN = 0;
	public static final int FACILE = 1;
	public static final int MOYEN = 2;
	public static final int DIFFICILE = 3;
	
	public static final int JOUEUR1 = 1;
	public static final int JOUEUR2 = 2;
	
	void joue(int l, int c);

	void tictac();

	void fixerInterfaceGraphique(InterfaceGraphique interfaceGraphique);

	int joueurCourant();
	
	public void commande(String commande);
	
	public void surligne(int l, int c);
	
	public String etatJoueur();
	
	public String etatIA(int j);

}
