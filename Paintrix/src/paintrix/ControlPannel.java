
//***************************************************************
//controlPannel class
//Purpose: Display game interface as well as providing game logic.
//Called by: Paintrix.java
//Calls:Block.java, Menu.java, 
//***************************************************************

package paintrix;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ControlPannel extends JPanel implements ActionListener {
	Timer timer;
	int actionPanelWidth, actionPanelLength;
	//final int windowLength = 300;
	//final int windoWidth = 600;
	final int arrayLength = 15;
	final int arrayWidth = 30;
	int numLinesRemoved = 0;
	int highestLineRemoved = 0;
	int[][] controlPannel = new int[arrayWidth][arrayLength];
	boolean collision, checkTop, removeLn;
	boolean isStop = false;
	boolean allWayDown = false;
	Block blockClass = new Block();
	JLabel statusbar;
	int ID;
	private Paintrix parent;
	
	public ControlPannel(Paintrix parent) {
		this.parent = parent;
		statusbar = parent.getStatusBar();
		ID = blockClass.blockGenerator(controlPannel);
		setFocusable(true);
		setBackground(Color.BLACK);
		addKeyListener(new controls());
		timer = new Timer(400, this);
		timer.start();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// get panel size
		actionPanelLength = getSize().width;
		actionPanelWidth = getSize().height;
		int actionRow = actionPanelLength / arrayLength;
		int actionColume = actionPanelWidth / arrayWidth;

		for (int i = 0; i < arrayWidth; i++) {
			for (int j = 0; j < arrayLength; j++) {
				if (controlPannel[i][j] == 1 || controlPannel[i][j] == 9) {
					g.setColor(Color.WHITE);
					g.fillRect(j * actionColume + 1, i * actionRow + 1, actionColume - 1, actionRow - 1);
				}else if (controlPannel[i][j] == 2){
					g.setColor(Color.BLUE);
					g.fillRect(j * actionColume + 1, i * actionRow + 1, actionColume - 1, actionRow - 1);
				}else if (controlPannel[i][j] == 3){
					g.setColor(Color.RED);
					g.fillRect(j * actionColume + 1, i * actionRow + 1, actionColume - 1, actionRow - 1);
				}else if (controlPannel[i][j] == 4){
					g.setColor(Color.ORANGE);
					g.fillRect(j * actionColume + 1, i * actionRow + 1, actionColume - 1, actionRow - 1);
				}else if (controlPannel[i][j] == 5){
					g.setColor(Color.YELLOW);
					g.fillRect(j * actionColume + 1, i * actionRow + 1, actionColume - 1, actionRow - 1);
				}else if (controlPannel[i][j] == 6){
					g.setColor(Color.GREEN);
					g.fillRect(j * actionColume + 1, i * actionRow + 1, actionColume - 1, actionRow - 1);
				}else if (controlPannel[i][j] == 7){
					g.setColor(Color.CYAN);
					g.fillRect(j * actionColume + 1, i * actionRow + 1, actionColume - 1, actionRow - 1);
				}else if (controlPannel[i][j] == 8){
					g.setColor(Color.MAGENTA);
					g.fillRect(j * actionColume + 1, i * actionRow + 1, actionColume - 1, actionRow - 1);
				}
			}
		}
	}

	
	//public Dimension getPreferredSize() {
		//return new Dimension(490, windoWidth);
	//}
	//@Override
	public void moveOneDown(int[][] array) {
		for (int i = arrayWidth - 1; i >= 0; i--) {
			for (int j = arrayLength - 1; j >= 0; j--) {
				if (array[i][j] == 1) {
					array[i][j] = 0;
					array[i + 1][j] = 1;
				}
				if (array[i][j] == 9){
					array[i][j] = 0;
					array[i+1][j] = 9;
				}
				// System.out.println(array[i][j]);
			}
		}
	}

	@Override
	// timer firing every 400 ms
	public void actionPerformed(ActionEvent e) {
		collision = collisionWhenDrop(controlPannel);
		if (collision) {
			makeStatic(controlPannel);
			removeLine(controlPannel);
			ID = blockClass.blockGenerator(controlPannel);
		}

		checkTop = checkTop(controlPannel);
		if (checkTop) {
			setFocusable(false);
			timer.stop();
			gameOverInterface();
			reset(controlPannel);
			statusbar.setText("Line removed: " + String.valueOf(numLinesRemoved) + "			" + "Hightest line removed: " + String.valueOf(highestLineRemoved));
			ID = blockClass.blockGenerator(controlPannel);
			timer.start();
		}
		
		while(allWayDown){
			moveOneDown(controlPannel);
			collision = collisionWhenDrop(controlPannel);
			if (collision) {
				makeStatic(controlPannel);
				removeLine(controlPannel);
				ID = blockClass.blockGenerator(controlPannel);
				allWayDown = false;
			}
			statusbar.setText("Line removed: " + String.valueOf(numLinesRemoved) + "			" + "Hightest line removed: " + String.valueOf(highestLineRemoved));
			repaint();
		}

		moveOneDown(controlPannel);
		repaint();
	}

	public void pause() {
		isStop = !isStop;
		if (isStop) {
			timer.stop();
			statusbar.setText("paused");
		} else {
			timer.start();
			statusbar.setText("Line removed: " + String.valueOf(numLinesRemoved) + "			" + "Hightest line removed: " + String.valueOf(highestLineRemoved));
			//statusbar.setText(" 0");
		}
		repaint();
	}

	// Clear the whole panel, reset score
	public void reset(int[][] array) {
		for (int i = 0; i < arrayWidth; i++) {
			for (int j = 0; j < arrayLength; j++) {
				array[i][j] = 0;
			}
		}
		
		if (numLinesRemoved > highestLineRemoved){
		highestLineRemoved = numLinesRemoved;
		}
		numLinesRemoved = 0;
		
	}

	// check all elements with 1, move left one
	public void moveLeftOne(int[][] array) {
		for (int i = 0; i < arrayWidth; i++) {
			for (int j = 0; j < arrayLength; j++) {
				if (array[i][j] == 1) {
					array[i][j] = 0;
					array[i][j - 1] = 1;
				}
				if (array[i][j] == 9){
					array[i][j] = 0;
					array[i][j - 1] = 9;
				}
			}
		}
		repaint();
	}

	// check all elements with 1, move right one, but it is checked in right to
	// left order
	public void moveRightOne(int[][] array) {
		for (int i = 0; i < arrayWidth; i++) {
			for (int j = arrayLength - 1; j >= 0; j--) {
				if (array[i][j] == 1) {
					array[i][j] = 0;
					array[i][j + 1] = 1;
				}
				if (array[i][j] == 9){
					array[i][j] = 0;
					array[i][j + 1] = 9;
				}
			}
		}
		repaint();
	}
	
	//Rotation with implement block detection to prevent rotate into block
	public void rotateLeft(int[][] array){
		int[][] temp = new int[arrayWidth][arrayLength];
		int axisPosX = 0; 
		int	axisPosY = 0; 
		int adjPosX = 0;
		int adjPosY = 0;
		boolean switchable = true;
		for (int i = 0; i < arrayWidth; i++) {
			for (int j = 0; j < arrayLength; j++) {
				if (array[i][j] == 1){
					temp[i][j] = 0;
				}else{
					temp[i][j] = array[i][j];
				}
			}
		}
		
		for (int i = 0; i < arrayWidth; i++) {
			for (int j = 0; j < arrayLength; j++) {
				if (array[i][j] == 9){
					if (i>=arrayWidth-3){
						switchable = false;
					}
					axisPosX = j;
					axisPosY = i;
				}
			}
		}
		
		for (int i = 0; i < arrayWidth; i++) {
			for (int j = 0; j < arrayLength; j++) {
				if (array[i][j] == 1){
					adjPosX = axisPosX-j;
					adjPosY = axisPosY-i;
					if (adjPosY == 1 && adjPosX == 1){
						if (array[i][j+2] == 0 || array[i][j+2] == 1){
							temp[i][j+2] = 1;
						}else{
							switchable = false;
							break;
						}
					}
					else if (adjPosY == 1 && adjPosX == -1){
						if (array[i+2][j] == 0 || array[i+2][j] == 1){
							if (i+2<=arrayWidth-3){
								temp[i+2][j] = 1;
							}else{
								switchable = false;
								break;
							}
						}else{
							switchable = false;
							break;
						}
					}
					else if (adjPosY == -1 && adjPosX == -1){
						if (array[i][j-2] == 0 || array[i][j-2] == 1){
							temp[i][j-2] = 1;
						}else{
							switchable = false;
							break;
						}
					}
					else if (adjPosY == -1 && adjPosX == 1){
						if (array[i-2][j] == 0 || array[i-2][j] == 1){
							temp[i-2][j] = 1;
						}else{
							switchable = false;
							break;
						}
					}
					else {
						if (array[axisPosY+(j-axisPosX)][axisPosX+(axisPosY-i)] == 0 || array[axisPosY+(j-axisPosX)][axisPosX+(axisPosY-i)] == 1){
							if (axisPosY+(j-axisPosX)<=arrayWidth-3){
								temp[axisPosY+(j-axisPosX)][axisPosX+(axisPosY-i)] = 1;
							}else{
								switchable = false;
								break;
							}
						}else{
							switchable = false;
							break;
						}
					}
				}
			}
		}
		if(switchable){
			for (int i = 0; i < arrayWidth; i++) {
				for (int j = 0; j < arrayLength; j++) {
					array[i][j] = temp[i][j];
				}
			}
		}
	}
	
	//Remove the whole row of block if the line is filled with block
	public void removeLine(int[][] array){
		removeLn = false;
		for (int i = 0; i < arrayWidth; i++) {
			for (int j = 0; j < arrayLength; j++) {
				if (array[i][j] != 1 && array[i][j] != 9 && array[i][j] != 0){
					removeLn = true;
				}else{
					removeLn = false;
					break;
				}
			}
			
			if(removeLn){
				for (int j = 0; j < arrayLength; j++) {
					array[i][j] = 0;
				}
				for (int k = i-1; k >=0; k--) {
					for (int l = 0; l < arrayLength; l++) {
						array[k+1][l] = array[k][l];
					}
				}
				numLinesRemoved += 1;
				if (numLinesRemoved > highestLineRemoved){
					highestLineRemoved = numLinesRemoved;
				}
				removeLn = false;
			}
		}
	}
	
	//Check if block out of bound or touching other block (left side)
	public boolean checkLeftCollision(int[][] array) {
		for (int i = 0; i < arrayWidth; i++) {
			for (int j = 0; j < arrayLength; j++) {
				if (array[i][j] == 1 || array[i][j] == 9){
					if(j == 0){
						return true;
					}
					if(array[i][j-1] != 0 && array[i][j-1] != 1 && array[i][j-1] != 9){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	//Check if block out of bound or touching other block (right side)
	public boolean checkRightCollision(int[][] array) {
		for (int i = 0; i < arrayWidth; i++) {
			for (int j = 0; j < arrayLength; j++) {
				if (array[i][j] == 1 || array[i][j] == 9){
					if (j == arrayLength-1){
						return true;
					}
					if(array[i][j+1] != 0 && array[i][j+1] != 1 && array[i][j+1] != 9){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	//When block traveling one block down, check block collision
	public boolean collisionWhenDrop(int[][] array) {
		for (int i = 0; i < arrayWidth; i++) {
			for (int j = 0; j < arrayLength; j++) {
				if (array[i][j] == 1 || array[i][j] == 9) {
					if (array[i + 1][j] == 2 || array[i + 1][j] == 3 || array[i + 1][j] == 4 || array[i + 1][j] == 5 || array[i + 1][j] == 6 || array[i + 1][j] == 7 || array[i + 1][j] == 8) {
						return true;
					}
					if (i == arrayWidth - 3)
						return true;
				}
			}
		}
		return false;
	}
	
	//changing the value of the block to static block
	public void makeStatic(int[][] array) {
		for (int i = 0; i < arrayWidth; i++) {
			for (int j = 0; j < arrayLength; j++) {
				if (array[i][j] == 1 || array[i][j] == 9) {
					if (ID == 10){
						array[i][j] = 2;
					}
					else if (ID == 11){
						array[i][j] = 3;
					}
					else if (ID == 12){
						array[i][j] = 4;
					}
					else if (ID == 13){
						array[i][j] = 5;
					}
					else if (ID == 14){
						array[i][j] = 6;
					}
					else if (ID == 15){
						array[i][j] = 7;
					}
					else if (ID == 16){
						array[i][j] = 8;
					}
				}
			}
		}
	}
	
	//Function that check block overflow and ends game afterwards
	private boolean checkTop(int[][] array) {
		for (int j = 0; j < arrayLength; j++) {
			if ((array[2][j] == 1 && array[3][j] != 1 && array[3][j] != 0 && array[3][j] != 9) || (array[2][j] == 9 && array[3][j] != 1 && array[3][j] != 0 && array[3][j] != 9)) {
				return true;
			}
		}
		return false;
	}
	
	public void pauseInterface(){
		JFrame pan=new JFrame();
		pan.setLayout(new FlowLayout());

		Object[] options = {"Resume",
				"Retry",
                "Back to Menu",
               	};
		int selection = JOptionPane.showOptionDialog(pan,
			    		"GAME PAUSED",
			    	    "PAUSED",
			    	    JOptionPane.YES_NO_CANCEL_OPTION,
			    	    JOptionPane.PLAIN_MESSAGE,
			    	    null,
			    	    options,
			    	    options[2]);
		if (selection == JOptionPane.YES_OPTION){
			setFocusable(true);
			pause();
		}else if (selection == JOptionPane.NO_OPTION) {
			setFocusable(true);	
			reset(controlPannel);
			ID = blockClass.blockGenerator(controlPannel);
			pause();
		}else if (selection == JOptionPane.CANCEL_OPTION){
			Menu menu = new Menu();
			parent.dispose();
			
		}
	}
	
	public void gameOverInterface(){
		JFrame pan=new JFrame();
		pan.setLayout(new FlowLayout());

		Object[] options = {"Play Again",
				"Quit",
                "Back to Menu",
               	};
		int selection = JOptionPane.showOptionDialog(pan,
			    		"GAME OVER"+"\n"+ "Line removed: " + numLinesRemoved + "\n" + "Highest line removed: " + highestLineRemoved,
			    	    "GAME OVER",
			    	    JOptionPane.YES_NO_CANCEL_OPTION,
			    	    JOptionPane.PLAIN_MESSAGE,
			    	    null,
			    	    options,
			    	    options[2]);
		if (selection == JOptionPane.YES_OPTION){
			setFocusable(true);
		}else if (selection == JOptionPane.NO_OPTION) {
			System.exit(0);
		}else if (selection == JOptionPane.CANCEL_OPTION){
			Menu menu = new Menu();
			parent.dispose();
			
		}
	}

	class controls extends KeyAdapter {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {
			
			int key = e.getKeyCode();
			
//			if (key == KeyEvent.VK_P) {
//                pause();
//                return;
//            }
			
			if (key == KeyEvent.VK_ESCAPE) {
				setFocusable(false);
				pause();
				pauseInterface();
			}
			
			if (isStop){
				return;
			}

			switch(key){
			case KeyEvent.VK_A:
				if (!checkLeftCollision(controlPannel)){moveLeftOne(controlPannel);}
				break;
			case KeyEvent.VK_D:
				if (!checkRightCollision(controlPannel)){moveRightOne(controlPannel);}
				break;
			case KeyEvent.VK_W:
				if(ID != 10){rotateLeft(controlPannel);}
				break;
			case KeyEvent.VK_S:
				allWayDown = true;
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		public void paintComponent(Graphics g) {
			// TODO Auto-generated method stub

		}
	}
}
