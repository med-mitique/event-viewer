package extends_classes;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class MyLabel extends JLabel {

	
	public MyLabel(String text, int x, int y, int width, int hiegth, Color c) {
		this.setText(text);
		this.setBounds(x, y, width, hiegth);
		this.setForeground(c);
		this.setFont(new Font("century gothic", Font.ITALIC,11));
	}
	
	public MyLabel(String text, int x, int y, int width, int hiegth) {
		
		this.setText(text);
		this.setBounds(x, y, width, hiegth);
		this.setForeground(Color.cyan);
		this.setFont(new Font("century gothic", Font.ITALIC,11));
	}
	
	public MyLabel( int x, int y, int width, int hiegth) {
		
		this.setBounds(x, y, width, hiegth);
		this.setForeground(Color.cyan);
		this.setFont(new Font("century gothic", Font.ITALIC,11));
	}
	
	public MyLabel() {
		
	}
}
