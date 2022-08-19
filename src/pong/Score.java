package pong;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Score extends Rectangle{
	
	static int width;
	static int height;
	int player1;
	int player2;
	
	Score(int width, int height){
		Score.width = width;
		Score.height = height;
	}
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Consolas",Font.PLAIN,60));
		
		g.drawLine(width/2, 0, width/2, height);
		
		g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10), (width/2)-85, 50);
		g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10), (width/2)+20, 50);
	}
}
