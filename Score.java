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

public class Score extends Rectangle {
    
    //declare variables
    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int player1;
    int player2;
    
    //create constructor
    Score(int GAME_WIDTH, int GAME_HEIGHT){
        //assign to variables of the scoring class
        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_HEIGHT;
    }
    
    //create method to draw score on screen
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Arcade",Font.PLAIN,50));       
        //draw a line in the middle
        g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
        //display score
        g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10), (GAME_WIDTH/2)-85, 50);
        g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10), (GAME_WIDTH/2)+20, 50);
        
    }
}
