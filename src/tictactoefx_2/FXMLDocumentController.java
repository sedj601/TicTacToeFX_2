/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoefx_2;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author blj0011
 */
public class FXMLDocumentController implements Initializable
{

    @FXML
    private Label label;
    @FXML ImageView ivOne, ivTwo, ivThree, ivFour, ivFive, ivSix, ivSeven, ivEight, ivNine;
    boolean computerTurn;
    int turnCounter;
    String boardState;
    Image xIcon, oIcon;
    

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        boardState = "123456789";
        computerTurn = false;
        turnCounter = 1;
        xIcon = new Image(getClass().getResourceAsStream("xIcon.png"));
        oIcon = new Image(getClass().getResourceAsStream("oIcon.png"));
        
    }

    @FXML
    public void handleIVOnMouseClicked(MouseEvent event)
    {        
        System.out.println(event.getSource());
        System.out.println(GridPane.getColumnIndex(((ImageView) event.getSource()).getParent()));
        System.out.println(GridPane.getRowIndex(((ImageView) event.getSource()).getParent()));
        int columnIndex = GridPane.getColumnIndex(((ImageView) event.getSource()).getParent());
        int rowIndex = GridPane.getRowIndex(((ImageView) event.getSource()).getParent());
        
        if(turnCounter % 2 == 1)
        {
            boardState = boardState.replace(getCharacter(columnIndex, rowIndex), 'o');
            System.out.println(boardState);
            ((ImageView)event.getSource()).setImage(oIcon);
        }
//        else
//        {
//            boardState = boardState.replace(getCharacter(columnIndex, rowIndex), 'x');
//            System.out.println(boardState);
//            ((ImageView)event.getSource()).setImage(xIcon);
//        }        
        turnCounter++;
        if(turnCounter % 2 == 0)
        {
            makeComputerMove(computerMove(boardState, 'x', 'o'));
        }
        turnCounter++;
//            boardState = boardState.replace(getCharacter(columnIndex, rowIndex), 'o');
//            System.out.println(boardState);
//            ((ImageView)event.getSource()).setImage(oIcon);
//        }
//        else
//        {
//            boardState = boardState.replace(getCharacter(columnIndex, rowIndex), 'x');
//            System.out.println(boardState);
//            ((ImageView)event.getSource()).setImage(xIcon);
//        }  
        
    }
    
    int computerMove(String currentBoardState, char me, char opp)
    {
        
        List<String> triples = TicTacToeBrainUtility.getTriples(boardState);
        if(TicTacToeBrainUtility.iCanWin(triples, me) != -1)
        {
            return TicTacToeBrainUtility.iCanWin(triples, me);
        }
        else if(TicTacToeBrainUtility.opponentCanWin(triples, opp) != -1)
        {
            return TicTacToeBrainUtility.opponentCanWin(triples, opp);
        }
        else if(TicTacToeBrainUtility.iCanFork(triples, me).size() > 0)
        {
            return Integer.parseInt(TicTacToeBrainUtility.iCanFork(triples, me).get(0));
        }
        else if(TicTacToeBrainUtility.opponentCanFork(triples, opp).size() > 0)
        {
            return Integer.parseInt(TicTacToeBrainUtility.iCanFork(triples, opp).get(0));
        }
        else if(TicTacToeBrainUtility.currentSquareOpen("5", boardState))
        {
            return 5;
        }
        
        else if(TicTacToeBrainUtility.playCorner(currentBoardState) > 0)
        {
            return TicTacToeBrainUtility.playCorner(currentBoardState);
        }   
        
        
        return -1;
    }
    
    void makeComputerMove(int move)
    {
            System.out.println("state: " + boardState);
            
        switch(move)
        {
            case 1: 
                ivOne.setImage(xIcon);
                boardState = boardState.replace('1', 'x');
                break;
            case 2: 
                ivTwo.setImage(xIcon);
                boardState = boardState.replace('2', 'x');
                break;
             case 3: 
                ivThree.setImage(xIcon);
                boardState = boardState.replace('3', 'x');
                break;
            case 4: 
                ivFour.setImage(xIcon);
                boardState = boardState.replace('4', 'x');
                break;            
            case 5: 
                ivFive.setImage(xIcon);
                boardState = boardState.replace('5', 'x');
                break;
            case 6: 
                ivSix.setImage(xIcon);
                boardState = boardState.replace('6', 'x');
                break;
            case 7: 
                ivSeven.setImage(xIcon);
                boardState = boardState.replace('7', 'x');
                break;
            case 8: 
                ivEight.setImage(xIcon);
                boardState = boardState.replace('8', 'x');
                break;
            case 9: 
                ivNine.setImage(xIcon);
                boardState = boardState.replace('9', 'x');
                break;
        }
    }
    
    char getCharacter(int columnIndex, int rowIndex)
    {
        switch(rowIndex)
        {
            case 0:
                switch(columnIndex)
                {
                    case 0: return '1';
                    case 1: return '2';
                    case 2: return '3';
                }
                break;
            case 1:
                switch(columnIndex)
                {
                    case 0: return '4';
                    case 1: return '5';
                    case 2: return '6';
                }
                break;
            case 2: 
                switch(columnIndex)
                {
                    case 0: return '7';
                    case 1: return '8';
                    case 2: return '9';
                }
                break;
        }
        
        return '0';
    }
    
}
