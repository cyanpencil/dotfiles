package gapp.gui.Windows;

import gapp.gui.*;
import gapp.ulg.game.GameFactory;
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
import jdk.nashorn.internal.runtime.regexp.joni.Config;

import java.util.Arrays.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class AdvancedGameSettings implements Window {

    private Animation arrowAnimation = null;
    private final HashMap<String, Object> choicesMade;
    private final GameFactory gF;
    private final StackPane root;
    private final HBox prevNext;
    private final ArrayList<Parent> windowsList;
    private final BiConsumer<Main.SlideType, Boolean> slideFunc;
    private VBox promptTextContainer;

    List<Param> paramList;
    int selectedParamIndex = 0;

    private Text selectedText;

    private Rectangle arrowContainer = new Rectangle(0, 0, 9.33, 20);

    private VBox valuesColumn = new VBox(), valuesBox = new VBox(), valuesWrapper = new VBox();
    private VBox paramColumn = new VBox();

    private ArrayList<String> settings = new ArrayList<>(Arrays.asList("MaxBlockTime", "tol", "timeout", "minTime", "maxTh", "fjpSize", "bgExecSize"));
    private ArrayList<String> descriptions = new ArrayList<>(Arrays.asList(
                "Defines the maximum time to wait for player related functions such as setGame() and moved().\nIf the value is less than zero, no time limit is applied.\nThe time is expressed in milliseconds.",
                "Defines the tolerance time allowed to a player that has broken its time limit while calculating a move.\nIf the value is equal or less than zero, then the players have no additional tolerance time to calculate their moves.\nThe time is expressed in milliseconds.",
                "Defines the maximum time to wait for player related functions such as setGame() and moved().\nIf the value is equal or less than zero, no time limit is applied.\nThis property has a lower priority than maxBlockTime.\nThe time is expressed in milliseconds.",
                "Defines the minimum time that a player has to wait before making a move.\nIf the value is equal or less than zero, the players aren't forced to wait any time between moves.\nThe time is expressed in milliseconds.",
                "Defines the maximum number of additional threads a player is allowed to use while calculating a move.\nIf the value is less than zero, the players can spawn an infinite amount of threads.",
                "Defines the maximum number of threads a player is allowed to create in the ForkJoinPool.\nIf the value is zero, the players are not allowed to use a ForkJoinPool.\nIf the value is less than zero, the players can not only spawn an infinite amount of threads in the ForkJoinPool, but they are also allowed to use the common pool.",
                "Defines the maximum number of background threads a player can use to calculate its moves.\nIf the value is zero, the players are not allowed to do background computation.\nIf the value is less than zero, the players can spawn an infinite amount of background threads."));

    public AdvancedGameSettings(HashMap<String, Object> choicesMade, StackPane root, HBox prevNext, ArrayList<Parent> windowsList, BiConsumer<Main.SlideType, Boolean> slideFunc){
        this.choicesMade = choicesMade;
        this.gF          = (GameFactory) choicesMade.get("gF");
        this.root        = root;
        this.prevNext    = prevNext;
        this.windowsList = windowsList;
        this.slideFunc   = slideFunc;
        choicesMade.put("advancedGameSettings", new ArrayList<Integer>(Arrays.asList(-1, -1, -1, -1, -1, -1, -1)));
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
        wrapper.maxHeightProperty().bind(root.heightProperty().subtract(110));
        wrapper.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, null, null)));

        valuesColumn = new VBox();
        valuesBox = new VBox();
        valuesWrapper = new VBox();
        paramColumn = new VBox();

        // -----------> Inizio titolo della finestra <-----------------
        HBox title = new HBox();
        title.setStyle("-fx-pref-height: " + titleBoxHeight + "px;");
        title.setMinHeight(titleBoxHeight);
        Text text = new Text(this.gF.name() + " advanced parameters");
        text.setTextAlignment(TextAlignment.CENTER);
        text.setStyle((String) Configuration.getParamCurrentValue("Titolo"));
        title.getChildren().add(text);
        title.setAlignment(Pos.CENTER);
        wrapper.getChildren().add(title);
        // -----------> Fine titolo della finestra <-----------------

/*
 *        HBox body = new HBox();
 *        body.setBackground(new Background(new BackgroundFill(Color.DEEPSKYBLUE, null, null)));
 *        body.prefHeightProperty().bind(root.heightProperty());
 *
 *        wrapper.getChildren().addAll(title, body, prevNext);
 *        return wrapper;
 */

        HBox body = new HBox(); // Contenuti della finestra
