package com.example.project;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.IOException;

public class MenuController {
    @FXML
    private Button changeAccButton;

    @FXML
    private Button newGameButton;

    @FXML
    private VBox gameVBox;

    @Setter
    private User user;

    // Metode, kas tiek izsaukta, kad lietotājs vēlas mainīt kontu
    @FXML
    void changeAccount(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("account-change.fxml"));
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setTitle("Registration");
        stage.setScene(scene);
        stage.show();

        Stage primaryStage = (Stage)newGameButton.getScene().getWindow();
        primaryStage.close();
    }

    // Metode, kas tiek izsaukta, kad lietotājs vēlas pievienot jaunu spēli
    @FXML
    void addNewGame(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("add-new-game.fxml"));
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setTitle("Add new game!");
        stage.setScene(scene);
        stage.show();

        Stage primaryStage = (Stage)changeAccButton.getScene().getWindow();
        primaryStage.close();
    }
    // Metode, kas tiek izsaukta, kad lietotājs vēlas rediģēt spēli


    public void generateButtons() {
        // Izveido pogas un pievieno tās konteineram (VBox)
        for(Game game : user.getGameList()){
            Button newButton = new Button(game.getTitle());
            newButton.getStylesheets().add(0, getClass().getClassLoader().getResource("button.css").toExternalForm());
            newButton.setMaxWidth(Double.MAX_VALUE); // fill the button fully on width


            newButton.setOnAction(e -> {
                try {
                    // Atver jaunu logu, lai lietotājs varētu pieslēgties
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game-window.fxml"));
                    Stage stage = new Stage();

                    Scene scene = new Scene(fxmlLoader.load());

                    stage.setTitle("Game");
                    stage.setScene(scene);

                    GameWindow gameWindow = fxmlLoader.getController();
                    gameWindow.setGame(game);
                    gameWindow.fillForms();
                    //root.fillForms();

                    stage.show();

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            });
            // Pievieno pogu konteineram
            gameVBox.getChildren().add(newButton);
        }
    }
}




