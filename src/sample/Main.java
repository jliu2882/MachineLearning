package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//TODO OBSTACLES AND MAKE BEST DOT JOIN NEXT GENERATION RATHER THAN STAY STILL AND BRAIN HAS OTHER THINGS IDK


public class Main extends Application {

    // some static variables idk i wanted to be cool
    //MAP PROPERTIES
    public static int WIDTH = 1000;
    public static int HEIGHT = 1000;
    //POPULATION PROPERTIES
    public static int DOTSTARTX = WIDTH/2;
    public static int DOTSTARTY = 3*HEIGHT/4;
    public static int DOTRADIUS = 3;
    public static int POPSIZE = 1000;
    public static double MUTATIONRATE = 0.01;
    public static int DIRECTIONSIZE = 400;
    //GOAL PROPERTIES
    public static int GOALX = 250;
    public static int GOALY = 250;
    public static int GOALRADIUS = 10;
    //OBSTACLE PROPERTIES
    public static int x = 100;
    public static int y = 100;
    public static int oWidth = 100;
    public static int oHeight = 100;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //initializes all the dots
        Population test = new Population(POPSIZE);

        Goal goal = new Goal(GOALX, GOALY);
        //wasn't cool enough so color changes to goal go here
        goal.setFill(Color.RED);

        Group root = new Group();
        for(int i = 0; i < test.getDots().length;i++) {
            root.getChildren().add(test.getDots()[i]);
        }
        root.getChildren().add(goal);

        primaryStage.setTitle("What am I doing? smh");
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();

        //loop for dots to do things
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(test.allDotsDead()){
                    //clear all dead dots
                    root.getChildren().clear();
                    //how to tell what can make babies
                    test.calculateFitness();
                    //chooses what can make babies
                    test.naturalSelection();
                    //change babies so they aren't the exact same and fail
                    test.mutate();
                    //add dots back into sight
                    for(int i = 0; i < test.getDots().length;i++) {
                        root.getChildren().add(test.getDots()[i]);
                    }
                    root.getChildren().add(goal);
                }
                else{
                    test.update();
                }
            }
        };
        gameLoop.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
