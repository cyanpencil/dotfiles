package gapp.gui.Windows;

import gapp.gui.*;
import gapp.gui.Configuration;
import gapp.ulg.game.GameFactory;
import gapp.ulg.game.Param;
import gapp.ulg.game.PlayerFactory;
import gapp.ulg.Utilities.interruptStrategy;
import gapp.ulg.Utilities.MyCouple;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.stage.DirectoryChooser;
import javafx.util.Duration;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;

public class PlayersSettings implements Window {

    private final HashMap<String, Object> choicesMade;
    private final GameFactory gF;
    private final StackPane root;
    private Thread computingThread;
    private ArrayList<MyCouple<PlayerFactory, String>> teaPlayers = new ArrayList<>(Arrays.asList(new MyCouple<>(null, "Player 1"), new MyCouple<>(null, "Player 2 ")));

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

    int titleBoxHeight = 100;
    int playerSettingsWidth = 600;

    private HBox body;

    public PlayersSettings(HashMap<String, Object> choicesMade, StackPane root, HBox prevNext, ArrayList<Parent> windowsList, BiConsumer<Main.SlideType, Boolean> slideFunc, Main windowsManager){
        this.choicesMade = choicesMade;
        this.gF = (GameFactory) choicesMade.get("gF");
        this.root = root;
        this.windowsManager = windowsManager;

    }

    public Parent getWindow() {
        return playersWindow();
    }

