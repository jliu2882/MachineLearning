package sample;

import javafx.scene.paint.Color;

public class Population {
    Dot[] dots;
    double fitnessSum;
    int generation = 1;
    int bestDotIndex = 0;
    int minStep = Main.DIRECTIONSIZE;

    public Population(int size){
        dots = new Dot[size];
        for(int i = 0; i < size; i++){
            dots[i] = new Dot();
        }
    }
    //here because javafx was being a bum
    public Dot[] getDots(){
        return dots;
    }
    //it just does what it does
    public void update(){
        for(int i = 0; i < dots.length; i++){
            //kill the weak
            if(dots[i].brain.step>minStep){
                dots[i].dead = true;
            }
            else {
                dots[i].update();
            }
        }
        dots[0].show();
    }
    //yessir
    public void calculateFitness(){
        for(int i = 0; i < dots.length; i++){
            dots[i].calculateFitness();
        }
    }
    //test if all dots are dead
    public boolean allDotsDead(){
        for(int i = 0; i < dots.length; i++){
            if(!dots[i].dead && !dots[i].reachedGoal){
                return false;
            }
        }
        return true;
    }
    //selects the best dots and lets them make babies for the next generation
    public void naturalSelection(){
        Dot[] newDots = new Dot[dots.length];
        setBestDot();
        calculateFitnessSum();

        //he's immortal baby!
        newDots[0] = dots[bestDotIndex];
        newDots[0].isBest = true;
        for(int i = 1; i < newDots.length;i++){
            Dot parent = selectParent();
            newDots[i] = parent.getBaby();
        }
        update();
        dots = newDots.clone();
        generation++;
    }
    //chooses parent through some nice rng
    public Dot selectParent(){
        //random number between 0 and fitnesssSum
        double rand = Math.random()*fitnessSum;
        //a bigger fitness means more chance to be greater than the random number
                //ie bigger fitness = survive
        double runningSum = 0;
        for(int i = 0; i < dots.length; i++){
            runningSum += dots[i].fitness;
            if(runningSum > rand){
                return dots[i];
            }
        }
        //shouldn't reach here
        return null;
    }
    //calculates.. fitness sum
    public void calculateFitnessSum(){
        fitnessSum=0;
        for(int i = 0; i < dots.length; i++){
            fitnessSum += dots[i].fitness;
        }
    }
    //mutate babies brains o no
    public void mutate(){
        for(int i = 1; i < dots.length;i++){
            dots[i].brain.mutate();
        }
    }
    //finds best dot and makes him immortal so dots don't devolve
    public void setBestDot(){
        double max = 0;
        int maxIndex = 0;
        for(int i = 0; i < dots.length;i++){
            if(dots[i].fitness >max){
                max = dots[i].fitness;
                maxIndex=i;
            }
        }
        bestDotIndex = maxIndex;

        if(dots[bestDotIndex].reachedGoal){
            minStep = dots[bestDotIndex].brain.step;
            System.out.println("The best dot of generation " + generation + " took " + minStep + "steps");
        }
    }
}
