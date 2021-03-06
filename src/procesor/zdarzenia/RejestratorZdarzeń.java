package procesor.zdarzenia;

import java.text.SimpleDateFormat;
import java.util.Date;

import procesor.Model;

public class RejestratorZdarzeń {

	private static volatile RejestratorZdarzeń instancja;
	
	private Model model;
	
	private RejestratorZdarzeń() {}
	
	private RejestratorZdarzeń(Model model) {
		
		this.model = model;
	}
	
	public static RejestratorZdarzeń pobierzInstancję() {
		
		if (instancja == null) {
			
			synchronized(RejestratorZdarzeń.class) {
				
				if(instancja == null) {
					
					instancja = new RejestratorZdarzeń();
				}
			}
		} 	
		
		return instancja;
	}	
	
	public static void przekażReferencję(Model model) {
		
		if (instancja == null) {
			
			synchronized(RejestratorZdarzeń.class) {
				
				if(instancja == null) {
					
					instancja = new RejestratorZdarzeń(model);
				}
			}
		} 	
	}
	
	/** Metoda loguje w dzienniku zdarzeń wpisy z flagą error
	 * 
     * @param text Opis zdarzenia
     * 
     * @param logArea pole tekstowe dla logowania zdarzeń aplikacji
     * 
     * @since 2.0
     */
	public void error(String tekst) {

		zapiszDoDziennika(tekst, -1);
	}
	
	/** Metoda loguje w dzienniku zdarzeń wpisy z flagą info
	 * 
     * @param tekst Opis zdarzenia
     * 
     * @param logArea pole tekstowe dla logowania zdarzeń aplikacji
     * 
     * @since 2.0
     */
	public void info(String tekst){

		zapiszDoDziennika(tekst, 0);
	}
	
	/** Metoda loguje w dzienniku zdarzeń wpisy z flagą debug
	 * 
     * @param tekst Opis zdarzenia
     * 
     * @param logArea pole tekstowe dla logowania zdarzeń aplikacji
     * 
     * @since 2.0
     */
	public void debug(String tekst){

		zapiszDoDziennika(tekst, 3);
	}
	
	  /** Metoda loguje w dzienniku zdarzeń wpisy z flagą w zależności od parametru wejściowego flaga

     * @param text Opis zdarzenia

     * @param flaga priorytet zdarzenia, -1 (error), 0 (info), 1 (trace), 2 (warn), 3 (debug).

     * @param logArea pole tekstowe dla logowania zdarzeń aplikacji

     * @since 2.0

	   */
	private void zapiszDoDziennika(String tekst, int flaga) {
		
        if (flaga == -1) {

        	model.zapiszDoDziennikaZdarzeń(pobierzAktualnyZnacznikCzasu() + "   ERROR  " + tekst + System.getProperty("line.separator"));
        }

        if (flaga == 0){

        	model.zapiszDoDziennikaZdarzeń(pobierzAktualnyZnacznikCzasu() + "   INFO  " + tekst + System.getProperty("line.separator"));
        }

		if (flaga == 1){

            //logArea.append(getCurrentTimeStamp() + "   TRACE " + text + System.getProperty("line.separator"));
		}
        
        if (flaga == 2){

                //logArea.append(getCurrentTimeStamp() + "   WARN  " + text + System.getProperty("line.separator"));
        }

        if (flaga == 3){

        	model.zapiszDoDziennikaZdarzeń(pobierzAktualnyZnacznikCzasu() + "   DEBUG  " + tekst + System.getProperty("line.separator"));
        }

        //logArea.setCaretPosition(logArea.getDocument().getLength());
   }

	private String pobierzAktualnyZnacznikCzasu() {
		
        SimpleDateFormat spersonalizowanyFormatCzasu = new SimpleDateFormat("yyyy/MM/dd, HH:mm:ss");
        Date bieżącyCzas = new Date();
        String spersonalizowanyCzas = spersonalizowanyFormatCzasu.format(bieżącyCzas);

        return spersonalizowanyCzas;
    } 
}
