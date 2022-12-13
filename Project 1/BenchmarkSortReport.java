/**
 * Author: Opeyemi Ajisegiri
 * Class:  Design And Analysis of Computer Algorithm (CMSC 451)
 * Project: 1
 * File: 	BenchmarkSortReport.java
 * Purpose: A User Interface creating program that allows the user to choose the file to read from 
 * 			and calculate and formulate the values to be displayed in the MergeSort Report in a table format using
 * 			Java File Chooser(JFileChooser), Java Frame (JFrame) and Java Table (JTable).
 * **/

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;

public class BenchmarkSortReport extends JFrame{
	
	private JFileChooser chooser;
	/* Global variables */
	JTable report;
	JButton  open = new JButton();		
	
	int size = 0, i = 0, n = 0, countSum = 0, countAvg = 0;
	long timeSum = 0, timeAvg = 0, countVar = 0, cvSum = 0, timeVar = 0, timeCVSum = 0;
	double time_CV = 0, countCV = 0;
	String countCoeff = null, timeCoeff = null;
	/* Creating a two-dimensional array to  store the values in the Java Table(JTable),
	 *  and an array to store the titles of columns in the table */ 
	Object[][] data = {{size, countAvg, countCoeff, timeAvg, timeCoeff },
						{size, countAvg, countCoeff, timeAvg, timeCoeff },
						{size, countAvg, countCoeff, timeAvg, timeCoeff },
						{size, countAvg, countCoeff, timeAvg, timeCoeff },
						{size, countAvg, countCoeff, timeAvg, timeCoeff },
						{size, countAvg, countCoeff, timeAvg, timeCoeff },
						{size, countAvg, countCoeff, timeAvg, timeCoeff },
						{size, countAvg, countCoeff, timeAvg, timeCoeff },
						{size, countAvg, countCoeff, timeAvg, timeCoeff },
						{size, countAvg, countCoeff, timeAvg, timeCoeff }};
	String[] columns = {"Size", "Avg Count", "Coeff Count", "Avg Time", "Coeff Time" };
	
	/** **/
	BenchmarkSortReport(){
		String fileName = null;
		chooser = new JFileChooser();
		chooser.setDialogTitle("Open File");
		chooser.setCurrentDirectory(new File(".."+File.separatorChar+"MergeSortReports"));
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		if(chooser.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
			int[] counts = new int[50];
			long[] durations = new long[50];
			try {
				Scanner input = new Scanner(chooser.getSelectedFile());
				fileName = chooser.getSelectedFile().getName().toString();
				while(input.hasNextLine()) {
					String line = input.nextLine(); 
					Scanner inputLine = new Scanner(line);
					size = inputLine.nextInt();
					data[n][0] = size;
					while(inputLine.hasNext()) {
						if(inputLine.hasNextInt()) {
							counts[i] = inputLine.nextInt();
							countSum += counts[i];
						}if(inputLine.hasNextLong()) {
							durations[i] = inputLine.nextLong();
							timeSum += durations[i];
						}
						i++;
					}
					countAvg = countSum/i;
					timeAvg = timeSum/i;
					data[n][1] = countAvg;
					data[n][3] = timeAvg;
					for(int j = 0; j < i; j++) {
						countVar = (int) Math.pow(counts[j] - countAvg,2);
						cvSum += countVar;
						timeVar = (int) Math.pow(durations[j] - timeAvg,2);
						timeCVSum += timeVar;
					}
					countVar = cvSum /i;
					countCV = ((Math.sqrt(countVar))/countAvg) * 100;
					data[n][2] = String.format("%.3f", countCV) + "%";
					timeVar = timeCVSum /i;
					time_CV = ((Math.sqrt(timeVar))/timeAvg) * 100;
					data[n][4] = String.format("%.3f", time_CV)+  "%";
					inputLine.close();
					i = 0;
					n++;
				}
				input.close();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setLayout(null);
		if(fileName.equals("recursion.txt")) 
			setTitle("Recursive MergeSort Benchmark Report");
		else 
			setTitle("Iterative MergeSort Benchmark Report");
		report = new JTable(data,columns);
		report.setBounds(30, 40, 200, 300);
		JScrollPane pane = new JScrollPane(report);
		pane.setSize(500, 450);
		add(pane);
		setLocation(150, 150);
	    setSize(500, 250);
		setVisible(true);
	}

	public static void main(String[] args) {
		new BenchmarkSortReport();
	}

}
