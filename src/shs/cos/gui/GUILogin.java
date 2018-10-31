package shs.cos.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;

public class GUILogin extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("Lucky Strike Valley");
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

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);

        Button btnLogin = new Button("Login");
        btnLogin.setOnAction(login);
        hbBtn.getChildren().add(btnLogin);

        Button btnCancel = new Button("New User");
        hbBtn.getChildren().add(btnCancel);
        grid.add(hbBtn, 1, 3);

        Scene scene = new Scene(grid, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("LSV: Log In");
        primaryStage.show();
    }

    TextField fldUsername;
    TextField fldPassword;
    EventHandler<ActionEvent> login = e -> {
        if (loginAttempt(fldUsername.getText(), fldPassword.getText())) {
            GUIGame gui = new GUIGame();
            gui.makeGameWindow();
        }
    };

    private String separator = "::";
    private TreeMap<String, String> map = new TreeMap<>();

    private boolean loginAttempt(String username, String password) {
        // attempt to load the file
        Scanner fileIn;
        try {
            fileIn = new Scanner(new File("userdata/" + username + ".txt"));
        } catch (FileNotFoundException e) {
            // TODO: dialogue box
            System.out.println("File does not exist. Try again, or start a new game.");
            return false;
        }

        // attempt successful, read it in
        while (fileIn.hasNextLine()) {
            String[] nextLine = fileIn.nextLine().split(separator);
            map.put(nextLine[0], nextLine[1]);
        }

        // check that passwords match
        if (password.equals(map.get("pw"))) {
            // TODO: pass data to the game controller.
            return true;
        } else {
            System.out.println("Incorrect password.");
            return false;
        }
    }
}