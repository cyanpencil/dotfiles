package gapp.gui;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Created by Edoardo on 08/09/2016.
 */
public class DialogBox {
    enum Type {
        /** Tipo di Box di dialogo per visualizzare un messaggio diretto all'utente */
        MESSAGE,
        /** Tipo di Box di dialogo per permettere all'utente di confermare una propria decisione*/
        CONFIRM,
        /** Tipo di Box di dialogo per permettere all'utente di scegliere tra più alternative diverse */
        CHOICE
    }

    private final Type type;
    private final String title;

    private final String prompt;

    private final String msg;
    private final ImagePattern img;

    private final Consumer<Integer> action;
    private final ArrayList<String> choices;
    private final ArrayList<ImagePattern> choicesPictures;

    public DialogBox(String title, String prompt, Consumer<Integer> action){
        this.type = Type.CONFIRM;
        this.title = title;
        this.prompt = prompt;
        this.action = action;

        msg = null; img = null; choices = null; choicesPictures = null;

    }

    public DialogBox(String title, String prompt, Consumer<Integer> action, boolean message){
        this.type = Type.MESSAGE;
        this.title = title;
        this.prompt = prompt;
        this.action = action;
        msg = null; img = null; choices = null; choicesPictures = null;

    }

    public DialogBox(String title, String msg, ImagePattern img){
        this.type = Type.MESSAGE;
        this.title = title;
        this.msg = msg;
        this.img = img;

        this.prompt = null;
        this.choices = null;
        this.choicesPictures = null;
        this.action = null;
    }
    public DialogBox(String title, ArrayList<String> choices, ArrayList<ImagePattern> choicesPictures, Consumer<Integer> action){
        this.type = Type.CHOICE;
        this.title = title;
        this.choices = choices;
        this.choicesPictures = choicesPictures;
        this.action = action;

        this.prompt = null;
        this.msg = null;
        this.img = null;
    }


