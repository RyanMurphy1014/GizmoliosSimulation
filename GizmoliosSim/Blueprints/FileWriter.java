package Blueprints;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;

import Algorithm.Order;

public class FileWriter {
	
	 public static PrintWriter openWrite() {
			Frame f = new Frame();
			// decide from where to read the file
			FileDialog foBox = new FileDialog(f, "Pick location for writing your file", FileDialog.SAVE);
			System.out.println("The dialog box will appear behind Eclipse.  " + 
				      "\n   Choose where you would like to write your data.");
			foBox.setVisible(true);
			// get the absolute path to the file
			String foName = foBox.getFile();
			String dirPath = foBox.getDirectory();

			// create a file instance for the absolute path
			File outFile = new File(dirPath + foName);
			PrintWriter out = null;

			try {
				out = new PrintWriter(outFile);
			} catch (IOException e) {
				System.out.println("You threw an exception");
			}
			return out;
		}
	 
	 public static void outputData(LinkedList<Order> nums) {
			PrintWriter out = null;
			try {
				out = openWrite();
				int ID = 1;
				out.println("ID,Name,Time(hrs),Late Penalty,Order Arrival,Requested,Start Processing,End Processing,Penalty");
				for (int i = 0; i < nums.size(); i++) {
					
					out.println("Order_" + ID + "," + nums.get(i).toString());
				
				}
			} finally {
				try {  	out.close();} catch (Exception e) {	}
			}
		}

}
