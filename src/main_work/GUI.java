package main_work;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTabbedPane;

import Graphs.RadarChart;
import Graphs.BarChart;
import Graphs.LineChart;
import Graphs.PieChart;
import extends_classes.Butt;
import extends_classes.MyColor;
import extends_classes.MyLabel;
import extends_classes.PanelGraphic;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	
	public static String path;
	
	private static PanelGraphic panelWelcome, panelHome, panelDashboard, panel1, panel2, pnOptions;
	private static PieChart circleCharPanel; private LineChart lineCharPanel; 
	private RadarChart radarCharPanel;private BarChart barCharPanel;
	
	private Butt[] buttOption;
																							
	private JProgressBar pr;
	public final int Bu_Close = 10, Bu_Full = 11, Bu_Hide = 12, BU_Return = 13, Bu_Home = 14, BU_DASH = 15, Bu_Hel = 16, Bu_H = 17 ;//ids
	public static final short Bu_Help = 16, panDashboard = 3, panTitle = 1, panOption = 4, panWelcome = 0, panHome = 2, withoutId = -1; // ids
	MyLabel label2 = new MyLabel("File Load From: \" "+"\"", 6, 2, 600, 20, Color.blue);;
	
	//  Constructor
	public GUI() {
		try {
			BufferedImage image = ImageIO.read(new File("icon\\dashboard.png"));
			setIconImage(image);
		} catch (IOException e) {}
		
		initialHome();
		initialDashboard();
		  
	    JTabbedPane tabbedPane = new JTabbedPane();

	    tabbedPane.addTab("Home", null, panelHome, "informations");	 
	    tabbedPane.addTab("Dashboard", null, panelDashboard, "analytics");
//	    tabbedPane.addChangeListener(new ChangeListener() {
//			
//			@Override
//			public void stateChanged(ChangeEvent e) {
//				if(tabbedPane.getSelectedIndex() == 1) {
//					if(!PanelGraphic.isfilechosed)
//						if(createChFDialog()) {
//							if(ConnectDataBase.connectDB()){
//								PanelGraphic.isfilechosed = true;
//								label2.setText(path);
//								/*
//								 * try { ConnectDataBase.exportToDB(new File(path)); } catch
//								 * (FileNotFoundException e1) { }
//								 */
//								ConnectDataBase.countTotal();
//								ConnectDataBase.countAverTotal();
//								ConnectDataBase.countErrTotal();
//								ConnectDataBase.countInfoTotal();
//								ConnectDataBase.plusFreqent();
//								ConnectDataBase.plusFreqentId();
//								ConnectDataBase.plusFreqentId();
//
//								if(ConnectDataBase.countPercentageNiv() != null) 
//									circleCharPanel.remplir(ConnectDataBase.getPercs());
//								
//								if(ConnectDataBase.plusFreqentPerc() != null) 
//									radarCharPanel.remplir(ConnectDataBase.getPersPlusFre(), ConnectDataBase.getPlusFreqents());
//								
//								if(ConnectDataBase.nbrplusFreqentId() != null) 
//									barCharPanel.remplir(ConnectDataBase.getNbrPlusFreId(),ConnectDataBase.getPlusFreqentsId());
//								
//								if(ConnectDataBase.countAverErrParDeuxMoins() != null) 
//									lineCharPanel.remplir(ConnectDataBase.getNbrDeuxMoins());
//		
//								ConnectDataBase.disconnectDB();
//							}else JOptionPane.showMessageDialog(GUI.this, "cant connect to the database !");
//						}else{
//							JOptionPane.showMessageDialog(GUI.this, "no file .txt choosed");
//							tabbedPane.setSelectedIndex(0);
//						}
//					repaint();
//					}
//			}
//		});
	    add(tabbedPane);

		setSize( 1000, 620 );
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	protected boolean createChFDialog() {
		
		int dialog = JOptionPane.showConfirmDialog(this, "For show the Dashboard you should chose first a file .txt",
				"Asking chose file" , JOptionPane.OK_CANCEL_OPTION);
		
		if (dialog == 0) {
			JFileChooser fc = new JFileChooser();
			fc.setFileFilter(new FileNameExtensionFilter("TEXT FILES", "txt", "text"));
			int a = fc.showOpenDialog(this);
			if(a == JFileChooser.APPROVE_OPTION ) {
				File f = new File(fc.getSelectedFile().getAbsolutePath());
				if(f.getName().toLowerCase().endsWith(".txt")) {
					path = f.getAbsolutePath();
					return true;
				}
			}
		}
		return false;
	}

	protected void createHelpDialog() {

		JOptionPane.showMessageDialog(this, 
				"This is a application help you to analyst your system event !"
				+ "\n 1 . after do anything you must first activate your data base server !"
				+ "\n 2 . after go to the dashboard"
				+ "\n 3 . chose ok to chose your event file (.txt)"
				+ "\n 4 . them chose your file that you want to craete"
				+ "\n then the graphics analistics well be affiched automaticly .");
	}
	
	protected void initialDashboard() {
		
		label2.setFont(new Font("century gotic", Font.ITALIC, 13));
		buttOption = new Butt[5];
		
		int a = 100;
		for (int k = 0; k<5; k++) {buttOption[k] = new Butt(1, a, 198, 40, 17+k, null); a+=40;}
	
		
		buttOption[0].setText("LAST HOUR");
		buttOption[0].setChecked(true);
		buttOption[1].setText("LAST DAY");
		buttOption[2].setText("LAST WEEK");
		buttOption[3].setText("LAST MONTH");
		buttOption[4].setText("LAST 6 MONTH");
		buttOption[0].setForeground(MyColor.gray);
		
		
		MyLabel labOptions = new MyLabel("ADVANCED OPTIONS", 0, 0, 150, 20);
		pnOptions = new PanelGraphic(40, 80, 135, 20, withoutId, null);
		pnOptions.add(labOptions);
		panel1 = new PanelGraphic(225, 36, 755, 24, (short)2, null);
		panel1.add(label2);
		
		panel2 = new PanelGraphic(20, 36, 200, 514, panOption, null);
		panel2.setBorder(new LineBorder(new Color(28, 46, 74)));
		panel2.add(pnOptions);
		panel2.addAll(buttOption);
		
		circleCharPanel = new PieChart(225, 70, 375, 235, "The Levels variable percentage  ", null);
		lineCharPanel = new LineChart(605, 70, 375, 235, "The variation of defferent Levels / months", null); 
		radarCharPanel = new RadarChart(225, 310, 375, 240, "The variation the freqents categorie",  null);
		barCharPanel = new BarChart(605, 310, 375, 240, "the variation of event classed by Id", null); 
		
		panelDashboard = new PanelGraphic(0, 0, 1000, 570, panDashboard, null);
		panelDashboard.addAll(panel1, panel2, circleCharPanel, lineCharPanel, radarCharPanel, barCharPanel);
	}

	protected void initialHome(){
		JLabel label4 = new JLabel();
		label4.setBounds(200, 140, 600, 300);
		label4.setForeground(Color.white);
		label4.setFont(new Font("Centurry gothic", Font.PLAIN, 18));
		label4.setText("<html><pre>Projet D'application informatique en java</pre> "
				+ "<pre>Encadr� par le professeur Aniss Moumen</pre>"
				+ "<pre>Projet realis� par:</pre>"
				+ "<br><pre width=\"20\" color=\\\"black\\\">--> Mohamed Mitique.</pre></html>");
		panelHome = new PanelGraphic(3, 37, 1000, 563, panHome, null);
		panelHome.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.gray));
		panelHome.add(label4);
		panelHome.setLayout(null);
		
}	
	
	protected void initialWelcome() {
		
		panelWelcome = new PanelGraphic(0, 0, 600, 404, panWelcome, Color.cyan);
		pr = new JProgressBar();
		Font f1 = new Font("Calibr", Font.BOLD, 16);
		JLabel label3 = new JLabel("Loading...");
		label3.setBounds(10, 375, 200, 20);
		label3.setForeground(Color.white);
		label3.setFont(f1);
		pr.setBounds(0, 395, 600, 4);
		pr.setValue(0);
		pr.setForeground(Color.blue);
		panelWelcome.add(pr);
		panelWelcome.add(label3);
		
		
	}
	
	mai
}
