
//***************************************************************
//Menu class
//Purpose: Setup game menu page
//Called by: ControlPannel.java, Instructions.java
//Calls: Instructions.java, Paintrix.java
//***************************************************************

package paintrix;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

class BackgroundPanel extends JPanel{
	Image bgg = new ImageIcon(getClass().getResource("/resources/cubes_3d_art_front.jpg")).getImage();
	@Override
	public void paintComponent(Graphics g) {
		 g.drawImage(bgg, 0, 0, getWidth(), getHeight(), this);
	}
	
}

class gameTitle extends JPanel{
	gameTitle(){
		setOpaque(false);
		Font font = new Font("Comic Sans MS", Font.BOLD, 40);
		JTextArea textTitle = new JTextArea();
		textTitle.append("\n\n\nPAINTRIX\n");
		textTitle.setOpaque(false);
		textTitle.setEditable(false);
		textTitle.setFocusable(false);
		textTitle.setBackground(UIManager.getColor("Label.background"));
	    textTitle.setFont(UIManager.getFont("Label.font"));
	    textTitle.setForeground(Color.BLUE);
	    textTitle.setFont(font);
	    
	    Font font2 = new Font("Comic Sans MS", Font.BOLD, 30);
	    JTextArea subTitle = new JTextArea();
	    subTitle.append("Inspired by Tetris\u2122");
	    subTitle.setOpaque(false);
	    subTitle.setEditable(false);
	    subTitle.setFocusable(false);
	    subTitle.setBackground(UIManager.getColor("Label.background"));
	    subTitle.setFont(UIManager.getFont("Label.font"));
	    subTitle.setForeground(Color.BLUE);
	    subTitle.setFont(font2);
	    
	    add(textTitle);
	    add(subTitle);
	}
}

public class Menu extends JFrame implements ActionListener{
	final int windowLength = 350;
	final int windoWidth = 600;
	
	public Menu() {
		//setLayout(new BorderLayout());
		JButton start = new JButton();
		BackgroundPanel background = new BackgroundPanel();
		background.setLayout(new BorderLayout());
		background.add(new gameTitle(), BorderLayout.CENTER);
		add(background, BorderLayout.CENTER);
		start.setText("START GAME");
		start.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  dispose();
				  new Paintrix();
				 
			 } 
		} );
		
		JButton quit = new JButton();
		quit.setText("QUIT");
		quit.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  dispose();
				  System.exit(0);
			 } 
		} );
		
		JButton about = new JButton();
		about.setText("Instructions");
		about.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  dispose();
				  new Instructions();
			  }
		});
		
		//JLabel label = new JLabel(new ImageIcon("cubes_3d_art_81381_300x500.jpg"));
		JPanel navigate = new JPanel();
		
		navigate.add(start, BorderLayout.CENTER);
		navigate.add(quit, BorderLayout.CENTER);
		navigate.add(about, BorderLayout.CENTER);
		//add(label, BorderLayout.CENTER);
		add(navigate, BorderLayout.SOUTH);
		setSize(windowLength, windoWidth);
		setTitle("Paintrix");
//		ImageIcon img = new ImageIcon("Paintrix/Tetris.png");
//		setIconImage(img.getImage());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		//setSize(windowLength-1, windoWidth-1);
	    //setSize(windowLength, windoWidth);
		
	}
	
	public static void main(String[] args) {
		Menu game = new Menu();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
