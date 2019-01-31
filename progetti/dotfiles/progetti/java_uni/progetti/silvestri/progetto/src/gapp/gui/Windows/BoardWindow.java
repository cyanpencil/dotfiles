package gapp.gui.Windows;

import gapp.gui.*;
import gapp.gui.MyBoards.OctagonalBoard;
import gapp.ulg.game.GameFactory;
import gapp.ulg.game.Param;
import gapp.ulg.game.PlayerFactory;
import gapp.ulg.game.board.Board;
import gapp.ulg.game.board.GameRuler;
import gapp.ulg.game.board.PieceModel;
import gapp.ulg.game.util.PlayGUI;
import gapp.ulg.Utilities.MyCouple;
import javafx.animation.Animation;
import javafx.animation.*;
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
import javafx.util.Duration;

import java.nio.file.Path;
import java.io.File;
import java.util.*;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;

/**
 * Created by Edoardo on 24/08/2016.
 */
public class BoardWindow implements Window {


    private final Main windowsManager;

    private final HashMap<String, Object> choicesMade;
    private final GameFactory gF;
    private List<MyCouple<PlayerFactory, String>> playerChoosen;
    private GameRuler matchForBoard;
    private int timeout;

    private final StackPane root;
    private final ArrayList<Parent> windowsList;
    private final BiConsumer<Main.SlideType, Boolean> slideFunc;
    private Thread computingThread;


    PlayGUI playGUI;

    StackPane subWrapper = new StackPane();
    OctagonalBoard<PieceModel<PieceModel.Species>> bG = null;
    StackPane boardPane;

    Parent h1C;
    Parent v1C;
    Parent h2C;
    Parent v2C;

    StackPane firstPlayer;
    HBox scoreWrapperFirst = null;
    HBox firstTimerBox = new HBox();
    Text fscLabel = new Text();
    Text scorePlayer1 = new Text("0");
    MyTimer firstPlayerTimer = new MyTimer();
    Text fpName;
    Text sscLabel = new Text();


    StackPane secondPlayer;
    HBox scoreWrapperSecondPlayer = null;
    Text scorePlayer2 = new Text("0");
    HBox secondTimerBox = new HBox();
    MyTimer secondPlayerTimer = new MyTimer();
    Text spName;


    // Variabili grafiche
    int boardWrapperWidth = 0;
    int boardWrapperHeight = 0;
    int coordinatesSize = 20;
    int playerBoxHeight = 50;
    int coordinatesSpacing = 5;
    int sideLength;

    private MasterChef master;
    private List<Integer> advancedGameSettings ;

    private final AtomicBoolean isOver = new AtomicBoolean(false);


    public BoardWindow(HashMap<String, Object> choicesMade, StackPane root, ArrayList<Parent> windowsList, BiConsumer<Main.SlideType, Boolean> slideFunc, Main windowsManager){
        this.choicesMade = choicesMade;
        this.gF = (GameFactory) choicesMade.get("gF");
        this.playerChoosen = Arrays.asList((MyCouple<PlayerFactory, String>) choicesMade.get("teaPlayer0"), (MyCouple<PlayerFactory, String>) choicesMade.get("teaPlayer1"));
        this.root = root;
        this.windowsList = windowsList;
        this.slideFunc = slideFunc;
        this.windowsManager = windowsManager;

        this.master = new MasterChef();

        GameFactory gForBoard = (GameFactory)choicesMade.get("gF");
        gForBoard.setPlayerNames("Edoardo", "Luca");
        matchForBoard = (GameRuler) gForBoard.newGame();
        Board<PieceModel<PieceModel.Species>> b = matchForBoard.getBoard();
        float maxBoardSize = Math.max(b.height(), b.width());
        sideLength = (b.height() == 3 && b.width() == 3) ? 50 : (int) Math.round((65f / 100f) * (root.getWidth() / maxBoardSize));
        boardWrapperWidth = coordinatesSize*2 + sideLength*b.width() + coordinatesSpacing;
        boardWrapperHeight = 40 + (sideLength * b.height()) + coordinatesSpacing;
        if(bG == null) bG = new OctagonalBoard<PieceModel<PieceModel.Species>>(b, sideLength, 0);
        bG.setMaster(master);
        master.setBoard(bG);

        if(boardPane == null) boardPane = new StackPane(bG.drawBOARD(), bG.drawPieces(), bG.drawCirclesPane());
    }

