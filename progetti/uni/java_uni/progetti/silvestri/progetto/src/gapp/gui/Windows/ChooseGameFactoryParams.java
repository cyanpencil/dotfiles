package gapp.gui.Windows;

import gapp.gui.*;
import gapp.gui.Configuration;
import gapp.ulg.game.GameFactory;
import gapp.ulg.game.Param;
import gapp.ulg.game.board.Board;
import gapp.ulg.play.PlayerFactories;
import javafx.animation.Animation;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Edoardo on 24/08/2016.
 */
public class ChooseGameFactoryParams implements Window {

    private final HashMap<String, Object> choicesMade;
    Animation arrowAnimation = null;
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

    private TilePane valuesColumn = new TilePane();
    private VBox valuesWrapper = new VBox();

    private VBox paramColumn = new VBox();

    public ChooseGameFactoryParams(HashMap<String, Object> choicesMade, StackPane root, HBox prevNext, ArrayList<Parent> windowsList, BiConsumer<Main.SlideType, Boolean> slideFunc){
        this.choicesMade = choicesMade;
        this.gF = (GameFactory) choicesMade.get("gF");
        this.paramList = this.gF.params();     // Lista dei parametri da scegliere
        this.root = root;
        this.prevNext = prevNext;
        this.windowsList = windowsList;
        this.slideFunc = slideFunc;

        //this.windowsList.add(new ChoosePlayers(choicesMade, root, prevNext, windowsList).getWindow());
        ArrayList<Param> ggFParams = new ArrayList<>();
        List<Param> paramList = this.gF.params();
        paramList.forEach(param -> ggFParams.add(param));
        choicesMade.put("gFParams", ggFParams);
    }

