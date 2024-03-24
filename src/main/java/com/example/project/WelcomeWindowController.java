package com.example.project;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class WelcomeWindowController {

    @FXML
    private Button signIn;

    @FXML
    private Button signUp;

    @FXML
    void switchToLogin(ActionEvent event) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();

        Stage primaryStage = (Stage) signIn.getScene().getWindow();
        primaryStage.close();
    }

    @FXML
    void switchToRegistration(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setTitle("Registration");
        stage.setScene(scene);
        stage.show();

        Stage primaryStage = (Stage) signUp.getScene().getWindow();
        primaryStage.close();
    }
    @FXML
    void quitTheProgramm(ActionEvent event) {
        Platform.exit();
        System.exit(0);

    }




}