    public Parent getWindow(){
        Parent p = createWindow();
        createPlayGUI();
        return p;
    }

    public Parent createWindow() {
        if(firstPlayer != null) firstPlayer.getChildren().clear();
        if(scoreWrapperFirst != null) scoreWrapperFirst.getChildren().clear();
        if(secondPlayer != null) secondPlayer.getChildren().clear();
        if(scoreWrapperSecondPlayer != null) scoreWrapperSecondPlayer.getChildren().clear();
        advancedGameSettings = (ArrayList<Integer>) choicesMade.get("advancedGameSettings");

        timeout = (int) Math.round(TO_MS.get(TIMES.indexOf(((Param<Integer>) (gF.params().get(0))).get())));
        advancedGameSettings = (ArrayList<Integer>) choicesMade.get("advancedGameSettings");
        // -------------------------------------> Parametri partita impostati <-----------------------------

        VBox wrapper = new VBox();
        wrapper.maxHeightProperty().bind(root.heightProperty()); wrapper.minHeightProperty().bind(root.heightProperty()); wrapper.prefHeightProperty().bind(root.heightProperty());
        wrapper.minWidthProperty().bind(root.widthProperty()); wrapper.maxWidthProperty().bind(root.widthProperty()); wrapper.prefWidthProperty().bind(root.widthProperty());
        wrapper.setAlignment(Pos.CENTER);

        subWrapper = new StackPane();
        subWrapper.maxHeightProperty().setValue(boardWrapperHeight+playerBoxHeight*2); subWrapper.minHeightProperty().setValue(boardWrapperHeight+playerBoxHeight*2); subWrapper.prefHeightProperty().setValue(boardWrapperHeight+playerBoxHeight*2);
        subWrapper.minWidthProperty().bind(root.widthProperty().multiply(0.8)); subWrapper.maxWidthProperty().bind(root.widthProperty().multiply(0.8)); subWrapper.prefWidthProperty().bind(root.widthProperty().multiply(0.8));


        // --------------------------------------> Top menu <--------------------------------------
        HBox topMenu = new HBox();
        topMenu.setAlignment(Pos.CENTER_RIGHT);
        topMenu.setMinHeight(15); topMenu.setMaxHeight(15); topMenu.setPrefHeight(15);
        topMenu.setMinWidth(700); topMenu.setMaxWidth(700); topMenu.setPrefWidth(700);
        topMenu.setSpacing(20);

        Text menu = new Text(Configuration.getTranslation("Main Menu"));
        menu.setOnMouseClicked(click -> {
                isOver.set(false);
                windowsManager.mainMenu();
        });
        Text newGame = new Text(Configuration.getTranslation("New Game"));
        newGame.setOnMouseClicked(click -> {
            if(isOver.get()){
                startNewGame();
                return;
            }
            String title = Configuration.getTranslation("Confirm new game");
            String prompt = Configuration.getTranslation("The current match is going to be terminated. Continue?");
            bG.sendDialog(new DialogBox(title, prompt, (confirm) -> {
                bG.clearDialog();
                if (confirm == 1) return;
                startNewGame();
            }));
        });
        Text resign = new Text(Configuration.getTranslation("Resign"));
        resign.setOnMouseClicked(requestResign -> {
            if(isOver.get()) return;
            if (playerChoosen.get(turn - 1).getFirst() != null) return;
            String prompt = playerChoosen.get(turn - 1).getSecond() + " " + Configuration.getTranslation("sei sicuro di volerti arrendere?");
            bG.sendDialog(new DialogBox(Configuration.getTranslation("Conferma resa"), prompt, (confirm) -> {
                bG.clearDialog();
                if (confirm == 1) return;
                master.moveChooserResign();
                isOver.set(true);
            }));
        });
        Text back = new Text(Configuration.getTranslation("Back selection"));
        back.setOnMouseClicked(clicked -> master.moveChooserBack());
        Text pass = new Text(Configuration.getTranslation("Pass"));

        topMenu.getChildren().addAll(menu, newGame, resign, back, pass);

        HBox settingsWrapper = new HBox();
        settingsWrapper.setMinHeight(15); settingsWrapper.setMaxHeight(15); settingsWrapper.setPrefHeight(15);
        Rectangle settings = new Rectangle(15, 15);
        Image settingsImage = new Image(getClass().getResource("/Settings.png").toExternalForm());
        ImagePattern settingsPattern = new ImagePattern(settingsImage);
        settings.setFill(settingsPattern);

        final RotateTransition rotate = new RotateTransition(Duration.seconds(1), settings);
        rotate.setByAngle(360);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);

