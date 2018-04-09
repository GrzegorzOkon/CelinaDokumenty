package procesor.zdarzenia;

import java.text.SimpleDateFormat;
import java.util.Date;

import procesor.Model;

public class RejestratorZdarzeñ {

	private static volatile RejestratorZdarzeñ instancja;
	
	private Model model;
	
	private RejestratorZdarzeñ() {}
	
	private RejestratorZdarzeñ(Model model) {
		
		this.model = model;
	}
	
	public static RejestratorZdarzeñ pobierzInstancjê() {
		
		if (instancja == null) {
			
			synchronized(RejestratorZdarzeñ.class) {
				
				if(instancja == null) {
					
					instancja = new RejestratorZdarzeñ();
				}
			}
		} 	
		
		return instancja;
	}	
	
	public static void przeka¿Referencjê(Model model) {
		
		if (instancja == null) {
			
			synchronized(RejestratorZdarzeñ.class) {
				
				if(instancja == null) {
					
					instancja = new RejestratorZdarzeñ(model);
				}
			}
		} 	
	}
	
	/** Metoda loguje w dzienniku zdarzeñ wpisy z flag¹ error
	 * 
     * @param text Opis zdarzenia
     * 
     * @param logArea pole tekstowe dla logowania zdarzeñ aplikacji
     * 
     * @since 2.0
     */
	public void error(String tekst) {

		zapiszDoDziennika(tekst, -1);
	}
	
	/** Metoda loguje w dzienniku zdarzeñ wpisy z flag¹ info
	 * 
     * @param tekst Opis zdarzenia
     * 
     * @param logArea pole tekstowe dla logowania zdarzeñ aplikacji
     * 
     * @since 2.0
     */
	public void info(String tekst){

		zapiszDoDziennika(tekst, 0);
	}
	
	  /** Metoda loguje w dzienniku zdarzeñ wpisy z flag¹ w zale¿noœci od parametru wejœciowego flaga

     * @param text Opis zdarzenia

     * @param flaga priorytet zdarzenia, -1 (error), 0 (info), 1 (trace), 2 (warn), 3 (debug).

     * @param logArea pole tekstowe dla logowania zdarzeñ aplikacji

     * @since 2.0

	   */
	private void zapiszDoDziennika(String tekst, int flaga) {
		
        if (flaga == -1) {

        	model.zapiszDoDziennikaZdarzeñ(pobierzAktualnyZnacznikCzasu() + "   ERROR  " + tekst + System.getProperty("line.separator"));
        }

        if (flaga == 0){

        	model.zapiszDoDziennikaZdarzeñ(pobierzAktualnyZnacznikCzasu() + "   INFO  " + tekst + System.getProperty("line.separator"));
        }

		if (flaga == 1){

            //logArea.append(getCurrentTimeStamp() + "   TRACE " + text + System.getProperty("line.separator"));
		}
        
        if (flaga == 2){

                //logArea.append(getCurrentTimeStamp() + "   WARN  " + text + System.getProperty("line.separator"));
        }

        if (flaga == 3){

                //logArea.append(getCurrentTimeStamp() + "   DEBUG " + text + System.getProperty("line.separator"));
        }

        //logArea.setCaretPosition(logArea.getDocument().getLength());
   }

	private String pobierzAktualnyZnacznikCzasu() {
		
        SimpleDateFormat spersonalizowanyFormatCzasu = new SimpleDateFormat("yyyy/MM/dd, HH:mm:ss");
        Date bie¿¹cyCzas = new Date();
        String spersonalizowanyCzas = spersonalizowanyFormatCzasu.format(bie¿¹cyCzas);

        return spersonalizowanyCzas;
    } 
}