//        body.setBackground(new Background(new BackgroundFill(Color.DEEPSKYBLUE, null, null)));
        body.prefHeightProperty().bind(root.heightProperty());
        body.setAlignment(Pos.CENTER);

        VBox paramBox = new VBox(); // Box dei parametri
        paramBox.setAlignment(Pos.BOTTOM_RIGHT);
        paramBox.prefHeightProperty().bind(body.heightProperty().multiply(1));
        paramBox.prefWidthProperty().bind(root.widthProperty().multiply(percentageDivision[0]));
        paramBox.minWidthProperty().bind(root.widthProperty().multiply(percentageDivision[0]));
        paramBox.maxWidthProperty().bind(root.widthProperty().multiply(percentageDivision[0]));

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
            //provaw.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
            provaw.setPrefHeight(30);
            provaw.prefWidthProperty().bind(paramWrapper.widthProperty());

            VBox paramPrompt = new VBox();
            paramWrapper.setPrefHeight(30);
            paramWrapper.prefWidthProperty().bind(paramBox.widthProperty());
            paramWrapper.minWidthProperty().bind(paramBox.widthProperty());
            //paramWrapper.setSpacing(20);
            paramPrompt.setPrefHeight(30);
            paramWrapper.setAlignment(Pos.CENTER_RIGHT);
            paramPrompt.setPadding(new Insets(5, 80, 0, 0));
            paramPrompt.setAlignment(Pos.TOP_RIGHT);
            //paramPrompt.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, null, null)));
            paramPrompt.prefWidthProperty().bind(paramWrapper.widthProperty().subtract(paramTextContainer.widthProperty()).subtract(provaw.widthProperty()));
            Text prompt = new Text(descriptions.get(i));
            paramPrompt.getChildren().add(prompt);
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


                //((VBox) valuesColumn.getChildren().get(0)).getChildren().remove(0);
                //((VBox) valuesColumn.getChildren().get(0)).getChildren().add(0, getValueBox(i2).getChildren().get(0));
                valuesColumn.getChildren().remove(0);
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

        valuesColumn.setAlignment(Pos.CENTER_LEFT);
        valuesColumn.setPadding(new Insets(0, 0, 0, 20));
        valuesColumn.getChildren().addAll(getValueBox(0));
        valuesColumn.maxWidthProperty().bind(valuesBox.widthProperty().multiply(0.9));

        valuesWrapper.getChildren().addAll(valuesColumn);
        valuesWrapper.setAlignment(Pos.CENTER);

        valuesBox.setPadding(valuesBoxPadding);
        valuesBox.setAlignment(Pos.TOP_CENTER);
        valuesBox.prefHeightProperty().bind(root.heightProperty().multiply(1));
        valuesBox.maxHeightProperty().bind(root.heightProperty().multiply(1));
        valuesBox.getChildren().add(valuesWrapper);
        valuesBox.minWidthProperty().bind(root.widthProperty().subtract(60).multiply(percentageDivision[1]));

        body.getChildren().addAll(paramBox, arrowPane, valuesBox);

        Separator titleSeparator = new Separator(Orientation.HORIZONTAL);
        titleSeparator.getStyleClass().add("horizontal-separator");
        wrapper.getChildren().addAll(titleSeparator, body);
        return wrapper;
    }

    private VBox getValueBox(int paramIndex){
        VBox promptWrapper = new VBox();
        promptWrapper.setMaxWidth(300);
        promptWrapper.setMinWidth(300);
        promptWrapper.setMinHeight(200);
        promptWrapper.setPrefHeight(200);

        String translated = Configuration.getTranslation(settings.get(paramIndex) + "Expl");
        Text prompt = new Text(translated.equals(settings.get(paramIndex)+"Expl") ? descriptions.get(paramIndex) : translated);
        prompt.wrappingWidthProperty().setValue(300);
        prompt.setTextAlignment(TextAlignment.JUSTIFY);
        prompt.getStyleClass().add("text");
        promptWrapper.getChildren().add(prompt);

        TextField textField = new TextField("-1"){
            @Override public void replaceText(int start, int end, String text) {
                if (this.getText().concat(text).matches("-?[0-9]*"))
                    super.replaceText(start, end, text);
                Integer res = this.getText().matches("-?") ? 0 : Integer.parseInt(this.getText());
                ((ArrayList<Integer>) choicesMade.get("advancedGameSettings")).set(selectedParamIndex, res);
            }
            @Override public void replaceSelection(String text) {
                if (this.getText().concat(text).matches("-?[0-9]*"))
                    super.replaceSelection(text);
            }
        };
        if (((ArrayList<Integer>) choicesMade.get("advancedGameSettings")).get(selectedParamIndex) != null) 
            textField.setText("" + ((ArrayList<Integer>)choicesMade.get("advancedGameSettings")).get(selectedParamIndex));
        textField.getStyleClass().add("text-field");
        textField.setAlignment(Pos.CENTER);
        HBox hbox = new HBox(textField);
        hbox.setMaxWidth(100);

        Separator promptSeparator = new Separator(Orientation.HORIZONTAL);
        promptSeparator.getStyleClass().add("horizontal-separator-description");

        VBox valueBox = new VBox(promptWrapper, promptSeparator, hbox);
        valueBox.getStyleClass().add("descriptionTextContainer");
        valueBox.setAlignment(Pos.CENTER);
        valueBox.setSpacing(20);
        valueBox.setMaxWidth(300);
        return valueBox;
    }

    final Animation fadeIn = new Transition() {
        { setCycleDuration(Duration.millis(400)); }
        protected void interpolate(double frac) {
            ((VBox)valuesColumn.getChildren().get(0)).getChildren().get(0).setOpacity(frac);
        }
    };

    public Parent reset() {
        return getWindow();
    }

    //devo riempire la mappa choicesMade con la chiave advancedGameSettings con un ArrayList
}
