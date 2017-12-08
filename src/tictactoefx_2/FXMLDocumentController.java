/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoefx_2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO

    }

    @FXML
    public void handleIVOnMouseClicked(MouseEvent event)
    {
        System.out.println(event.getSource());
        System.out.println(GridPane.getColumnIndex((ImageView) event.getSource()));
        System.out.println(GridPane.getRowIndex((ImageView) event.getSource()));
    }

}
