package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Modality;
import model.Knjiga;
import view.MainView;
import view.SideView;

public class ProdajaController implements EventHandler<ActionEvent> {

	MainView view;
	
	public ProdajaController(MainView view) {
		this.view = view;
	}

	@Override
	public void handle(ActionEvent event) {
		Knjiga knjiga = view.getTabela().getSelectionModel().getSelectedItem();
		
		if (knjiga != null) {
			SideView sv = new SideView(view, knjiga);
			sv.initModality(Modality.APPLICATION_MODAL);
			sv.show();
		}
	}
}
