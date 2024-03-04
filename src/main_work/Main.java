package main_work;

import java.io.FileNotFoundException;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;


public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		FlatMacDarkLaf.setup(); 
		new GUI();
	}
}
