
//***************************************************************
//Instructions class
//Purpose: Setup game instruction/about page
//Called by: Menu.java
//Calls: Menu.java
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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

class BgPanel extends JPanel {
	Image bg = new ImageIcon(getClass().getResource("/resources/cubes_black.jpg")).getImage();
	 @Override
	public void paintComponent(Graphics g) {
		 g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
	}
}

class foreground extends JPanel{
	foreground(){
		setOpaque(false);
		Font font = new Font("Arial", Font.BOLD, 16);
		JTextArea textTitle = new JTextArea();
		textTitle.append("\n\n\nCONTROLS");
		textTitle.setOpaque(false);
		textTitle.setEditable(false);
		textTitle.setFocusable(false);
		textTitle.setBackground(UIManager.getColor("Label.background"));
	    textTitle.setFont(UIManager.getFont("Label.font"));
	    //textTitle.setBorder(UIManager.getBorder("Label.border"));
	    textTitle.setForeground(Color.WHITE);
	    textTitle.setFont(font);
		
		JTextArea textParagraph = new JTextArea();
		textParagraph.append("Press ESC to pause and show menu \n\nPress A move block left, press D move block right. \n\nPress W rotate \n\nPress S send block instantly to bottom\n\n\n");
		textParagraph.setOpaque(false);
		textParagraph.setEditable(false);
		textParagraph.setFocusable(false);
		textParagraph.setBackground(UIManager.getColor("Label.background"));
		textParagraph.setFont(UIManager.getFont("Label.font"));
		textParagraph.setBorder(UIManager.getBorder("Label.border"));
		textParagraph.setForeground(Color.WHITE);
		textParagraph.setFont(font);
		
		JTextArea aboutTitle = new JTextArea();
		aboutTitle.append("ABOUT");
		aboutTitle.setOpaque(false);
		aboutTitle.setEditable(false);
		aboutTitle.setFocusable(false);
		aboutTitle.setBackground(UIManager.getColor("Label.background"));
		aboutTitle.setFont(UIManager.getFont("Label.font"));
		aboutTitle.setBorder(UIManager.getBorder("Label.border"));
		aboutTitle.setForeground(Color.WHITE);
		aboutTitle.setFont(font);
		
		JTextArea aboutText = new JTextArea();
		aboutText.append("This game, 'Paintrix', is essentially the original title \n'Tetris' with a little visual twist made by Difei Gu. \nThis game is developed solely serves programming \npractice purposes.");
		aboutText.setOpaque(false);
		aboutText.setEditable(false);
		aboutText.setFocusable(false);
		aboutText.setBackground(UIManager.getColor("Label.background"));
		aboutText.setFont(UIManager.getFont("Label.font"));
		aboutText.setBorder(UIManager.getBorder("Label.border"));
		aboutText.setForeground(Color.WHITE);
		aboutText.setFont(font);
		
		add(textTitle);
		add(textParagraph);
		add(aboutTitle);
		add(aboutText);
		
	}
}

public class Instructions extends JFrame implements ActionListener {
	final int windowLength = 450;
	final int windoWidth = 600;

	public Instructions(){
		BgPanel backgroundPanel = new BgPanel();
		backgroundPanel.setLayout(new BorderLayout());
		backgroundPanel.add(new foreground(), BorderLayout.CENTER);
		add(backgroundPanel, BorderLayout.CENTER);
		
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				 dispose();
				 new Menu();
			  }
		});
		
		add(back, BorderLayout.SOUTH);
		
		setSize(windowLength, windoWidth);
		setTitle("Paintrix");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
