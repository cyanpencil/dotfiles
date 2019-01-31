package gapp.gui;

import gapp.gui.MyBoards.BoardGUI;
import gapp.gui.Windows.BoardWindow;
import gapp.ulg.game.board.*;
import javafx.scene.layout.StackPane;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class TheView<P>{


    private final StackPane wrapper;
    private final BoardGUI boardPane;
    private final BoardWindow boardWindow;

    public TheView(StackPane wrapper, BoardGUI BoardPane, BoardWindow boardWindow){
        this.boardPane = BoardPane;
        this.wrapper = wrapper;
        this.boardWindow = boardWindow;
    }

    public void accept(Board<P> currentBoard, int turn, ArrayList<Double> scores, int result) {
        Board<P> board = currentBoard;
        boardPane.setBoard(board);

        StackPane piecesPane = boardPane.drawPieces();
        wrapper.getChildren().set(1, piecesPane);

        if(scores != null){
            double scoreFirst = scores.get(0);
            double scoreSecond = scores.get(1);
            boardWindow.changeTurn(turn, piecesPane, new ArrayList<>(Arrays.asList(scoreFirst, scoreSecond)));
        }
        else
            boardWindow.changeTurn(turn, piecesPane, null);
        if(result != -1){
            boardWindow.notifyOver(result);
            if(wrapper.getChildren().size() > 3) wrapper.getChildren().remove(wrapper.getChildren().size()-1); // Per togliere i cerchietti alla fine della partita
        }

    }

    public void interrupted(){
        boardPane.sendDialog(new DialogBox("Partita interrotta", "La partita e' stata interrotta!", b -> {
            boardPane.clearDialog();
            boardWindow.notifyOver(0);
        }, true));
    }
}