    private Parent playersWindow(){
        playerSettingss[0] = null;
        playerSettingss[1] = null;
        loadingBox1 = new HBox();
        loadingBox2 = new HBox();
        loadingBoxWrapper = new HBox();
        wrapper = new VBox();
        wrapper2 = new VBox();
        paramText = new Text("");
        playersPlay[0] = (PlayerFactory.Play) choicesMade.get("playersPlay0");
        playersPlay[1] = (PlayerFactory.Play) choicesMade.get("playersPlay1");
        teaPlayers.set(0, (MyCouple<PlayerFactory, String>) choicesMade.get("teaPlayer0"));
        teaPlayers.set(1, (MyCouple<PlayerFactory, String>) choicesMade.get("teaPlayer1"));

        // VBox settingsWrapper = new VBox(); // Contenitore complessivo della finestra
        settingsWrapper = new VBox();
        settingsWrapper.maxHeightProperty().bind(root.heightProperty());
        settingsWrapper.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, null, null)));
        settingsWrapper.setAlignment(Pos.TOP_CENTER);

        // -----------> Inizio titolo della finestra <-----------------
        HBox title = new HBox();
        title.setStyle("-fx-pref-height: " + titleBoxHeight + "px;");
        title.setMinHeight(titleBoxHeight);

        String pL = Configuration.getTranslation("Player").equals("Player") ? "Player" : Configuration.getTranslation("Player");
        Text player1 = new Text(pL + " 1");
        player1.setTextAlignment(TextAlignment.CENTER);
        player1.setStyle((String) Configuration.getParamCurrentValue("Titolo"));

        HBox buttonWrapper = new HBox();
        buttonWrapper.setAlignment(Pos.CENTER);
        buttonWrapper.setMinWidth(130);
        buttonWrapper.setMaxWidth(130);
        buttonWrapper.setPrefWidth(130);
        Parent buttonBox = changePlayerButton();
        buttonWrapper.getChildren().add(buttonBox);

        Text player2 = new Text(pL + " 2");
        player2.setTextAlignment(TextAlignment.CENTER);
        player2.setStyle((String) Configuration.getParamCurrentValue("Titolo"));

        title.getChildren().addAll(player1, buttonWrapper, player2);
        title.setAlignment(Pos.CENTER);
        // -----------> Fine titolo della finestra <-----------------

        body = new HBox();
        body.setPadding(new Insets(20, 0, 0, 0));


        body.setPrefHeight(300);
        body.maxHeightProperty().bind(root.heightProperty().subtract(300));
        body.minHeightProperty().bind(root.heightProperty().subtract(300));
        body.setAlignment(Pos.CENTER);
        body.setFillHeight(false);

        // ----------> Separatore Prompt <---------------
        getPlayerSettingsWindow(1);
        body.getChildren().addAll(getPlayerSettingsWindow(0));

        Separator paramPromptSeparator = new Separator(Orientation.HORIZONTAL);
        paramPromptSeparator.getStyleClass().add("horizontal-separator");
        paramPromptSeparator.setPadding(new Insets(0, 0, 10, 0));
        // ------------> Fine separatore prompt <--------------

        // ------------> Param Prompt Bottom <-------------
        HBox paramPrompt = new HBox();
        paramPrompt.setMinHeight(30);
        paramPrompt.minHeightProperty().setValue(30);
        paramPrompt.setMaxHeight(30);
        paramPrompt.setPrefHeight(30);
        paramPrompt.minWidthProperty().setValue(600);
        paramPrompt.maxWidth(600);
        paramPrompt.minWidth(600);
        paramPrompt.setAlignment(Pos.CENTER);
        paramPrompt.getChildren().add(paramText);
        // ------------> Fine Prompt Bottom <-------------

        settingsWrapper.getChildren().clear();
        settingsWrapper.getChildren().addAll(title, body);
        settingsWrapper.getChildren().add(loadingBoxWrapper);
        loadingBoxWrapper.setAlignment(Pos.CENTER);
        settingsWrapper.getChildren().addAll(paramPromptSeparator, paramPrompt);

        return settingsWrapper;
    }

    private HBox[] playerSettingss = new HBox[2];
    public HBox getPlayerSettingsWindow(int index) {

        currentIndex = index;
        int playerSettingsWidth = 600;

        loadingBoxWrapper.setPrefHeight(40); loadingBoxWrapper.setMinHeight(40); loadingBoxWrapper.setMaxHeight(40);
        HBox loadingBox = index == 0 ? loadingBox1 : loadingBox2;
        loadingBox.getChildren().clear();
        loadingBoxWrapper.getChildren().clear();
        getLoadingButton(index);
        if(playersPlay[index].equals(PlayerFactory.Play.TRY_COMPUTE)) loadingBoxWrapper.getChildren().add(loadingBox);

        HBox playerSettings = playerSettingss[index];
        if (playerSettingss[index] != null){
            return playerSettings;
        }
        playerSettingss[index] = new HBox();
        playerSettingss[index].setAlignment(Pos.CENTER);
        playerSettingss[index].prefHeightProperty().setValue(300);
        playerSettingss[index].maxHeightProperty().setValue(300);
        playerSettingss[index].minHeightProperty().setValue(300);
        playerSettingss[index].setPrefWidth(playerSettingsWidth); playerSettingss[index].setMinWidth(playerSettingsWidth); playerSettingss[index].setMaxWidth(playerSettingsWidth);


        VBox paramBox = new VBox();
        paramBox.setAlignment(Pos.TOP_LEFT);
        paramBox.setPrefWidth(150);
        paramBox.setMinWidth(150);
        paramBox.setMaxWidth(150);

        String nM = Configuration.getTranslation("Name").equals("Name") ? "Name" : Configuration.getTranslation("Name");
        Label nameLabel = new Label(nM + " :");


        paramText.setWrappingWidth(400);
        paramText.setTextAlignment(TextAlignment.CENTER);
        paramText.getStyleClass().add("description");

        HBox paramWrapper = new HBox(nameLabel);
        paramWrapper.setPrefHeight(50);
        paramWrapper.setMinHeight(50);
        paramWrapper.setMaxHeight(50);
        paramWrapper.setAlignment(Pos.CENTER_RIGHT);
        paramWrapper.setPadding(new Insets(0, 10, 0, 0));

        paramWrapper.setOnMouseEntered(entered -> {
            paramText.textProperty().setValue("Nome del " + (index == 0 ? "primo" : "secondo") + " giocatore");
        });
        paramWrapper.setOnMouseExited(exited -> {
            paramText.setText("");
        });

        Separator provaSeparatoreMetaSinistra = new Separator(Orientation.HORIZONTAL);
        provaSeparatoreMetaSinistra.getStyleClass().add("horizontal-separator-middleleft");

        paramBox.getChildren().addAll(paramWrapper, provaSeparatoreMetaSinistra);

        VBox valuesBox = new VBox();
        valuesBox.setAlignment(Pos.TOP_RIGHT);
        valuesBox.setMinWidth(300);
        valuesBox.setMaxWidth(300);
        valuesBox.setPrefWidth(300);


        String playerNameInput = Configuration.getTranslation("Player").equals("Player") ? "Player" : Configuration.getTranslation("Player");
        TextField nameInput = new TextField (playerNameInput + " " + (int)(index+1)) {
            @Override public void replaceText(int start, int end, String text) {
                if (getText().concat(text).length() < 18) {
                    super.replaceText(start, end, text);
                    teaPlayers.get(index).setSecond(this.getText());
                }
            }
            @Override public void replaceSelection(String text) {
                if (getText().concat(text).length() < 18) {
                    super.replaceSelection(text);
                    teaPlayers.get(index).setSecond(this.getText());
                }
            }
        };
        nameInput.setMinWidth(200); nameInput.setPrefWidth(200); nameInput.setMaxWidth(200);
        HBox valueWrapper = new HBox(nameInput);
        valueWrapper.setPrefHeight(50); valueWrapper.setMinHeight(50); valueWrapper.setMaxHeight(50);
        valueWrapper.setAlignment(Pos.CENTER_LEFT);
        valueWrapper.setPadding(new Insets(0, 0, 0, 30));


        Separator provaSeparatoreMetaDestra = new Separator(Orientation.HORIZONTAL);
        provaSeparatoreMetaDestra.getStyleClass().add("horizontal-separator-middleright");

        valuesBox.getChildren().addAll(valueWrapper, provaSeparatoreMetaDestra);

        if(playersPlay[index].equals(PlayerFactory.Play.TRY_COMPUTE)){
            windowsManager.disableNext();
            String dL = Configuration.getTranslation("Directory").equals("Directory") ? "Directory" : Configuration.getTranslation("Directory");
            Label directoryLabel = new Label(dL + " : ");
            HBox directoryBox = new HBox(directoryLabel);
            directoryBox.setPrefHeight(50);
            directoryBox.setMinHeight(50);
            directoryBox.setMaxHeight(50);
            directoryBox.setAlignment(Pos.CENTER_RIGHT);
            directoryBox.setPadding(new Insets(0, 10, 0, 0));
            Separator separatorLeftSide = new Separator(Orientation.HORIZONTAL);
            separatorLeftSide.getStyleClass().add("horizontal-separator-middleleft");
            directoryBox.setOnMouseEntered(entered -> {
                paramText.textProperty().setValue("Directory del primo giocatore. Serve per importare la strategia, se è presente nella cartella specificata.");
            });
            directoryBox.setOnMouseExited(entered -> {
                paramText.textProperty().setValue("");
            });
            paramBox.getChildren().addAll(directoryBox, separatorLeftSide);

            DirectoryChooser directoryChooser = new DirectoryChooser();
            Label selectedDirectory = new Label(dL);
            selectedDirectory.setAlignment(Pos.CENTER_LEFT);

            String selectButtontText = Configuration.getTranslation("Select").equals("Select") ? "Select" : Configuration.getTranslation("Select");
            Button directoryChooserButton = new Button(selectButtontText);
            directoryChooserButton.setOnAction(clicked -> {
                File directory = directoryChooser.showDialog(windowsManager.primaryStage);
                if(directory == null){
                    String nD = Configuration.getTranslation("No Directory").equals("No Directory") ? "No Directory" : Configuration.getTranslation("No Directory");
                    selectedDirectory.setText(nD);
                }
                else {
                    selectedDirectory.setText(directory.getAbsolutePath());
                    teaPlayers.get(index).getFirst().setDir(directory.toPath());
                    choicesMade.put("dir", directory.toPath());
                }
            });

            selectedDirectory.setMinWidth(110); selectedDirectory.setPrefWidth(110); selectedDirectory.setMaxWidth(110);
            directoryChooserButton.setMinWidth(80); directoryChooserButton.setPrefWidth(80); directoryChooserButton.setMaxWidth(80);
            directoryChooserButton.maxHeightProperty().setValue(25); directoryChooserButton.prefHeightProperty().setValue(25); directoryChooserButton.minHeightProperty().setValue(25);

            HBox directoryChooserWrapper = new HBox(directoryChooserButton, selectedDirectory);
            directoryChooserWrapper.setSpacing(10);
            directoryChooserWrapper.maxHeightProperty().setValue(50);
            directoryChooserWrapper.prefHeightProperty().setValue(50);
            directoryChooserWrapper.minHeightProperty().setValue(50);
            //directoryChooserWrapper.setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));
            directoryChooserWrapper.setAlignment(Pos.CENTER_LEFT);
            directoryChooserWrapper.setPadding(new Insets(0, 0, 0, 30));
            Separator separatorRSide = new Separator(Orientation.HORIZONTAL);
            separatorRSide.getStyleClass().add("horizontal-separator-middleright");
            valuesBox.getChildren().addAll(directoryChooserWrapper, separatorRSide);


            String parallelString = Configuration.getTranslation("Parallel").equals("Parallel") ? "Parallel" : Configuration.getTranslation("Parallel");
            Label parallelLabel = new Label(parallelString);
            HBox parallelBox = new HBox(parallelLabel);
            //parallelBox.setBackground(new Background(new BackgroundFill(Color.SEASHELL, null, null)));
            parallelBox.setPrefHeight(50);
            parallelBox.setMinHeight(50);
            parallelBox.setMaxHeight(50);
            parallelBox.setAlignment(Pos.CENTER_LEFT);
            parallelBox.setAlignment(Pos.CENTER_RIGHT);
            parallelBox.setPadding(new Insets(0, 10, 0, 0));

            parallelBox.setOnMouseEntered(entered -> {
                paramText.textProperty().setValue("Determina se il primo giocatore può utilizzare più di un thread per calcolare la strategia");
            });
            parallelBox.setOnMouseExited(entered -> {
                paramText.textProperty().setValue("");
            });

            Separator separatorTLeft = new Separator(Orientation.HORIZONTAL);
            separatorTLeft.getStyleClass().add("horizontal-separator-middleleft");
            paramBox.getChildren().addAll(parallelBox, separatorTLeft);

            CheckBox parallelToggle = new CheckBox();
            HBox toggleWrapper = new HBox(parallelToggle);
            toggleWrapper.setMinWidth(300); toggleWrapper.setPrefWidth(300); toggleWrapper.setMaxWidth(300);
            toggleWrapper.setPrefHeight(50); toggleWrapper.setMinHeight(50); toggleWrapper.setMaxHeight(50);
            toggleWrapper.setAlignment(Pos.CENTER_LEFT);
            toggleWrapper.setPadding(new Insets(0, 0, 0, 30));

            Separator separatorTRight = new Separator(Orientation.HORIZONTAL);
            separatorTRight.getStyleClass().add("horizontal-separator-middleright");
            valuesBox.getChildren().addAll(toggleWrapper, separatorTRight);
        }


        PlayerFactory playerfactory = teaPlayers.get(index).getFirst();
        if (playerfactory != null) {
            for (Param param : (List<Param>) playerfactory.params()) {
                String paramName = param.name();
                String translated = Configuration.getTranslation(paramName);
                String paramLabelText = translated.equals(paramName) ? paramName : translated;
                List<Object> paramValues = param.values();
                ObservableList<String> comboBoxValues = FXCollections.observableArrayList();
                paramValues.forEach(value -> {
                    String translatedValue = Configuration.getTranslation(value.toString());
                    String valText = translated.equals(value.toString()) ? value.toString() : translatedValue;
                    comboBoxValues.add(valText);
                });

                Label paramLabel = new Label(paramLabelText);
                Separator leftSeparator = new Separator(Orientation.HORIZONTAL);
                leftSeparator.getStyleClass().add("horizontal-separator-middleleft");
                HBox singleParamBox = new HBox(paramLabel);
                //singleParamBox.setBackground(new Background(new BackgroundFill(Color.SEASHELL, null, null)));
                singleParamBox.setPrefHeight(50);
                singleParamBox.setMinHeight(50);
                singleParamBox.setMaxHeight(50);
                singleParamBox.setAlignment(Pos.CENTER_RIGHT);
                singleParamBox.setPadding(new Insets(0, 10, 0, 0));

                singleParamBox.setOnMouseEntered(entered -> {
                    paramText.textProperty().setValue(param.prompt());
                });
                singleParamBox.setOnMouseExited(entered -> {
                    paramText.textProperty().setValue("");
                });

                paramBox.getChildren().addAll(singleParamBox, leftSeparator);


                ComboBox paramChoiceBox = new ComboBox(comboBoxValues);
                paramChoiceBox.setOnAction(a -> {
                    String selectedValue = paramChoiceBox.getSelectionModel().getSelectedItem().toString();
                    param.set(paramValues.stream().filter(value -> Configuration.getTranslation(value.toString()).equals(selectedValue)).findAny().get());
                });
                paramChoiceBox.setMinWidth(200);
                paramChoiceBox.setPrefWidth(200);
                paramChoiceBox.setMaxWidth(200);
                HBox oneParamValuesWrapper = new HBox(paramChoiceBox);
                oneParamValuesWrapper.setPrefHeight(50);
                oneParamValuesWrapper.setMinHeight(50);
                oneParamValuesWrapper.setMaxHeight(50);
                //oneParamValuesWrapper.setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));
                oneParamValuesWrapper.setAlignment(Pos.CENTER_LEFT);
                oneParamValuesWrapper.setPadding(new Insets(0, 0, 0, 30));

                Separator separatorRlSide = new Separator(Orientation.HORIZONTAL);
                separatorRlSide.getStyleClass().add("horizontal-separator-middleright");

                valuesBox.getChildren().addAll(oneParamValuesWrapper, separatorRlSide);
            }
        }
        if(valuesBox.getChildren().size() > 2){
            paramBox.setAlignment(Pos.CENTER);
            valuesBox.setAlignment(Pos.CENTER);
        }
        else {
            paramBox.setPadding(new Insets(25, 0, 0, 0));
            valuesBox.setPadding(new Insets(25, 0, 0, 0));
        }


        Separator separatorV = new Separator(Orientation.VERTICAL);
        separatorV.getStyleClass().add("vertical-separator-faded");
        //separatorV.setPadding(new Insets(0, 10, 0, 10));
        playerSettingss[index].getChildren().addAll(paramBox, separatorV, valuesBox);


        // ----------> Loading box <------------------





        // -----------> Fine loading Box <------------


        return playerSettingss[index];
    }

    public Parent getLoadingButton(int index) {
        HBox loadingBox = index == 0 ? loadingBox1 : loadingBox2;
        loadingBox.setAlignment(Pos.BOTTOM_CENTER);
        loadingBox.setPrefHeight(40); loadingBox.setMinHeight(40); loadingBox.setMaxHeight(40);
        loadingBox.setPrefWidth(playerSettingsWidth); loadingBox.setMinWidth(playerSettingsWidth); loadingBox.setMaxWidth(playerSettingsWidth);
        //loadingBox.setBackground(new Background(new BackgroundFill(Color.POWDERBLUE, null, null)));

        int buttonHeight = 25;
        int buttonWidth = 120;
        int buttonCompleteWidth = 200;
        int scatolettaWidth = 30;
        int duration      = 300;

        HBox scatoletta = new HBox();
        scatoletta.setMinWidth(scatolettaWidth);
        scatoletta.setMaxWidth(scatolettaWidth);
        scatoletta.setMinHeight(buttonHeight);
        scatoletta.setMaxHeight(buttonHeight);
        scatoletta.getStyleClass().add("scatoletta");



        final Animation scatolettaAnim = new Transition() {
            { setCycleDuration(Duration.millis(3500));
                setInterpolator(Interpolator.LINEAR);
            }
            protected void interpolate(double x) {
                double length = ((buttonWidth + buttonCompleteWidth)/2 - scatolettaWidth / 2);
                //scatoletta.setTranslateX(length * Math.sin((Math.PI * 2 * x)));
                scatoletta.setTranslateX(length * (1 - 2*x));
            }
        };
        StackPane loadingButton = new StackPane();

        final Animation endLoad = new Transition() {
            { setCycleDuration(Duration.millis(500)); }
            protected void interpolate(double x) {
                loadingButton.setPrefWidth(buttonCompleteWidth - (buttonCompleteWidth - buttonWidth) * (x <= 0.5? f(2*x)/2 : (2 - f(2*(1-x)))/2));
                loadingButton.setMinWidth(buttonCompleteWidth - (buttonCompleteWidth - buttonWidth) * (x <= 0.5? f(2*x)/2 : (2 - f(2*(1-x)))/2));
                loadingButton.setMaxWidth(buttonCompleteWidth - (buttonCompleteWidth - buttonWidth) * (x <= 0.5? f(2*x)/2 : (2 - f(2*(1-x)))/2));
            }
        };

        final Animation startLoad = new Transition() {
            { setCycleDuration(Duration.millis(duration)); }
            protected void interpolate(double x) {
                //                cerchio.setTranslateX((1 - f(1 - x)) * 30);
                double length = buttonCompleteWidth;
                loadingButton.setPrefWidth(buttonWidth + length * (x <= 0.5? f(2*x)/2 : (2 - f(2*(1-x)))/2));
                loadingButton.setMinWidth(buttonWidth + length * (x <= 0.5? f(2*x)/2 : (2 - f(2*(1-x)))/2));
                loadingButton.setMaxWidth(buttonWidth + length * (x <= 0.5? f(2*x)/2 : (2 - f(2*(1-x)))/2));
            }
        };
        Text buttonText = new Text(Configuration.getTranslation("Compute Strategy"));

        computingThread  = new Thread(() -> {

            teaPlayers.get(index).getFirst().tryCompute(gF, false, interruptStrategy.get());
            scatolettaAnim.stop();
            startLoad.setRate(-1);
            startLoad.play();
            startLoad.setOnFinished((finished) -> { endLoad.play(); });
            Platform.runLater(() -> {
                loadingButton.getChildren().clear();
                loadingBox.getChildren().remove(1);
            });
            windowsManager.enableNext();
        });
        computingThread.setDaemon(true);

        loadingButton.setOnMouseClicked(click -> {
            loadingButton.getChildren().remove(buttonText);
            startLoad.play();
            startLoad.setOnFinished(finished -> {

                loadingButton.getChildren().add(scatoletta);

                HBox closeWrapper = new HBox();
                closeWrapper.setMinSize(25, 25);
                closeWrapper.setMaxSize(25, 25);
                closeWrapper.setAlignment(Pos.CENTER);
                Rectangle close = new Rectangle(15, 15);
                Image closeIcon = new Image(getClass().getResource("/Close15.png").toExternalForm());
                ImagePattern closeIconPattern  = new ImagePattern(closeIcon);
                close.setFill(closeIconPattern);
                closeWrapper.getChildren().add(close);
                closeWrapper.setOnMouseClicked(c -> {
                    interruptStrategy.setValue(true);
                });

                loadingButton.setBackground(new Background(new BackgroundFill(Color.web("#18D704"), null, null)));
                loadingBox.getChildren().add(closeWrapper);

                scatolettaAnim.setCycleCount(Animation.INDEFINITE);
                scatolettaAnim.setAutoReverse(true);
                scatolettaAnim.play();

                computingThread.start();


                loadingButton.setOnMouseClicked(clicked -> {});
            });


        });
        loadingButton.setAlignment(Pos.CENTER);
        loadingButton.setPrefHeight(buttonHeight); loadingButton.setMinHeight(buttonHeight); loadingButton.setMaxHeight(buttonHeight);
        loadingButton.setPrefWidth(buttonWidth); loadingButton.setMinWidth(buttonWidth); loadingButton.setMaxWidth(buttonWidth);
        loadingButton.setBackground(new Background(new BackgroundFill(Color.web("#E17D00"), null, null)));




        loadingButton.getChildren().add(buttonText);
        loadingBox.getChildren().add(loadingButton);
        return loadingBox;
    }

    public Parent changePlayerButton()  {
        double offset     = 15.0;
        int backgroundWidth   = 80;
        int backgroundHeight  = 40;
        int cerchioRadius = 65;
        int duration      = 300;

        VBox playerCircle = new VBox();
        playerCircle.setMinWidth(cerchioRadius);
        playerCircle.setMaxWidth(cerchioRadius);
        playerCircle.setMinHeight(cerchioRadius);
        playerCircle.setMaxHeight(cerchioRadius);
        playerCircle.setTranslateX(-offset);
        playerCircle.getStyleClass().add("bplayers-cerchio");

        VBox background = new VBox();
        background.setMinWidth(backgroundWidth);
        background.setMaxWidth(backgroundWidth);
        background.setMinHeight(backgroundHeight);
        background.setMaxHeight(backgroundHeight);
        background.getStyleClass().add("bplayers-sfondo");


        final Animation circleAnim = new Transition() {
            { setCycleDuration(Duration.millis(duration)); }
            protected void interpolate(double x) {
                double length = 2*offset + (background.getWidth() - playerCircle.getWidth());
                playerCircle.setTranslateX(-offset + length * (x <= 0.5? f(2*x)/2 : (2 - f(2*(1-x)))/2));
            }
        };
        circleAnim.setOnFinished(e -> circleAnim.setRate((-1.0) * circleAnim.getRate()));

        Text first = new Text("1");
        first.getStyleClass().add("bplayers-text");
        first.setTranslateX(11);
        first.setTranslateY(0);
        first.setMouseTransparent(true);
        Text second = new Text("2");
        second.getStyleClass().add("bplayers-text");
        second.setTranslateX(58);
        second.setTranslateY(0);
        second.setMouseTransparent(true);

        StackPane stackpane = new StackPane(background, playerCircle, first, second);
        stackpane.setOnMouseClicked(e -> {
            if (circleAnim.getStatus() == Animation.Status.RUNNING) return;
            circleAnim.play();
            fadeIn.setRate(-1.0);
            fadeIn.play();
            fadeIn.setOnFinished(a -> {
                body.getChildren().remove(0);
                body.getChildren().add(0, getPlayerSettingsWindow(1 - currentIndex));
                fadeIn.setRate(1.0);
                fadeIn.play();
                fadeIn.setOnFinished(b -> {});
            });
        });
        stackpane.setAlignment(Pos.CENTER_LEFT);
        return stackpane;
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
