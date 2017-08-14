package view;

import controller.ObracunController;
import controller.OdustaniController;
import controller.Prodaja2Controller;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Knjiga;

public class SideView extends Stage {

	MainView view;
	Knjiga knjiga;
	
	BorderPane root    = new BorderPane();
	GridPane   centar  = new GridPane();
	HBox       hb      = new HBox(10);
	ListView<Knjiga> lista = new ListView<>();
	
	Label 	  lblCenaBezPopusta = new Label("Ukupna cena bez popusta:"); 
	TextField tfCenaBezPopusta  = new TextField();
	Label 	  lblCenaSaPopustom = new Label("Ukupna sa popustom izdavaca:");
	TextField tfCenaSaPopustom  = new TextField();
	Label     lblDodatniPopust  = new Label("Dodatni popust:");
	TextField tfDodatniPopust 	= new TextField();
	
	Button btnObracun  = new Button("Obracun");
	Label  lblZaUplatu = new Label("Ukupno za uplatu:");
	
	Button btnProdaja  = new Button("Prodaja");
	Button btnOdustani = new Button("Odustani");
	
	public SideView(MainView view, Knjiga knjiga) {
		this.view = view;
		this.knjiga = knjiga;
		
		lista.getItems().addAll(view.getTabela().getSelectionModel().getSelectedItems());
		
		centar.setVgap(10);
		centar.setHgap(10);
		centar.setPadding(new Insets(10));
		centar.setAlignment(Pos.CENTER);
		
		tfCenaBezPopusta.setEditable(false);
		tfCenaSaPopustom.setEditable(false);
		
		float ukupnaCena = 0;
		for (Knjiga k : view.getTabela().getSelectionModel().getSelectedItems()) {
			ukupnaCena += k.getCena();
		}
		
		int popust = view.getTabela().getSelectionModel().getSelectedItem().getPopust();
		tfCenaBezPopusta.textProperty().set(Float.toString(ukupnaCena));
		tfCenaSaPopustom.textProperty().set(Float.toString(ukupnaCena*(1 - (float)popust/100)));
		lblZaUplatu.setText("Ukupno za uplatu: " + Float.toString(ukupnaCena*(1 - (float)popust/100)));
		
		btnObracun.setOnAction(new ObracunController(this));
		
		centar.add(lblCenaBezPopusta, 0, 0);
		centar.add(tfCenaBezPopusta,  1, 0);
		centar.add(lblCenaSaPopustom, 0, 1);
		centar.add(tfCenaSaPopustom,  1, 1);
		centar.add(lblDodatniPopust,  0, 2);
		centar.add(tfDodatniPopust,   1, 2);
		centar.add(btnObracun,        0, 3);
		centar.add(lblZaUplatu,       1, 3);
		
		btnProdaja.setOnAction(new Prodaja2Controller(this, view));
		btnOdustani.setOnAction(new OdustaniController(this));
		
		hb.setAlignment(Pos.CENTER);
		hb.getChildren().addAll(btnProdaja, btnOdustani);
		
		root.setPadding(new Insets(10));
		
		root.setTop(lista);
		root.setCenter(centar);
		root.setBottom(hb);

		setMinHeight(400);
		setMinWidth(500);
		Scene scene = new Scene(root);
		setScene(scene);
	}

	public GridPane getCentar() {
		return centar;
	}

	public void setTop(GridPane centar) {
		this.centar = centar;
	}

	public MainView getView() {
		return view;
	}

	public void setView(MainView view) {
		this.view = view;
	}

	public Knjiga getKnjiga() {
		return knjiga;
	}

	public void setKnjiga(Knjiga knjiga) {
		this.knjiga = knjiga;
	}

	public BorderPane getRoot() {
		return root;
	}

	public void setRoot(BorderPane root) {
		this.root = root;
	}

	public ListView<Knjiga> getLista() {
		return lista;
	}

	public void setLista(ListView<Knjiga> lista) {
		this.lista = lista;
	}

	public Label getLblCenaBezPopusta() {
		return lblCenaBezPopusta;
	}

	public void setLblCenaBezPopusta(Label lblCenaBezPopusta) {
		this.lblCenaBezPopusta = lblCenaBezPopusta;
	}

	public TextField getTfCenaBezPopusta() {
		return tfCenaBezPopusta;
	}

	public void setTfCenaBezPopusta(TextField tfCenaBezPopusta) {
		this.tfCenaBezPopusta = tfCenaBezPopusta;
	}

	public Label getLblCenaSaPopustom() {
		return lblCenaSaPopustom;
	}

	public void setLblCenaSaPopustom(Label lblCenaSaPopustom) {
		this.lblCenaSaPopustom = lblCenaSaPopustom;
	}

	public TextField getTfCenaSaPopustom() {
		return tfCenaSaPopustom;
	}

	public void setTfCenaSaPopustom(TextField tfCenaSaPopustom) {
		this.tfCenaSaPopustom = tfCenaSaPopustom;
	}

	public Label getLblDodatniPopust() {
		return lblDodatniPopust;
	}

	public void setLblDodatniPopust(Label lblDodatniPopust) {
		this.lblDodatniPopust = lblDodatniPopust;
	}

	public TextField getTfDodatniPopust() {
		return tfDodatniPopust;
	}

	public void setTfDodatniPopust(TextField tfDodatniPopust) {
		this.tfDodatniPopust = tfDodatniPopust;
	}

	public Button getBtnObracun() {
		return btnObracun;
	}

	public void setBtnObracun(Button btnObracun) {
		this.btnObracun = btnObracun;
	}

	public Label getLblZaUplatu() {
		return lblZaUplatu;
	}

	public void setLblZaUplatu(Label lblZaUplatu) {
		this.lblZaUplatu = lblZaUplatu;
	}

	public Button getBtnProdaja() {
		return btnProdaja;
	}

	public void setBtnProdaja(Button btnProdaja) {
		this.btnProdaja = btnProdaja;
	}

	public Button getBtnOdustani() {
		return btnOdustani;
	}

	public void setBtnOdustani(Button btnOdustani) {
		this.btnOdustani = btnOdustani;
	}
}
