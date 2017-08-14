package view;

import java.io.File;
import java.util.Scanner;

import controller.PretraziController;
import controller.ProdajaController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Knjiga;

public class MainView extends BorderPane {

	Label 	  lblNaslov = new Label("Naslov:");
	TextField tfNaslov  = new TextField();
	Label 	  lblAutor  = new Label("Autor:");
	TextField tfAutor   = new TextField();
	Label 	  lblGodinaIzdanja = new Label("Godina izdanja:");
	TextField tfGodinaIzdanja  = new TextField();
	Label 	  lblIzdavac = new Label("Izdavac:");
	ComboBox<String> cmbIzdavac = new ComboBox<>();
	Button    btnPretrazi = new Button("Pretrazi");
	CheckBox  cbNaslov    = new CheckBox("Tacan naslov");
	CheckBox  cbAutor     = new CheckBox("Tacan autor");
	short     popust;
	
	TableView<Knjiga> tabela = new TableView<>();
	
	ObservableList<Knjiga> sveKnjige = FXCollections.observableArrayList();
	ObservableList<String> izdavaci  = FXCollections.observableArrayList();
	
	Button btnProdaja = new Button("Prodaja");
	Button btnSacuvaj = new Button("Sacuvaj novo stanje");

	public MainView() {
		formirajGornjiDeo();
		formirajSredinu();
		formirajDonjiDeo();
	}

	private void ucitajTabelu() {
		try {
			Scanner sc = new Scanner(new File("knjizara.txt"));
			
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] parts = line.split(";");
				String naslov = parts[0];
				String autor = parts[1];
				int godinaIzdanja = Integer.parseInt(parts[2]);
				String izdavac = parts[3];
				float cena = Float.parseFloat(parts[4]);
				int stanje = Integer.parseInt(parts[5]);
				
				Knjiga knjiga = new Knjiga(autor, naslov, izdavac, godinaIzdanja, cena, stanje, popust);
				sveKnjige.add(knjiga);
			}
			
