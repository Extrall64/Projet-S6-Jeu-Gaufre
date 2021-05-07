package Interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Arbitre.InterfaceArbitre;

public class AdaptateurCommande implements ActionListener {
		InterfaceArbitre arbitre;
		String commande;

		AdaptateurCommande(InterfaceArbitre a, String com) {
			arbitre = a;
			commande = com;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			arbitre.commande(commande);
		}
}