    /** Crea e ritorna un VBox contenente la casellina di avvertimento.
     * @return il VBox della casellina da visuallizzare */
    public VBox getDialogBox(){
        VBox wrapper = new VBox();
        wrapper.setMaxSize(300, 200); wrapper.setMinSize(300, 200); wrapper.setPrefSize(300, 200);
        wrapper.setBackground(new Background(new BackgroundFill(Color.web("#519AF2"), null, null)));

        HBox titleWrapper = new HBox();
        titleWrapper.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, null, null)));
        titleWrapper.setMinSize(298, 30); titleWrapper.setMaxSize(298, 30); titleWrapper.setPrefSize(298, 30);
        titleWrapper.setAlignment(Pos.CENTER);
        Text titleText = new Text(Configuration.getTranslation(title));
        titleText.getStyleClass().add("dialogBoxTitle");
        titleWrapper.getChildren().add(titleText);


        Separator separator  = new Separator(Orientation.HORIZONTAL);
        separator.getStyleClass().add("horizontal-separator-total");
        wrapper.getChildren().addAll(titleWrapper);

        if(type == Type.CHOICE){ TilePane choicesPane = new TilePane();
            choicesPane.setOrientation(Orientation.HORIZONTAL);
            if(choices != null && choicesPictures != null){
                for(int i = 0; i < choices.size(); i++){
                    VBox choiceWrapper = new VBox();
                    choiceWrapper.setAlignment(Pos.CENTER);
                    choiceWrapper.setMaxSize(50, 100);
                    Rectangle imageWrapper = new Rectangle(50, 50);
                    imageWrapper.setFill(choicesPictures.get(i));
                    Text choiceText = new Text(Configuration.getTranslation(choices.get(i)));
                    choiceWrapper.getChildren().addAll(imageWrapper, choiceText);
                    choicesPane.getChildren().add(choiceWrapper);
                }
            }
            else if(choicesPictures != null){
                for(int i = 0; i < choicesPictures.size(); i++){
                    Rectangle imageWrapper = new Rectangle(50, 50);
                    int i2 = i;
                    imageWrapper.setOnMouseClicked(clicked -> action.accept(i2));
                    imageWrapper.setFill(choicesPictures.get(i));
                    choicesPane.getChildren().add(imageWrapper);
                }
            }
            choicesPane.setMaxSize(298, 168); choicesPane.setMinSize(298, 168); choicesPane.setPrefSize(298, 168);
            choicesPane.setAlignment(Pos.CENTER);
            choicesPane.setHgap(15); choicesPane.setVgap(15);
            choicesPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));

            wrapper.getChildren().add(choicesPane);
            wrapper.setAlignment(Pos.CENTER); }
        else if(type == Type.CONFIRM){
            wrapper.setAlignment(Pos.CENTER);
            HBox promptWrapper = new HBox();
            promptWrapper.setPadding(new Insets(20, 0, 0, 0));
            promptWrapper.setMinSize(280, 50); promptWrapper.setMaxSize(280, 50); promptWrapper.setPrefSize(280, 50);
            promptWrapper.setAlignment(Pos.CENTER);
            Text promptText = new Text(Configuration.getTranslation(prompt));
            promptText.setWrappingWidth(280);
            promptText.setTextAlignment(TextAlignment.CENTER);
            promptWrapper.getChildren().add(promptText);

            HBox buttonsWrapper = new HBox();
            buttonsWrapper.setSpacing(20);
            buttonsWrapper.setAlignment(Pos.CENTER);
            buttonsWrapper.setMinSize(298, 119); buttonsWrapper.setMaxSize(298, 119); buttonsWrapper.setPrefSize(298, 119);

            Button buttonYes = new Button("Yes");
            buttonYes.setPadding(new Insets(10));
            buttonYes.setBackground(new Background(new BackgroundFill(Color.web("#e0e2e6"), new CornerRadii(2), null)));
            buttonYes.setMinSize(90, 40); buttonYes.setMaxSize(90, 40);
            buttonYes.setOnMouseClicked(clicked -> action.accept(0));


            Button buttonNo = new Button("No");
            buttonNo.setPadding(new Insets(10));
            buttonNo.setBackground(new Background(new BackgroundFill(Color.web("#e0e2e6"), new CornerRadii(2), null)));
            buttonNo.setMinSize(90, 40); buttonNo.setMaxSize(90, 40); buttonNo.setPrefSize(90, 40);
            buttonNo.setOnMouseClicked(clicked -> action.accept(1));

            buttonsWrapper.getChildren().addAll(buttonYes, buttonNo);
            wrapper.getChildren().addAll(promptWrapper, buttonsWrapper);
        }
        else if(type == Type.MESSAGE){
            wrapper.setAlignment(Pos.CENTER);
            HBox promptWrapper = new HBox();
            promptWrapper.setPadding(new Insets(20, 0, 0, 0));
            promptWrapper.setMinSize(280, 50); promptWrapper.setMaxSize(280, 50); promptWrapper.setPrefSize(280, 50);
            promptWrapper.setAlignment(Pos.CENTER);
            Text promptText = new Text(this.prompt);
            promptText.setWrappingWidth(280);
            promptText.setTextAlignment(TextAlignment.CENTER);
            promptWrapper.getChildren().add(promptText);

            HBox buttonsWrapper = new HBox();
            buttonsWrapper.setSpacing(20);
            buttonsWrapper.setAlignment(Pos.CENTER);
            buttonsWrapper.setMinSize(298, 119); buttonsWrapper.setMaxSize(298, 119); buttonsWrapper.setPrefSize(298, 119);

            Button buttonOk = new Button("Ok");
            buttonOk.setPadding(new Insets(10));
            buttonOk.setBackground(new Background(new BackgroundFill(Color.web("#e0e2e6"), new CornerRadii(2), null)));
            buttonOk.setMinSize(90, 40); buttonOk.setMaxSize(90, 40);
            buttonOk.setOnMouseClicked(clicked -> action.accept(0));

            buttonsWrapper.getChildren().addAll(buttonOk);
            wrapper.getChildren().addAll(promptWrapper, buttonsWrapper);
        }
        return wrapper;
    }
}
