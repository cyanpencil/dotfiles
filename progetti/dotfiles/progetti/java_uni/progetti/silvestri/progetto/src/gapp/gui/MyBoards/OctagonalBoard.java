package gapp.gui.MyBoards;


import gapp.gui.MyShapes.Square;
import gapp.ulg.Utilities.UsefulMethods;
import gapp.ulg.game.board.Action;
import gapp.ulg.game.board.Board;
import gapp.ulg.game.board.PieceModel;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import gapp.gui.*;

import java.util.*;
import java.util.List;
import java.util.Map;
import gapp.ulg.game.board.PieceModel.*;

public class OctagonalBoard<P> implements BoardGUI<P>{

    private double sideLength;
    private double spacing;
    private int width;              
    private int height;             

    private Board<P> board;
    private Map<gapp.ulg.game.board.Pos, Square> positionList = new HashMap<>();
    private DialogBox currentDialog;
    private Map<gapp.ulg.game.board.Pos, P> boardMap = null;
    private StackPane piecesPane = new StackPane();
    private StackPane circlesPane = new StackPane();
    
    private MasterChef master;

    public OctagonalBoard(Board<P> board, double sideLength, double spacing){
        this.board = board;
        this.sideLength = sideLength;
        this.spacing = spacing;
    }
    
    public void setMaster(MasterChef master) {
        this.master = master;
    }

