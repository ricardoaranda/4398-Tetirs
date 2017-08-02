import java.awt.event.KeyEvent;
import java.util.Scanner;

public class GameManager {
	
	public static Integer moveLeft = 0,
						  moveRight = 0,
						  moveDown = 0;
	public static boolean pauseFlag = false;
	
	
	// Get Board public methods and variables
		// place piece method
		// hasPlayerLost method
	
	// restart method
		// clear score
		// clear play method
			// remove falling piece
			// clear saved piece
			// call to reset board image to starting state
	
	
/**
* exitGameplay() method
* Sets game screen to the outro screen upon the user "Losing" the game,
* or by user desire to exit current game.
*/
public static void exitGameplay() {
	//screen 1 - intro, screen 2 - gameplay, screen 3 - outro
	BoardManager.changeGameState(3);
}
	
/**
* pause() method 
* Sets pause flag to false, puts any block movement on hold.
*/
public static void pause() {
	if (pauseFlag) {
		pauseFlag = false;
	} else {
		pauseFlag = true;
	}
}

/**
*  collision() method
*  Determines if a collision occurs in the three directions possible,
*  sending the results back as a three-element boolean array.
*/
public static boolean[] collision (AbstractPiece gamePiece, BoardManager board){
	boolean colHolder[] = {false, false, false};
	if (gamePiece.hasLeftContact()) {
		colHolder[0] = true;
		}
	if (gamePiece.hasRightContact()) {
		colHolder[1] = true;
		}
	if (gamePiece.hasBottomContact()) {
		colHolder[2] = true;
		// Give Player "Time" to move the piece while it rests
		// Send Piece to Board
		if (gamePiece.getPosition() >= board.getHeight()) {
			exitGameplay();
		}
	}
	return colHolder;
}
	
/**
* play() method
* Main function that sets the game in motion.
* 
* Game operations are vulnerable to the pauseFlag,
* and dependent on a gamePiece existing.
* 
* Notices if a key from the keyboard has been pressed,
* and calls for the keyPressed() method as a result.
* 
* Finally calls the update function, by which the current
* falling piece's position on the board, and rotation related
* to it's prior state may be updated.
*/
public static void play() {
	// Set up gameboard
	BoardManager board = new BoardManager();
	board.bmInit();
	
	// Assign a game piece
	AbstractPiece gamePiece = new AbstractPiece();
	
	//Assign game time
	int gameTime;
	
	while(!pauseFlag) {
		if (!gamePiece.exists()) {
			gamePiece.initialize();
		}
		if (gamePiece.exists()) {
			gameTime = Math.round(System.nanoTime());
			boolean holder[] = collision(gamePiece, board);
			fallingState(gameTime);
			
			if (holder[2]) {
				gameTime = Math.round(System.nanoTime());
				int restTime = 0;
				
				if (gamePiece.getPosition() >= board.getHeight()) {
					exitGameplay();
				} 
				
				while (restTime <= 2 && holder[2]) {
					//-------- 
					// can still get user input here 
					// --------
					int currentTime = Math.round(System.nanoTime());
					int restTimer = currentTime - gameTime;
					if (restTimer >= 2) {
						BoardManager.transitionPiece(gamePiece);
						gamePiece.clearPiece();
					}
					restTimer = 0;
				}
			}
			
			// Detect Keyboard Input
			KeyEvent keyboardEvent;
			keyPressed(keyboardEvent, gamePiece, board);
			update(gamePiece);
		}
	}
}
	
/**
 * keyPressed() method
 * 
 *  Triggers off a keyboard button press.
 *  
 *  Based off keyboard input, determines the actions to take
 *  on the current game piece, the pause state, towards restarting
 *  the game, or whether or not to exit the gameplay state. 
 */
public static void keyPressed (KeyEvent event, AbstractPiece gamePiece, BoardManager board) {
	boolean cHolder[] = collision(gamePiece, board);
	
	switch (event.getKeyCode()) {
			// Affects the game piece
		case KeyEvent.VK_UP:
			gamePiece.rotate();
			break;
			
		case KeyEvent.VK_DOWN:
			if(!cHolder[2]) {
				gamePiece.speedDown();
			}
			break;
			
		case KeyEvent.VK_LEFT:
			if(!cHolder[0]) {
				gamePiece.moveLeft();
			}			
			break;
		case KeyEvent.VK_RIGHT:
			if(!cHolder[1]) {
				gamePiece.moveRight();
			}
			break;
			
		case KeyEvent.VK_SPACE:
			gamePiece.drop();
			break;
			
			// Affects the game state
		case KeyEvent.VK_P:
			pause();
			break;
			
		case KeyEvent.VK_R:
			restart();
			break;
			
		case KeyEvent.VK_E:
			exitGameplay();
			break;
	}
}
	
	public static void fallingState(int gameTime){
	   if (gameTime%2 == 0) {
		   moveDown += 1;
	   }
	}

	public static void update(AbstractPiece gamePiece){
		// update Piece Position
		gamePiece.movePosition(moveLeft, moveRight, moveDown);
		// update the Graphic Position
		// ...........................
		// Reset the amount a piece should move
		moveLeft = moveRight = moveDown = 0;
	}
}
