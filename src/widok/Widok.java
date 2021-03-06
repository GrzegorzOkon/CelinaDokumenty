package widok;

import java.util.*;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

import kontroler.Kontroler;
import procesor.Model;
import procesor.raporty.wejscie.Raport;
import procesor.zdarzenia.RejestratorZdarze�;

public class Widok extends Application {
	
	// Klasy wzorca projektowego mvc
	private Model model;
	private Kontroler kontroler;  
	
    private Scene scena; // scena
    private TabPane kontenerZakladek; 
    private Tab zakladka0, zakladka1, zakladka2, zakladka3;
    private BorderPane kontenerGlowny, konternerDolny;
    private VBox kontenerOpcji;
    private Label etykieta0, etykieta1, etykieta2, etykieta3;
    private Font czcionkaPochylona;
    private ToggleGroup grupaOpcji;
    private RadioButton opcjaNumerW�asny, opcjaNumerSystemowy, opcjaNumerEwidencyjny;
    private TextArea poleWyszukiwania, poleRaportuDlaHelpDesku, poleRaportuDlaAdministratora, poleDziennikaZdarze�;
    private Button SprawdzWCentrali, SprawdzLokalnie, Wyczysc, Zamknij;
    private HBox kontenerprzyciskow;
    
    // =============================================================================
    
    private void prepareScene(Stage primaryStage) {   
    	
        kontenerZakladek = new TabPane();
        kontenerZakladek.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        zakladka0 = new Tab("Wyszukiwanie");
        zakladka1 = new Tab("Raport dla HelpDesku");
        zakladka2 = new Tab("Raport dla Administratora"); 
        zakladka3 = new Tab("Dziennik zdarze�");
        
        kontenerZakladek.getTabs().addAll(zakladka0, zakladka1, zakladka2, zakladka3);
    	
        kontenerGlowny = new BorderPane();
        kontenerGlowny.setPadding(new Insets(15, 15, 15, 15));  //tworzy odst�p wok� konteneru
        
        kontenerOpcji = new VBox(5);  //przerwy mi�dzy wierszami 5
        kontenerOpcji.setPadding(new Insets(0, 0, 10, 0)); 
        
        zakladka0.setContent(kontenerGlowny);
        
        etykieta0 = new Label("Sprawd� dla:");
        etykieta1 = new Label("np. '01PWS12345678'");
        etykieta2 = new Label("np. 'SAD2/D/2017/00/12345678'");
        etykieta3 = new Label("np. 'OGL/123456/00/123456/2017'");
        
        czcionkaPochylona = Font.font("Courier", FontPosture.ITALIC, 10);  //kursywa
        etykieta1.setFont(czcionkaPochylona);
        etykieta1.setPadding(new Insets(0, 0, 0, 30));
        etykieta2.setFont(czcionkaPochylona);
        etykieta2.setPadding(new Insets(0, 0, 0, 30));
        etykieta3.setFont(czcionkaPochylona);
        etykieta3.setPadding(new Insets(0, 0, 0, 30));
        
        grupaOpcji = new ToggleGroup();
        opcjaNumerW�asny = new RadioButton("numer w�asny (nr_akt)");
        opcjaNumerW�asny.setMnemonicParsing(false);  //w��cza wy�wietlanie '_' w RadioButonie
        opcjaNumerW�asny.setToggleGroup(grupaOpcji);
        opcjaNumerSystemowy = new RadioButton("numer systemowy (id_dok)");
        opcjaNumerSystemowy.setMnemonicParsing(false);
        opcjaNumerSystemowy.setToggleGroup(grupaOpcji);
        opcjaNumerSystemowy.setSelected(true);
        opcjaNumerEwidencyjny = new RadioButton("numer ewidencyjny (sym_dok)");
        opcjaNumerEwidencyjny.setMnemonicParsing(false);
        opcjaNumerEwidencyjny.setToggleGroup(grupaOpcji);
        
        kontenerOpcji.getChildren().add(etykieta0);
        kontenerOpcji.getChildren().add(opcjaNumerW�asny);
        kontenerOpcji.getChildren().add(etykieta1);
        kontenerOpcji.getChildren().add(opcjaNumerSystemowy);
        kontenerOpcji.getChildren().add(etykieta2);
        kontenerOpcji.getChildren().add(opcjaNumerEwidencyjny);
        kontenerOpcji.getChildren().add(etykieta3);
        
        kontenerGlowny.setTop(kontenerOpcji);
        
        poleWyszukiwania = new TextArea();
        kontenerGlowny.setCenter(poleWyszukiwania);
        
        konternerDolny = new BorderPane();
        konternerDolny.setPadding(new Insets(10, 0, 0, 0));  // tworzy odst�p nad kontenerem
        
        kontenerprzyciskow = new HBox(16);
        
        SprawdzWCentrali = new Button("Sprawd� w centrali");
        SprawdzLokalnie = new Button("Sprawd� lokalnie");
        Wyczysc = new Button("Wyczy��");
        Zamknij = new Button("Zamknij");
        
        kontenerprzyciskow.getChildren().add(SprawdzWCentrali);
        kontenerprzyciskow.getChildren().add(SprawdzLokalnie);
        kontenerprzyciskow.getChildren().add(Wyczysc);
        kontenerprzyciskow.getChildren().add(Zamknij);
        
		// Przypisanie dzia�ania do przycisku sprawdzenia w centrali
        SprawdzWCentrali.setOnAction((event) -> {		    
            if (opcjaNumerW�asny.isSelected() == true) {
            	kontroler.wyszukajWCentraliPoNumerachAkt(walidujDane());
            } else if (opcjaNumerSystemowy.isSelected() == true) {
            	kontroler.wyszukajWCentraliPoIdentyfikatorachDokument�w(walidujDane());
            } else {
            	kontroler.wyszukajWCentraliPoSymbolachDokument�w(walidujDane());
            }         	
		});
        
		// Przypisanie dzia�ania do przycisku sprawdzenia we wszystkich izbach
        SprawdzLokalnie.setOnAction((event) -> {		    
            if (opcjaNumerW�asny.isSelected() == true) {
            	kontroler.wyszukajLokalniePoNumerachAkt(walidujDane());
            } else if (opcjaNumerSystemowy.isSelected() == true) {
            	kontroler.wyszukajLokalniePoIdentyfikatorachDokument�w(walidujDane());
            } else {	
            	kontroler.wyszukajLokalniePoSymbolachDokument�w(walidujDane());
            }         	
		});
        
		// Przypisuje czyszczenie p�l do przycisku Wyczy��
        Wyczysc.setOnAction((event) -> {
        	
        	poleWyszukiwania.clear();
        	poleRaportuDlaHelpDesku.clear();
        	poleRaportuDlaAdministratora.clear();
		});
        
        Zamknij.setOnAction((event) -> {
        	
        	primaryStage.close();
		});
        
        konternerDolny.setRight(kontenerprzyciskow);
        kontenerGlowny.setBottom(konternerDolny);
        
        poleRaportuDlaHelpDesku = new TextArea();
        poleRaportuDlaHelpDesku.setEditable(false);
        poleRaportuDlaHelpDesku.setWrapText(true);
        zakladka1.setContent(poleRaportuDlaHelpDesku);
        
        poleRaportuDlaAdministratora = new TextArea();
        poleRaportuDlaAdministratora.setEditable(false);
        poleRaportuDlaAdministratora.setWrapText(true);
        zakladka2.setContent(poleRaportuDlaAdministratora);
        
        poleDziennikaZdarze� = new TextArea();
        zakladka3.setContent(poleDziennikaZdarze�);
        
        scena = new Scene(kontenerZakladek, 600, 600);
    }
    
