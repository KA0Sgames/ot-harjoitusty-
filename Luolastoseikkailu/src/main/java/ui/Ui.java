package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import domain.creatures.Player;
import domain.CharacterInfo;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import domain.Controller;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import domain.creatures.Creature;
import javafx.scene.shape.Polygon;
import java.util.Random;

public class Ui extends Application {
    private Scene loginScene;
    private Scene userCreationScene;
    private Scene characterScene;
    private Scene gameScene;
    private Controller controller;
    private VBox characterList;
    private Player player;
    private HashMap<Player, Polygon> playerPolygon;
    private HashMap<Creature, Polygon> creatures;
    private Polygons polygons;
    
    public void init() throws Exception {
        this.controller = new Controller("jdbc:sqlite:database.db", new Random());
        this.characterList = new VBox();
        this.playerPolygon = new HashMap<>();
        this.creatures = new HashMap<>();
        this.polygons = new Polygons();
    }
    
    public Node createCharacterNode(Stage stage, CharacterInfo character) {
        HBox characterBox = new HBox(10);
        Button charButton = new Button(character.getName());
        Label charXpInfo = new Label("XP: " + character.getExperience());
        Label charGoldInfo = new Label("Gold: " + character.getGold());
        
        charButton.setOnAction(e -> {
           this.controller.createSession(character.getName(), character.getExperience(), character.getGold());
           stage.setScene(gameScene);
        });
        
        characterBox.getChildren().addAll(charButton, charXpInfo, charGoldInfo);
        return characterBox;
    }
    
    public void redrawCharacterlist(Stage stage, String user) {
        this.characterList.getChildren().clear();
        
        ArrayList<CharacterInfo> characters = this.controller.getCharacters(user);
        System.out.println(characters);
        characters.forEach(character -> {
            this.characterList.getChildren().add(createCharacterNode(stage, character));
        });
    }
    
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
        PasswordField passwordInput = new PasswordField();
        Button loginButton = new Button("Login");
        Button createUserButton = new Button("Create user");
        
        loginPane.add(errorMessage, 2, 1);
        loginPane.add(new Label("Username"), 1, 2);
        loginPane.add(usernameInput, 2, 2);
        loginPane.add(new Label("Password"), 1, 3);
        loginPane.add(passwordInput, 2, 3);
        loginPane.add(loginButton, 2, 4);
        loginPane.add(createUserButton, 2, 5);
        
        this.loginScene = new Scene(loginPane);
        
        loginButton.setOnAction(e -> {
            if (!this.controller.askIfUserExists(usernameInput.getText().trim())) {
                errorMessage.setText("Username doesn't exist");
                errorMessage.setTextFill(Color.RED);
            } else if (!this.controller.askIfPasswordMatches(usernameInput.getText().trim(), passwordInput.getText())) {
                errorMessage.setText("Password incorrect!");
                errorMessage.setTextFill(Color.RED);
            } else {
                this.controller.logInUser(usernameInput.getText().trim());
                redrawCharacterlist(stage, this.controller.getLoggedInUser());
                errorMessage.setText("");
                usernameInput.setText("");
                passwordInput.setText("");
                stage.setScene(this.characterScene);
            }
        });
        
        createUserButton.setOnAction(e -> {
            errorMessage.setText("");
            usernameInput.setText("");
            passwordInput.setText("");
            stage.setScene(this.userCreationScene);
        });
        
        
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
        
        this.userCreationScene = new Scene(userCreationPane);
        
        createUser.setOnAction(e -> {
            if (this.controller.askIfUserExists(newUsername.getText().trim())) {
                errorInCreation.setText("Username is already reserved");
                errorInCreation.setTextFill(Color.RED);
            } else if (newUsername.getText().trim().length() < 4) {
                errorInCreation.setText("Username is too short, at least 4 characters needed.");
                errorInCreation.setTextFill(Color.RED);
            } else if (newPassword.getText().trim().length() < 4) {
                errorInCreation.setText("Password is too short, at least 4 characters needed.");
                errorInCreation.setTextFill(Color.RED);
            } else {
                this.controller.createUser(newUsername.getText().trim(), newPassword.getText().trim());
                errorInCreation.setText("");
                newUsername.setText("");
                newPassword.setText("");
                stage.setScene(this.loginScene);
            }
        });
        
