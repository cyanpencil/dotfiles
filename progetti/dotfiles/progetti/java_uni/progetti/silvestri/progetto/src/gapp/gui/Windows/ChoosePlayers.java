package gapp.gui.Windows;

import gapp.gui.*;
import gapp.gui.Configuration;
import gapp.ulg.game.GameFactory;
import gapp.ulg.game.PlayerFactory;
import gapp.ulg.play.PlayerFactories;
import gapp.ulg.Utilities.MyCouple;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.BiConsumer;

/**
 * Created by Edoardo on 24/08/2016.
 */
public class ChoosePlayers implements Window {

    private final HashMap<String, Object> choicesMade;
    private final GameFactory gF;
    private final StackPane root;
    private final HBox prevNext;
    private final ArrayList<Parent> windowsList;
    private final BiConsumer<Main.SlideType, Boolean> slideFunc;
    private ArrayList<MyCouple<PlayerFactory, String>> teaPlayers = new ArrayList<>(Arrays.asList(new MyCouple<>(null, "Giocatore 1"), new MyCouple<>(null, "Giocatore 2")));

    private HBox loadingBox1 = new HBox();
    private HBox loadingBox2 = new HBox();
    private Text paramText = new Text("");

    PlayerFactory.Play[] playersPlay = {null, null};
    private ArrayList<PlayerFactory> players = new ArrayList<>();

    private final Main windowsManager;
    VBox wrapper = new VBox(); // Contenitore complessivo della finestra
    VBox wrapper2 = new VBox(); // Contenitore complessivo della finestra
    VBox settingsWrapper = new VBox();

    HBox loadingBoxWrapper = new HBox();
    private final int playerIndex;

    int titleBoxHeight = 100;
    int playerSettingsWidth = 600;

    private HBox body;

    public ChoosePlayers(int playerIndex, HashMap<String, Object> choicesMade, StackPane root, HBox prevNext, ArrayList<Parent> windowsList, BiConsumer<Main.SlideType, Boolean> slideFunc, Main windowsManager){
        this.choicesMade = choicesMade;
        this.gF = (GameFactory) choicesMade.get("gF");
        this.root = root;
        this.prevNext = prevNext;
        this.windowsList = windowsList;
        this.slideFunc = slideFunc;
        this.windowsManager = windowsManager;
        this.playerIndex = playerIndex;

        choicesMade.put("players", teaPlayers);
    }

    public Parent getWindow(){
        currentIndex = playerIndex;
        return this.firstPlayerWindow(playerIndex);
    }

    private Parent firstPlayerWindow(int index){

        VBox wrapper = new VBox();
        //VBox wrapper = this.wrapper;

        wrapper.maxHeightProperty().bind(root.heightProperty().subtract(110));

        // Variabili grafiche
        int titleBoxHeight = 80;


        wrapper.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, null, null)));
        // -----------> Inizio titolo della finestra <-----------------
        HBox title = new HBox();
        title.setStyle("-fx-pref-height: " + titleBoxHeight + "px;");
        title.setMinHeight(titleBoxHeight);

        String playerNumber = index == 0 ? "One" : "Two";
        Text text = new Text(Configuration.getTranslation("Choose Player " + playerNumber));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setStyle((String) Configuration.getParamCurrentValue("Titolo"));
        title.getChildren().add(text);
        title.setAlignment(Pos.CENTER);
        // -----------> Fine titolo della finestra <-----------------


        //VBox bBox = new VBox(10, playerButtons.toArray(new Button [playerButtons.size()]));
        TilePane bBox = new TilePane();
        bBox.setOrientation(Orientation.HORIZONTAL);
        bBox.setPadding(new Insets(0, 0, 0, 20));
        bBox.setAlignment(Pos.CENTER_LEFT);
        bBox.setTileAlignment(Pos.CENTER_LEFT);
        bBox.setVgap(30);
        bBox.setHgap(30);
        bBox.prefHeightProperty().bind(root.heightProperty().subtract(110));
        //bBox.setPrefHeight(250);
        bBox.setPrefTileWidth(200); bBox.setMaxWidth(450);

        String[] availableBoardFactories = PlayerFactories.availableBoardFactories();
        ArrayList<String> availablePlayers = new ArrayList<>();

        for(String boardFactory : availableBoardFactories){
            GameFactory gF = (GameFactory) choicesMade.get("gF");
            PlayerFactory pF = PlayerFactories.getBoardFactory(boardFactory);
            if(pF.canPlay(gF) != PlayerFactory.Play.NO) availablePlayers.add(boardFactory);
        }
        availablePlayers.add("Player GUI");


        for (String playerName : availablePlayers) {

            VBox playerBox = new VBox();
            playerBox.setAlignment(Pos.CENTER);
            playerBox.setSpacing(3);
            Rectangle rect = new Rectangle(95, 95);

            String translatedPlayerName = Configuration.getTranslation(playerName);
            Text playerText = new Text(translatedPlayerName.equals(playerName) ? playerName : translatedPlayerName);

            String playerPicture = (playerName.equals("Optimal Player") || playerName.equals("Random Player") || playerName.equals("Monte-Carlo Tree Search Player") || playerName.equals("Player GUI")) ? playerName.replaceAll("\\s", "") + ".png" : "UnknownPlayer.png";

            Image playerImage = new Image(getClass().getResource("/Players/" + playerPicture).toExternalForm());
            ImagePattern playerPattern = new ImagePattern(playerImage);
            rect.setFill(playerPattern);

            playerBox.getChildren().addAll(playerText, rect);
            playerBox.setOnMouseClicked(click -> {
                if (playerName.equals("Player GUI")) {
                    playersPlay[index] = PlayerFactory.Play.YES;
                    teaPlayers.get(index).setFirst(null);
                    if (players.size() > index) players.set(index, null);
                    else players.add(null);
                }
                else {
                    PlayerFactory pF = PlayerFactories.getBoardFactory(playerName);
                    playersPlay[index] = pF.canPlay(gF);
                    teaPlayers.get(index).setFirst(pF);
                    if (players.size() > index) players.set(index, pF);
                    else players.add(pF);
                }

                choicesMade.put("playersPlay" + index, playersPlay[index]);
                choicesMade.put("teaPlayer" + index, teaPlayers.get(index));

                slideFunc.accept(Main.SlideType.NEXT, false);
            });

            bBox.getChildren().add(playerBox);
        }


        Separator titleSeparator = new Separator(Orientation.HORIZONTAL);
        titleSeparator.getStyleClass().add("horizontal-separator");

        HBox body = new HBox();
        body.setFillHeight(false);
        body.setAlignment(Pos.CENTER);
        body.getChildren().addAll(bBox);
        //body.setBackground(new Background(new BackgroundFill(Color.DEEPSKYBLUE, null, null)));

        body.prefHeightProperty().bind(root.heightProperty());
        wrapper.getChildren().addAll(title, titleSeparator, body);
        return wrapper;
    }

    public double f(double x) {
        return Math.pow(1.5, 10 * (x - 1));
    }
    
    
    final Animation fadeIn = new Transition() {
        { setCycleDuration(Duration.millis(200)); }
        protected void interpolate(double frac) {
            body.setOpacity(frac);
            loadingBoxWrapper.setOpacity(frac);
        }
    };

    int currentIndex = 0;

    public Parent reset() {
        return getWindow();
    }
}
