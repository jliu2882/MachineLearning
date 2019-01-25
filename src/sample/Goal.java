package sample;

import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;

public class Goal extends Circle{
    public Point2D pos;

    public Goal(int x, int y){
        pos = new Point2D(x,y);
        show();
    }

    //its here because its cool
    public void show(){
        setCenterX(pos.getX());
        setCenterY(pos.getY());
        setRadius(Main.GOALRADIUS);
    }
}
