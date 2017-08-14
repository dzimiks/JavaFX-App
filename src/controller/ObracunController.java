package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.SideView;

public class ObracunController implements EventHandler<ActionEvent> {

	SideView view;
	
	public ObracunController(SideView view) {
		this.view = view;
	}

	@Override
	public void handle(ActionEvent event) {
		float popust = Float.parseFloat(view.getTfCenaSaPopustom().getText());
		float novi   = Float.parseFloat(view.getTfDodatniPopust().getText());
		
		if (novi != 0) 
			view.getLblZaUplatu().setText("Ukupno za uplatu: " + Float.toString(popust*(1 - novi/100)));
	} 
}
