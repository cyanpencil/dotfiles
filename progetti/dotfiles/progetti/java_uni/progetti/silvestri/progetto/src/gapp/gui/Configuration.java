package gapp.gui;

import gapp.ulg.Utilities.FileUtilities;
import gapp.ulg.game.Param;
import java.util.*;
import java.util.List;

public class Configuration {

    private static HashMap<String, String> translations;
    private static HashMap<String, String> choicesMade;

    private static Param<ArrayList<String>>  theme = new Param<ArrayList<String>>() {

        private ArrayList<String> theme = new ArrayList<>(Arrays.asList("b6bcc7", "e0e2e6", "000000"));
        private final ArrayList<ArrayList<String>> themesList = new ArrayList<>();
        private final String[] themeNames = {"LightBlue", "LightGreen"};

        @Override
        public String name() {
            return "Theme";
        }

        @Override
        public String prompt() {
            return "Tema della GUI";
        }

        @Override
        public List values() {
            themesList.add(new ArrayList<>(Arrays.asList("b6bcc7", "e0e2e6", "fdfdfd"))); // lightblue
            return Collections.unmodifiableList(Arrays.asList(themeNames));
        }

        @Override
        public void set(Object v) {
            if(!this.values().contains(v)) throw new IllegalArgumentException("Valore non valido");
            this.theme = (ArrayList<String>) v;
        }

        @Override
        public ArrayList<String> get() {
            return this.theme;
        }
    };

    private static Param<String>  titleStyle = new Param<String>() {

        private String titleStyle = "-fx-font-family: '☞ChronicaPro-Bold'; -fx-font-size: 30px; -fx-font-color: #000000 ";
        private final String[] fontNames = {"☞ChronicaPro-Bold"};

        @Override
        public String name() {
            return "Theme";
        }

        @Override
        public String prompt() {
            return "Tema della GUI";
        }

        @Override
        public List values() {
            return Collections.unmodifiableList(Arrays.asList(fontNames));
        }

        @Override
        public void set(Object v) {
            if(!(v instanceof String)) throw new IllegalArgumentException("Valore non valido");
            String value = (String) v;
            if(value.length() < 8) throw new IllegalArgumentException("Valore non valido");
            int size = new Integer(value.substring(0, 2));
            String color = value.substring(2, 8);
            String font = value.substring(8);
            if(!this.values().contains(font)) throw new IllegalArgumentException("Valore non valido");
            this.titleStyle = "-fx-font-family: " + " '" + font + "' " + "; -fx-font-size: " + size + "; -fx-font-color: " +  color + ";";
        }

        @Override
        public String get() {
            return titleStyle;
        }
    };

    /** Ritorna il valore corrente del parametro di nome name
     * @param paramName nome del parametro
     * @return valore corrente impostato del parametro */
    public static Object getParamCurrentValue(String paramName){
        if(paramName.equals("Tema") || paramName.equals("Lingua") || paramName.equals("Pezzi"))
            return FileUtilities.readParamValue("/config.rc", paramName);
        if (paramName.equals("ValidMoves") || paramName.equals("Coordinates")) {
            ArrayList<String> paramValues = FileUtilities.readParamValue("/config.rc", paramName);
            String value = paramValues.get(0);
            return value.equals("True") || value.equals("true");
        }
        if(paramName.equals("Titolo")){
            ArrayList<String> paramValues = FileUtilities.readParamValue("/config.rc", "Titolo");
            String titleStyle = "-fx-font-family: " + " '" + paramValues.get(0) + "' " + "; -fx-font-size: " + paramValues.get(1) + "; -fx-fill: " +  paramValues.get(2) + ";";
            return titleStyle;
        }
        throw new IllegalArgumentException("Nome del parametro non valido");
    }

    
    /** Imposta il valore corrente del parametro di nome name
     * @param paramName nome del parametro */
    public static void  setParamCurrentValue(String paramName, Object paramValue){
        if(Arrays.asList("Tema", "Lingua", "Titolo", "ValidMoves", "Coordinates", "Pezzi").contains(paramName)){
            FileUtilities.editParam("/config.rc", paramName, paramValue.toString());
            return;
        }        
        throw new IllegalArgumentException("Nome del parametro non valido");
    }
   
    
    /** Ritorna la traduzione (se presente) di una stringa.
     * Se non c'è nessuna traduzione disponibile, ritorna la stringa originaria
     * @param string la stringa da tradurre
     * @return la stringa tradotta.  */
    public static String getTranslation(String string){
        String lang = choicesMade.get("Language");
        //ArrayList<String> a = (ArrayList<String>) getParamCurrentValue("Lingua");
        //if (a == null) return string;
        if (lang == null) lang = ((ArrayList<String>) getParamCurrentValue("Lingua")).get(0);
        if (lang.equals("English")) return string;
        //if (translations == null) translations = FileUtilities.readAllTranslations("resources/dictionary.lang");
        //String translated = translations.get(string);
        String translated = FileUtilities.readTranslation("/dictionary.lang", 1, string);
        return translated != null ? translated : string;
    }

    public static void setChoicesMade(HashMap map) {
        choicesMade = map;
    }
}