        // character scene:
        
        VBox characterPane = new VBox();
        characterPane.setPrefSize(400, 300);
        characterPane.setPadding((new Insets(10)));
        characterPane.setSpacing(10);
        
        int rows = this.characterList.getChildren().size();

        HBox characterCreation = new HBox(10);
        TextField newCharName = new TextField();
        Button createCharacter = new Button("Create");
        Label errorInCharCreation = new Label("");
            
        createCharacter.setOnAction(e -> {
            if (newCharName.getText().trim().length() < 4) {
                errorInCharCreation.setText("Character name too short, must be atleast 4 characters.");
                errorInCharCreation.setTextFill(Color.RED);
            } else {
                boolean succeeded = this.controller.addCharacter(this.controller.getLoggedInUser(), newCharName.getText().trim());
                if (!succeeded) {
                    errorInCharCreation.setText("Character with that name already exists.");
                    errorInCharCreation.setTextFill(Color.RED);
                }
                System.out.println(this.controller.getLoggedInUser());
                newCharName.setText("");
                redrawCharacterlist(stage, this.controller.getLoggedInUser());
            }
        });
        
        
        characterCreation.getChildren().addAll(newCharName, createCharacter, errorInCharCreation);
        
        Button logoutButton = new Button("Logout");
        
        logoutButton.setOnAction(e -> {
            this.controller.logOutUser();
            stage.setScene(this.loginScene);
        });
        System.out.println(rows);
        if (rows < 3) {
            characterPane.getChildren().addAll(this.characterList, characterCreation, logoutButton);
        } else {
            characterPane.getChildren().addAll(this.characterList, logoutButton);
        }
        
        this.characterScene = new Scene(characterPane);
        
        // game scene
        
        Pane screen = new Pane();
        screen.setPrefSize(1200, 800);
        
        this.player = this.controller.initPlayer();

        this.playerPolygon.put(this.player, this.polygons.createPolygon(this.player.getName()));
        
        this.playerPolygon.get(this.player).setTranslateX(this.player.getX());
        this.playerPolygon.get(this.player).setTranslateY(this.player.getY());
        
        screen.getChildren().add(this.playerPolygon.get(this.player));
         
        ArrayList<Creature> startCreatures = this.controller.initGame();
        System.out.println("Start creatures: " + startCreatures);
        for (Creature startCreature : startCreatures) {
            Polygon portrait = this.polygons.createPolygon(startCreature.getName());
            portrait.setTranslateX(startCreature.getX());
            portrait.setTranslateY(startCreature.getY());
            this.creatures.put(startCreature, portrait);
        }
           
        for (Polygon portrait : this.creatures.values()) {
            screen.getChildren().add(portrait);
        }
        
        this.gameScene = new Scene(screen);
        stage.setTitle("Caventure");
        stage.setScene(loginScene);
        stage.show();
        
        Map<KeyCode, Boolean> pressedKeys = new HashMap<>();
        
        this.gameScene.setOnKeyPressed(event -> {
            pressedKeys.put(event.getCode(), Boolean.TRUE);
        });
        
        this.gameScene.setOnKeyReleased(event -> {
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
                
                playerPolygon.get(player).setTranslateX(player.getX());
                playerPolygon.get(player).setTranslateY(player.getY());
                
                controller.UpdateCreatures();
                
                for (Creature creature: creatures.keySet()) {
                    creatures.get(creature).setTranslateX(creature.getX());
                    creatures.get(creature).setTranslateY(creature.getY());
                }
            }
        }.start();
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
