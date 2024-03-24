package com.example.project;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    // Saraksts, lai uzglabātu lietotāju informāciju
    private List<User> users;

    // Metode, kas tiek izsaukta, kad tiek spiests "Register" poga
    @FXML
    public void switchToRegister(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setTitle("Registration");
        stage.setScene(scene);
        stage.show();

        Stage primaryStage = (Stage) registerButton.getScene().getWindow();
        primaryStage.close();
    }

    // Metode, kas tiek izsaukta, kad tiek spiests "Login" poga
    @FXML
    public void switchToMenu(ActionEvent event) throws Exception {
        User user = validateUserInput();
        if (user != null) {
            try {
                // Atver jaunu logu, lai lietotājs varētu pieslēgties
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
                Stage stage = new Stage();

                Scene scene = new Scene(fxmlLoader.load());

                stage.setTitle("Menu");
                stage.setScene(scene);

                MenuController menuController = fxmlLoader.getController();
                menuController.setUser(user);
                menuController.generateButtons();
                //root.fillForms();

                stage.show();

                Stage primaryStage = (Stage) loginButton.getScene().getWindow();
                primaryStage.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    // Metode, kas pārbauda lietotāja ievadīto informāciju
    private User validateUserInput() {
        String errorMessage = "";

        User user = findUser(usernameField.getText(), emailField.getText());
        if (user == null) {
            errorMessage += "Such username or email doesn't exist.\n";
        }

        if (usernameField.getText().isEmpty()) {
            errorMessage += "Please enter a username.\n";
        } else if (!validateUsername(usernameField.getText())) {
            errorMessage += "Please enter a valid username.\n";
        }

        if (passwordField.getText().isEmpty()) {
            errorMessage += "Please enter a password.\n";
        } else if (!validatePassword(passwordField.getText())) {
            errorMessage += "Please enter a valid password.\n";
        }

        if (emailField.getText().isEmpty()) {
            errorMessage += "Please enter an email.\n";
        } else if (!validateEmail(emailField.getText())) {
            errorMessage += "Please enter a valid email.\n";
        }

        if (errorMessage.isEmpty()) {
            return user;
        } else {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", errorMessage, ButtonType.OK);
            return null;
        }
    }

    // Metode, kas pārbauda lietotājvārdu derīgumu
    private boolean validateUsername(String username) {
        if (username.length() < 5 || username.length() > 20) {
            return false;
        }
        if (!username.matches("^(?=.{5,}$)[a-zA-Z][a-zA-Z0-9]*$")){
            return false;
        }
        return true;
    }

    // Metode, kas pārbauda paroles derīgumu
    private boolean validatePassword(String password) {
        if (password.length() < 6 || password.length() > 20) {
            return false;
        }
        if (!password.matches("^(?=.{6,}$)[a-zA-Z0-9]*$")) {
            return false;
        }
        return true;
    }

    // Metode, kas pārbauda e-pasta derīgumu
    private boolean validateEmail(String email) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    private void showAlert(AlertType alertType, String title, String message, ButtonType... buttonTypes) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.getButtonTypes().setAll(buttonTypes);
        alert.showAndWait();
    }

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
    // Metode, kas aizpilda formas ar lietotāja datiem
    public void fillForms(User user) {
        usernameField.setText(user.getUsername());
        emailField.setText(user.getEmail());
    }


    // Metode, kas atrod lietotāju pēc lietotājvārda un e-pasta
    private User findUser(String username, String email) {
        if (users == null) {
            System.out.println("Error: users list is null");
            return null;
        }

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getEmail().equals(email)) {
                return user;
            }
        }

        return null;
    }

    @FXML
    private void initialize() {
        try {
            users = readUsersFromFile();
        } catch (Exception e) {
            System.out.println("Error initializing users: " + e.getMessage());
        }
    }

    @FXML
    void focus(MouseEvent event) {
        anchorPane.requestFocus();
    }



}
