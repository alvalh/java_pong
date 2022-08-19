package pong;

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {
	
	Game game = new Game();
	
	GameFrame(){
		
		JFrame frame = new JFrame("Example Frame");
	
		frame.setTitle("Pong");
		frame.add(game);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setBackground(Color.black);
		frame.pack();
		frame.setVisible(true);
	}
	
}
