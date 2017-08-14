package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Knjiga;
import view.MainView;

public class PretraziController implements EventHandler<ActionEvent> {

	MainView view;
	
	public PretraziController(MainView view) {
		this.view = view;
	}

	@Override
	public void handle(ActionEvent event) {
		String  naslov 		  = view.getTfNaslov().getText();
		String  autor  		  = view.getTfAutor().getText();
		String  godinaIzdanja = view.getTfGodinaIzdanja().getText();
		String  izdavac 	  = view.getCmbIzdavac().getValue();
		boolean tacanNaslov   = view.getCbNaslov().isSelected();
		boolean tacanAutor    = view.getCbAutor().isSelected();
		ObservableList<Knjiga> pretrazeni = FXCollections.observableArrayList();
		
		for (Knjiga k : view.getSveKnjige()) {
			if (naslov != null) {
				if (tacanNaslov) {
					if (!k.getNaslov().equals(naslov))
						continue;
				}
				else {
					if (!k.getNaslov().contains(naslov))
						continue;
				}
			}
			
			if (autor != null) {
				if (tacanAutor) {
					if (!k.getAutor().equals(autor))
						continue;
				}
				else {
					if (!k.getAutor().contains(autor))
						continue;
				}
			}
			
			if (godinaIzdanja != null) {
				String god = Integer.toString(k.getGodinaIzdanja());
				if (!god.contains(godinaIzdanja))
					continue;
			}
			
			if (izdavac != null) {
				if (!k.getIzdavac().contains(izdavac))
					continue;
			}
			
			pretrazeni.add(k);
		}
		
		view.getTabela().setItems(pretrazeni);
	}
}
