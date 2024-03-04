package Graphs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import extends_classes.MyColor;
import extends_classes.MyLabel;

@SuppressWarnings("serial")
public class RadarChart extends JPanel {
	
	private int[][] Percentage = new int[2][6];
	private String[] sources; // source d'evenements
	private JLabel[] lab;
	
	private final int r = 80, Ox = 220, Oy = 130;

	protected void paintComponent(Graphics g2) {
		super.paintComponent(g2);
		
		Graphics2D g = (Graphics2D) g2;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		double a ;
		int x, y;
		int[][] coordx = new int[2][6] ;
		int[][] coordy = new int[2][6] ;
		
		
		// *draw the repere diagram (Color)
		g.setColor(MyColor.DARK_GRAY);
		//lines
		for(int angl = 0; angl<180; angl+=60) {
			x = (int) (r*Math.sin(Math.toRadians(angl)));
			y = (int) (r*Math.cos(Math.toRadians(angl)));
			g.drawLine(Ox+x, Oy-y, Ox-x, Oy+y);
		}
		// pour les polygone
		for(int i=16; i<=r; i+=16) {
			x = (int) (i*Math.cos(Math.toRadians(30)));
			y = (int) (i*Math.sin(Math.toRadians(30)));
			g.drawPolygon( new int[] {Ox, Ox+x, Ox+x, Ox, Ox-x, Ox-x}, new int[] {Oy-i, Oy-y, Oy+y,Oy+i, Oy+y, Oy-y}, 6);
		}
		
		if(Percentage == null) return;
		// painting the statistics
		if(sources != null ) {
			
			// des tableau pour les polygone
			a = 0.8*Math.cos(Math.toRadians(30));
			coordx[0] = new int[] {Ox, Ox+(int)(a*Percentage[0][1]), Ox+(int)(a*Percentage[0][2]), Ox, Ox-(int)(a*Percentage[0][4]), Ox-(int)(a*Percentage[0][5])};
			coordx[1] = new int[] {Ox, Ox+(int)(a*Percentage[1][1]), Ox+(int)(a*Percentage[1][2]), Ox, Ox-(int)(a*Percentage[1][4]), Ox-(int)(a*Percentage[1][5])};
			
			a = 0.8*Math.sin(Math.toRadians(30));
			coordy[0] = new int[] {Oy-(int)(0.8*Percentage[0][0]), Oy-(int)(a*Percentage[0][1]), Oy+(int)(a*Percentage[0][2]), Oy+(int)(0.8*Percentage[0][3]), Oy+(int)(a*Percentage[0][4]), Oy-(int)(a*Percentage[0][5])};
			coordy[1] = new int[] {Oy-(int)(0.8*Percentage[1][0]), Oy-(int)(a*Percentage[1][1]), Oy+(int)(a*Percentage[1][2]), Oy+(int)(0.8*Percentage[1][3]), Oy+(int)(a*Percentage[1][4]), Oy-(int)(a*Percentage[1][5])};
			
			//  label for the diagram's variable
			int angle = 0;
			for(int k=0;k<6;k++) {
				x = Ox + (int)(r*Math.sin(Math.toRadians(angle)));
				y = Oy - (int)(r*Math.cos(Math.toRadians(angle)))-5;
				angle += 60;
				if(x < Ox) {
					int xlab = x-80;
					if(sources[k].length()*3 < 60) xlab = x-sources[k].length()*3;
					lab[k].setBounds(xlab, y-5, 80, 15);
				}
				else if(x > Ox) {
					
					lab[k].setBounds(x+2, y-5, 80, 15);
				}
				
				else {
					if(y < Oy) {
				
						int xlab =  x-40;
						if(sources[k].length()*3 < 80) xlab = x-sources[k].length()*3/2;
						lab[k].setBounds(xlab, y-13, 80, 15);
					}
					else {
						lab[k].setBounds(x-40, y+5, 80, 15);
						}
				}
			}
		}	
			g.setColor(Color.orange);
			g.drawPolygon(coordx[0], coordy[0], 6);
			g.drawLine(20, 198, 50, 198);
			this.add(new MyLabel("Errours", 55, 190, 100, 10));
			
			g.setColor(Color.blue);
			g.drawPolygon(coordx[1], coordy[1], 6);
			g.drawLine(20, 213, 50, 213);
			this.add(new MyLabel("Avertisments", 55, 205, 100, 10));
		
		
	}
	

	public void remplir(int[][] percent, String[] str) {
		this.sources = str;
		this.Percentage = percent;
		for (int k=0; k<6; k++) {lab[k].setText(sources[k]);}
		repaint();
	}
	
	
	// initialise panel
	private void initialise(int x, int y, int width, int heigth, Color Backround) {
		
		lab = new JLabel[9];
		for (int k=0; k<6; k++) {
		
			lab[k] = new JLabel();
			lab[k].setForeground( Color.LIGHT_GRAY);
			lab[k].setFont(new Font("cntury gothic", Font.ITALIC,12));
			this.add(lab[k]);
		}
	
		
		this.setBounds(x,y,width,heigth);
		this.setLayout(null);
		this.setBorder(new LineBorder(Color.black));
		this.setBackground(Backround);
	}
	
	
	// Constructor
	public RadarChart(int x, int y, int width, int heigth, String title, Color c) {
		JLabel lab = new JLabel(title);
		lab.setBounds(20,2,360,30);
		lab.setForeground(Color.white);
		lab.setFont(new Font("century gothic", Font.TRUETYPE_FONT, 14));
		initialise(x, y, width, heigth, c);
		add(lab);
	}
	

	// Getters And Setters
	public void setPercentage(int[][] percentage) {
		Percentage = percentage;
	}
	public String[] getSources() {
		return sources;
	}
	public void setSources(String[] sources) {
		this.sources = sources;
	}
	

}
