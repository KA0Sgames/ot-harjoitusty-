package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import domain.Player;
import java.util.HashMap;
import java.util.Map;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

public class Ui extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        // login scene:
        
        GridPane loginPane = new GridPane();
        loginPane.setPrefSize(400, 300);
        loginPane.setPadding(new Insets(10));
        loginPane.setVgap(10);
        loginPane.setHgap(10);
        
        Label errorMessage = new Label("");
        TextField usernameInput = new TextField();
        TextField passwordInput = new TextField();
        Button loginButton = new Button("Login");
        Button createUserButton = new Button("Create user");
        
        loginPane.add(errorMessage, 2, 1);
        loginPane.add(new Label("Username"), 1, 2);
        loginPane.add(usernameInput, 2, 2);
        loginPane.add(new Label("Password"), 1, 3);
        loginPane.add(passwordInput, 2, 3);
        loginPane.add(loginButton, 2, 4);
        loginPane.add(createUserButton, 2, 5);
        
        Scene loginScene = new Scene(loginPane);
        
        // user creation scene:
        
        GridPane userCreationPane = new GridPane();
        userCreationPane.setPrefSize(400, 300);
        userCreationPane.setPadding((new Insets(10)));
        userCreationPane.setVgap(10);
        userCreationPane.setHgap(10);
        
        Label errorInCreation = new Label("");
        TextField newUsername = new TextField();
        TextField newPassword = new TextField();
        Button createUser = new Button("Create");
        
        userCreationPane.add(errorInCreation, 2, 1);
        userCreationPane.add(new Label("Username:"), 1, 2);
        userCreationPane.add(newUsername, 2, 2);
        userCreationPane.add(new Label("Password:"), 1, 3);
        userCreationPane.add(newPassword, 2, 3);
        userCreationPane.add(createUser, 2, 4);
        
        Scene userCreationScene = new Scene(userCreationPane);
        
        
        Pane screen = new Pane();
        screen.setPrefSize(1200, 800);
        
        Player player = new Player(50, 400);
        
        screen.getChildren().add(player.getCharacter());
        
        Scene scene = new Scene(screen);
        stage.setTitle("Caventure");
        stage.setScene(loginScene);
        stage.show();
        
        Map<KeyCode, Boolean> pressedKeys = new HashMap<>();
        
        scene.setOnKeyPressed(event -> {
            pressedKeys.put(event.getCode(), Boolean.TRUE);
        });
        
        scene.setOnKeyReleased(event -> {
            pressedKeys.put(event.getCode(), Boolean.FALSE);
        });
        
        new AnimationTimer() {
            
            @Override
            public void handle(long present) {
                if (pressedKeys.getOrDefault(KeyCode.LEFT, Boolean.FALSE)) {
                    player.moveLeft();
                }
                
                if (pressedKeys.getOrDefault(KeyCode.RIGHT, Boolean.FALSE)) {
                    player.moveRight();
                }
                
                if (pressedKeys.getOrDefault(KeyCode.UP, Boolean.FALSE)) {
                    player.moveUp();
                }
                
                if (pressedKeys.getOrDefault(KeyCode.DOWN, Boolean.FALSE)) {
                    player.moveDown();
                }
            }
        }.start();
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
