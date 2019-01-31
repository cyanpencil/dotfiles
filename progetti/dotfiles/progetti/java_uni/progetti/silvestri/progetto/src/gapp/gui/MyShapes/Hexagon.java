package gapp.gui.MyShapes;

import gapp.ulg.game.board.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

public class Hexagon extends StackPane{


    private final double sideLength;
    private Paint fillColor;
    private final Pos position;

    private final double sqrt;      
    private final double half;     

    public Hexagon(double sideLength, Paint fillColor, Pos pos){
        this.sideLength = sideLength;
        this.fillColor = fillColor;
        this.position = pos;
        this.sqrt = sideLength*Math.sqrt(3)/2;
        this.half = sideLength/2;
    }

    /** Ritorna la posizione a cui corrisponde questo esagono sulla board.
     * @return la Pos corrispondente dell'esagono.  */
    public Pos getPosition(){
        return this.position;
    }

    /** Ritorna uno stackpane su cui viene disegnato l'esagono.
     * @return lo stackPane contente il disegno dell'esagono.  */
    public Hexagon drawHexagon(){
        Polygon hexagon = new Polygon(0, sideLength, -sqrt, half, -sqrt, -half, 0, -sideLength, sqrt, -half, sqrt, half);
        hexagon.setFill(this.fillColor);
        hexagon.setOnMouseEntered(e -> hexagon.setFill(Color.RED));
        hexagon.setOnMouseExited(e -> hexagon.setFill(this.fillColor));
        this.setPickOnBounds(false);
        if(this.position != null){
            this.getChildren().addAll(hexagon);
            return this;
        }
        return this;
    }
}
