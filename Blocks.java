import java.util.Random;

public abstract class Tetris
{
	//four blocks 
	protected int[][] cells;
	protected State[] state; //rotate state
	protected int index = 100000;

	//rotate
	protected class State
	{
		int row0, col0, row1, col1, row2, col2, row3, col3; //rotate one circle 
		
		public State(int row0, int col0, int row1, int col1, int row2, int col2, int row3, int col3)
		{
			super();
			this.row0 = row0;
			this.col0 = col0;
			this.row1 = row1;
			this.col1 = col1;
			this.row2 = row2;
			this.col2 = col2;
			this.row3 = row3;
			this.col3 = rol3;
	
		}
	}
	
	public abstract int getColor();
	public abstract int[][] getShape();


//static for four blocks       
public static Tetris randomOne() 
{            
	// blocks created         
	Random random = new Random();           
	int type = random.nextInt(7);           
	switch(type) 
	{            
		case 0 : return new T();           
		case 1 : return new I();           
		case 2 : return new J();           
		case 3 : return new L();           
		case 4 : return new S();           
		case 5 : return new Z();           
		case 6 : return new O();           
	}               
	
	return null;      
}
}


class T extends Tetris 
{      
	public T() 
	{            
		cells = new int [][] {{0,1,0},
				      {1,1,1}};
		states = new State[4];            
		states[0] = new State(0,0, 0,-1, 0,1, 1,0);           
		states[1] = new State(0,0, -1,0, 1,0, 0,-1);           
		states[2] = new State(0,0, 0,1, 0,-1, -1,0);           
		states[3] = new State(0,0, 1,0, -1,0, 0,1);      
		} 
		
	public int getColor()
	{
		return 3;
	}
	
	public int[][] getShape()
	{
		return cells;
	}
}  

class I extends Tetris
{      
	public I() 
	{            
		cells = new int [][] {{1,1,1,1}};      
		states = new State[] 
		{
			new State(0,0, 0,1, 0,-1, 0,-2),                                      
			new State(0,0, -1,0, 1,0, 2,0)};
	} 
	
	public int getColor()
	{
		return 0;
	}

	public int[][] getShape() 
	{
		return cells;
	}
}

class J extends Tetris 
{      
	public J() 
	{            
		cells = new int [][] {{0,1,0},
				      {0,1,0},
		     		      {1,1,0}};          
		states = new State[]
		{             
			new State(0,0, 0,-1, 0,1, 1,1),            
			new State(0,0, -1,0, 1,0, 1,-1), 
           		new State(0,0, 0,1, 0,-1, -1,-1),            
		  	new State(0,0, 1,0, -1,0, -1,1)
		};      
	} 
	
	public int getColor()
	{
		return 1;
	}

	public int[][] getShape() 
	{
		return cells;
	}
}

class L extends Tetris 
{      
	public L() 
	{            
		cells = new int [][] {{0,1,0},
				      {0,1,0},
				      {0,1,1}};           
		states = new State[]
		{                           
			new State(0,0, 0,-1, 0,1, 1,-1),
			new State(0,0, -1,0, 1,0, -1,-1),
			new State(0,0, 0,1, 0,-1, -1,1), 
			new State(0,0, 1,0, -1,0, 1,1)
		};      
	} 
	
	public int getColor()
	{
		return 5;
	}

	public int[][] getShape() 
	{
		return cells;
	}
}  

class S extends Tetris 
{      
	public S() 
	{            
		cells = new int [][] {{0,1,1},
				      {1,1,0}};  
       		states = new State[]
		{    
			new State(0,0, 0,1, 1,-1, 1,0),
			new State(0,0, -1,0, 1,1, 0,1)
		};      
	} 
	
	public int getColor()
	{
		return 2;
	}

	public int[][] getShape()
	{
		return cells;
	}
}


class Z extends Tetris 
{      
	public Z() 
	{  
		cells = new int [][] {{1,1,0},
				      {0,1,1}};
		states = new State[]
		{ 
			new State(0,0, -1,-1, -1,0, 0,1), 
			new State(0,0, -1,1, 0,1, 1,0)
		};

	} 
	
	public int getColor()
	{
		return 4;
	}

	public int[][] getShape() 
	{
		return cells;
	}
}


class O extends Tetris 
{ 
	public O() 
	{ 
		cells = new int [][] {{1,1},
				      {1,1}};
		states = new State[]{
			new State(0,0,0,1,1,0,1,1),
			new State(0,0,0,1,1,0,1,1);				
	}
	
	public int getColor()
	{
		return 6;
	}

	public int[][] getShape()
	{
		return cells;
	}
}
