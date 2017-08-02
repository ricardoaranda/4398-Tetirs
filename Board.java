import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


class Board extends JPanel
{ 
	private Image image; // board backgroud image
	
	public Board() 
	{  }   
	public Board(int row, int col, Image image) 
	{
		super();   
		this.row = row;   
		this.col = col;   
		this.image = image;  
	}    
	public int getRow() 
	{   
		return row;  
	}    
	
	public void setRow(int row) 
	{   
		this.row = row;  
	}    
	
	public int getCol() 
	{   
		return col;  
	} 
	
	public void setCol(int col) 
	{   
		this.col = col;  
	}       
	
	public Image getImage() 
	{   
		return image;  
	}    
	
	public void setImage(Image image) 
	{   
		this.image = image;  
	} 
	
	
	public static final int BLOCK_SIZE = 24;
	public static final int BOTTOM = 510;

	private BufferedImage tiles;
	private int color;
	private int[][] coords;
	private Tetris t;
	private int level;
	private int bottomBorder;
	private int row; 
	private int col;  
	private int numRotations;


	// constructor for the boards gui components
	public Board() 
	{ 
		col = 190;
		row = 32;
		level = 1;
		
		newShape();
		loadImage();
	} 
	
	public void rotate(){
		coords = t.getState(numRotations%4);
		numRotations++;
	}
	
	public void speedDown(){
		//row = row + 15;
		GUIManager.setSpeed(1000);
	}
	public void drop(){
		row = BOTTOM;
	}
	public void moveRight(){
		col = col - 15;
	}
	public void moveLeft() {
		col = col + 15;
	}
	
	
	// paints the board and renders the block shapes at the same time
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		printShape(col, row, g);
		paintBoard(g);

	}
	
	// resets the y position of the block so it stays on the screen
	public void updatePosition(){
		if(row <= BOTTOM-bottomBorder)
			row += 1;
		else{
			row = 32;
			repaint();
			newShape();
		}
	}
	
	// gets a random block shape, color, and coordinates
	public void newShape(){
		t = Tetris.randomOne();
		color = BLOCK_SIZE * t.getColor();
		coords = t.getShape();
	}
	
	// loads the tile image 
	private void loadImage(){
		tiles = null;
		// reads the image file of the blocks
		try {
			tiles = ImageIO.read(new File("C:\\Users\\Amy\\Documents\\GitHub\\Tetris\\tiles.png"));
		} catch (IOException e) {
			
		}
	}
	
	//paints the simple lines of the gameplay board
	private void paintBoard(Graphics g){
		// prints the board lines
		g.drawRect(100, 30, 240, BOTTOM);
		
		g.drawString("Hold", 420, 40);
		g.drawRect(400, 50, 100, 100);
		
		g.drawString("Up Next", 420, 190);
		g.drawRect(400, 200, 100, 100);
	}
	
	// prints the tetris blocks based on 2D array coordinates
	private void printShape(int x, int y, Graphics g){
		//crops the image to a single 24X24 block
		BufferedImage block = tiles.getSubimage(color, 0, BLOCK_SIZE, BLOCK_SIZE);
		
		//prints the block in the shape of the tetromino
		for(int row = 0; row < coords.length; row++)
			for(int col = 0; col < coords[row].length; col++)
				if(coords[row][col] == 1){
					g.drawImage(block, col*BLOCK_SIZE+x, row*BLOCK_SIZE+y, null);
					bottomBorder += BLOCK_SIZE * row;
				}
		bottomBorder = 0;
	}
	

}
