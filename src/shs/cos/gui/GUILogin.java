package shs.cos.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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

        // set the scene
        int w = 500, h = 400;
        Scene scene = new Scene(new Group());

        // https://www.pexels.com/photo/brown-mountain-under-blue-and-white-sky-974091/
        grid.setStyle("-fx-background-image: url('/shs/cos/gui/GUILogin.jpg');");
        grid.setPrefSize(w, h);

        Rectangle r = new Rectangle(w, 200);
        r.setLayoutX(0);
        r.setLayoutY(100);
        r.getStyleClass().add("rect");
        r.setStyle("-fx-fill: rgba(0,0,0,0.61);");

        ((Group)scene.getRoot()).getChildren().add(grid);
//        ((Group)scene.getRoot()).getChildren().add(r);

        // create labels and fields
        Text sceneTitle = new Text("Existing User");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
//        sceneTitle.setStyle("-fx-text-fill: white;");
//        sceneTitle.setTextAlignment(TextAlignment.RIGHT);
        grid.add(sceneTitle, 0, 0, 2, 1);

        Label lblUsername = new Label("Username:");
        lblUsername.setStyle("-fx-text-fill: white; -fx-font-weight: bold");
        grid.add(lblUsername, 0, 1);
        fldUsername = new TextField();
        grid.add(fldUsername, 1, 1);

        Label lblPassword = new Label("Password:");
        lblPassword.setStyle("-fx-text-fill: white; -fx-font-weight: bold");
        grid.add(lblPassword, 0, 2);
        fldPassword = new PasswordField();
        grid.add(fldPassword, 1, 2);

        // dialogue buttons
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);

        Button btnLogin = new Button("Log In");
        btnLogin.setDefaultButton(true);
        btnLogin.setStyle("-fx-background-color: #2DAF3A; -fx-text-fill: white;");
        btnLogin.setOnAction(eventLogIn);
        hbBtn.getChildren().add(btnLogin);

        Button btnNewUser = new Button("New User");
        btnNewUser.setStyle("-fx-background-color: #365caf; -fx-text-fill: white;");
        btnNewUser.setOnAction(eventCreateNewUser);
        hbBtn.getChildren().add(btnNewUser);

        grid.add(hbBtn, 1, 3);

        Platform.runLater(fldUsername::requestFocus);

        // finalize showing
        primaryStage.setTitle("Lucky Strike Valley");
        primaryStage.setMaxWidth(w);  primaryStage.setMinWidth(w);
        primaryStage.setMaxHeight(h); primaryStage.setMinHeight(h);
        primaryStage.setScene(scene);

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
            btnResume.setDefaultButton(true);
            btnResume.setStyle("-fx-background-color: #2DAF3A; -fx-text-fill: white;");
            btnResume.setOnAction(arg0 -> {
                dialogue.close();
                launchGameWindow();
            });

            Button btnNewGame = new Button("New Game");
            btnNewGame.setStyle("-fx-background-color: #365caf; -fx-text-fill: white;");
            btnNewGame.setOnAction(arg0 -> {
                dialogue.close();
                GameManager.clearPlayerData();
                launchGameWindow();
            });

            Button btnLogOut = new Button("Log Out");
            btnLogOut.setStyle("-fx-background-color: #af110e; -fx-text-fill: white;");
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
        username.setPromptText("Required");
        PasswordField password = new PasswordField();
        password.setPromptText("Required");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

        Button btnCreate = new Button("Create");
        btnCreate.setStyle("-fx-background-color: #2DAF3A; -fx-text-fill: white;");
        btnCreate.setDefaultButton(true);
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
        btnCancel.setStyle("-fx-background-color: #af110e; -fx-text-fill: white;");
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