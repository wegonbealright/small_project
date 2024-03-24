package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class EditGameController {

    @FXML
    private ScrollPane gameField;
    @FXML
    private Label editGame2;
    @FXML
    private TextField gameCategory2;

    @FXML
    private TextField gameDescription2;

    @FXML
    private TextField gameName2;

    @FXML
    void confirm2(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("menu");
            stage.setScene(scene);
            stage.show();

            Stage primaryStage = (Stage) editGame2.getScene().getWindow();
            primaryStage.close();


    }
    public void fillFields(Game game) {
        gameCategory2.setText(game.getCategory());
        gameDescription2.setText(game.getDescription());
        gameName2.setText(game.getTitle());
    }


}



