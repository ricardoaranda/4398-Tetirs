
import javax.swing.*;

public class GUIManager extends JPanel {
	
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	private JFrame window;
	private Board board;

	
	public GUIManager(){

		window = new JFrame("Tetris Game");
		window.setSize(WIDTH, HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		board = new Board();
		board.loadImage();
		window.add(board);
		window.setVisible(true);
		
	}
	
	public static void main(String args[]){
		new GUIManager();
	}
	

}
;