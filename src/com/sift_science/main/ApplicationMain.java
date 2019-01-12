/**
 * ApplicationMain.java 
 * Purpose: ApplicationMain class to start the application. 
 * 			
 *
 * @author Apurba Mahanta
 * @version 1.0 
 */

package com.sift_science.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.sift_science.service.SetService;

public class ApplicationMain {

	public static void main(String[] args) {

		
		ArrayList<String> inputCards = new ArrayList<>();
		

		SetService set = new SetService();

		BufferedReader br = null;

		try {
			 br = new BufferedReader(new InputStreamReader(System.in));
			 //br = new BufferedReader(new FileReader("input2.txt"));
			String line;
			br.readLine(); // this will read the first line
			while ((line = br.readLine()) != null) {
				if (line.length() > 1) {
					inputCards.add(line);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		set.generateSets(inputCards);

	}

}