        settings.setOnMouseEntered(entered -> rotate.play());
        settings.setOnMouseExited(exited -> rotate.stop());
        settings.setOnMouseClicked(c -> windowsManager.slideOptions(Main.SlideType.NEXT));
        settingsWrapper.getChildren().add(settings);

        settingsWrapper.setPadding(new Insets(0, 15, 0, 0));
        topMenu.getChildren().add(settingsWrapper);
        wrapper.setPickOnBounds(true);
        subWrapper.setPickOnBounds(false);
        topMenu.setPickOnBounds(false);
        // --------------------------------------> Top menu <--------------------------------------



        // --------------------------------------> Second Player <--------------------------------------
        secondPlayer = new StackPane();
        secondPlayer.setMinHeight(playerBoxHeight); secondPlayer.setMaxHeight(playerBoxHeight); secondPlayer.setPrefHeight(playerBoxHeight);
        secondPlayer.minWidthProperty().bind(subWrapper.widthProperty()); secondPlayer.maxWidthProperty().bind(subWrapper.widthProperty()); secondPlayer.prefWidthProperty().bind(subWrapper.widthProperty());
        secondPlayer.setPadding(new Insets(0, 0, 10, 0));

        StackPane nameScoreBoxSecondPlayer = new StackPane();
        nameScoreBoxSecondPlayer.setAlignment(Pos.BOTTOM_LEFT);
        nameScoreBoxSecondPlayer.setMinHeight(40); nameScoreBoxSecondPlayer.setMaxHeight(40); nameScoreBoxSecondPlayer.setPrefHeight(40);
        nameScoreBoxSecondPlayer.minWidthProperty().bind(secondPlayer.widthProperty().multiply(0.8)); nameScoreBoxSecondPlayer.maxWidthProperty().bind(secondPlayer.widthProperty().multiply(0.8)); nameScoreBoxSecondPlayer.prefWidthProperty().bind(secondPlayer.widthProperty().multiply(0.8));
        spName = new Text(playerChoosen.get(1).getSecond());
        spName.getStyleClass().add("playerNameActive");
        nameScoreBoxSecondPlayer.getChildren().addAll(spName);
        nameScoreBoxSecondPlayer.setAlignment(spName, Pos.BOTTOM_LEFT);

        scoreWrapperSecondPlayer = null;
        try{
            double scoreFirst = matchForBoard.score(1);
            double scoreSecond = matchForBoard.score(2);
            scoreWrapperSecondPlayer = new HBox();
            scoreWrapperSecondPlayer.setAlignment(Pos.BOTTOM_RIGHT);
            sscLabel = new Text(Configuration.getTranslation("Score") + ": ");
            scorePlayer2.setText("" + (int) scoreSecond);

            sscLabel.setStyle((String) Configuration.getParamCurrentValue("Titolo"));
            scorePlayer2.setStyle((String) Configuration.getParamCurrentValue("Titolo"));
            scoreWrapperSecondPlayer.getChildren().addAll(sscLabel, scorePlayer2);
            scoreWrapperSecondPlayer.setMaxWidth(100);

        } catch (UnsupportedOperationException e) {}

