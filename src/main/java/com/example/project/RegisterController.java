package com.example.project;
import com.google.gson.Gson;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class RegisterController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button registerButton;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    // Saraksts, kur glabāsies visi reģistrētie lietotāji
    private List<User> users = new ArrayList<>();

    // Regulārās izteiksmes, lai pārbaudītu lietotājvārdu, e-pasta un paroles derīgumu
    private static final String USERNAME_REGEX = "^(?=.{5,}$)[a-zA-Z][a-zA-Z0-9]*$";
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static final String PASSWORD_REGEX = "^(?=.{6,}$)[a-zA-Z0-9]*$";


    @FXML
    void backToMenu(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("welcome-window.fxml"));
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setTitle("Registration");
        stage.setScene(scene);
        stage.show();

        Stage primaryStage = (Stage)anchorPane.getScene().getWindow();
        primaryStage.close();
    }

    public void switchToMenu(ActionEvent event) throws Exception{
        String errorMessage = validateUserInput();
        if (errorMessage != null) {
            showAlert(AlertType.ERROR, "Invalid Input", errorMessage, ButtonType.OK);
        }else{
            User newUser = new User(usernameField.getText(), passwordField.getText(), emailField.getText());
            users.add(newUser);
            saveUsers(users);

            // Atver jaunu logu, lai lietotājs varētu pieslēgties
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
            Stage stage = new Stage();

            Scene scene = new Scene(fxmlLoader.load());

            stage.setTitle("Menu");
            stage.setScene(scene);

            MenuController menuController = fxmlLoader.getController();
            menuController.setUser(newUser);
            menuController.generateButtons();
            //root.fillForms();

            stage.show();

            Stage primaryStage = (Stage) registerButton.getScene().getWindow();
            primaryStage.close();
        }

    }

    // Saglabā lietotājus failā JSON formātā
    private void saveUsers(List<User> users) {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(users);
            FileWriter fileWriter = new FileWriter("src/main/resources/data/userInfo.txt");
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Metode, kas pārbauda lietotāja ievadītos datus
    private String validateUserInput() {
        String errorMessage = "";

        ArrayList<String> nicknames = new ArrayList<>();
        for (User user : users){
            nicknames.add(user.getUsername());
        }
        if (usernameField.getText().isEmpty()) {
            errorMessage = "Please enter a username.";
        } else if (!validateUsername(usernameField.getText())) {
            errorMessage = "Please enter a valid username (at least 5 characters, starts with a letter, and contains only letters and digits).";
        } else if (nicknames.contains(usernameField.getText())){
            errorMessage = "Username already exists!";
        }


        if (emailField.getText().isEmpty()) {
            errorMessage += "\nPlease enter an email.";
        } else if (!validateEmail(emailField.getText())) {
            errorMessage += "\nPlease enter a valid email address.";
        }

        if (passwordField.getText().isEmpty()) {
            errorMessage += "\nPlease enter a password.";
        } else if (!validatePassword(passwordField.getText())) {
            errorMessage += "\nPlease enter a password with at least 6 characters and only letters and digits.";
        }

        if (errorMessage.isEmpty()) {
            return null;
        } else {
            return errorMessage;
        }
    }

    private boolean validateUsername(String username) {
        Pattern pattern = Pattern.compile(USERNAME_REGEX);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    private boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    // Metode, kas parāda brīdinājuma logu ar norādīto ziņojumu
    private void showAlert(AlertType alertType, String title, String message, ButtonType... buttonTypes) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.getButtonTypes().setAll(buttonTypes);
        alert.showAndWait();
    }

    // Metode, kas nodrošina, ka, ja lietotājs uzklikšķina uz jebkuras vietas, tiek pievērsta uzmanība pamata logam
    @FXML
    void focus(MouseEvent event) {
        anchorPane.requestFocus();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            String data = Files.readString(Paths.get("src/main/resources/data/userInfo.txt"));
            Gson gson = new Gson();
            users = gson.fromJson(data, new TypeToken<ArrayList<User>>() {
            }.getType());
            System.out.println(users);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
