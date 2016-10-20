package gapp.gui;

import javafx.scene.Parent;

public interface Window {

    /** Crea e ritorna la schermata. 
     * @return un Parent che contiene l'intera schermata.*/
    Parent getWindow();

    /** Resetta la schermata da capo.
     * Questo metodo deve essere chiamato quando vengono effettuati cambiamenti
     * che comportano una nuova costruzione della GUI, come il cambio della lingua.
     * In alcuni casi, questo metodo potrebbe essere uguale a {@link getWindow()},
     * ma altre volte, come nella schermata della scacchiera, vanno ricreati solo
     * alcuni precisi elementi della schermata.
     * @return un Parent che contiene l'intera schermata resettata.*/
    Parent reset();

}