    /**
     * Metoda ustawiaj�ca referencje do obiekt�w modelu mvc (model - view - controller).
     * 
     * @param primaryStage
     *           G��wne okno aplikacji.
     */ 
    private void ustawReferencje(Stage primaryStage)
    {
    	model = new Model(this);
    	kontroler = new Kontroler(this, model);    //inicjalizuje obiekt kontrolera przekazuj�c m.in. obiekt samej siebie (this)   
    }
    
    // =============================================================================
    
    @Override
    public void start(Stage primaryStage) {   
    	
        prepareScene(primaryStage);

        ustawReferencje(primaryStage);
        
        primaryStage.setTitle(kontroler.pobierzOpis());
        primaryStage.setScene(scena);
        primaryStage.show();
        
		kontroler.por�wnajWersje();
    }

    // =============================================================================

    public static void main(String[] args) { 
    	
          launch(args);
    }
    
    /**
     * Metoda spradzaj�ca wprowadzone dane do formatki pod k�tem duplikat�w i zb�dnych danych.
     * 
     * @return uporzadkowaneWiersze - lista posortowanych alfabetycznie numer�w dokument�w bez duplikat�w
     */   
	private TreeSet<String> walidujDane() {

		TreeSet<String> uporzadkowaneWiersze = new TreeSet<>();
		String[] nieobrobioneWiersze = poleWyszukiwania.getText().split("\n");
		int licznik = 0;
		
		for (String nieobrobionyWiersz : nieobrobioneWiersze) {
			//usuwa zb�dne znaki, spacje i nie dopisuje duplikat�w
			uporzadkowaneWiersze.add(nieobrobionyWiersz.replaceAll(",|;|\\.|'|\"", "").trim());	
		}
		
		for (String wiersz : uporzadkowaneWiersze) {
			
			RejestratorZdarze�.pobierzInstancj�().debug("Dokument " + ++licznik + ": " + wiersz);
		}
		
		return uporzadkowaneWiersze;
	}
	
	public synchronized void wy�wietlRaporty(List<Raport> raporty) {	
		for(Raport raport : raporty) {
			Platform.runLater(() -> poleRaportuDlaHelpDesku.appendText(raport.getRaportDlaHelpDesku() + "\n\n"));
			Platform.runLater(() ->	poleRaportuDlaAdministratora.appendText(raport.getRaportDlaAdministratora()));
		}
	}
	
	public synchronized void wy�wietlZdarzenie(String zdarzenie) {	
		poleDziennikaZdarze�.appendText(zdarzenie);
	}
}
