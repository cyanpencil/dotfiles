package gapp.gui.MyShapes;

import gapp.ulg.game.board.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.shape.*;

public class Square extends StackPane {
    private final double sideLength;
    private Paint fillColor;
    private final Pos position;
    private Shape shape;

    private Rectangle square;

    public Square(double sideLength, Paint fillColor, Pos pos){
        this.sideLength = sideLength;
        this.fillColor = fillColor;
        this.position = pos;
    }

    /** Ritorna la posizione a cui corrisponde questo quadrato sulla board.
     * @return la Pos corrispondente dell'esagono.  */
    public Pos getPosition(){
        return this.position;
    }

    /** Ritorna il poligono rappresentante questo quadrato.
     * @return il poligono che delinea il quadrato.  */
    public Shape getCurrentShape() {
        return this.shape;
    }


    /** Ritorna uno stackpane su cui viene disegnato il quadrato.
     * @return lo stackPane contente il disegno dell'esagono.  */
    public Square drawSquare(){
        square = new Rectangle(0, 0, sideLength, sideLength);
        square.setFill(this.fillColor);
        this.setPickOnBounds(false);
        this.shape = square;
        square.setOnMouseEntered(e -> square.setFill(Color.web("F3FBF3")));
        square.setOnMouseExited(e -> square.setFill(this.fillColor));
        if(this.position != null){
            this.getChildren().addAll(square);
            return this;
        }
        return this;
    }

    /** Imposta il colore di sfondo del quadrato.
     * @param fillColor il colore dello sfondo.  */
    public void setFill(Paint fillColor){
        this.fillColor = fillColor;
        this.square.setFill(fillColor);
    }

}
