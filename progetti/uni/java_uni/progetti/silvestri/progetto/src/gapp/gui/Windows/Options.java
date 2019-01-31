package gapp.gui.Windows;

import gapp.gui.Configuration;
import gapp.gui.*;
import gapp.ulg.game.GameFactory;
import javafx.collections.*;
import javafx.collections.ObservableList;
import gapp.ulg.game.Param;
import gapp.ulg.game.board.Board;
import gapp.ulg.play.PlayerFactories;
import javafx.animation.Animation;
import javafx.beans.value.*;
import javafx.scene.control.*;
import javafx.animation.Transition;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import java.util.*;
import jdk.nashorn.internal.runtime.regexp.joni.Config;

import java.util.Arrays.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class Options {

    private Animation arrowAnimation = null;
    private final HashMap<String, Object> choicesMade;
    private final StackPane root;
    private VBox promptTextContainer;

    List<Param> paramList;
    int selectedParamIndex = 0;

    private Text selectedText;

    private Rectangle arrowContainer = new Rectangle(0, 0, 9.33, 20);

    private VBox valuesColumn = new VBox(), valuesBox = new VBox(), valuesWrapper = new VBox();
    private VBox paramColumn = new VBox();

    private ArrayList<String> settings = new ArrayList<>(Arrays.asList("Gameplay", "Graphics", "Audio"));
    private Main windowsManager;

    public Options(HashMap<String, Object> choicesMade, StackPane root, Main windowsManager) {
        this.choicesMade = choicesMade;
        this.root        = root;
        this.windowsManager = windowsManager;
    }

    // Variabili grafiche
    int titleBoxHeight                 = 80;
    double[] percentageDivision        = {0.3, 0.7};
    int bodyPaddingLeft                = 20;
    int paramColumnSpacing             = 20;
    int descriptionPromptWrappingWidth = 330;
    Insets valuesBoxPadding            = new Insets(20, 0, 0, 0);
    public Parent getWindow(){

        VBox wrapper = new VBox(); // Contenitore complessivo della finestra
        wrapper.maxHeightProperty().bind(root.heightProperty().multiply(1));
        wrapper.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, null, null)));

        // -----------> Inizio titolo della finestra <-----------------
        HBox title = new HBox();
        title.setStyle("-fx-pref-height: " + titleBoxHeight + "px;");
        title.setMinHeight(titleBoxHeight);
        Text text = new Text(Configuration.getTranslation("Options"));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setStyle((String) Configuration.getParamCurrentValue("Titolo"));
        title.getChildren().add(text);
        title.setAlignment(Pos.CENTER);
        wrapper.getChildren().add(title);
        // -----------> Fine titolo della finestra <-----------------


        HBox body = new HBox(); // Contenuti della finestra
        body.prefHeightProperty().bind(root.heightProperty().multiply(0.8));
        body.setAlignment(Pos.CENTER);

        VBox paramBox = new VBox(); // Box dei parametri
        paramBox.setAlignment(Pos.BOTTOM_RIGHT);
        paramBox.prefHeightProperty().bind(body.heightProperty().multiply(1));
        paramBox.prefWidthProperty().bind(root.widthProperty().multiply(percentageDivision[0]));
        paramBox.minWidthProperty().bind(root.widthProperty().multiply(percentageDivision[0]));
        paramBox.maxWidthProperty().bind(root.widthProperty().multiply(percentageDivision[0]));

        paramColumn = new VBox();
        paramColumn.minHeightProperty().bind(body.heightProperty().subtract(bodyPaddingLeft));
        paramColumn.maxHeightProperty().bind(body.heightProperty().subtract(bodyPaddingLeft));
        paramColumn.setMaxWidth(100);
        paramColumn.setSpacing(paramColumnSpacing);

        for(int i = 0; i < settings.size(); i++) {
            String setting = settings.get(i);
            String translated = Configuration.getTranslation(setting);
            VBox paramTextContainer = new VBox();
            paramTextContainer.setPrefSize(100, 30);
            paramTextContainer.setMaxWidth(100);
            paramTextContainer.getStyleClass().add("paramTextContainer");
            paramTextContainer.setAlignment(Pos.CENTER);
            Text paramName = new Text(translated.equals(setting) ? setting : translated);
            paramName.getStyleClass().add("text");
            paramTextContainer.getChildren().add(paramName);


            HBox paramWrapper = new HBox();

            VBox provaw = new VBox();
            Rectangle prova = new Rectangle(30, 30);
            prova.setFill(Color.RED);
            provaw.getChildren().add(prova);
            provaw.setPrefHeight(30);
            provaw.prefWidthProperty().bind(paramWrapper.widthProperty());

            VBox paramPrompt = new VBox();
            paramWrapper.setPrefHeight(30);
            paramWrapper.prefWidthProperty().bind(paramBox.widthProperty());
            paramWrapper.minWidthProperty().bind(paramBox.widthProperty());
            paramPrompt.setPrefHeight(30);
            paramWrapper.setAlignment(Pos.CENTER_RIGHT);
            paramPrompt.setPadding(new Insets(5, 80, 0, 0));
            paramPrompt.setAlignment(Pos.TOP_RIGHT);
            paramPrompt.prefWidthProperty().bind(paramWrapper.widthProperty().subtract(paramTextContainer.widthProperty()).subtract(provaw.widthProperty()));
            paramWrapper.getChildren().addAll(paramTextContainer);

            paramColumn.getChildren().add(paramWrapper);
        }
        paramBox.getChildren().addAll(paramColumn);

        int clicked = 20;
        // Animazione di scorrimento della freccetta
        arrowAnimation = new Transition() {
            { setCycleDuration(Duration.millis(400)); }
            protected void interpolate(double frac) {
                final float n = (1 * (float) frac);
                double h = paramColumn.getChildren().size() > 0 ? ((HBox)paramColumn.getChildren().get(0)).getHeight(): 0;
                arrowContainer.setTranslateY(arrowContainer.getTranslateY() +
                        (((h + paramColumn.getSpacing()) * selectedParamIndex + h/2f - arrowContainer.getHeight()/2f + 18) - arrowContainer.getTranslateY()) * n);
            }
        };
        // Colonna dei parametri
        for (int i = 0; i < paramColumn.getChildren().size(); i++) {
            int i2 = i;
            paramColumn.getChildren().get(i).setOnMouseClicked(e -> {
                arrowAnimation.stop();
                selectedParamIndex = i2;
                arrowAnimation.play();

                fadeIn.stop();
                fadeIn.play();


                valuesColumn.getChildren().clear();
                valuesColumn.getChildren().addAll(getValueBox(i2));
        });}
        arrowAnimation.play(); // Per la posizione iniziale della freccia

        Image arrow = new Image(getClass().getResource("/Arrow30.png").toExternalForm());
        ImagePattern arrowPattern = new ImagePattern(arrow);
        arrowContainer.setFill(arrowPattern);

        VBox arrowBox = new VBox(arrowContainer);
        arrowBox.setAlignment(Pos.TOP_RIGHT);

        StackPane arrowPane = new StackPane(arrowBox);
        arrowPane.setAlignment(Pos.CENTER_RIGHT);
        arrowPane.setPrefWidth(60);
        arrowPane.setMinWidth(60);
        arrowPane.prefHeightProperty().bind(root.heightProperty());

        Separator line = new Separator(Orientation.VERTICAL);
        line.getStyleClass().add("vertical-separator");

        arrowPane.getChildren().add(line);


        valuesColumn = new VBox();
        valuesColumn.setAlignment(Pos.CENTER_LEFT);
        valuesColumn.setPadding(new Insets(0, 0, 0, 20));
        valuesColumn.getChildren().addAll(getValueBox(0));
        valuesColumn.maxWidthProperty().bind(valuesBox.widthProperty().multiply(0.9));

        valuesWrapper = new VBox();
        valuesWrapper.getChildren().addAll(valuesColumn);
        valuesWrapper.setAlignment(Pos.CENTER_LEFT);

        valuesBox = new VBox();
        valuesBox.setPadding(valuesBoxPadding);
        valuesBox.setAlignment(Pos.TOP_CENTER);
        valuesBox.prefHeightProperty().bind(root.heightProperty().multiply(1));
        valuesBox.maxHeightProperty().bind(root.heightProperty().multiply(1));
        valuesBox.getChildren().add(valuesWrapper);
        valuesBox.minWidthProperty().bind(root.widthProperty().subtract(60).multiply(percentageDivision[1]));


        Separator titleSeparator = new Separator(Orientation.HORIZONTAL);
        titleSeparator.getStyleClass().add("horizontal-separator");

        HBox returnButton = new HBox();
        returnButton.setMinSize(25, 25);
        returnButton.setMaxSize(25, 25);
        Rectangle returnRectangle = new Rectangle(25, 25);
        Image closeIcon = new Image(getClass().getResource("/Close25.png").toExternalForm());
        ImagePattern closeIconPattern  = new ImagePattern(closeIcon);
        returnRectangle.setFill(closeIconPattern);
        returnButton.setAlignment(Pos.CENTER);
        returnButton.getChildren().add(returnRectangle);

        returnButton.setOnMousePressed(e -> {
            windowsManager.slideOptions(Main.SlideType.PREVIOUS);
        });
        
        StackPane returnButtonWrapper = new StackPane(returnButton);
        returnButtonWrapper.setAlignment(Pos.CENTER);
        
        body.getChildren().addAll(paramBox, arrowPane, valuesBox);
        wrapper.getChildren().addAll(titleSeparator, body, returnButtonWrapper);
        return wrapper;
    }
    
    private VBox getGraphics() {
        VBox valueBox = new VBox(getPieces());
        valueBox.getStyleClass().add("descriptionTextContainer");
        valueBox.setAlignment(Pos.CENTER);
        valueBox.setSpacing(20);
        valueBox.setMaxWidth(300);
        return valueBox;
    }

    private HBox getPieces() {
        ObservableList<String> comboBoxValues = FXCollections.observableArrayList("Tournament", "Club");
        ComboBox paramChoiceBox = new ComboBox(comboBoxValues);
        paramChoiceBox.setMinWidth(200); paramChoiceBox.setPrefWidth(200); paramChoiceBox.setMaxWidth(200);
        String selectedLanguage = ((ArrayList<String>) Configuration.getParamCurrentValue("Pezzi")).get(0);
        paramChoiceBox.setValue(selectedLanguage == null ? "Tournament" : selectedLanguage);
        paramChoiceBox.setOnAction(a -> {
            String selectedValue = paramChoiceBox.getSelectionModel().getSelectedItem().toString();
            choicesMade.put("Pezzi", selectedValue);
            Configuration.setParamCurrentValue("Pezzi", selectedValue);
            resetGUI();
        });

        VBox promptWrapper = new VBox();
        promptWrapper.setMaxWidth(100);
        promptWrapper.setMinWidth(100);
        promptWrapper.setAlignment(Pos.CENTER_LEFT);

        String translated = Configuration.getTranslation("Pieces style");
        Text prompt = new Text(translated + ":");
        prompt.setTextAlignment(TextAlignment.JUSTIFY);
        prompt.getStyleClass().add("text");
        promptWrapper.getChildren().add(prompt);

        HBox language = new HBox(promptWrapper, paramChoiceBox);
        language.setMaxWidth(300);
        return language;
    }
    
    private VBox getAudio() {
        return new VBox();
    }
    
    private HBox getCoordinates() {
        CheckBox circlesToggle = new CheckBox();
        circlesToggle.setSelected((boolean) Configuration.getParamCurrentValue("Coordinates"));
        circlesToggle.setOnAction(a -> {
            boolean prevValue = ((boolean) Configuration.getParamCurrentValue("Coordinates"));
            String selectedValue = prevValue ? "False" : "True";
            choicesMade.put("Coordinates", selectedValue);
            Configuration.setParamCurrentValue("Coordinates", selectedValue);
            resetGUI();
        });

        VBox promptWrapper = new VBox();
        promptWrapper.setMaxWidth(250);
        promptWrapper.setMinWidth(250);
        promptWrapper.setAlignment(Pos.CENTER_LEFT);

        String translated = Configuration.getTranslation("Show board coordinates");
        Text prompt = new Text(translated + ":");
        prompt.setTextAlignment(TextAlignment.JUSTIFY);
        prompt.getStyleClass().add("text");
        promptWrapper.getChildren().add(prompt);

        HBox circle = new HBox(promptWrapper, circlesToggle);
        circle.setMinWidth(300);
        return circle;
    }

    private HBox getCircles() {
        CheckBox circlesToggle = new CheckBox();
        circlesToggle.setSelected((boolean) Configuration.getParamCurrentValue("ValidMoves"));
        circlesToggle.setOnAction(a -> {
            String selectedValue = circlesToggle.isSelected() ? "True" : "False";
            choicesMade.put("ValidMoves", selectedValue);
            Configuration.setParamCurrentValue("ValidMoves", selectedValue);
            resetGUI();
        });
        
        VBox promptWrapper = new VBox();
        promptWrapper.setMaxWidth(250);
        promptWrapper.setMinWidth(250);
        promptWrapper.setAlignment(Pos.CENTER_LEFT);

        String translated = Configuration.getTranslation("Highlight valid moves");
        Text prompt = new Text(translated + ":");
        prompt.setTextAlignment(TextAlignment.JUSTIFY);
        prompt.getStyleClass().add("text");
        promptWrapper.getChildren().add(prompt);
        
        HBox circle = new HBox(promptWrapper, circlesToggle);
        circle.setMinWidth(300);
        return circle;
    }
    
    private HBox getLanguage() {
        ObservableList<String> comboBoxValues = FXCollections.observableArrayList("English", "Italiano");
        ComboBox paramChoiceBox = new ComboBox(comboBoxValues);
        paramChoiceBox.setMinWidth(200); paramChoiceBox.setPrefWidth(200); paramChoiceBox.setMaxWidth(200);
        String selectedLanguage = ((ArrayList<String>) Configuration.getParamCurrentValue("Lingua")).get(0);
        paramChoiceBox.setValue(selectedLanguage == null ? "English" : selectedLanguage);
        paramChoiceBox.setOnAction(a -> {
            String selectedValue = paramChoiceBox.getSelectionModel().getSelectedItem().toString();
            choicesMade.put("Language", selectedValue);
            Configuration.setParamCurrentValue("Lingua", selectedValue);
            resetGUI();
        });

        VBox promptWrapper = new VBox();
        promptWrapper.setMaxWidth(100);
        promptWrapper.setMinWidth(100);
        //promptWrapper.minHeightProperty().bind(paramChoiceBox.heightProperty());
        //promptWrapper.maxHeightProperty().bind(paramChoiceBox.heightProperty());
        promptWrapper.setAlignment(Pos.CENTER_LEFT);

        String translated = Configuration.getTranslation("Language");
        Text prompt = new Text(translated + ":");
        prompt.setTextAlignment(TextAlignment.JUSTIFY);
        prompt.getStyleClass().add("text");
        promptWrapper.getChildren().add(prompt);

        HBox language = new HBox(promptWrapper, paramChoiceBox);
        language.setMaxWidth(300);
        return language;
    }
    
    private VBox getGameplay() {
        
        HBox lang = getLanguage();
        
        HBox circ = getCircles();
        
        HBox coor = getCoordinates();
        
        VBox valueBox = new VBox(lang, circ, coor);
        valueBox.getStyleClass().add("descriptionTextContainer");
        valueBox.setAlignment(Pos.CENTER);
        valueBox.setSpacing(20);
        valueBox.setMaxWidth(300);
        return valueBox;
    }

    private VBox getValueBox(int paramIndex){
        VBox b = new VBox();
        switch(paramIndex) {
            case 0: b = getGameplay();
                    break;
            case 1: b = getGraphics();
                    break;
            case 2: b = getAudio();
                    break;
        }
        b.setAlignment(Pos.CENTER_LEFT);
        return b;
    }
    
    private void resetGUI() {
        selectedParamIndex = 0;
        root.getChildren().remove(windowsManager.opzioni);
        windowsManager.opzioni = this.getWindow();
        root.getChildren().add(windowsManager.opzioni);
        windowsManager.resetGUI();
    }
    
    

    final Animation fadeIn = new Transition() {
        { setCycleDuration(Duration.millis(400)); }
        protected void interpolate(double frac) {
            ((VBox)valuesColumn).setOpacity(frac);
        }
    };


    //
    //devo riempire la mappa choicesMade con la chiave advancedGameSettings con un ArrayList
    //
    //
}
