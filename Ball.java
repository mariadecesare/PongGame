/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecttwo;

/**
 *
 * @author mariadecesare
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Ball extends Rectangle {
    
    //create instance of random class
    Random random;
    
    //declare variables
    int xVelocity;
    int yVelocity;
    int initialSpeed = 2;
    
    //create constructor
    Ball(int x, int y, int width, int height){
        /*
        *can call the super constructor to send in these arguments for us
        *because ball is a subclass of the rectangle superclass  
        */
        super(x,y,width,height);
        
        //set random direction in which ball will head
        random = new Random();
        //create local variable for ball constructor
        int randomXDirection = random.nextInt(2);
        if(randomXDirection == 0)
            randomXDirection--;
        setXDirection(randomXDirection*initialSpeed);
        int randomYDirection = random.nextInt(2);
        if(randomYDirection == 0)
            randomYDirection--;
        setYDirection(randomYDirection*initialSpeed);        
    }
    
    //create methods
    
    //sets direction
    public void setXDirection(int randomXDirection) {
        xVelocity = randomXDirection;
    }
    public void setYDirection(int randomYDirection) {
        yVelocity = randomYDirection;      
    }    
    //sets movement
    public void move() {
        x += xVelocity;
        y += yVelocity;
    }
    //draws ball
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, height, width);
    }
}
