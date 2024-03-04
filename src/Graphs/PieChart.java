package Graphs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import extends_classes.MyColor;
import extends_classes.MyLabel;

@SuppressWarnings("serial")
public class PieChart extends JPanel{
	
	private float[] Pr;
	private final static short DEBUT = 90, Ox = 160, Oy = 50, R = 65;
	private MyLabel[] labs ;
	
	protected void paintComponent(Graphics g2) {
		super.paintComponent(g2); 
		
		Graphics2D g = (Graphics2D) g2;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if(Pr != null) {
			int angDebut = DEBUT, angRotation;
			
			int a,b;
			Color[] c = new Color[] {MyColor.arc1, MyColor.arc2, MyColor.arc3};			
			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(2);
			
			for(int k=0;k<3;k++) {
				
				// painting the Pie arc by arc (mitique)
				g.setColor(c[k]);
				angRotation = (int)(Pr[k]*3.6);
				if(k ==2) angRotation = 360-angDebut+DEBUT;
				g.fillArc(Ox, Oy, 2*R, 2*R, angDebut, angRotation);
				g.fillRect(20+k*110, 205, 10, 10);
				
				//  painting the percentage in the pie chart
				a = (int) (Ox+R+R*Math.cos(Math.toRadians(angDebut+angRotation/2)));
				b = (int)(Oy+R-R*Math.sin(Math.toRadians(angDebut+angRotation/2)));
				g.setColor(Color.LIGHT_GRAY);

				if(a > Ox+R && b > Oy+R) {
					g.drawPolyline(new int[] {a, a+5,a+30}, new int[] {b, b+5,b+5}, 3);
					labs[k].setBounds(a+40, b-5, 60, 15);
				}
				else if(a > Ox+R && b <= Oy+R){
					g.drawPolyline(new int[] {a, a+5,a+30}, new int[] {b, b-5,b-5}, 3);
					labs[k].setBounds(a+40, b-15, 60, 15);
				}
				else if(a <= Ox+R && b> Oy+R) {
					
					g.drawPolyline(new int[] {a, a-5,a-30}, new int[] {b, b+5,b+5}, 3);
					labs[k].setBounds(a-80, b-5, 60, 15);
				}
				else {
					g.drawPolyline(new int[] {a, a-5,a-30}, new int[] {b, b-5,b-5}, 3);	
					labs[k].setBounds(a-80, b-15, 60, 15);
				}
				
				labs[k].setText(df.format(Pr[k])+"%");
				angDebut += angRotation; 
				g.setColor(Color.white);
				g.drawLine(Ox+R, Oy+R, (int)(Ox+R+R*Math.cos(Math.toRadians(angDebut))), (int)(Oy+R-R*Math.sin(Math.toRadians(angDebut))));
				
			}
		
		}else {
			g.setColor(Color.cyan);
			g.fillOval(Ox, Oy, 2*R, 2*R);
		}
	}
	
	public void remplir(float[] pr) {
		if(pr != null) {
			this.Pr = pr;
			this.repaint();
		}
	}
	

	// pour les construteurs
	private void initialise(int x, int y, int width, int heigth, Color Backround) {
		
		labs = new MyLabel[6];
		String[] txt = new String[] {"Informations", "Avertisments", "Errours"};
		for(int k=0; k<6; k++) {
			if(k>=3) {
				labs[k] = new MyLabel(txt[k-3], 35+(k-3)*110, 200, 90, 20, Color.LIGHT_GRAY);
			}else { 
				labs[k] = new MyLabel();
				labs[k].setForeground(Color.LIGHT_GRAY);
			}
			this.add(labs[k]);
		}

		this.setBounds(x,y,width,heigth);
		this.setLayout(null);
		this.setBorder(new LineBorder(Color.black));
		this.setBackground(Backround);
	}
	
	
	// Constructor
	public PieChart(int x, int y, int width, int heigth, String title, Color c) {
		JLabel lab = new JLabel(title);
		lab.setBounds(20,2,360,30);
		lab.setForeground(Color.white);
		lab.setFont(new Font("century gothic", Font.TRUETYPE_FONT, 14));
		initialise(x, y, width, heigth, c);
		add(lab);
	}

	// *  Getters And Setters
	public float[] getPr() {
		return Pr;
	}
}