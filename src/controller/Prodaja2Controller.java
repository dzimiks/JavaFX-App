package controller;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Knjiga;
import view.MainView;
import view.SideView;

public class Prodaja2Controller implements EventHandler<ActionEvent> {

	SideView view;
	MainView mainView;
	
	public Prodaja2Controller(SideView view, MainView mainView) {
		this.view = view;
		this.mainView = mainView;
	}

	@Override
	public void handle(ActionEvent event) {
		for (Knjiga k : view.getLista().getItems()) {
			k.setStanjeNaLageru(k.getStanjeNaLageru() - 1);
		}
		
		ArrayList<Knjiga> zaBrisanje = new ArrayList<>();
		
		for (Knjiga k : mainView.getSveKnjige()) {
			if (k.getStanjeNaLageru() <= 0) {
				zaBrisanje.add(k);
			}
		}

		for(Knjiga k : zaBrisanje) {
			mainView.getSveKnjige().remove(k);
		}
		
		mainView.getTabela().refresh();
		view.close();
	}
}
