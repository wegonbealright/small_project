package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class GameWindow {

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Label category;

    @FXML
    private Label description;

    @FXML
    private Label gameName;

    @Setter
    private Game game;

    @FXML
    private ImageView gameImage;


    public void fillForms() throws FileNotFoundException {
        gameName.setText(game.getTitle());
        description.setText(game.getDescription());
        category.setText(game.getCategory());
        FileInputStream input = new FileInputStream(game.getImagePath());
        Image image = new Image(input);
        gameImage.setImage(image);


    }


    @FXML
    void editGame(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("edit-game.fxml"));
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setTitle("Edit game");
        stage.setScene(scene);
        stage.show();

        Stage primaryStage = (Stage)deleteButton.getScene().getWindow();
        primaryStage.close();
    }





}
