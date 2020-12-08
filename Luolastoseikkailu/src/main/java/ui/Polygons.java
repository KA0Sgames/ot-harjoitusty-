package ui;

/**
 * Class provides templates for creating polygons for each creature type
 */

import javafx.scene.shape.Polygon;

public class Polygons {
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
