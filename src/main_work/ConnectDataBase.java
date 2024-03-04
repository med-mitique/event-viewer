package main_work;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;;


public class ConnectDataBase {
	
	private static Connection connection;
	private static Statement stm;
	private static String query;
	private static float[] percs;
	private static String[] plusFreqents;
	private static String[] plusFreqentsId;
	private static int[][] persPlusFre ;
	private static int[][] nbrPlusFreId ;
	private static int total, errTotal, averTotal, infoTotal;
	private static int[][] nbrDeuxMoins ;
	private static int[][] nbrErrAviInfoPlusFr ;
	
	
	public static boolean connectDB() {
		try {
			
			String url = "jdbc:mysql://localhost/";
			String user = "root";
			String passwd ="";
			connection = DriverManager.getConnection(url, user, passwd);
			Statement stm = connection.createStatement();
			stm.execute("CREATE DATABASE IF NOT EXISTS evenments;");
			connection.close();
			url = "jdbc:mysql://localhost/evenments";
			connection = DriverManager.getConnection(url, user, passwd);
			stm.close();
			return true;
			
		} catch (SQLException e) {
			return false;
		}catch (NullPointerException e) {
			return false;
		}
		
	}
	
	
 	@SuppressWarnings("resource")
	public static boolean exportToDB(File f) throws FileNotFoundException {
		
 		Scanner scanner = new Scanner(f);
		query = "CREATE TABLE IF NOT EXISTS  event(niveau VARCHAR(50), date text, source text,"
				+ " id text, categorie text, description text)";
		
		try{
			stm = connection.createStatement();
			stm.execute("DROP TABLE event IF EXISTS");
			stm.execute(query);
		
			// importer le fichie 
			String line = scanner.nextLine();
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				int a = line.split("\t").length;
				
				line =  "('" +line.replace("'", " ").replace("\t", "','")+"')"; // formuler les colonmes
				
				if(a == 6) {
					query = "INSERT INTO event VALEUS "+line;
					stm.execute(query);
				}
					
				else if(a == 5) {
					query = "INSERT INTO event (niveau, date, source, id, categorie) VALEUS "+line;
					stm.execute(query);
				}
			}
			stm.close();
			return true;
		}
		catch(SQLException | NullPointerException e) {
			return false;
		}
		
		
	}
	
 	
	public static int countTotal(){
		try {
			stm = connection.createStatement();
			ResultSet res = stm.executeQuery("SELCT COUNT(*) AS total FROM `event`");
			while(res.next()) total = res.getInt("total");
			return total;
			
		} catch (SQLException | NullPointerException e) {
			return -1;
		}
	}
	
	
	public static int countErrTotal(){
		try{
			stm = connection.createStatement();
			ResultSet res = stm.executeQuery("SLECT COUNT(*) AS total FROM `event` WHERE niveau='Erreur'");
			while(res.next()) errTotal = res.getInt("total");
			stm.close();
			return errTotal;
		}catch (SQLException | NullPointerException e) {
			return -1;
		}
	}
	
	public static int countAverTotal(){
		try {
			stm = connection.createStatement();
			ResultSet res = stm.executeQuery("SLECT COUNT(*) AS total FROM `event` WHERE niveau='Avertissement'");
			while(res.next()) averTotal = res.getInt("total");
			stm.close();
			return averTotal;
			
		}catch(SQLException | NullPointerException e){
			return -1;
		}
		
	}
	
	public static int countInfoTotal(){
		try {
			stm = connection.createStatement();
			ResultSet res = stm.executeQuery("SLECT COUNT(*) AS total FROM `event` WHERE niveau='Information'");
			while(res.next()) infoTotal = res.getInt("total");
			stm.close();
			return averTotal;
		}catch(SQLException | NullPointerException e){
			return -1;
		}
		
	}
	
	public static float[] countPercentageNiv()  {
		try {
			percs = new float[3];
			percs[0] = (float) (infoTotal*100.0/total);
			percs[1] = (float) (averTotal*100.0/total);
			percs[2] = (float) (errTotal*100.0/total);
			return percs;
			
		} catch (Exception e) {
			return null;
		}
		
	}

	public static String[] plusFreqent() {
		
		plusFreqents = new String[6];
		//pour deffinir le plus frequets source d'evenment
		query = "SELECT source, COUNT(*) AS nbr FROM `event`WHERE niveau='Avertissement' OR niveau='Erreur' GROUP BY source ORDER BY nbr DESC";
		
		try {
			stm = connection.createStatement();
			ResultSet res = stm.executeQuery(query);
			int k = 0;
			while(res.next() && k < 6) {
				plusFreqents[k] = res.getString("source");
				k++;
			}
			stm.close();
			
			return plusFreqents;
		}catch(SQLException | NullPointerException e) {
			return null;
		}
		
	}
	
	public static int[][] plusFreqentPerc() {
		
		persPlusFre = new int[2][6];
		ResultSet res;
		try {
			for(int i=0;i<2;i++) {
				for (int k=0;k<6;k++) {
					if(i==0) query = "SELECT source, COUNT(*) AS nbr FROM `event` where source='"+plusFreqents[k]+"' AND niveau='Avertissement'";
					else if(i==1)query = "SELECT source, COUNT(*) AS nbr FROM `event` where source='"+plusFreqents[k]+"' AND niveau='Erreur'";
					stm = connection.createStatement();
					res = stm.executeQuery(query);
					if (i==0)while(res.next()) persPlusFre[i][k] = (int) (res.getInt("nbr")*100/averTotal);
					else while(res.next()) persPlusFre[i][k] = (int) (res.getInt("nbr")*100/errTotal);
				}
				
			}
			stm.close();
			return persPlusFre;
		}catch(SQLException | NullPointerException | ArithmeticException e) {
			return null;
		}
		
	}
	
	public static int[][] countAverErrParDeuxMoins() {
		
		nbrDeuxMoins = new int[2][6];
		
		try {
			stm = connection.createStatement();
			for(int i=0;i<2;i++) {
				for (int k=0;k<6;k++) {
					if(i==0) query = "SELECT COUNT(*) AS nbr FROM `event` WHERE date LIKE '%/0"+(k+1)+"/%' AND niveau='Avertissement'";
					else query = "SELECT COUNT(*) AS nbr FROM `event` WHERE date LIKE '%/0"+(k+1)+"/%' AND niveau='Erreur'";
					
					ResultSet res = stm.executeQuery(query);
					while(res.next()) { nbrDeuxMoins[i][k] = res.getInt("nbr");}
				}
			}
			stm.close();
			return nbrDeuxMoins;
		}catch(SQLException | NullPointerException e) {
			return null;
		}
	}

	public static String[] plusFreqentId() {
	
		plusFreqentsId = new String[6];
		query = "SELECT id, COUNT(*) AS nbr FROM `event` WHERE niveau='Erreur' GROUP BY  id ORDER BY nbr DESC";
		
		try {
			stm = connection.createStatement();
			ResultSet res = stm.executeQuery(query);
			int k = 0;
			while(res.next() && k < 6) {
				plusFreqentsId[k] = res.getString(1);
				k++;
			}
			stm.close();
			return plusFreqentsId;
		}catch(SQLException | NullPointerException e) {
			return null;
		}
	}
	

	public static int[][] nbrplusFreqentId() {

		try {
			nbrPlusFreId = new int[3][6];
			for(int i=0;i<3;i++) {
				for (int k=0; k < 6; k++) {
					if(i==0) query = "SELECT COUNT(*) AS nbr FROM `event` WHERE id='"+plusFreqentsId[k]+"' AND niveau='Information'";
					else if(i==1) query = "SELECT COUNT(*) AS nbr FROM `event` WHERE id='"+plusFreqentsId[k]+"' AND niveau='Avertissement'";
					else query = "SELECT COUNT(*) AS nbr FROM `event` WHERE id='"+plusFreqentsId[k]+"' AND niveau='Erreur'";
					
					stm = connection.createStatement();
					ResultSet res = stm.executeQuery(query);
					while(res.next()) nbrPlusFreId[i][k] = res.getInt("nbr");
															
				}
			}
			stm.close();
			return nbrPlusFreId;
		}catch(SQLException | NullPointerException e) {
			return null;
		}
	}


	// getters and setters
	public static String[] getPlusFreqentsId() {return plusFreqentsId;}
	public static void setPlusFreqentsId(String[] plusFreqentsId) { ConnectDataBase.plusFreqentsId = plusFreqentsId;}
	public static int[][] getNbrPlusFreId() {return nbrPlusFreId;}
	public static void setNbrPlusFreId(int[][] nbrPlusFreId) {ConnectDataBase.nbrPlusFreId = nbrPlusFreId;}
	public static float[] getPercs() {return percs;}
	public static void setPercs(float[] percs) {ConnectDataBase.percs = percs;}
	public static String[] getPlusFreqents() {return plusFreqents;}
	public static void setPlusFreqents(String[] plusFreqents) {ConnectDataBase.plusFreqents = plusFreqents;}
	public static int[][] getPersPlusFre() {return persPlusFre;}
	public static void setPersPlusFre(int[][] persPlusFre) {ConnectDataBase.persPlusFre = persPlusFre;}
	public static int getTotal() {return total;}
	public static void setTotal(int total) {ConnectDataBase.total = total;}
	public static int getErrTotal() {return errTotal;}
	public static void setErrTotal(int errTotal) {ConnectDataBase.errTotal = errTotal;}
	public static int getAverTotal() {return averTotal;}
	public static void setAverTotal(int averTotal) {ConnectDataBase.averTotal = averTotal;}
	public static int[][] getNbrDeuxMoins() {return nbrDeuxMoins;}
	public static void setNbrDeuxMoins(int[][] nbrDeuxMoins) {ConnectDataBase.nbrDeuxMoins = nbrDeuxMoins;}
	public static int[][] getNbrErrAviInfoPlusFr() {return nbrErrAviInfoPlusFr;}
	public static void setNbrErrAviInfoPlusFr(int[][] nbrErrAviInfoPlusFr) {ConnectDataBase.nbrErrAviInfoPlusFr = nbrErrAviInfoPlusFr;}
}
