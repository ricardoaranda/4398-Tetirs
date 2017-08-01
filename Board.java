import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


class Board extends JPanel
{
	private int row; 
	private int col;  
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
	public static final int VERTICAL_MIDDLE = 32;
	public static final int HORIZONTAL_MIDDLE = 190;
	
	private BufferedImage tiles;
	private int color;
	// test shape
	private int[][] coords = new int[][]{{0,1,1},
					     {1,1,0},
					     {0,0,0}};
	
	
	
	// Loads an image file of individual tiles
	public void loadImage(){
		tiles = null;
		// reads the image file of the blocks
		try {
			tiles = ImageIO.read(new File("C:\\Users\\Amy\\Documents\\GitHub\\Tetris\\tiles.png"));
		} catch (IOException e) {
			
		}
	}
	
	//gets the shape and color from the generated shape
	public void retrieveShape(){
		Tetris t = Tetris.randomOne();
		color = BLOCK_SIZE * t.getColor();
		coords = t.getShape();
	}
	
	
	// paints the board and renders the block shapes at the same time
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		//crops the image to a single 24X24 block
		retrieveShape();
		BufferedImage block = tiles.getSubimage(color, 0, BLOCK_SIZE, BLOCK_SIZE);
		
		//prints the block in the shape of the tetromino
		for(int row = 0; row < coords.length; row++)
			for(int col = 0; col < coords[row].length; col++)
				if(coords[row][col] == 1)
					g.drawImage(block, col*BLOCK_SIZE+HORIZONTAL_MIDDLE, row*BLOCK_SIZE+VERTICAL_MIDDLE, null);
		
		
		// prints the board lines
		g.drawRect(100, 30, 240, 480);
		g.drawString("Hold", 420, 40);
		g.drawRect(400, 50, 100, 100);
		g.drawString("Up Next", 420, 190);
		g.drawRect(400, 200, 100, 100);

	}