    public Parent getWindow(){
        
        int titleBoxHeight = 80;
        double[] percentageDivision = {0.5, 0.5};
        int bodyPaddingLeft = 20;
        int ValuesTileBPadding = 50;
        int paramColumnSpacing = 20;
        Insets valuesBoxPadding = new Insets(20, 0, 0, 25);

        GameFactory chosenGameFactory = this.gF;                // GameFactory scelta nella finestra precedente
        VBox wrapper = new VBox(); // Contenitore complessivo della finestra
        wrapper.maxHeightProperty().bind(root.heightProperty().subtract(110)); // Sottraggo l'altezza dei bottoni prevnext

        valuesColumn = new TilePane();
        valuesWrapper = new VBox();
        paramColumn = new VBox();

        // -----------> Inizio titolo della finestra <-----------------
        HBox title = new HBox();
        title.setStyle("-fx-pref-height: " + titleBoxHeight + "px;");
        title.setMinHeight(titleBoxHeight);
        Text text = new Text(chosenGameFactory.name());
        text.setTextAlignment(TextAlignment.CENTER);
        text.setStyle((String) Configuration.getParamCurrentValue("Titolo"));
        title.getChildren().add(text);
        title.setAlignment(Pos.CENTER);
        wrapper.getChildren().add(title);
        // -----------> Fine titolo della finestra <-----------------

        HBox body = new HBox(); // Contenuti della finestra
        //body.setPadding(new Insets(0, 10, 0, 0));
        body.prefHeightProperty().bind(root.heightProperty().multiply(1)); // Altezza del corpo // todo: controllare se va fatto .subtract(100)
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

        for(Param param : paramList){
            VBox paramTextContainer = new VBox();
            paramTextContainer.setPrefSize(100, 30);
            paramTextContainer.setMaxWidth(100);
            paramTextContainer.getStyleClass().add("paramTextContainer");
            paramTextContainer.setAlignment(Pos.CENTER);
            Text paramName = new Text(Configuration.getTranslation(param.name()));
            paramName.getStyleClass().add("text");
            paramTextContainer.getChildren().add(paramName);
            //paramTextContainer.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));


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
            Text prompt = new Text(Configuration.getTranslation(param.prompt()));
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
                double h = ((HBox)paramColumn.getChildren().get(0)).getHeight();
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

                ArrayList<VBox> newValues = getValuesBoxexOfParam(i2);
                valuesWrapper.getChildren().remove(0);
                valuesWrapper.getChildren().add(0, newValues.get(0));

                valuesColumn.getChildren().remove(0, valuesColumn.getChildren().size());
                valuesColumn.getChildren().addAll(newValues.subList(1, newValues.size()));
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

        VBox valuesBox = new VBox();
        valuesBox.setPadding(valuesBoxPadding);
        valuesBox.setAlignment(Pos.TOP_LEFT);

        valuesBox.prefHeightProperty().bind(root.heightProperty().multiply(1));
        valuesBox.setMaxHeight(300);
        valuesBox.maxHeightProperty().bind(root.heightProperty().multiply(1));

        valuesBox.prefWidthProperty().bind(root.widthProperty().multiply(percentageDivision[1]));
        valuesBox.minWidthProperty().bind(root.widthProperty().multiply(percentageDivision[1]));

        ArrayList<VBox> vBoxes = getValuesBoxexOfParam(0);
        valuesWrapper.setMinWidth(180);
        valuesWrapper.setPrefWidth(180);
        valuesWrapper.setMaxWidth(180);

        valuesWrapper.setMinHeight(300);
        valuesWrapper.setMaxHeight(300);

        valuesColumn.setOrientation(Orientation.VERTICAL);
        valuesColumn.setAlignment(Pos.TOP_CENTER);

        valuesColumn.setPrefHeight(250); valuesColumn.setMaxHeight(250); valuesColumn.setMinHeight(250);


        valuesColumn.setPrefTileWidth(50); valuesColumn.setMinWidth(180); valuesColumn.setPrefWidth(180);valuesColumn.setMaxWidth(180);

        valuesColumn.getChildren().addAll(vBoxes.subList(1, vBoxes.size()));

        Separator promptSeparator = new Separator(Orientation.HORIZONTAL);
        promptSeparator.getStyleClass().add("horizontal-separator");

        valuesWrapper.getChildren().addAll(vBoxes.get(0), promptSeparator, valuesColumn);
        valuesWrapper.setSpacing(8);
        valuesBox.getChildren().add(valuesWrapper);


        body.getChildren().addAll(paramBox, arrowPane, valuesBox);

        Separator titleSeparator = new Separator(Orientation.HORIZONTAL);
        titleSeparator.getStyleClass().add("horizontal-separator");
        wrapper.getChildren().addAll(titleSeparator, body);


        return wrapper;
    }

    private ArrayList<VBox> getValuesBoxexOfParam(int paramIndex){
//        if(value.toString().matches("-?\\d+(\\.\\d+)?")) paramList.get(paramIndex).set(Integer.parseInt(value.toString()));
//        else paramList.get(paramIndex).set(value.toString());
//
//        if(paramIndex >= gFParams.size())
//            gFParams.add(value.toString());
//        else
//            gFParams.set(paramIndex, value.toString());
        
        List<Param> paramList = this.gF.params();
        ArrayList<VBox> valuesBoxes = new ArrayList<>();
        Param param = paramList.get(paramIndex);

        promptTextContainer = new VBox();
        promptTextContainer.setPrefHeight(50);
        promptTextContainer.minHeight(50);
        promptTextContainer.maxHeight(50);

        promptTextContainer.setAlignment(Pos.CENTER); // Per centrare il testo in altezza
        Text prompt = new Text(Configuration.getTranslation(param.prompt()));
        prompt.setTextAlignment(TextAlignment.CENTER); // Per centrare il testo in larghezza
        prompt.setWrappingWidth(180); prompt.minHeight(50); prompt.prefHeight(50);
        promptTextContainer.getChildren().add(prompt);

        valuesBoxes.add(promptTextContainer);
        for(int valIndex = 0; valIndex < param.values().size(); valIndex++){
            Object value = paramList.get(paramIndex).values().get(valIndex);
            VBox valueTextContainer = new VBox();
            valueTextContainer.setPrefSize(50, 25);
            valueTextContainer.setMaxWidth(100);
            //valueTextContainer.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, null, null)));
            valueTextContainer.getStyleClass().add("valueTextContainer");

            valueTextContainer.setAlignment(Pos.CENTER);
            Text valueText = new Text(Configuration.getTranslation(value.toString()));
            //valueText.setFill(Color.web("#5A68AA"));
            valueText.getStyleClass().add("text");
            valueTextContainer.getChildren().add(valueText);
            valueTextContainer.setOnMouseClicked(clicked -> {
                ArrayList gFParams = (ArrayList) choicesMade.get("gFParams");
                if(gFParams == null)
                    gFParams = new ArrayList();
                
                selectAValue(valueText);

                if(value.toString().matches("-?\\d+(\\.\\d+)?")) paramList.get(paramIndex).set(Integer.parseInt(value.toString()));
                else paramList.get(paramIndex).set(value.toString());

                if(paramIndex >= gFParams.size())
                    gFParams.add(paramList.get(paramIndex));
                else
                    gFParams.set(paramIndex, paramList.get(paramIndex));

                choicesMade.put("gFParams", gFParams);
            });

            if (paramList.get(paramIndex).get().equals(value)) selectAValue(valueText);
            valuesBoxes.add(valueTextContainer);
        }
        return valuesBoxes;
    }
    
    final Animation fromBlackToRed = new Transition() {
        { setCycleDuration(Duration.millis(200)); }
        protected void interpolate(double frac) {
            selectedText.setFill(Color.rgb((int) Math.round(frac * 255d), 0, 0));
        }
    };
    
    final Animation fadeIn = new Transition() {
        { setCycleDuration(Duration.millis(400)); }
        protected void interpolate(double frac) {
            promptTextContainer.setOpacity(frac);
            valuesColumn.setOpacity(frac);
        }
    };
    
    private void selectAValue(Text value) {
        fromBlackToRed.stop();
        deselectAllValues();
        selectedText = value;
        fromBlackToRed.play();
    }
    
    private void deselectAllValues() {
        valuesColumn.getChildren().forEach(valueTextContainer -> ((VBox)valueTextContainer).getChildren().forEach(text -> ((Text) text).setFill(Color.BLACK)));
    }

    public Parent reset() {
        return getWindow();
    }
}
