package Interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Arbitre.InterfaceArbitre;

public class AdaptateurTemps implements ActionListener {
	private InterfaceArbitre arbitre;
	
	public AdaptateurTemps(InterfaceArbitre a) {
		arbitre = a;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		arbitre.tictac();
	}
}
