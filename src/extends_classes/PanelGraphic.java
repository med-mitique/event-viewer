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
import javax.swing.JComponent;
import javax.swing.JPanel;

import main_work.ConnectDataBase;
import main_work.GUI;

/*
 * this is a class for panel used to the normal
 */

@SuppressWarnings("serial")
public class PanelGraphic extends JPanel{
	
	private short id;
	public static boolean isfilechosed = false ;
	
	
	protected void paintComponent(Graphics g2) {
		super.paintComponent(g2);
		Graphics2D g = (Graphics2D) g2;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//welcome panel draw image
		if(this.id == GUI.panWelcome) {
			try {
				final BufferedImage image1 = ImageIO.read(new File("icon\\Myback.jpg"));
				g.drawImage(image1, 0, 0, 600, 400,null);
			} catch (IOException e) {
			}

		}
		
		else if (this.id == GUI.panHome) {
			try {
				final BufferedImage image = ImageIO.read(new File("icon\\ensak.png"));
				g.drawImage(image, 400, 35, 200, 100, null);
			} catch (IOException e) {
			}
	
		}
		
		else if(this.id == GUI.panOption) {
			try {
				final BufferedImage image = ImageIO.read(new File("icon\\signal.jpg"));
				g.drawImage(image, 1, 1, 198, 100, null);
			} catch (IOException e) {}
			float nbrE = ConnectDataBase.countPercentageNiv()[2], nbrA =  ConnectDataBase.countPercentageNiv()[1];

			g.setColor(Color.blue);
			g.fillArc(50, 310, 90, 90, 450-(int)(nbrE*3.6), (int) (nbrE*3.6));
			g.setColor(Color.GRAY);
			g.fillArc(50, 310, 90, 90, 90, 360-(int)(nbrE*3.6));
			g.setColor(new Color(28, 46, 74));
			g.fillOval(53, 313, 84, 84);
			g.setColor(Color.cyan);
			g.setFont(new Font("century gothic", Font.BOLD, 16));
			g.drawString(ConnectDataBase.getErrTotal()+" Err", 70, 360);
			
			g.setColor(Color.green);
			g.fillArc(50, 410, 90, 90,  450-(int)(nbrA*3.6), (int) (nbrA*3.6));
			g.setColor(Color.GRAY);
			g.fillArc(50, 410, 90, 90, 90, 360-(int)(nbrA*3.6));
			g.setColor(new Color(28, 46, 74));
			g.fillOval(53, 413, 84, 84);
			g.setColor(Color.cyan);
			g.setFont(new Font("century gothic", Font.BOLD, 16));
			g.drawString(ConnectDataBase.getAverTotal()+" Aver", 60, 460);
			g.setColor(MyColor.dark_violet);
			g.drawLine(1, 405, 198, 405);
		}
		
		
		//help butt
		else if (id == GUI.Bu_Help) {
				
			g.setColor(Color.LIGHT_GRAY);
			g.drawOval(1, 4, 16, 16);
			g.drawArc(6, 7, 6, 6, 270, 260);
			g.drawLine(9, 13, 9, 14);
			g.fillOval(9, 15, 1, 2);
		}
		
		
	}
	
	public void addAll(JComponent... comps) {
		for(JComponent comp : comps)
			add(comp);
	}
	
	private void initialise(int x, int y, int width, int heigth, Color Background) {
		this.setLayout(null);
		this.setBounds(x,y,width,heigth);
		this.setBackground(Background);	
	}
		
	// ** Constructors
	
	public PanelGraphic(int x, int y, int width, int heigth, short id, Color c) {
		this.id = id;
		initialise(x, y, width, heigth, c);
	}
	public PanelGraphic(int x, int y, int width, int heigth, Color c) {
		initialise(x, y, width, heigth, c);
	}
	
	
	// getters and setters
	public int getId() {
		return id;
	}
	public void setId(short id) {
		this.id = id;
	}
	
	
}
