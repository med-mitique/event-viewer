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
public class LineChart extends JPanel{
	
	private int[][] matrix_Err_Aver ;
	private static final int debutX = 40, debutY = 50 ;

	
	protected void paintComponent(Graphics g2) {
		super.paintComponent(g2);
		
		Graphics2D g = (Graphics2D) g2;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		
		//  * paint border diagram
		g.setFont(new Font(null, Font.ITALIC, 11));
		g.setColor(Color.black);
		g.drawRect(debutX, debutY, 300, 120);
		g.setColor(Color.cyan);
		g.drawString("months", debutX+265, debutY+134);
		
		
		//  * paint line diagram
		for(int line=10;line<80;line+=10) {
			g.setColor(Color.pink);
			g.drawString(""+(80-line), debutX-15, (int) (debutY+1.5*line+3));
			if(line != 70) g.drawString(""+line/10, debutX+4*line-5, debutY+135);
			g.setColor(MyColor.DARK_GRAY);
			g.drawLine(debutX+1, (int) (debutY+1.5*line), debutX+299, (int) (debutY+1.5*line));
		}

		if(matrix_Err_Aver == null) return;
		// pour eviter le depassement de la limite
		for(int k=0;k<2;k++) {
			for(int a=0;a<6;a++) {
				if(matrix_Err_Aver[k][a] > 78) matrix_Err_Aver[k][a] = 78;
				if (matrix_Err_Aver[k][a] < 2) matrix_Err_Aver[k][a] += 1;
			}
		}
				
		
		// draw premier courbe Erour
		int line =debutY+120;
		g.setColor(Color.green);
		for(int k=1; k<7;k++) {
			g.fillOval(debutX+k*40, line-(int)(1.5*matrix_Err_Aver[0][k-1])-2, 4, 4);
			if(k==6) break;
			g.drawLine(debutX+k*40+2, line-(int)(1.5*matrix_Err_Aver[0][k-1]), debutX+(k+1)*40+1, line-(int)(1.5*matrix_Err_Aver[0][k]));
			
		}
		g.drawLine(20, 198, 50, 198);
		this.add(new MyLabel("Errours", 55, 190, 100, 10, Color.LIGHT_GRAY));
		
		
		// draw deuxieme courbe Avertissement
		g.setColor(Color.blue);
		for(int k=1; k<7;k++) {
			g.fillOval(debutX+k*40, line-(int)(1.5*matrix_Err_Aver[1][k-1])-2, 4, 4);
			if(k==6) break;
			g.drawLine(debutX+k*40+2, line-(int)(1.5*matrix_Err_Aver[1][k-1]), debutX+(k+1)*40+1, line-(int)(1.5*matrix_Err_Aver[1][k]));
			
		}
		g.drawLine(20, 213, 50, 213);
		this.add(new MyLabel("Avertisments", 55, 205, 100, 10,  Color.LIGHT_GRAY));

	}
	
	public void remplir(int[][] matrix) {
		this.matrix_Err_Aver = matrix;
		repaint();
	}
	
	private void initialise(int x, int y, int width, int heigth, Color Backround) {
		this.setBounds(x,y,width,heigth);
		this.setLayout(null);
		this.setBorder(new LineBorder(Color.black));
		this.setBackground(Backround);
	}
	
	
	// Constructor
	public LineChart(int x, int y, int width, int heigth, String title, Color c) {
		JLabel lab = new JLabel(title);
		lab.setBounds(20,2,360,30);
		lab.setForeground(Color.white);
		lab.setFont(new Font("century gothic", Font.TRUETYPE_FONT, 14));
		initialise(x, y, width, heigth, c);
		add(lab);
	}


	
	// Getters And Setters
	public int[][] getMatrix_Err_Aver() {
		return matrix_Err_Aver;
	}
	public void setMatrix_Err_Aver(int[][] matrix_Err_Aver) {
		this.matrix_Err_Aver = matrix_Err_Aver;
	}
}
