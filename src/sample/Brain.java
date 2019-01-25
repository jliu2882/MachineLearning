package sample;

import javafx.geometry.Point2D;


public class Brain {
    Point2D[] directions;
    int step = 0;
    //it be like that
    public Brain(int size){
        directions = new Point2D[size];
        randomize();
    }
    //fills up the array with random vectors for the acceleration
    public void randomize(){
        for(int i = 0; i < directions.length;i++){
            setAngle(i);
        }
    }
    //clones the brain
    public Brain clone(){
        Brain clone = new Brain(directions.length);
        for(int i = 0; i < directions.length;i++){
            //might need to change because clones direction might change with regular direction
            clone.directions[i] = directions[i];
        }
        return clone;
    }
    //mutates the brains
    public void mutate(){
        for(int i = 0; i < directions.length;i++){
            //iff random number is less than the mutation rate
                //i.e. don't mutate all the time
            //could improve with slight changes rather than total changes
            if(Math.random()<Main.MUTATIONRATE){
                setAngle(i);
            }
        }
    }
    //create an angle and then set it in terms of radians so math.cos/math.sin would work
    public void setAngle(int i){
        double randomAngle = (int)(Math.random()*(360))+1;
        randomAngle = randomAngle * Math.PI / 180;
        directions[i] = new Point2D(Math.cos(randomAngle),Math.sin(randomAngle));
    }
}