    public StackPane drawBOARD(){
        int width = this.board.width();
        int height = this.board.height();


        StackPane boardBox = new StackPane();

        boardBox.setPrefHeight(0); boardBox.setMinHeight(0); boardBox.setMaxHeight(0);
        boardBox.setPrefWidth(width*sideLength); boardBox.setMinWidth(width*sideLength); boardBox.setMaxWidth(width*sideLength);


        boardBox.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));

        boardBox.setAlignment(Pos.CENTER);
        boardBox.setPickOnBounds(true);


        double offset_x = ((sideLength) * (width - 1) + spacing * (width - 1)) / 2d;
        double offset_y = ((sideLength) * (height - 1) + spacing * (height - 1)) / 2d;

        for (gapp.ulg.game.board.Pos position : board.positions()) {
            int x = position.b;
            int y = position.t;

            Color fill = (x+y)%2==0 ? Color.rgb(181, 136, 99, 1) : Color.rgb(240, 217, 181, 1);
            Square square = new Square(sideLength, fill, new gapp.ulg.game.board.Pos(x, y)).drawSquare();
            if (master != null) square.getCurrentShape().setOnMouseClicked(e -> {
                master.click(square.getPosition());
            });
            square.setTranslateX(x*(sideLength + spacing) - offset_x);
            square.setTranslateY(- y * (sideLength + spacing) + offset_y);


            positionList.put(position, square);
            boardBox.getChildren().add(square);
        }
        return boardBox;

    }

    public StackPane drawPieces(){
        piecesPane = new StackPane();

        String pieceStyle = ((ArrayList<String>) Configuration.getParamCurrentValue("Pezzi")).get(0);
        if (pieceStyle == null) pieceStyle = "Club";

        for(gapp.ulg.game.board.Pos position : this.board.positions()){
            PieceModel<Species> piece = (PieceModel<Species>) board.get(position);
            if(piece != null){

                int side = (int) Math.round(35 * (sideLength / 50));
                Rectangle r = new Rectangle(side, side);

                String pieceName = (piece.color.equals("bianco") ? "White" : "Black") + piece.species.toString().toLowerCase().substring(0, 1).toUpperCase() + piece.species.toString().toLowerCase().substring(1);


                Image img = new Image(getClass().getResource("/" + pieceStyle + "/50/" + pieceName + ".png").toString());
                ImagePattern imagePattern = new ImagePattern(img);
                r.setFill(imagePattern);

                r.setTranslateX(positionList.get(position).getTranslateX());
                r.setTranslateY(positionList.get(position).getTranslateY());
                piecesPane.getChildren().add(r);

            }
        }
        piecesPane.setMouseTransparent(true);
        return  piecesPane;
    }

    public StackPane drawCirclesPane(){
        circlesPane.setMouseTransparent(true);
        return circlesPane;
    }

    @Override
    public void setBoard(Board<P> board){
        circlesPane.setVisible(true);
        master.updateBoardGr(board);
        this.boardMap = null;
        this.board = board;
    }
    
    @Override
    public Board<P> getBoard(){
        return board;
    }

    @Override
    public void updateBoard(List<gapp.ulg.game.board.Pos> highlightedPositions, List<Action<P>> actionsList){
        Platform.runLater(() -> circlesPane.getChildren().clear());
        boolean drawCircles = (boolean) Configuration.getParamCurrentValue("ValidMoves");
        if (!drawCircles && actionsList.size() == 0) return;

        String pieceStyle = ((ArrayList<String>) Configuration.getParamCurrentValue("Pezzi")).get(0);
        if (pieceStyle == null) pieceStyle = "Club";

        if(actionsList == null){
            boardMap = null;
            piecesPane.getChildren().clear();
            for(gapp.ulg.game.board.Pos position : this.board.positions()){
                PieceModel<Species> piece = (PieceModel<Species>) board.get(position);
                if(piece != null){

                    int side = (int) Math.round(35 * (sideLength / 50));
                    Rectangle r = new Rectangle(side, side);

                    String pieceName = (piece.color.equals("bianco") ? "White" : "Black") + piece.species.toString().toLowerCase().substring(0, 1).toUpperCase() + piece.species.toString().toLowerCase().substring(1);

                    Image img = new Image(getClass().getResource("/" + pieceStyle + "/50/" + pieceName + ".png").toString());
                    ImagePattern imagePattern = new ImagePattern(img);
                    r.setFill(imagePattern);

                    r.setTranslateX(positionList.get(position).getTranslateX());
                    r.setTranslateY(positionList.get(position).getTranslateY());
                    piecesPane.getChildren().add(r);
                }
            }
        }
        else if(actionsList.size() != 0) {
            piecesPane.getChildren().clear();

            for(int y = this.board.height()-1; y >= 0; y--){
                for(int x = 0; x < this.board.width(); x++){
                    if(x == 0){
                        if(((Integer)y).toString().length() == 2) System.out.print(y + " ");
                        else System.out.print(y + "  ");
                    }
                    gapp.ulg.game.board.Pos position = new gapp.ulg.game.board.Pos(x, y);
                    if(!board.isPos(position)){
                        System.out.print("#  ");
                    }
                    else {
                        PieceModel<Species> piecem = (PieceModel<Species>) this.board.get(position);
                        String piece = piecem == null ? "-  " : (piecem.color.equals("bianco") ? (piecem.species.toString().substring(0, 1).toUpperCase() + "  ") : (piecem.species.toString().substring(0, 1).toLowerCase() + "  ") );
                        System.out.print(piece);
                    }
                }
            }


            boardMap = boardMap == null ? UsefulMethods.createMap(board) : boardMap;
            for(Action<P> action : actionsList) UsefulMethods.doAction(boardMap, action, board::adjacent);

            for(gapp.ulg.game.board.Pos position : positionList.keySet()){
                PieceModel<Species> piece = (PieceModel<Species>) boardMap.get(position);
                if(piece != null){
                    int side = (int) Math.round(35 * (sideLength / 50));
                    Rectangle r = new Rectangle(side, side);

                    String pieceName = (piece.color.equals("bianco") ? "White" : "Black") + piece.species.toString().toLowerCase().substring(0, 1).toUpperCase() + piece.species.toString().toLowerCase().substring(1);



                    Image img = new Image(getClass().getResource("/" + pieceStyle + "/50/" + pieceName + ".png").toString());
                    ImagePattern imagePattern = new ImagePattern(img);
                    r.setFill(imagePattern);

                    r.setTranslateX(positionList.get(position).getTranslateX());
                    r.setTranslateY(positionList.get(position).getTranslateY());
                    piecesPane.getChildren().add(r);

                }
            }

            piecesPane.setMouseTransparent(true);
        }

        if(!drawCircles) return;
        for(gapp.ulg.game.board.Pos position : positionList.keySet()){
             boolean highlighted = highlightedPositions.contains(position);



             if (highlighted) {
                 int radius = board.get(position) == null ? 10 : ((int) Math.round((sideLength/2)-10));
                 Circle c = new Circle(radius);
                 c.setStyle("-fx-fill: #00000000; -fx-stroke: black; -fx-opacity: 0.4");
                 c.setTranslateX(positionList.get(position).getTranslateX());
                 c.setTranslateY(positionList.get(position).getTranslateY());
                 Platform.runLater(() -> circlesPane.getChildren().add(c));
             }
         }
     }


    @Override
    public void sendDialog(DialogBox dialog){
        System.out.println("Senddddddddddddd");
        circlesPane.setVisible(false);
        piecesPane.setMouseTransparent(false);
        VBox dialogBox = dialog.getDialogBox();
        StackPane dialogMask = new StackPane();
        dialogMask.getChildren().addAll(dialogBox);
        piecesPane.getChildren().addAll(dialogMask);
    }

    @Override
    public void clearDialog(){
        System.out.println("finiiiishhhh");
        circlesPane.setVisible(true);
        piecesPane.getChildren().remove(piecesPane.getChildren().size()-1);
        piecesPane.setMouseTransparent(true);
    }

}
