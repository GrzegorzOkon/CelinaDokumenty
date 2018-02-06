package widok;

import java.util.*;

import javafx.application.Application;
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
    private RadioButton opcjaNumerWłasny, opcjaNumerSystemowy, opcjaNumerEwidencyjny;
    private TextArea poleWyszukiwania, poleRaportuDlaHelpDesku, poleRaportuDlaAdministratora, poleDziennikaZdarzeń;
    private Button SprawdzWCentrali, SprawdzLokalnie, Wyczysc, Zamknij;
    private HBox kontenerprzyciskow;
	
    // =============================================================================
    
    private void prepareScene(Stage primaryStage) {   
    	
        kontenerZakladek = new TabPane();
        kontenerZakladek.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        zakladka0 = new Tab("Wyszukiwanie");
        zakladka1 = new Tab("Raport dla HelpDesku");
        zakladka2 = new Tab("Raport dla Administratora"); 
        zakladka3 = new Tab("Dziennik zdarzeń");
        
        kontenerZakladek.getTabs().addAll(zakladka0, zakladka1, zakladka2, zakladka3);
    	
        kontenerGlowny = new BorderPane();
        kontenerGlowny.setPadding(new Insets(15, 15, 15, 15));  //tworzy odstęp wokół konteneru
        
        kontenerOpcji = new VBox(5);  //przerwy między wierszami 5
        kontenerOpcji.setPadding(new Insets(0, 0, 10, 0)); 
        
        zakladka0.setContent(kontenerGlowny);
        
        etykieta0 = new Label("Sprawdź dla:");
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
        opcjaNumerWłasny = new RadioButton("numer własny (nr_akt)");
        opcjaNumerWłasny.setMnemonicParsing(false);  //włącza wyświetlanie '_' w RadioButonie
        opcjaNumerWłasny.setToggleGroup(grupaOpcji);
        opcjaNumerSystemowy = new RadioButton("numer systemowy (id_dok)");
        opcjaNumerSystemowy.setMnemonicParsing(false);
        opcjaNumerSystemowy.setToggleGroup(grupaOpcji);
        opcjaNumerSystemowy.setSelected(true);
        opcjaNumerEwidencyjny = new RadioButton("numer ewidencyjny (sym_dok)");
        opcjaNumerEwidencyjny.setMnemonicParsing(false);
        opcjaNumerEwidencyjny.setToggleGroup(grupaOpcji);
        
        kontenerOpcji.getChildren().add(etykieta0);
        kontenerOpcji.getChildren().add(opcjaNumerWłasny);
        kontenerOpcji.getChildren().add(etykieta1);
        kontenerOpcji.getChildren().add(opcjaNumerSystemowy);
        kontenerOpcji.getChildren().add(etykieta2);
        kontenerOpcji.getChildren().add(opcjaNumerEwidencyjny);
        kontenerOpcji.getChildren().add(etykieta3);
        
        kontenerGlowny.setTop(kontenerOpcji);
        
        poleWyszukiwania = new TextArea();
        kontenerGlowny.setCenter(poleWyszukiwania);
        
        konternerDolny = new BorderPane();
        konternerDolny.setPadding(new Insets(10, 0, 0, 0));  // tworzy odstęp nad kontenerem
        
        kontenerprzyciskow = new HBox(16);
        
        SprawdzWCentrali = new Button("Sprawdz w centrali");
        SprawdzLokalnie = new Button("Sprawdz lokalnie");
        Wyczysc = new Button("Wyczyść");
        Zamknij = new Button("Zamknij");
        
        kontenerprzyciskow.getChildren().add(SprawdzWCentrali);
        kontenerprzyciskow.getChildren().add(SprawdzLokalnie);
        kontenerprzyciskow.getChildren().add(Wyczysc);
        kontenerprzyciskow.getChildren().add(Zamknij);
        
		// Przypisanie działania do przycisku sprawdzenia w centrali
        SprawdzWCentrali.setOnAction((event) -> {		    
            if (opcjaNumerWłasny.isSelected() == true) {
            	kontroler.wyszukajWCentraliNrAkt(walidujDane());
            } else if (opcjaNumerSystemowy.isSelected() == true) {
            	kontroler.wyszukajWCentraliIdDok(walidujDane());
            } else {
            	kontroler.wyszukajWCentraliSymDok(walidujDane());
            }         	
		});
        
		// Przypisuje czyszczenie pól do przycisku Wyczyść
        Wyczysc.setOnAction((event) -> {		    
        	poleWyszukiwania.clear();
        	poleRaportuDlaHelpDesku.clear();
        	poleRaportuDlaAdministratora.clear();
        	poleDziennikaZdarzeń.clear();
		});
        
        konternerDolny.setRight(kontenerprzyciskow);
        kontenerGlowny.setBottom(konternerDolny);
        
        poleRaportuDlaHelpDesku = new TextArea();
        zakladka1.setContent(poleRaportuDlaHelpDesku);
        
        poleRaportuDlaAdministratora = new TextArea();
        zakladka2.setContent(poleRaportuDlaAdministratora);
        
        poleDziennikaZdarzeń = new TextArea();
        zakladka3.setContent(poleDziennikaZdarzeń);
        
        scena = new Scene(kontenerZakladek, 800, 600);
    }
    
    /**
     * Metoda ustawiająca referencje do obiektów modelu mvc (model - view - controller).
     * 
     * @param primaryStage
     *           Główne okno aplikacji.
     */ 
    private void ustawReferencje(Stage primaryStage)
    {
    	model = new Model(this);
    	kontroler = new Kontroler(this, model);    //inicjalizuje obiekt kontrolera przekazując m.in. obiekt samej siebie (this)   
    	
    }
    
    // =============================================================================
    
    @Override
    public void start(Stage primaryStage) {   
    	
        prepareScene(primaryStage);

        ustawReferencje(primaryStage);
        
        primaryStage.setTitle(kontroler.pobierzWersję());
        primaryStage.setScene(scena);
        primaryStage.show();
    }

    // =============================================================================

    public static void main(String[] args) { 
    	
          launch(args);
    }
    
    /**
     * Metoda spradzająca wprowadzone dane do formatki pod kątem duplikatów i usuwania spacji.
     * 
     * @return wiersze
     * 			 Lista posortowanych alfabetycznie numerów dokumentów.
     */   
	private TreeSet<String> walidujDane() {
		String[] nieobrobioneWiersze = poleWyszukiwania.getText().split("\n");
		TreeSet<String> uporzadkowaneWiersze = new TreeSet<>();

		for (String nieobrobionyWiersz : nieobrobioneWiersze) {
			uporzadkowaneWiersze.add(nieobrobionyWiersz.trim());	//usuwa spacje i nie dopisuje duplikatów
		}
		
		return uporzadkowaneWiersze;
	}
	
	public synchronized void wyświetlRaporty(List<String> raporty) {
		poleRaportuDlaHelpDesku.appendText(raporty.get(0));
		poleRaportuDlaAdministratora.appendText(raporty.get(1));
	}
	
	public void wyświetlKomunikatBłedu() { 
	    Alert alert = new Alert(Alert.AlertType.ERROR);
	    alert.setTitle("Komunikat o błędzie...");
	    alert.setHeaderText("Wystąpił nieoczekiwany błąd!");
	    alert.setContentText("Sprawdz log programu...");
	    alert.showAndWait();
	}
}
