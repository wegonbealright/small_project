package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class AddNewGame2 {

    @FXML
    private Button saveButton;

    // Metode, kas tiek izsaukta, kad lietotājs izvēlas failu
    @FXML
    void chooseFile(ActionEvent event) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog((Stage) saveButton.getScene().getWindow());
        if (selectedFile != null) {
        }

    }

    // Metode, kas tiek izsaukta, kad lietotājs izvēlas screenshot failu
    @FXML
    void chooseScreenshot(ActionEvent event) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog((Stage) saveButton.getScene().getWindow());
        if (selectedFile != null) {
        }

    }

    // Metode, kas tiek izsaukta, kad lietotājs izvēlas video failu
    @FXML
    void chooseVideo(ActionEvent event) throws FileNotFoundException{
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog((Stage) saveButton.getScene().getWindow());
        if (selectedFile != null) {
        }

    }
    // Metode, kas tiek izsaukta, kad lietotājs saglabā izmaiņas un atgriežas galvenajā izvēlnē
    @FXML
    void saveChanges(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setTitle("menu");
        stage.setScene(scene);
        stage.show();

        Stage primaryStage = (Stage)saveButton.getScene().getWindow();
        primaryStage.close();

    }

}