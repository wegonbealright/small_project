package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddNewGame {

    @FXML
    private TextField gameDescription;
    @FXML
    private TextField gameCategory;

    @FXML
    private TextField gameName;


    @FXML
    void confirm(ActionEvent event) throws IOException {
        // Pārbaude, vai visi lauki ir aizpildīti
        if (gameName.getText().isEmpty() || gameCategory.getText().isEmpty() || gameDescription.getText().isEmpty()) {
            // Ja kāds no laukiem nav aizpildīts, tiek parādīts kļūdas ziņojums
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Please fill in all fields.");
            alert.showAndWait();
        }
        // Pārbaude, vai Description laukā nav vairāk par 230 burtiem/simboliem
        else if (gameDescription.getText().length() >= 230){
            //Ja ir vairāk par 230 simboliem, tad tiek parādīts kļūdas ziņojums
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Description is too long!");
            alert.showAndWait();
        }else {

        // Ja ir aizpildīti visi lauki, tiek atvērts jauns logs ar izvēlni un pašreizējais logs tiek aizvērts
            Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
            Stage stage = new Stage();

            Scene scene = new Scene(root);

            stage.setTitle("Menu");
            stage.setScene(scene);
            stage.show();

            Stage primaryStage = (Stage) gameDescription.getScene().getWindow();
            primaryStage.close();
        }
    }

        @FXML
        void addMore (ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("add-new-game-2.fxml"));
            Stage stage = new Stage();

            Scene scene = new Scene(root);

            stage.setTitle("Add new game!");
            stage.setScene(scene);
            stage.show();

            Stage primaryStage = (Stage) gameDescription.getScene().getWindow();
            primaryStage.close();
        }
    }

