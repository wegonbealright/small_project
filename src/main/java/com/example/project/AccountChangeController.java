package com.example.project;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AccountChangeController implements Initializable {

    @FXML
    private Button loginButton;

    @FXML
    private Button loginButton1;

    @FXML
    private VBox accountVBox;

    // Nolasa lietotājus no faila, izmantojot Gson bibliotēku
    private List<User> readUsersFromFile() {
        List<User> users = new ArrayList<>();
        try {
            String data = Files.readString(Paths.get("src/main/resources/data/userInfo.txt"));
            Gson gson = new Gson();
            users = gson.fromJson(data, new TypeToken<ArrayList<User>>() {}.getType());
            System.out.println(users);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    // Metode, kas tiek izsaukta, kad tiek nospiesta "Main menu" poga
    @FXML
    void switchToMenu(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setTitle("Main menu");
        stage.setScene(scene);
        stage.show();

        Stage primaryStage = (Stage) loginButton.getScene().getWindow();
        primaryStage.close();
    }

    // Metode, kas tiek izsaukta, kad tiek nospiesta "Registration" poga
    @FXML
    void switchToRegister(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setTitle("Registration");
        stage.setScene(scene);
        stage.show();

        Stage primaryStage = (Stage) loginButton.getScene().getWindow();
        primaryStage.close();
    }


    // Metode, kas tiek izsaukta, kad kontrolieris tiek inicializēts
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Iegūst lietotājus no faila
        List<User> users = readUsersFromFile();
        // Izveido pogas un pievieno tās konteineram (VBox)
        for(User user : users){
            Button newButton = new Button(user.getUsername());
            newButton.getStylesheets().add(0, getClass().getClassLoader().getResource("button.css").toExternalForm());
            newButton.setMaxWidth(Double.MAX_VALUE); // fill the button fully on width
            newButton.setOnAction(e -> {
                try {
                    // Atver jaunu logu, lai lietotājs varētu pieslēgties
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("logIn.fxml"));
                    Stage stage = new Stage();

                    Scene scene = new Scene(fxmlLoader.load());

                    stage.setTitle("Log in");
                    stage.setScene(scene);

                    // Iegūst loga kontrolieri un aizpilda formas ar lietotāja datiem
                    LoginController loginController = fxmlLoader.getController();
                    loginController.fillForms(user);
                    //root.fillForms();

                    stage.show();

                    Stage primaryStage = (Stage) loginButton.getScene().getWindow();
                    primaryStage.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            });
            // Pievieno pogu konteineram
            accountVBox.getChildren().add(newButton);

        }
    }
}