			sc.close();
		}
		catch (Exception ex) {
			Alert a = new Alert(AlertType.ERROR, ex.getMessage(), ButtonType.OK);
			a.show();
		}
	}
	
	private void ucitajIzdavace() {
		try {
			Scanner sc = new Scanner(new File("izdavaci.txt"));
			
			while (sc.hasNextLine()) {
				String   line    = sc.nextLine();
				String[] parts   = line.split(",");
				String   izdavac = parts[0];
				short    popust  = Short.parseShort(parts[1]);
				
				this.popust = popust;
				izdavaci.addAll(izdavac);
				cmbIzdavac.setItems(izdavaci);
			}
			
			cmbIzdavac.setValue("CET");
			sc.close();
		}
		catch (Exception ex) {
			Alert a = new Alert(AlertType.ERROR, ex.getMessage(), ButtonType.OK);
			a.show();
		}
	}
	
	private void formirajGornjiDeo() {
		ucitajIzdavace();
		
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		gp.setPadding(new Insets(10));
		gp.setHgap(10);
		gp.setVgap(10);
		
		btnPretrazi.setOnAction(new PretraziController(this));
		
		gp.add(lblNaslov, 0, 0);
		gp.add(tfNaslov, 1, 0);
		gp.add(cbNaslov, 2, 0);
		gp.add(lblAutor, 0, 1);
		gp.add(tfAutor, 1, 1);
		gp.add(cbAutor, 2, 1);
		gp.add(lblGodinaIzdanja, 0, 2);
		gp.add(tfGodinaIzdanja, 1, 2);
		gp.add(lblIzdavac, 0, 3);
		gp.add(cmbIzdavac, 1, 3);
		gp.add(btnPretrazi, 0, 4);
		
		setTop(gp);
	}

	private void formirajSredinu() {
		ucitajTabelu();
		
		TableColumn<Knjiga, String> tcNaslov = new TableColumn<>("Naslov");
		tcNaslov.setCellValueFactory(new PropertyValueFactory<>("naslov"));
		
		TableColumn<Knjiga, String> tcAutor = new TableColumn<>("Autor");
		tcAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
		
		TableColumn<Knjiga, Integer> tcGodinaIzdanja = new TableColumn<>("Godina izdanja");
		tcGodinaIzdanja.setCellValueFactory(new PropertyValueFactory<>("godinaIzdanja"));
		
		TableColumn<Knjiga, String> tcIzdavac = new TableColumn<>("Izdavac");
		tcIzdavac.setCellValueFactory(new PropertyValueFactory<>("izdavac"));
		
		TableColumn<Knjiga, Float> tcCena = new TableColumn<>("Cena");
		tcCena.setCellValueFactory(new PropertyValueFactory<>("cena"));
		
		TableColumn<Knjiga, Integer> tcStanje = new TableColumn<>("Stanje");
		tcStanje.setCellValueFactory(new PropertyValueFactory<>("stanjeNaLageru"));
		
		tabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tabela.getColumns().addAll(tcNaslov, tcAutor, tcGodinaIzdanja, tcIzdavac, tcCena, tcStanje);
		tabela.setItems(sveKnjige);
		
		setCenter(tabela);
	}
	
	private void formirajDonjiDeo() {
		HBox hb = new HBox(10);
		hb.setAlignment(Pos.CENTER);
		
		btnProdaja.setOnAction(new ProdajaController(this));
		
		hb.getChildren().addAll(btnProdaja, btnSacuvaj);
		setBottom(hb);
	}
	
	public short getPopust() {
		return popust;
	}

	public void setPopust(short popust) {
		this.popust = popust;
	}

	public ObservableList<String> getIzdavaci() {
		return izdavaci;
	}

	public void setIzdavaci(ObservableList<String> izdavaci) {
		this.izdavaci = izdavaci;
	}

	public Label getLblNaslov() {
		return lblNaslov;
	}
	
	public void setLblNaslov(Label lblNaslov) {
		this.lblNaslov = lblNaslov;
	}
	
	public TextField getTfNaslov() {
		return tfNaslov;
	}
	
	public void setTfNaslov(TextField tfNaslov) {
		this.tfNaslov = tfNaslov;
	}
	
	public Label getLblAutor() {
		return lblAutor;
	}
	
	public void setLblAutor(Label lblAutor) {
		this.lblAutor = lblAutor;
	}
	
	public TextField getTfAutor() {
		return tfAutor;
	}
	
	public void setTfAutor(TextField tfAutor) {
		this.tfAutor = tfAutor;
	}
	
	public Label getLblGodinaIzdanja() {
		return lblGodinaIzdanja;
	}
	
	public void setLblGodinaIzdanja(Label lblGodinaIzdanja) {
		this.lblGodinaIzdanja = lblGodinaIzdanja;
	}
	
	public TextField getTfGodinaIzdanja() {
		return tfGodinaIzdanja;
	}
	
	public void setTfGodinaIzdanja(TextField tfGodinaIzdanja) {
		this.tfGodinaIzdanja = tfGodinaIzdanja;
	}
	
	public Label getLblIzdavac() {
		return lblIzdavac;
	}
	
	public void setLblIzdavac(Label lblIzdavac) {
		this.lblIzdavac = lblIzdavac;
	}
	
	public ComboBox<String> getCmbIzdavac() {
		return cmbIzdavac;
	}

	public void setCmbIzdavac(ComboBox<String> cmbIzdavac) {
		this.cmbIzdavac = cmbIzdavac;
	}

	public Button getBtnPretrazi() {
		return btnPretrazi;
	}

	public void setBtnPretrazi(Button btnPretrazi) {
		this.btnPretrazi = btnPretrazi;
	}

	public CheckBox getCbNaslov() {
		return cbNaslov;
	}

	public void setCbNaslov(CheckBox cbNaslov) {
		this.cbNaslov = cbNaslov;
	}

	public CheckBox getCbAutor() {
		return cbAutor;
	}

	public void setCbAutor(CheckBox cbAutor) {
		this.cbAutor = cbAutor;
	}

	public TableView<Knjiga> getTabela() {
		return tabela;
	}

	public void setTabela(TableView<Knjiga> tabela) {
		this.tabela = tabela;
	}

	public ObservableList<Knjiga> getSveKnjige() {
		return sveKnjige;
	}

	public void setSveKnjige(ObservableList<Knjiga> sveKnjige) {
		this.sveKnjige = sveKnjige;
	}

	public Button getBtnProdaja() {
		return btnProdaja;
	}

	public void setBtnProdaja(Button btnProdaja) {
		this.btnProdaja = btnProdaja;
	}

	public Button getBtnSacuvaj() {
		return btnSacuvaj;
	}

	public void setBtnSacuvaj(Button btnSacuvaj) {
		this.btnSacuvaj = btnSacuvaj;
	}
}
