package luolastoseikkailu.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import domain.Player;
import java.util.HashMap;
import java.util.Map;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

public class Ui extends Application {
    
    public void start(Stage stage) throws Exception {
        Pane screen = new Pane();
        screen.setPrefSize(1200, 800);
        
        Player player = new Player(50, 400);
        
        screen.getChildren().add(player.getCharacter());
        
        Scene scene = new Scene(screen);
        stage.setTitle("Caventure");
        stage.setScene(scene);
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
                if(pressedKeys.getOrDefault(KeyCode.LEFT, Boolean.FALSE)) {
                    player.moveLeft();
                }
                
                if(pressedKeys.getOrDefault(KeyCode.RIGHT, Boolean.FALSE)) {
                    player.moveRight();
                }
            }
        }.start();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
