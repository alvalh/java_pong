package pong;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Ball extends Rectangle{
	
	int yVelocity=1;
	int xVelocity=1;
	Random random;
	
	public Ball(int x, int y, int d) {
		super(x, y, d, d);
		random = new Random();
		int randomXDirection = random.nextInt(2);
		if(randomXDirection == 0)
			randomXDirection--;
		setxVelocity(randomXDirection);
		
		int randomYDirection = random.nextInt(2);
		if(randomYDirection == 0)
			randomYDirection--;
		setyVelocity(randomYDirection);
	}
	
	public void move() {
		y = y + yVelocity;
		x = x + xVelocity;
	}
	
	public void setxVelocity(int velocity) {
		xVelocity = velocity;
	}
	
	public void setyVelocity(int velocity) {
		yVelocity = velocity;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(x, y, height, width);
	}
}
