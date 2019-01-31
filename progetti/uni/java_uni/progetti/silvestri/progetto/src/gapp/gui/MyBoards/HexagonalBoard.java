package gapp.gui.MyBoards;

import gapp.gui.MyShapes.Hexagon;
import gapp.ulg.game.board.Board;
import gapp.gui.*;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class HexagonalBoard<P>{

    private double sideLength;
    private int width;
    private int height;

    private Board<P> board;

    private double spacing;

    List<Hexagon> positionList = new ArrayList<>();

    public HexagonalBoard(Board<P> board, double sideLength, double spacing){
        this.board = board;
        this.sideLength = sideLength;
        this.spacing = spacing;
    }

    public StackPane drawBOARD(){

        int width = this.board.width();
        int height = this.board.height();

        StackPane boardBox = new StackPane();
        boardBox.setAlignment(Pos.CENTER);
        boardBox.setPickOnBounds(true);
        double offset_x = ((sideLength*Math.sqrt(3)) * (width - 1) + spacing * (width - 1)) / 2d;
        double offset_y = ((sideLength*Math.sqrt(3)) * (height - 1) + spacing * (height - 1)) / 2d; 

        for (gapp.ulg.game.board.Pos position : board.positions()) {
            int x = position.b;
            int y = position.t;

            Color fill = Color.GREEN;
            Hexagon hexagon = new Hexagon(sideLength, fill, new gapp.ulg.game.board.Pos(x, y));
            Hexagon zz = hexagon.drawHexagon();

            zz.setTranslateX(x*(2d*(sideLength*Math.sqrt(3)/2d)+spacing) - y*(sideLength*Math.sqrt(3)/2d + spacing) - offset_x);
            zz.setTranslateY(- y*((3d/2d)*sideLength + spacing) + 2*offset_y);

            positionList.add(zz);

            boardBox.getChildren().add(zz);
        }
    return boardBox;
    }

    public StackPane drawCirclesPane(){
        throw new UnsupportedOperationException("DA IMPLEMENTARE");
    }

    public void setMaster(MasterChef master) {
        throw new UnsupportedOperationException("DA IMPLEMENTARE");
    }

    public List<Hexagon> getPositionList(){
        return this.positionList;
    }

}
