package extends_classes;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;


@SuppressWarnings("serial")
public class Butt extends JButton {

	private int id ;
	private boolean checked;
	

	public void paint(Graphics g2) {
		super.paint(g2);
		
		Graphics2D g = (Graphics2D) g2;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// close butt
		if (id == 10 ) {
			g.setColor(Color.white);
		    g.drawLine(15, 10, 25, 20);
			g.drawLine(25, 10, 15, 20);
			
		}
		
		//  max button
		else if (id == 11 ) {
			g.setColor(Color.DARK_GRAY );
		    g.drawRect(15, 10, 9, 9);
			
		}

		// hide button
		else if (id == 12 ) {
			
			g.setColor(Color.cyan);
		    g.drawLine(15, 16, 25, 16);
			
		}
		
		// return butt
		else if (id == 13) {
			
			g.setColor(Color.cyan);
			g.drawLine(10, 14, 28, 14);
			g.drawLine(10, 14, 17, 7);
			g.drawLine(10, 14, 17, 21);
			
		}

		// home butt
		else if (id == 14 ) {
		
			g.setColor(Color.cyan);
		    g.drawPolygon(new int[] {10,19,20,29, 29,28,23,22, 22,21,18,17, 17,16,11,10}, new int[] {13,4,4,13, 21,22,22,21, 14,13,13,14 ,21,22,22,21}, 16);
		
		}
		
		// * dashboard button
		else if(id == 15) {
			try {
				final BufferedImage image = ImageIO.read(new File("icon\\dashb.png"));
				g.drawImage(image, 4, 5, 15, 20, null);
			} catch (IOException e) {}
			g.setColor(MyColor.gray);
			g.setFont(new Font("Malgun Gothic Bold", Font.PLAIN, 10));
			g.drawString("Dashboard", 20, 20);
		}
	}
	
	//getters an setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	//  ** Constructors ***
 	public Butt() {
 		this.setFont(new Font("Calibr", Font.BOLD, 12));
 		this.setForeground(Color.cyan);
	}

 	public Butt(int x, int y, int width, int heigth, Color c ) {
 		this.setFont(new Font("calibr", Font.BOLD, 12));
 		this.setForeground(Color.cyan);
		this.setBounds(x, y, width, heigth);
		this.setBackground(c);
		this.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.black));
		
	}
	public Butt(int x, int y, int width, int heigth, int id, Color c ) {
 		this.setFont(new Font("Calibr", Font.BOLD, 12));
 		this.setForeground(Color.cyan);
		this.setBounds(x, y, width, heigth);
		this.setBackground(c);
		this.id = id;
		this.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.black));
		
	}

	public Butt(int x, int y, int width, int heigth, int id, boolean checked, Color c ) {
 		this.setFont(new Font("Calibr", Font.BOLD, 12));
 		this.setForeground(Color.cyan);
		this.setBounds(x, y, width, heigth);
		this.setBackground(c);
		this.id = id;
		this.checked = checked;
		this.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.black));
	}
	
}
