
//***************************************************************
//Paintrix class
//Purpose: Provide functional objects (aka. score board), Initiate ControlPannel
//Called by: Menu.java
//Calls:ControlPannel.java 
//***************************************************************

package paintrix;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;

public class Paintrix extends JFrame {
	// variables
	final int windowLength = 300;
	final int windoWidth = 640;
	//int[][] controlPannel = new int[arrayLength][arrayWidth];
	JLabel statusbar;

	public Paintrix() {
		statusbar = new JLabel("Line removed: 0" + "			" + "Hightest line removed: 0");
		statusbar.setPreferredSize(new Dimension(windowLength, 45));
		add(statusbar, BorderLayout.SOUTH);
		setResizable(false);
		ControlPannel ControlPanel = new ControlPannel(this);
		add(ControlPanel, BorderLayout.CENTER);
		setSize(windowLength, windoWidth);
		setTitle("Paintrix");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		//Audiofile.INTROMUSIC.play();
	}
	
	public JLabel getStatusBar() {
	       return statusbar;
	   }
	
	public void closePanel() {
		setVisible(false);
	}


//	public static void main(String[] args) {
//		Paintrix game = new Paintrix();
//		game.setLocationRelativeTo(null);
//		game.setVisible(true);
//
//	}

	// dead code
	void GUI() {
		JFrame window = new JFrame();
		window.setResizable(false);
		// actionPanel blockLayout = new actionPanel();
		JPanel buttonPanel = new JPanel();
		// blockLayout.setBackground(Color.BLACK);
		buttonPanel.setPreferredSize(new Dimension(0, windoWidth));
		buttonPanel.setBackground(Color.BLUE);

		window.add(buttonPanel, BorderLayout.EAST);
		// window.add(blockLayout, BorderLayout.WEST);
	}
}