        secondPlayer.getChildren().add(nameScoreBoxSecondPlayer);
        secondPlayer.setAlignment(nameScoreBoxSecondPlayer, Pos.CENTER_LEFT);
        if(timeout != -1) {
            secondTimerBox = new HBox();
            secondTimerBox.setAlignment(Pos.CENTER_RIGHT);
            secondTimerBox.setMinHeight(40); secondTimerBox.setMaxHeight(40); secondTimerBox.setPrefHeight(40);
            secondTimerBox.setMinWidth(boardWrapperWidth*0.5); secondTimerBox.setMaxWidth(boardWrapperWidth*0.5); secondTimerBox.setPrefWidth(boardWrapperWidth*0.5);
            secondPlayerTimer.setDurationTime(timeout);
            StackPane timer = secondPlayerTimer.getTimer();
            timer.setAlignment(Pos.CENTER_LEFT);
            timer.setPrefHeight(40); timer.setMaxHeight(40); timer.setMinHeight(40);
            timer.setPrefWidth(100); timer.setMinWidth(100); timer.setPrefWidth(100);
            secondTimerBox.getChildren().add(timer);

            if(scoreWrapperSecondPlayer != null) {
                nameScoreBoxSecondPlayer.getChildren().add(scoreWrapperSecondPlayer);
                nameScoreBoxSecondPlayer.setAlignment(scoreWrapperSecondPlayer, Pos.BOTTOM_RIGHT);
            }
            secondPlayer.getChildren().add(secondTimerBox);
            secondPlayer.setAlignment(secondTimerBox, Pos.CENTER_RIGHT);
        }
        else{
            if(scoreWrapperSecondPlayer != null) {
                secondPlayer.getChildren().add(scoreWrapperSecondPlayer);
                secondPlayer.setAlignment(scoreWrapperSecondPlayer, Pos.BOTTOM_RIGHT);
            }
        }

        Separator secondPlayerSeparator = new Separator(Orientation.HORIZONTAL);
        secondPlayerSeparator.getStyleClass().add("horizontal-separator-total");

        secondPlayer.getChildren().add(secondPlayerSeparator);
        secondPlayer.setAlignment(secondPlayerSeparator, Pos.BOTTOM_CENTER);
        // ------------------------> Fine Player 2 <----------------------------



        // ------------------------> Inizio Board <-----------------------------
        StackPane boardWrapper = new StackPane();
        boardWrapper.setMaxHeight(boardWrapperHeight); boardWrapper.setPrefHeight(boardWrapperHeight); boardWrapper.setMinHeight(boardWrapperHeight);
        boardWrapper.setMaxWidth(boardWrapperWidth);
        boardWrapper.setBackground(new Background(new BackgroundFill(Color.web("9D6648"), null, null)));

        h1C = getCoordinatesBox(Coordinates.RIGHT, sideLength);
        v1C = getCoordinatesBox(Coordinates.UP, sideLength);

        h2C = getCoordinatesBox(Coordinates.LEFT, sideLength);
        v2C = getCoordinatesBox(Coordinates.DOWN, sideLength);

        boolean showCoordinates = (boolean) Configuration.getParamCurrentValue("Coordinates");
        if (showCoordinates) {
            boardWrapper.getChildren().addAll(h1C, v1C, h2C, v2C);
            boardWrapper.setAlignment(h1C, Pos.BOTTOM_CENTER);
            boardWrapper.setAlignment(v1C, Pos.CENTER_LEFT);
            boardWrapper.setAlignment(h2C, Pos.TOP_CENTER);
            boardWrapper.setAlignment(v2C, Pos.CENTER_RIGHT);
        }
        // ------------------------> Fine Board <-----------------------------

        // --------------------------------------> First Player <--------------------------------------
        firstPlayer = new StackPane();
        firstPlayer.setMinHeight(40); firstPlayer.setMaxHeight(40); firstPlayer.setPrefHeight(40);
        firstPlayer.minWidthProperty().bind(subWrapper.widthProperty()); firstPlayer.maxWidthProperty().bind(subWrapper.widthProperty()); firstPlayer.prefWidthProperty().bind(subWrapper.widthProperty());


