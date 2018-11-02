package shs.cos.gui;

import javafx.application.Application;
import javafx.application.Platform;
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
import shs.cos.utils.GameManager;

public class GUILogin extends Application {

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

        grid.add(new Label("Username:"), 0, 1);
        fldUsername = new TextField();
        grid.add(fldUsername, 1, 1);

        grid.add(new Label("Password:"), 0, 2);
        fldPassword = new PasswordField();
        grid.add(fldPassword, 1, 2);

        // dialogue buttons
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);

        Button btnLogin = new Button("Log In");
        btnLogin.setOnAction(eventLogIn);
        hbBtn.getChildren().add(btnLogin);

        Button btnNewUser = new Button("New User");
        btnNewUser.setOnAction(eventCreateNewUser);
        hbBtn.getChildren().add(btnNewUser);

        grid.add(hbBtn, 1, 3);

        Platform.runLater(fldUsername::requestFocus);

        // set the scene
        Scene scene = new Scene(grid, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Lucky Strike Valley");
        primaryStage.show();
    }

    private void launchGameWindow() {
        Main.loadFinalize();
    }

    private TextField fldUsername, fldPassword;

    private EventHandler<ActionEvent> eventLogIn = e ->
            createDialogueLogin(fldUsername.getText(), fldPassword.getText(), false);

    /**
     * Handles everything that happens after the user hits the "log in" button.
     */
    private void createDialogueLogin(String username, String password, boolean hasLogged) {
        if (!(hasLogged)) hasLogged = GameManager.attemptLogIn(username, password);

        if (hasLogged) {
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

            Text textWelcome = new Text("Welcome, " + GameManager.currentUser + "!");
            textWelcome.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            hbWelcome.getChildren().add(textWelcome);
            grid.add(hbWelcome, 1, 0);

            clearLoginFields();

            Button btnResume = new Button("Resume Game");
            btnResume.setOnAction(arg0 -> {
                dialogue.close();
                launchGameWindow();
            });

            Button btnNewGame = new Button("New Game");
            btnNewGame.setOnAction(arg0 -> {
                dialogue.close();
                GameManager.clearPlayerData();
                launchGameWindow();
            });

            Button btnLogOut = new Button("Log Out");
            btnLogOut.setOnAction(arg0 -> dialogue.close());

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
    }

    private void clearLoginFields() {
        fldUsername.clear();
        fldPassword.clear();
    }

    private EventHandler<ActionEvent> eventCreateNewUser = e -> createNewUser();

    /**
     * Reference used: https://code.makery.ch/blog/javafx-dialogs-official/
     */
    private void createNewUser() {
        Stage dialog = new Stage();
        dialog.setTitle("Create New User");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setResizable(false);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

        Button btnCreate = new Button("Create");
        btnCreate.setOnAction(a -> {
            String user = username.getText();
            String pw = password.getText();

            if (user.isEmpty() || pw.isEmpty()) return;

            GameManager.clearPlayerData();
            GameManager.createNewSaveFile(user);
            GameManager.updatePlayerData(GameManager.idPassword, pw);
            GameManager.updateFile();

            dialog.close();
            createDialogueLogin(user, pw, true);

        });

//        btnCreate.setDisable(true);
//
//        username.textProperty().addListener((observable, oldValue, newValue) ->
//                btnCreate.setDisable(newValue.trim().isEmpty()));

        Button btnCancel = new Button("Cancel");
        btnCancel.setOnAction(a -> dialog.close());

        Platform.runLater(username::requestFocus);

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BASELINE_RIGHT);
        hbBtn.getChildren().add(btnCreate);
        hbBtn.getChildren().add(btnCancel);

        grid.add(hbBtn, 1, 2);

        Scene scene = new Scene(grid, 400, 200);
        dialog.setScene(scene);

        dialog.showAndWait();
    }

    public static void displayWarning(String warning) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(null);
        alert.setHeaderText(null);

        alert.setContentText(warning);
        alert.showAndWait();
    }


}