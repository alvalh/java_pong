package pong;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Game extends JPanel implements Runnable{

	
	static final int width = 700;
	static final int height = 300;
	static final Dimension SCREEN_SIZE = new Dimension(width,height);
	static final int BallDiameter = 15;
	static final int PaddleWidth = 15;
	static final int PaddleHeight = 80;
	Thread gameThread;
	Ball ball;
	Score score;
	Paddle paddle1;
	Paddle paddle2;
	Image image;
	Graphics graphics;
	
	Game(){
		newPaddles();
		newBall();
		newScore();
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread = new Thread(this);
		gameThread.start();
	}	
	
	public void newPaddles() {
		paddle1 = new Paddle(0, ((height/2)-PaddleHeight/2), PaddleHeight, PaddleWidth, 1);
		paddle2 = new Paddle(width-PaddleWidth, ((height/2)-PaddleHeight/2), PaddleHeight, PaddleWidth, 2);
	}
	
	public void newBall() {
		ball = new Ball((width-BallDiameter)/2, (height-BallDiameter)/2, BallDiameter);
	}
	
	public void newScore() {
		score = new Score(width, height);
	}
	
    public void paint(Graphics g) {
  		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
    }
	
	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
	}
	
	public void move() {
		paddle1.move();
		paddle2.move();
		ball.move();
	}
	
	public void checkCollision() {
		//paddles reach end of screen
		if(paddle1.y <= 0) {
			paddle1.y = 0;
		}
		if(paddle1.y >= height - PaddleHeight) {
			paddle1.y = height - PaddleHeight;
		}
		
		if(paddle2.y <= 0) {
			paddle2.y = 0;
		}
		if(paddle2.y >= height - PaddleHeight) {
			paddle2.y = height - PaddleHeight;
		}
		
		//ball hits edge of screen
		if(ball.y >= height-BallDiameter || ball.y <= 0) {
			ball.setyVelocity(-ball.yVelocity);
		}
		//ball hits paddle
		if(ball.intersects(paddle1)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.setxVelocity(ball.xVelocity);
			ball.setyVelocity(ball.yVelocity);
		}
		if(ball.intersects(paddle2)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.setxVelocity(-ball.xVelocity);
			ball.setyVelocity(ball.yVelocity);

		}
		//add points
		if(ball.x <=0) {
			score.player2++;
			newPaddles();
			newBall();
		}
		if(ball.x >= width-BallDiameter) {
			score.player1++;
			newPaddles();
			newBall();
		}
	}
	
	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 64;
		
		double delta = 0;
		
		while(true) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			
			while (delta >= 1) {
				move();
				checkCollision();
				repaint();
				delta--;
			}
		}
	}
}