        StackPane nameScoreBox = new StackPane();
        nameScoreBox.setAlignment(Pos.CENTER_LEFT);
        nameScoreBox.setMinHeight(40); nameScoreBox.setMaxHeight(40); nameScoreBox.setPrefHeight(40);
        nameScoreBox.minWidthProperty().bind(firstPlayer.widthProperty().multiply(0.8)); nameScoreBox.maxWidthProperty().bind(firstPlayer.widthProperty().multiply(0.8)); nameScoreBox.prefWidthProperty().bind(firstPlayer.widthProperty().multiply(0.8));
        fpName = new Text(playerChoosen.get(0).getSecond());
        fpName.getStyleClass().add("playerNameActive");
        nameScoreBox.getChildren().addAll(fpName);
        nameScoreBox.setAlignment(fpName, Pos.CENTER_LEFT);

        scoreWrapperFirst = null;
        try{
            double scoreFirst = matchForBoard.score(1);
            double scoreSecond = matchForBoard.score(2);
            scoreWrapperFirst = new HBox();
            scoreWrapperFirst.setAlignment(Pos.CENTER_RIGHT);
            fscLabel = new Text(Configuration.getTranslation("Score") + ": ");
            fscLabel.setOpacity(1);
            sscLabel.setOpacity(0.5);
            scorePlayer1.setText("" + (int) scoreFirst);

            fscLabel.setStyle((String) Configuration.getParamCurrentValue("Titolo"));
            scorePlayer1.setStyle((String) Configuration.getParamCurrentValue("Titolo"));
            scoreWrapperFirst.getChildren().addAll(fscLabel, scorePlayer1);
            scoreWrapperFirst.setMaxWidth(100);
        } catch (UnsupportedOperationException e) {}


        firstPlayer.getChildren().addAll(nameScoreBox);
        firstPlayer.setAlignment(nameScoreBox, Pos.CENTER_LEFT);

        if(timeout != -1) {
            firstTimerBox = new HBox();
            firstTimerBox.setAlignment(Pos.CENTER_RIGHT);
            firstTimerBox.setMinHeight(40); nameScoreBox.setMaxHeight(40); firstTimerBox.setPrefHeight(40);
            firstTimerBox.setMinWidth(boardWrapperWidth*0.5); firstTimerBox.setMaxWidth(boardWrapperWidth*0.5); firstTimerBox.setPrefWidth(boardWrapperWidth*0.5);
            StackPane timer = firstPlayerTimer.getTimer();
            timer.setAlignment(Pos.CENTER_LEFT);
            firstPlayerTimer.setDurationTime(timeout);
            firstPlayerTimer.start();
            timer.setPrefHeight(40); timer.setMaxHeight(40); timer.setMinHeight(40);
            timer.setPrefWidth(100); timer.setMinWidth(100); timer.setPrefWidth(100);
            firstTimerBox.getChildren().add(timer);
            if(scoreWrapperFirst != null){
                nameScoreBox.getChildren().add(scoreWrapperFirst);
                nameScoreBox.setAlignment(scoreWrapperFirst, Pos.CENTER_RIGHT);
            }
            firstPlayer.getChildren().add(firstTimerBox);
            firstPlayer.setAlignment(firstTimerBox, Pos.CENTER_RIGHT);
        }
        else{
            if(scoreWrapperFirst != null){
                firstPlayer.getChildren().add(scoreWrapperFirst);
                firstPlayer.setAlignment(scoreWrapperFirst, Pos.CENTER_RIGHT);
            }
        }

        Separator firstPlayerSeparator = new Separator(Orientation.HORIZONTAL);
        firstPlayerSeparator.getStyleClass().add("horizontal-separator-total");


        firstPlayer.getChildren().add(firstPlayerSeparator);
        firstPlayer.setAlignment(firstPlayerSeparator, Pos.TOP_CENTER);
        // ------------------------> Fine Player 1 <----------------------------

