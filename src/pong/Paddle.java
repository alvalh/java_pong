package pong;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Paddle extends Rectangle {
	
	int num;
	int yVelocity;
	int speed = 2;
	
	public Paddle(int x, int y, int PaddleHeight, int PaddleWidth, int num) {
		super(x, y, PaddleHeight, PaddleWidth);
		this.num = num;
	}
	
	public void keyPressed(KeyEvent e) {
		switch(num){
		case 1:
			if(e.getKeyCode() == KeyEvent.VK_W) {
				setY(-speed);
			}
			if(e.getKeyCode() == KeyEvent.VK_S) {
				setY(speed);
			}
			break;
		case 2:
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				setY(-speed);
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				setY(speed);
			}			
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
		switch(num){
		case 1:
			if(e.getKeyCode() == KeyEvent.VK_W) {
				setY(0);
			}
			if(e.getKeyCode() == KeyEvent.VK_S) {
				setY(0);
			}
			break;
		case 2:
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				setY(0);
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				setY(0);
			}			
			break;
		}
	}
	
	public void setY(int velocity) {
		yVelocity = velocity;
	}
	
	public void move() {
		y = y + yVelocity;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, height, width);
	}
}
