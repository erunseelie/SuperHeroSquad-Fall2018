package shs.cos.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import shs.cos.Main;
import shs.cos.utils.IO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeMap;

public class GUILogin extends Application {

    public TreeMap<String, String> mapSaveData = new TreeMap<>();
    public String currentUser;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        // create labels and fields
        Text sceneTitle = new Text("Existing User:");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);

        Label lblUsername = new Label("Username:");
        grid.add(lblUsername, 0, 1);
        fldUsername = new TextField();
        grid.add(fldUsername, 1, 1);

        Label lblPassword = new Label("Password:");
        grid.add(lblPassword, 0, 2);
        fldPassword = new PasswordField();
        grid.add(fldPassword, 1, 2);

        // dialogue buttons
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);

        Button btnLogin = new Button("Log In");
        btnLogin.setOnAction(login);
        hbBtn.getChildren().add(btnLogin);

        Button btnNewUser = new Button("New User");
        btnNewUser.setOnAction(createNewUser);
        hbBtn.getChildren().add(btnNewUser);

        grid.add(hbBtn, 1, 3);

        // set the scene
        Scene scene = new Scene(grid, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Lucky Strike Valley");
        primaryStage.show();
    }

    private TextField fldUsername, fldPassword;

    /**
     * Handles everything that happens after the user hits the "log in" button.
     */
    private EventHandler<ActionEvent> login = e -> {
        if (attemptLogIn(fldUsername.getText(), fldPassword.getText())) {
            Stage dialogue = new Stage();
            dialogue.initModality(Modality.APPLICATION_MODAL);
            dialogue.setResizable(false);

            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(10, 10, 10, 10));

            HBox hbWelcome = new HBox(10);
            hbWelcome.setAlignment(Pos.BASELINE_CENTER);

            Text textWelcome = new Text("Welcome, " + currentUser + "!");
            textWelcome.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            hbWelcome.getChildren().add(textWelcome);
            grid.add(hbWelcome, 1, 0);

            Button btnResume = new Button("Resume Game");
            btnResume.setOnAction(arg0 -> {
                clearLoginFields();
                dialogue.close();
                // TODO: load from save file
                launchGameWindow();
            });

            Button btnNewGame = new Button("New Game");
            btnNewGame.setOnAction(arg0 -> {
                clearLoginFields();
                dialogue.close();
                // TODO: create save file, load new game w/o userdata
                launchGameWindow();
            });

            Button btnLogOut = new Button("Log Out");
            btnLogOut.setOnAction(arg0 -> {
                clearLoginFields();
                dialogue.close();
            });

            HBox hbBtn = new HBox(10);
            hbBtn.setAlignment(Pos.BASELINE_CENTER);
            hbBtn.getChildren().add(btnResume);
            hbBtn.getChildren().add(btnNewGame);
            hbBtn.getChildren().add(btnLogOut);

            grid.add(hbBtn, 1, 1);

            Scene scene = new Scene(grid, 500, 100);

            dialogue.setScene(scene);
            dialogue.show();
        }
    };

    private void launchGameWindow() {
        Main.loadFinalize();
    }

    private void clearLoginFields() {
        fldUsername.clear();
        fldPassword.clear();
    }

    private EventHandler<ActionEvent> createNewGame = e -> {
        // TODO: the user has created a new account, and we create & immediately launch a new game.
        clearLoginFields();
        launchGameWindow();
    };

    private EventHandler<ActionEvent> createNewUser = e -> {
        // TODO: the user has selected the "new user" button.
    };

    private String saveDirectory = "res/saves/", saveExtension = ".txt";

    /**
     * Copies all the data from a TreeMap to the designated file.
     * @param username the name of the file to write to.
     * @return whether the save file was successfully created & written to.
     */
    public boolean saveToFile(String username) {
        File f = new File(saveDirectory + username + saveExtension);
        PrintWriter fileOut;
        try {
            fileOut = new PrintWriter(f);
        } catch (FileNotFoundException e) {
            return false;
        }
        mapSaveData.forEach((k, v) -> {
            fileOut.write(k + IO.separator + v);
        });
        fileOut.close();
        return true;
    }

    /**
     * Attempts to load the save file associated with the given username.
     * If the file is found and the password is correct, the save file's data
     * is copied into this class' TreeMap.
     * @param username Username
     * @param password Password
     * @return Whether the login attempt was successful.
     */
    private boolean attemptLogIn(String username, String password) {
        // attempt to load the file
        Scanner fileIn;
        try {
            fileIn = new Scanner(new File( saveDirectory + username + saveExtension));
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("That user doesn't exist.\nTry again, or start a new game.");
            alert.showAndWait();
            return false;
        }

        // attempt successful, read it in
        while (fileIn.hasNextLine()) {
            String[] nextLine = fileIn.nextLine().split(IO.separator);
            mapSaveData.put(nextLine[0], nextLine[1]);
        }
        fileIn.close();

        // check that passwords match
        if (password.equals(mapSaveData.get("pw"))) {
            currentUser = username;
            return true;
        } else {
            mapSaveData.clear();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Your password was incorrect.\nTry again.");
            alert.showAndWait();
            return false;
        }
    }
}