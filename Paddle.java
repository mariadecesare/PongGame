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

public class Paddle extends Rectangle {
    
    //declare variables
    int id;
    int yVelocity; 
    int speed = 10;
    
    //create constructor
    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
        /*
        *can call the super constructor to send in these arguments for us
        *because paddle is a subclass of the rectangle superclass  
        */        
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
        this.id=id;
    }    
    
    //create methods
    public void keyPressed(KeyEvent e) {
        //create switch to examine contents of our ID variable
        switch(id) {
        case 1://for paddle one
            //create statement to execude code if someone types certain thing(W,S,UP, or DOWN on their keyboard
            if(e.getKeyCode()==KeyEvent.VK_W) {
                setYDirection(-speed);//move up on Y axis
                //call move function
                move();
            }
            if(e.getKeyCode()==KeyEvent.VK_S) {
                setYDirection(speed);//move down on Y axis
                //call move function
                move();
            }   
            break;
        case 2://for paddle two
            //create statement to execude code if someone types W on their keyboard
            if(e.getKeyCode()==KeyEvent.VK_UP) {
                setYDirection(-speed);//move up on Y axis
                //call move function
                move();
            }
            if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                setYDirection(speed);//move down on Y axis
                //call move function
                move();
            }    
            break;
        }
    }
    public void keyReleased(KeyEvent e) {
        //same as keyPressed except takes in 0 instead of speed so it doesn't go forever
        switch(id) {
        case 1://for paddle one
            //create statement to execude code if someone types certain thing(W,S,UP, or DOWN on their keyboard
            if(e.getKeyCode()==KeyEvent.VK_W) {
                setYDirection(0);//move up on Y axis
                //call move function
                move();
            }
            if(e.getKeyCode()==KeyEvent.VK_S) {
                setYDirection(0);//move down on Y axis
                //call move function
                move();
            }   
            break;
        case 2://for paddle two
            //create statement to execude code if someone types W on their keyboard
            if(e.getKeyCode()==KeyEvent.VK_UP) {
                setYDirection(0);//move up on Y axis
                //call move function
                move();
            }
            if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                setYDirection(0);//move down on Y axis
                //call move function
                move();
            }    
            break;
        }        
    }    
    public void setYDirection(int yDirection) {
        yVelocity = yDirection;       
    }
    public void move() {
        y = y + yVelocity;
    }
    public void draw(Graphics g) {
        //draw paddles
        //player 1
        if(id==1)
            g.setColor(Color.blue);
        //player 2
        else
            g.setColor(Color.red);
        //fill rectangle
        g.fillRect(x, y, width, height);
    }
}
