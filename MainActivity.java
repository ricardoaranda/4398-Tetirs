import java.awt.image.*;
import java.awt.*;

public class MainActivity extends Canvas implements Runnable{
	
	private Thread thread;
	private boolean running = false;
	
	/**
	 * start() - creates a game thread and calls run() method 
	*/
	private synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop() {
		if (!running)
			return;
		
		running = false;
		try 
		{
			thread.join();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		//System.exit(1);
	}
	/**
	 * run() - game loop updates moving block every second and renders 
	 * graphics every iteration
	*/
	public void run() {
		// timer variables
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0;				// nanoseconds conversion 
		double delta = 0;						// elapsed time b/w now and lastTime
		// fps counter variables
		int frames = 0;							// counts how many frames we have time to render
		int updates = 0;						// counts how many times update() is called
		
		while (running) {
			long now = System.nanoTime();
			delta += (now-lastTime) / ns;		// adds a 60th of a sec each iteration
			lastTime = now;
			while (delta >= 1 ) {				// update 60 times per second (should it be an if?)
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			// FPS counter
			if (System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				//System.out.println(updates + " updates" + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	public void update() {
		
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
		    createBufferStrategy(3);
		    return;
		}
	}
	
	//public static void main(String[] args) {
	//	MainActivity game = new MainActivity();
    //
    //    game.start();
	//}
	
}
