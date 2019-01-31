package gapp.gui;
import gapp.gui.*;
import gapp.gui.MyBoards.OctagonalBoard;
import gapp.gui.TheView;
import gapp.gui.Windows.*;
import gapp.gui.Windows.BoardWindow;
import gapp.gui.Windows.ChooseGameFactoryParams;
import gapp.gui.Windows.ChoosePlayers;
import gapp.ulg.game.GameFactory;
import gapp.ulg.game.Param;
import gapp.ulg.game.board.*;
import gapp.ulg.game.util.*;
import gapp.ulg.games.GameFactories;
import gapp.ulg.play.PlayerFactories;
import javafx.animation.Animation;
import javafx.animation.*;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;

import java.util.List;
import java.util.function.*;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.lang.reflect.Array;
import java.util.*;


public class Main extends Application {

    private StackPane root;

    private double winWidth = 700, winHeight = 700;

    private ArrayList<Window> windows = new ArrayList<>();
    private ArrayList<Parent> slides = new ArrayList<>();
    private String[] choicesToMake = {"gF", "gFParams", "advancedGameSettings", "players", "players", "players", "board"};
    private int currentIndexScene = 0;

    ArrayList<String> currentTheme = null;


    private HBox prevNextWrapper = new HBox();
    private Parent disappearing = new HBox();

    public Stage primaryStage;

    public Parent opzioni;
    private Parent slideInScene, slideOutScene;

    private HashMap<String, Object> choicesMade = new HashMap();

    @Override
    public void start(Stage primaryStage) {


        Font.loadFont(getClass().getResourceAsStream("/Fonts/Comfortaa-Light.ttf"), 14);

        Font.loadFont(getClass().getResourceAsStream("/Fonts/Chronica/TTF/ChronicaPro-Bold.ttf"), 14);

        Font.loadFont(getClass().getResourceAsStream("/Fonts/Junction-bold.otf"), 14);

        currentTheme = (ArrayList<String>) Configuration.getParamCurrentValue("Tema");

        this.primaryStage = primaryStage;
        choicesMade.put("gF", null); choicesMade.put("player", null);


        root = new StackPane();
        Parent chooseGameFactory = chooseGameFactory();

        Configuration.setChoicesMade(choicesMade);

        root.getChildren().addAll(chooseGameFactory);
        slides.addAll(Arrays.asList(chooseGameFactory));

        resetPrevNextWrapper();

        root.setStyle("-fx-background-color: " + currentTheme.get(0) + ";");

        Scene scene = new Scene(root, winWidth, winHeight);
        scene.getStylesheets().add("/style.css");

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

    }

    public Parent chooseGameFactory(){
        List<HBox> gameButtons = new ArrayList<>();
        for (String gameName : GameFactories.availableBoardFactories()) {
            Text nameText = new Text(gameName);
            HBox game = new HBox();
            game.getChildren().add(nameText);
            game.setBackground(new Background(new BackgroundFill(Color.web("#e0e2e6"), new CornerRadii(2), null)));
            nameText.getStyleClass().add("game");
            game.setMinSize(150, 45);
            game.setMinSize(150, 45);
            game.setAlignment(Pos.CENTER);
            gameButtons.add(game);
            game.setOnMouseClicked(press -> {
                choicesMade.put("gF", GameFactories.getBoardFactory(gameName));
                screenSlide(SlideType.NEXT, false);
            });
        }

        TilePane bBox = new TilePane();
        bBox.setOrientation(Orientation.VERTICAL);
        bBox.setAlignment(Pos.CENTER);
        bBox.setVgap(15);
        bBox.setHgap(15);
        bBox.setMinSize(400, 600);
        bBox.setMaxSize(400, 600);
        bBox.getChildren().addAll(gameButtons);

        HBox buttonOptions = new HBox();
        buttonOptions.translateYProperty().bind(root.heightProperty().multiply(-0.4));;
        buttonOptions.translateXProperty().bind(root.widthProperty().multiply(0.4));;
        buttonOptions.setMaxWidth(20); buttonOptions.setMinWidth(20);
        buttonOptions.setMaxHeight(20); buttonOptions.setMinHeight(20);
        buttonOptions.setStyle("-fx-background-color: blue");
        buttonOptions.setOnMouseClicked(c -> {
            slideOptions(SlideType.NEXT);
        });



        HBox settingsWrapper = new HBox();
        settingsWrapper.translateYProperty().bind(root.heightProperty().multiply(-0.4));;
        settingsWrapper.translateXProperty().bind(root.widthProperty().multiply(0.4));;
        settingsWrapper.setMinSize(15, 15); settingsWrapper.setMaxSize(15, 15); settingsWrapper.setPrefSize(15, 15);
        Rectangle settings = new Rectangle(15, 15);
        Image settingsImage = new Image(getClass().getClassLoader().getResource("Settings.png").toExternalForm());
        ImagePattern settingsPattern = new ImagePattern(settingsImage);
        settings.setFill(settingsPattern);

        final RotateTransition rotate = new RotateTransition(Duration.seconds(1), settings);
        rotate.setByAngle(360);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);

