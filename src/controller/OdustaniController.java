package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.SideView;

public class OdustaniController implements EventHandler<ActionEvent> {

	SideView view;
	
	public OdustaniController(SideView view) {
		this.view = view;
	}
	
	@Override
	public void handle(ActionEvent event) {
		view.close();
	}
}
