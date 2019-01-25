package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Obstacle extends Rectangle {
    public int x;
    public int y;
    public int width;
    public int height;

    public Obstacle(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void show(){
        setFill(Color.BLUE);
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        //setArcWidth(20);
        //setArcHeight(20);
    }
}
