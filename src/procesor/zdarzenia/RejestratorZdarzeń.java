package procesor.zdarzenia;

import java.text.SimpleDateFormat;
import java.util.Date;

import procesor.Model;

public class RejestratorZdarze� {

	private static volatile RejestratorZdarze� instancja;
	
	private Model model;
	
	private RejestratorZdarze�() {}
	
	private RejestratorZdarze�(Model model) {
		
		this.model = model;
	}
	
	public static RejestratorZdarze� pobierzInstancj�() {
		
		if (instancja == null) {
			
			synchronized(RejestratorZdarze�.class) {
				
				if(instancja == null) {
					
					instancja = new RejestratorZdarze�();
				}
			}
		} 	
		
		return instancja;
	}	
	
	public static void przeka�Referencj�(Model model) {
		
		if (instancja == null) {
			
			synchronized(RejestratorZdarze�.class) {
				
				if(instancja == null) {
					
					instancja = new RejestratorZdarze�(model);
				}
			}
		} 	
	}
	
	/** Metoda loguje w dzienniku zdarze� wpisy z flag� error
	 * 
     * @param text Opis zdarzenia
     * 
     * @param logArea pole tekstowe dla logowania zdarze� aplikacji
     * 
     * @since 2.0
     */
	public void error(String tekst) {

		zapiszDoDziennika(tekst, -1);
	}
	
	/** Metoda loguje w dzienniku zdarze� wpisy z flag� info
	 * 
     * @param tekst Opis zdarzenia
     * 
     * @param logArea pole tekstowe dla logowania zdarze� aplikacji
     * 
     * @since 2.0
     */
	public void info(String tekst){

		zapiszDoDziennika(tekst, 0);
	}
	
	  /** Metoda loguje w dzienniku zdarze� wpisy z flag� w zale�no�ci od parametru wej�ciowego flaga

     * @param text Opis zdarzenia

     * @param flaga priorytet zdarzenia, -1 (error), 0 (info), 1 (trace), 2 (warn), 3 (debug).

     * @param logArea pole tekstowe dla logowania zdarze� aplikacji

     * @since 2.0

	   */
	private void zapiszDoDziennika(String tekst, int flaga) {
		
        if (flaga == -1) {

        	model.zapiszDoDziennikaZdarze�(pobierzAktualnyZnacznikCzasu() + "   ERROR  " + tekst + System.getProperty("line.separator"));
        }

        if (flaga == 0){

        	model.zapiszDoDziennikaZdarze�(pobierzAktualnyZnacznikCzasu() + "   INFO  " + tekst + System.getProperty("line.separator"));
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
        Date bie��cyCzas = new Date();
        String spersonalizowanyCzas = spersonalizowanyFormatCzasu.format(bie��cyCzas);

        return spersonalizowanyCzas;
    } 
}
