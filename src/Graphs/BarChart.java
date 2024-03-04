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
public class BarChart extends JPanel{
	
	
	private int[][] matrix_Err_Aver ;//tableau[][] = {{1,2, 3,4,5,6},{1,2, 3,4,5,6}, {1,2, 3,4,5,6}}
	private String[] matrix_Id ;
	private static final int debutX = 40, debutY = 50 ; 
	
	
	protected void paintComponent(Graphics g2) {
		super.paintComponent(g2);
		
		Graphics2D g = (Graphics2D) g2;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// pour eviter le depassement de la limite
		
		Color[] c = new Color[] {MyColor.bar1, MyColor.bar2, MyColor.bar3};
		String[] str = new String[] {"Informations", "Avertissements", "Erreurs"};
		
		
		//  paint line diagram
		int line=60;
		g.setColor(Color.cyan);
		g.drawString("2 unit�s", debutX+265, debutY-5);
		for(int a=0; a<140; a+=10) {
			g.setColor(MyColor.gray);
			if(a%4 ==0)
				g.drawString(""+a, line-30, 46);
			g.setColor(MyColor.DARK_GRAY);
			g.drawLine(line, 51, line, 179);
			line += 20;
		}
		g.setColor(MyColor.black);
		g.drawRect(40, 50, 300, 130);
		
		// draw the statistics informations bar by bar
		int pr;
		if(matrix_Err_Aver != null) {
			for(int k=0;k<3;k++)
				for(int i=0;i<6; i++)
					if(matrix_Err_Aver[k][i] > 300) matrix_Err_Aver[k][i]=300;
			
			for(int a=64; a<184; a+=20) {
				for(int k = 0;k<3;k++) {
					
					g.setColor(c[k]);
					pr = matrix_Err_Aver[k][(a-64)/20];
					g.fillRect(40, a+4*k, (int) (pr), 4);
					if(a==64) g.fillRect(30+110*k, 206, 10, 10);this.add(new MyLabel(str[k], 45+110*k, 200, 100, 20,  Color.LIGHT_GRAY));
					g.setColor(MyColor.DarkGray);
					g.drawRect(40, a+4*k, (int) (pr), 4);
			
				}
				g.setColor(MyColor.gray);
				g.drawString(""+matrix_Id[(a-64)/20], 25-3*matrix_Id[(a-64)/20].length(), a+8);
			}
		}
		
	}
	
	public void remplir(int[][] matrix, String[] matrixId) {
		
		this.matrix_Err_Aver = matrix;
		this.matrix_Id = matrixId;
		repaint();
	}
	
	private void initialise(int x, int y, int width, int heigth, Color Backround) {	
		this.setBounds(x,y,width,heigth);
		this.setLayout(null);
		this.setBorder(new LineBorder(Color.black));
		this.setBackground(Backround);
	}
	
	
	// * Constructor
	public BarChart(int x, int y, int width, int heigth, String title, Color c) {
		JLabel lab = new JLabel(title);
		lab.setBounds(20,2,360,30);
		lab.setForeground(Color.white);
		lab.setFont(new Font("century gothic", Font.TRUETYPE_FONT, 14));
		initialise(x, y, width, heigth, c);
		add(lab);
	}


	// Getters And Setters]
	public int[][] getMatrix_Err_Aver() {
		return matrix_Err_Aver;
	}
	public void setMatrix_Err_Aver(int[][] matrix_Err_Aver) {
		this.matrix_Err_Aver = matrix_Err_Aver;
	}


}
