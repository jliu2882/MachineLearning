package sample;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Dot extends Circle {
    private Point2D pos;
    private Point2D vel;
    private Point2D acc;
    public double fitness;
    public Brain brain;
    public boolean dead = false;
    public boolean reachedGoal = false;
    public boolean isBest = false;

    //see i'm cool i used static variables
    public Dot(){
        brain = new Brain(Main.DIRECTIONSIZE);
        pos = new Point2D(Main.DOTSTARTX,Main.DOTSTARTY);
        vel = new Point2D(0,0);
        acc = new Point2D(0,0);
    }
    //gets a random direction from the brain and changes vel and pos its basic physics cmon
    public void move(){
        if(brain.directions.length > brain.step) {
            acc = brain.directions[brain.step];
            brain.step++;
        }
        vel  = vel.add(acc);
        //limits the speed of vel
        double mag = vel.magnitude();
        if(mag > 20){
            vel = vel.normalize();
            vel = vel.multiply(mag);
        }
        pos = pos.add(vel);
    }
    //changes the dots position
    public void update(){
        if(!dead && !reachedGoal) {
            move();
            if (pos.getX() < 2 || pos.getY() < 2 || pos.getX() > Main.WIDTH - 2 || pos.getY() > Main.HEIGHT - 2){
                dead = true;
            }
            else if(distanceToGoal() < Main.GOALRADIUS){
                reachedGoal = true;
            }
        }
        show();
    }
    //shows the dot because javafx  doesn't update position automatically smh
    public void show(){
        if(isBest){
            setFill(Color.LIGHTSEAGREEN);
            setCenterX(pos.getX());
            setCenterY(pos.getY());
            setRadius(Main.DOTRADIUS);
        }else{
            setFill(Color.BLACK);
            setCenterX(pos.getX());
            setCenterY(pos.getY());
            setRadius(Main.DOTRADIUS);
        }
    }
    //tells who was closest and fittest
    public void calculateFitness(){
        //if reached goal, they are more fit
        if(reachedGoal){
            fitness = 10000/(double)(brain.step * brain.step);
        }
        else {
            double distanceToGoal = distanceToGoal();
            fitness = 1 / (distanceToGoal * distanceToGoal);
        }
    }
    //finds distance to goal
    public double distanceToGoal() {
        return Math.sqrt((Main.GOALY-this.getCenterY())*(Main.GOALY-this.getCenterY())
                        + (Main.GOALX-this.getCenterX())*(Main.GOALX-this.getCenterX()));
    }
    //don't need two parents because simple program :)
    public Dot getBaby(){
        Dot baby = new Dot();
        baby.brain = brain.clone();
        return baby;
    }
}