        settings.setOnMouseEntered(entered -> rotate.play());
        settings.setOnMouseExited(exited -> rotate.stop());
        settings.setOnMouseClicked(c -> slideOptions(SlideType.NEXT));
        settingsWrapper.getChildren().add(settings);

        StackPane wrapper= new StackPane(bBox, settingsWrapper);
        wrapper.setAlignment(Pos.CENTER);

        return wrapper;
    }

    private HBox prevNext(){
        Parent back = createBottoneSlide(Configuration.getTranslation("Previous"), true);
        back.setOnMousePressed(press -> screenSlide(SlideType.PREVIOUS, false));

        Parent next = createBottoneSlide(Configuration.getTranslation("Next"), false);
        next.setOnMousePressed(press -> {
            if (choicesMade.get(choicesToMake[this.currentIndexScene]) == null) System.out.println("Prima di andare avanti, scegliere i parametri di gioco.");
            else{
                if(this.currentIndexScene == this.slides.size()-2){
                    screenSlide(SlideType.NEXT, true);
                }
                else screenSlide(SlideType.NEXT, false);
            }
        });
        HBox HWrapper = new HBox(250, back, next);
        HWrapper.setPrefHeight(110);
        HWrapper.setMinHeight(110);
        HWrapper.setMaxHeight(110);
        HWrapper.setAlignment(Pos.CENTER);
        return HWrapper;
    }

    public static void main(String[] args) {
        launch(args);
    }

    //SE NECESSARIO DA SPOSTARE IN UNA CLASSE A PARTE PER LE ANIMAZIONI
    public enum SlideType{
        PREVIOUS,
        NEXT
    }

    List<Supplier<gapp.gui.Window>> Windows = Arrays.asList(
            () -> {return new ChooseGameFactoryParams(choicesMade, root, prevNextWrapper, slides, this::screenSlide);},
            () -> {return new AdvancedGameSettings(choicesMade, root, prevNextWrapper, slides, this::screenSlide);},
            () -> {return new ChoosePlayers(0, choicesMade, root, prevNextWrapper, slides, this::screenSlide, this);},
            () -> {return new ChoosePlayers(1, choicesMade, root, prevNextWrapper, slides, this::screenSlide, this);},
            () -> {return new PlayersSettings(choicesMade, root, prevNextWrapper, slides, this::screenSlide, this);},
            () -> {return new BoardWindow(choicesMade, root, slides, this::screenSlide, this);}
    );

    private void screenSlide(SlideType type, boolean toFullScreen){
        if(type == SlideType.PREVIOUS && this.currentIndexScene == 0) throw new IllegalArgumentException("Impossibile tornare ancora più indietro");

        if (type.equals(SlideType.NEXT)) {
            if (currentIndexScene == 0 && slides.size() > 1) hardResetSlide(1);
            if (currentIndexScene == 0 && slides.size() > 2) hardResetSlide(2);
            if (currentIndexScene == 2 && slides.size() > 3) hardResetSlide(3);
            if (currentIndexScene == 3 && slides.size() > 4) hardResetSlide(4);
            if (currentIndexScene == 4 && slides.size() > 5) hardResetSlide(5);
            if (currentIndexScene == 5 && slides.size() > 6) hardResetSlide(6);

            if (currentIndexScene == 0 && slides.size() == 1) addSlide(0);
            if (currentIndexScene == 1 && slides.size() == 2) addSlide(1);
            if (currentIndexScene == 2 && slides.size() == 3) addSlide(2);
            if (currentIndexScene == 3 && slides.size() == 4) addSlide(3);
            if (currentIndexScene == 4 && slides.size() == 5) addSlide(4);
            if (currentIndexScene == 5 && slides.size() == 6) addSlide(5);
        }

        if (currentIndexScene == 1 && type.equals(SlideType.PREVIOUS))
            choicesMade.clear();

        Parent slideOutScene = this.slides.get(currentIndexScene);
        Parent slideInScene = this.slides.get(type == SlideType.PREVIOUS ? (currentIndexScene-1) : (currentIndexScene+1));

        if(root.getChildren().size() >= 2) root.getChildren().add(root.getChildren().size()-2, slideInScene);
        else root.getChildren().add(slideInScene);

        root.setAlignment(slideInScene, Pos.TOP_CENTER);
        double width = root.getWidth();
        double height = root.getHeight();
        boolean vertical = new Random().nextBoolean();


        final Animation slideAnim = new Transition() {
            { setCycleDuration(Duration.millis(800)); }
            protected void interpolate(double x) {
                double k = x <= 0.5? f(2*x)/2 : (2 - f(2*(1-x)))/2;
                if (vertical) {
                    double length = -height;
                    if (type == SlideType.PREVIOUS) length *= -1;
                    slideInScene.setTranslateY(length * (1 - k));
                    slideOutScene.setTranslateY(-length * k);
                } else {
                    double length = width;
                    if (type == SlideType.PREVIOUS) length *= -1;
                    slideInScene.setTranslateX(length * (1 - k));
                    slideOutScene.setTranslateX(-length * k);
                }
            }
        };
        slideAnim.setOnFinished(e -> {
            root.setMouseTransparent(false);
            slideOutScene.setTranslateX(0); slideOutScene.setTranslateY(0);
            root.getChildren().remove(slideOutScene);
        });
        slideAnim.play();
        updatePrevNext(type);
        root.setMouseTransparent(true);
        this.currentIndexScene = (type == SlideType.PREVIOUS ? (currentIndexScene-1) : (currentIndexScene+1));
    }

    public void slideOptions(SlideType type) {
        if (type == SlideType.PREVIOUS) {
            slideOutScene = opzioni;
            slideInScene = this.slides.get(currentIndexScene);
        }
        else {
            slideOutScene = this.slides.get(currentIndexScene);
            slideInScene = opzioni == null ? opzioni = new Options(choicesMade, root, this).getWindow() : opzioni;
        }

        if(root.getChildren().size() >= 2) root.getChildren().add(root.getChildren().size()-2, slideInScene);
        else root.getChildren().add(slideInScene);

        root.setAlignment(slideInScene, Pos.TOP_CENTER);
        double width = root.getWidth();
        double height = root.getHeight();
        boolean vertical = new Random().nextBoolean();

        final Animation slideAnim = new Transition() {
            { setCycleDuration(Duration.millis(800)); }
            protected void interpolate(double x) {
                double k = x <= 0.5? f(2*x)/2 : (2 - f(2*(1-x)))/2;
                if (vertical) {
                    double length = -height;
                    if (type == SlideType.PREVIOUS) length *= -1;
                    slideInScene.setTranslateY(length * (1 - k));
                    slideOutScene.setTranslateY(-length * k);
                } else {
                    double length = width;
                    if (type == SlideType.PREVIOUS) length *= -1;
                    slideInScene.setTranslateX(length * (1 - k));
                    slideOutScene.setTranslateX(-length * k);
                }
            }
        };
        slideAnim.setOnFinished(e -> {
            root.setMouseTransparent(false);
            root.getChildren().remove(slideOutScene);
            slideOutScene.setTranslateX(0); slideOutScene.setTranslateY(0);
        });
        slideAnim.play();
        root.setMouseTransparent(true);
    }

    public void disableNext(){
        ((HBox) prevNextWrapper.getChildren().get(0)).getChildren().get(1).setMouseTransparent(true);
    }

    public void enableNext(){
        ((HBox) prevNextWrapper.getChildren().get(0)).getChildren().get(1).setMouseTransparent(false);
    }

    public void updatePrevNext(SlideType type) {
        if(currentIndexScene == 0 && type.equals(SlideType.NEXT)) {
            disappearing = prevNextWrapper;
            showDisappearing(true);
        }
        else if ((currentIndexScene == 1 && type.equals(SlideType.PREVIOUS)) || (currentIndexScene == 5 && type == SlideType.NEXT)) {
            disappearing = prevNextWrapper;
            showDisappearing(false);
        }
        else if ((currentIndexScene == 2 && type.equals(SlideType.NEXT)) || (currentIndexScene == 5 && type == SlideType.PREVIOUS)) {
            disappearing = (Parent) ((HBox) (prevNextWrapper.getChildren().get(0))).getChildren().get(1);
            showDisappearing(false);
        }
        else if ((currentIndexScene == 3 && type.equals(SlideType.PREVIOUS)) || (currentIndexScene == 4 && type == SlideType.NEXT)) {
            disappearing = (Parent) ((HBox) (prevNextWrapper.getChildren().get(0))).getChildren().get(1);
            showDisappearing(true);
        }
    }

    public void showDisappearing(boolean show) {
        disappearing.setMouseTransparent(!show);
        fadeIn.setRate(show ? 1.0 : -1.0);
        fadeIn.play();
    }

    public void resetPrevNextWrapper() {
        prevNextWrapper.getChildren().clear();
        prevNextWrapper.getChildren().add(prevNext());
        prevNextWrapper.setAlignment(Pos.BOTTOM_CENTER);
        prevNextWrapper.setPickOnBounds(false);
        root.getChildren().remove(prevNextWrapper);
        root.getChildren().add(prevNextWrapper);
        root.setAlignment(prevNextWrapper, Pos.BOTTOM_CENTER);
        prevNextWrapper.setOpacity(0);
    }


    public Parent createBottoneSlide(String testo, boolean left) {
        final Text pArrow = new Text(left ? "<< " : " >>");
        pArrow.setOpacity(0);
        pArrow.setTranslateY(-1.5);
        final Text text = new Text(testo);
        text.setFill(Color.web(currentTheme.get(2)));

        StackPane stackpane = new StackPane(pArrow, text);
        stackpane.setAlignment(left? Pos.CENTER_LEFT : Pos.CENTER_RIGHT);
        HBox hbox = new HBox(stackpane);

        final Animation animation = new Transition() {
            { setCycleDuration(Duration.millis(200)); }
            protected void interpolate(double frac) {
                final int n = Math.round(20 * (float) frac);
                text.setStyle("-fx-translate-x: " + (left ? "" : "-") + n);
                stackpane.setStyle("-fx-translate-x: " + (left ? "-" : "") + n/2 + ";");
                hbox.setStyle(" -fx-background-color: #eeeeee;"
                        +  " -fx-border-color: #bbbbbb;"
                        +  " -fx-border-width: 1;"
                        +  " -fx-border-style: solid;"
                        );
            }
        };

        final Animation pArrowAnimation = new Transition() {
            { setCycleDuration(Duration.millis(200)); }
            protected void interpolate(double frac) {
                final int length = pArrow.getText().length();
                final int n = Math.round(length * (float) frac);
                pArrow.setOpacity(frac);
            }
        };

        hbox.setOnMouseEntered(e -> {
            pArrowAnimation.setRate(0.5);
            animation.setRate(1.0);
            animation.playFrom(animation.getCurrentTime());
            pArrowAnimation.playFrom(pArrowAnimation.getCurrentTime());
        });
        hbox.setOnMouseExited(e -> {
            pArrowAnimation.setRate(-2.0);
            animation.setRate(-1.0);
            pArrowAnimation.playFrom(pArrowAnimation.getCurrentTime());
            animation.playFrom(animation.getCurrentTime());
        });
        hbox.setAlignment(Pos.CENTER);

        hbox.setBackground(new Background(new BackgroundFill(Color.web(currentTheme.get(1)), new CornerRadii(2), null)));

        hbox.setMinSize(100, 40);

        hbox.setMaxWidth(100);
        hbox.setPrefWidth(100);
        hbox.setPrefHeight(30);
        hbox.setSpacing(20);
        VBox v = new VBox(hbox);
        v.setAlignment(Pos.CENTER);
        return v;
    }

    public void setCurrentIndexScene(int currentIndex){
        this.currentIndexScene = currentIndex;
    }

    public int getCurrentIndexScene(){
        return this.currentIndexScene;
    }

    final Animation fadeIn = new Transition() {
        { setCycleDuration(Duration.millis(400)); }
        protected void interpolate(double frac) {
            disappearing.setOpacity(frac);
        }
    };

    public double f(double x) {
        return Math.pow(1.7D, 10d * (x - 1));
    }

    public void addSlide(int i) {
        windows.add(Windows.get(i).get());
        slides.add(windows.get(i).getWindow());
    }

    public void resetSlide(int i) {
        if (i <= windows.size())
            slides.set(i, windows.get(i - 1).reset());
    }

    public void hardResetSlide(int i) {
        windows.set(i - 1, Windows.get(i - 1).get());
        slides.set(i, windows.get(i - 1).getWindow());
    }

    public void resetGUI() {
        resetPrevNextWrapper();
        for (int i = 0; i < windows.size(); i++) resetSlide(i + 1);
    }

    public void mainMenu() {
        slides.clear();
        windows.clear();
        setCurrentIndexScene(0);

        Parent chooseGameFactory = chooseGameFactory();

        root.getChildren().clear();
        root.getChildren().addAll(chooseGameFactory);
        resetPrevNextWrapper();
        slides.addAll(Arrays.asList(chooseGameFactory));
    }

}
