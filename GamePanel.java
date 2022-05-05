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

public class GamePanel extends JPanel implements Runnable {
    
    /*
    * declare variables 
    * static kayword so multiple game panel classes can all share one variable
    * final keyword to prohibit it from being modified
    * make uppercase because of naming convention for final variables
    */
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555)); //cast as int
    //create dimension
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
    //create ball diameter
    static final int BALL_DIAMETER = 20;
    //create paddle dimentions
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    
    //declare instances
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;
    
    //create constructor
    GamePanel(){
        
        //call methods
        newPaddles();
        newBall();
        
        //finish instantiaiting instance of score class called score
        score = new Score(GAME_WIDTH,GAME_HEIGHT);
        
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);
        
        //start thread
        gameThread = new Thread(this);
        gameThread.start();     
        
    }
    
    //create methods
    public void newBall() {
        //finish instatiating
        random = new Random();
        
        //pass in coordinates of ball to ball constructor
        ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
    }  
    public void newPaddles() {    
        //finish instatiating
        /*
        * create paddle all the way to left or right and in the middle vertically
        * send in paddle height and width
        * pass in unique ID # (1 or 2)
        */
        paddle1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
        paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);

    }   
    public void paint(Graphics g) {
        //create image with dimentions of panel
        image = createImage(getWidth(),getHeight());
        //create graphic
        graphics = image.getGraphics();
        //draw all of the components
        draw(graphics);//pass in graphic created from image
        //draw image
        g.drawImage(image,0,0,this);//pass in image and coordinates and this (our jpanel called game panel)
        
    }   
    public void draw(Graphics g) {
        //draw rectangle
        //take paddle and use draw function passing in graphics g
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }  
    public void move() {
        //called to make paddles move smoother/faster
        paddle1.move();
        paddle2.move();
        ball.move();
    }   
    public void checkCollision() {
        
        //bounce ball off top & bottom window edges
        if(ball.y <= 0) {
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.y >= GAME_HEIGHT-BALL_DIAMETER) {
            ball.setYDirection(-ball.yVelocity);
        }
        
        //bounces ball off paddles
        //paddle one
        if(ball.intersects(paddle1)) {
            //reverse velocity with absolute value to make ball go the other way
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; //optional for more difficulty
            if(ball.yVelocity > 0)
                ball.yVelocity++; //optional for more difficulty
            else
                ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        //paddle two
        if(ball.intersects(paddle2)) {
            //reverse velocity with absolute value to make ball go the other way
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; //optional for more difficulty
            if(ball.yVelocity > 0)
                ball.yVelocity++; //optional for more difficulty
            else
                ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        
        //stops paddles at window edges
        if(paddle1.y<=0)
            paddle1.y=0;
        if(paddle1.y>=(GAME_HEIGHT-PADDLE_HEIGHT))
            paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;
        if(paddle2.y<=0)
            paddle2.y=0;
        if(paddle2.y>=(GAME_HEIGHT-PADDLE_HEIGHT))
            paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT; 
        //give a player 1 point and creates new paddles and ball
        if(ball.x <=0) {
            score.player2++;
            newPaddles();
            newBall();
            System.out.println("Player 2: " + score.player2);
        }
        if(ball.x >= GAME_WIDTH-BALL_DIAMETER) {
            score.player1++;
            newPaddles();
            newBall();
            System.out.println("Player 1: " + score.player1);
        }        
    }   
    public void run() {
        //create game loop
        //create a long value called lastTime
        long lastTime = System.nanoTime();       
        //create variables
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        //create while loop
        while(true) {
            //create variable now
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            if(delta >= 1) {
                //move all of the components
                move();
                //check for any collisions
                checkCollision();
                //repaint everything
                repaint();
                //subtract one from delta
                delta--;
            }           
        }
    }
    
    //create inner class action listener
    public class AL extends KeyAdapter {
        
        //create methods
        public void keyPressed(KeyEvent e) { 
            //call keyPressed method of this class for paddles
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e) {
            //call keyRelease method of this class for paddles
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
