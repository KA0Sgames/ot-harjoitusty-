package ui;

/**
 * Class provides templates for creating polygons for each creature type.
 */

import javafx.scene.shape.Polygon;

public class Polygons {
    
    /**
     * Method for picking right polygon model of existing predetermined models to depict creatures in the game.
     * Ui will display creatures in the game as these polygons and for each creature a suitable polygon is picked
     * depending on it's name (type in the game).
     * @param creature name of the creature (type in the game, like spider).
     * @return Polygon picked to represent given creature.
     */
    public Polygon createPolygon(String creature) {
        Polygon newPolygon = new Polygon();
        switch (creature) {
            case "Player":
                newPolygon = new Polygon(-10, 0, 0, -10, 10, 0, 5, 10, -5, 10);
                break;
            case "spider":
                newPolygon = new Polygon(-5, 0, 0, 5, 5, 0, 0, -5);
        }
        
        return newPolygon;
    }
}