        boardWrapper.getChildren().add(boardPane);
        subWrapper.getChildren().addAll(secondPlayer, boardWrapper, firstPlayer);
        subWrapper.setAlignment(secondPlayer, Pos.TOP_CENTER);
        subWrapper.setAlignment(boardWrapper, Pos.CENTER);
        subWrapper.setAlignment(firstPlayer, Pos.BOTTOM_CENTER);
        wrapper.setSpacing(20);
        wrapper.getChildren().addAll(topMenu, subWrapper);

        return wrapper;
    }

    public void createPlayGUI() {
        advancedGameSettings = (ArrayList<Integer>) choicesMade.get("advancedGameSettings");
        playerChoosen = Arrays.asList((MyCouple<PlayerFactory, String>) choicesMade.get("teaPlayer0"), (MyCouple<PlayerFactory, String>) choicesMade.get("teaPlayer1"));

        BigBrother observer = new BigBrother(new TheView(boardPane, bG, this));
        playGUI = new PlayGUI(observer, advancedGameSettings.get(0));

        startNewGame();
    }

    public enum Coordinates{
        UP,
        DOWN,
        LEFT,
        RIGHT,
    }


    private Parent getCoordinatesBox(Coordinates orientation, int size) {

        Color coordinatesColor = Color.web("9D6648");

        if(orientation.equals(Coordinates.RIGHT) || orientation.equals(Coordinates.LEFT)){
            HBox hCoords = new HBox();
            int w = matchForBoard.getBoard().width();
            hCoords.setMaxHeight(coordinatesSize); hCoords.setMinHeight(coordinatesSize); hCoords.setPrefHeight(coordinatesSize);
            hCoords.setMaxWidth(size*w+40+coordinatesSpacing); hCoords.setMinWidth(size*w+40+coordinatesSpacing); hCoords.setPrefWidth(size*w+40+coordinatesSpacing);
            hCoords.setBackground(new Background(new BackgroundFill(coordinatesColor, null, null)));
            hCoords.setAlignment(Pos.CENTER);

            for(int i = 0; i < w; i++){
                String letter = letters[i];
                HBox letterBox = new HBox();
                letterBox.setMaxHeight(10); letterBox.setMinHeight(10); letterBox.setPrefHeight(10);
                letterBox.setMaxWidth(size); letterBox.setMinWidth(size); letterBox.setPrefWidth(size);

                letterBox.setAlignment(Pos.CENTER);
                if(orientation.equals(Coordinates.LEFT)) letterBox.setPadding(new Insets(5, 0, 0, 0));
                Text letterText = new Text(letter);
                letterText.getStyleClass().add("coordsText");
                letterBox.getChildren().add(letterText);
                hCoords.getChildren().add(letterBox);
            }
            return hCoords;
        }

        else{
            VBox vCoords = new VBox();
            vCoords.setAlignment(Pos.CENTER);
            vCoords.setBackground(new Background(new BackgroundFill(coordinatesColor, null, null)));
            int h = matchForBoard.getBoard().height();
            vCoords.setMaxHeight(size*h+1 + coordinatesSpacing); vCoords.setMinHeight(size*h+1+coordinatesSpacing); vCoords.setPrefHeight(size*h+1+coordinatesSpacing);
            vCoords.setMaxWidth(coordinatesSize); vCoords.setMinWidth(coordinatesSize); vCoords.setPrefWidth(coordinatesSize);

            for(int i = h; i > 0; i--){
                String letter = new Integer(i).toString();
                HBox letterBox = new HBox();

                letterBox.setAlignment(Pos.CENTER);
                letterBox.setMaxHeight(size); letterBox.setMinHeight(size); letterBox.setPrefHeight(size);
                letterBox.setMaxWidth(10); letterBox.setMinWidth(10); letterBox.setPrefWidth(10);
                Text letterText = new Text(letter);

                letterText.getStyleClass().add("coordsText");
                letterBox.getChildren().add(letterText);
                vCoords.getChildren().add(letterBox);
            }
            return vCoords;
        }
    }

    private final static String[] letters =  {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "N", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    int turn = 0;
    boolean first = true;
    int i = 0;
    public void changeTurn(int playerTurn, StackPane piecesPane, ArrayList<Double> scores){
        Parent toShow = playerTurn == 1 ? firstTimerBox : secondTimerBox;
        Parent toHide = playerTurn == 1 ? secondTimerBox : firstTimerBox;
        final Transition slideTimer = new Transition() {
            {setCycleDuration(new Duration(200));}
            public void interpolate(double frac) {
                toShow.setOpacity(frac);
                toHide.setOpacity(1 - frac);
            }
        };
        slideTimer.play();
        first = false;
        turn = playerTurn;
        if(scores != null){
            scorePlayer1.setText("" + (int) Math.round(scores.get(0)));
            scorePlayer2.setText("" + (int) Math.round(scores.get(1)));
        }
        if(playerTurn == 1){
            fpName.setOpacity(1);
            scorePlayer1.setOpacity(1);
            fscLabel.setOpacity(1);
            spName.setOpacity(0.5);
            scorePlayer2.setOpacity(0.5);
            sscLabel.setOpacity(0.5);
        }
        else{
            spName.setOpacity(1);
            scorePlayer2.setOpacity(1);
            sscLabel.setOpacity(1);
            fscLabel.setOpacity(0.5);
            fpName.setOpacity(0.5);
            scorePlayer1.setOpacity(0.5);

        }

        if(timeout == -1) return;
        if(playerTurn == 1){
            firstPlayerTimer.setDurationTime(timeout);
            secondPlayerTimer.stop();
            firstPlayerTimer.start();
            return;
        }
        secondPlayerTimer.setDurationTime(timeout);
        firstPlayerTimer.stop();
        secondPlayerTimer.start();
    }

    private void startNewGame() {

        bG.setBoard(matchForBoard.getBoard());
        master.setBoard(bG);

        if(isOver.get()){

            fpName.getStyleClass().clear();
            spName.getStyleClass().clear();
            fpName.getStyleClass().add("playerNameActive");
            spName.getStyleClass().add("playerNameActive");

            if(timeout == -1 && scoreWrapperFirst == null){
                if(firstPlayer.getChildren().size() == 3) firstPlayer.getChildren().remove(firstPlayer.getChildren().size()-1);
                if(secondPlayer.getChildren().size() == 3) secondPlayer.getChildren().remove(secondPlayer.getChildren().size()-1);
            }
            else if(timeout != -1){
                if(firstPlayer.getChildren().size() == 3) firstPlayer.getChildren().remove(firstPlayer.getChildren().size()-1);
                firstPlayer.getChildren().add(firstPlayer.getChildren().size()-1, firstTimerBox );   // Timer aggiunto
                StackPane firstPlayerNameScoreBox = (StackPane) firstPlayer.getChildren().get(0);
                if(scoreWrapperFirst != null) firstPlayerNameScoreBox.getChildren().add(scoreWrapperFirst); // Score rimosso
                firstPlayerNameScoreBox.setAlignment(scoreWrapperFirst, Pos.CENTER_RIGHT);

                if(secondPlayer.getChildren().size() == 3) secondPlayer.getChildren().remove(secondPlayer.getChildren().size()-1);
                secondPlayer.getChildren().add(secondPlayer.getChildren().size()-1, secondTimerBox );   // Timer aggiunto
                StackPane secondPlayerNameScoreBox = (StackPane) secondPlayer.getChildren().get(0);
                if(scoreWrapperFirst != null) secondPlayerNameScoreBox.getChildren().add(scoreWrapperSecondPlayer); // Score rimosso
                secondPlayerNameScoreBox.setAlignment(scoreWrapperSecondPlayer, Pos.CENTER_RIGHT);
            }
            else{
                if(firstPlayer.getChildren().size() == 3) firstPlayer.getChildren().remove(firstPlayer.getChildren().size()-1);
                firstPlayer.getChildren().add(firstPlayer.getChildren().size()-1,  scoreWrapperFirst);
                firstPlayer.setAlignment(scoreWrapperFirst, Pos.CENTER_RIGHT);

                if(secondPlayer.getChildren().size() == 3) secondPlayer.getChildren().remove(secondPlayer.getChildren().size()-1);
                secondPlayer.getChildren().add(secondPlayer.getChildren().size()-1,  scoreWrapperSecondPlayer);
                secondPlayer.setAlignment(scoreWrapperSecondPlayer, Pos.CENTER_RIGHT);
            }

        }
        boardPane.setMouseTransparent(false);
        playGUI.stop();
        playGUI.setGameFactory(((GameFactory) choicesMade.get("gF")).name());
        for(Param param : (ArrayList<Param>) choicesMade.get("gFParams")){
            playGUI.setGameFactoryParamValue(param.name(), param.get());
        }


        Path p = choicesMade.get("dir") == null ? null : (Path) choicesMade.get("dir");

        if (playerChoosen.get(0).getFirst() != null) playGUI.setPlayerFactory(1, playerChoosen.get(0).getFirst().name(), playerChoosen.get(0).getSecond(), p);
        else playGUI.setPlayerGUI(1, playerChoosen.get(0).getSecond(), master);

        if (playerChoosen.get(1).getFirst() != null) playGUI.setPlayerFactory(2, playerChoosen.get(1).getFirst().name(), playerChoosen.get(1).getSecond(), p);
        else playGUI.setPlayerGUI(2, playerChoosen.get(1).getSecond(), master);

        playGUI.play(advancedGameSettings.get(1), advancedGameSettings.get(2), advancedGameSettings.get(3), advancedGameSettings.get(4), advancedGameSettings.get(5), advancedGameSettings.get(6));
        isOver.set(false);
    }

    private static final List<String> TIMES = Arrays.asList("No limit","1s","2s","3s","5s","10s","20s","30s","1m","2m","5m");
    private static final List<Long> TO_MS = Arrays.asList(-1L,1000L,2000L,3000L,5000L,10000L,20000L,30000L,60000L,120000L,300000L);

    public void notifyOver(int winnerIndex){
        this.isOver.set(true);


        boardPane.setMouseTransparent(true);

        fpName.setOpacity(1);
        scorePlayer1.setOpacity(1);
        fscLabel.setOpacity(1);
        spName.setOpacity(1);
        scorePlayer2.setOpacity(1);
        sscLabel.setOpacity(1);
        if(winnerIndex == 0) return;


        Text winner = new Text(Configuration.getTranslation("WINNER"));
        winner.getStyleClass().add("winner");


        if(timeout == -1 && scoreWrapperFirst == null){}
        else if(timeout != -1){
            firstPlayer.getChildren().remove(firstPlayer.getChildren().size()-2);
            StackPane firstPlayerNameScoreBox = (StackPane) firstPlayer.getChildren().get(0);
            if(firstPlayerNameScoreBox.getChildren().size() == 2) firstPlayerNameScoreBox.getChildren().remove(firstPlayerNameScoreBox.getChildren().size()-1);

            secondPlayer.getChildren().remove(secondPlayer.getChildren().size()-2);
            StackPane secondPlayerNameScoreBox = (StackPane) secondPlayer.getChildren().get(0);
            if(secondPlayerNameScoreBox.getChildren().size() == 2) secondPlayerNameScoreBox.getChildren().remove(secondPlayerNameScoreBox.getChildren().size()-1);

        }
        else{
            firstPlayer.getChildren().remove(firstPlayer.getChildren().size()-2);
            secondPlayer.getChildren().remove(secondPlayer.getChildren().size()-2);

        }

        if(winnerIndex == 1) {
            if(timeout != -1) secondPlayerTimer.stop();
            firstPlayer.getChildren().add(winner);
            firstPlayer.setAlignment(winner, Pos.CENTER_RIGHT);
            fpName.getStyleClass().clear();
            fpName.getStyleClass().add("winner");
        }
        else {
            if(timeout != -1) firstPlayerTimer.stop();
            secondPlayer.getChildren().add(winner);
            secondPlayer.setAlignment(winner, Pos.CENTER_RIGHT);
            spName.getStyleClass().clear();
            spName.getStyleClass().add("winner");
        }

        if(timeout == -1) return;
        secondPlayerTimer.stop();
        firstPlayerTimer.stop();
        return;

    }

    public Parent reset() {
        return createWindow();
    }
}


