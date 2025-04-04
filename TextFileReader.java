package textFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import finalProject.Website;
import java.util.ArrayList;

public class TextFileReader {
	BufferedReader input;
	ArrayList<Website> websites = new ArrayList<Website>();
	
	public ArrayList<Website> createWebs() {
		try{
			 input = new BufferedReader(new FileReader("C:\\Users\\liam1\\Documents\\TestFile.txt"));
		}
		catch(FileNotFoundException fileNotFoundException)
		{
			System.out.println("Unable to read test file C:\\Users\\liam1\\Documents\\TestFile.txt");
		}	 
	
		try{
			 String webSiteURL;
			 String webSiteDescription;
			 String[] webSiteKeywords = new String[20];
		
			 String str;
			 while ((str = input.readLine()) != null) {
				 String[] tokens=str.split(", ");
				 if (tokens.length>2)
				    {
					Website website = new Website();
					webSiteURL = tokens[0];
				    webSiteDescription = tokens[1];
					website.setURL(webSiteURL);
					website.setDescription(webSiteDescription);
				    //System.out.printf("%nSite %s%n",webSiteURL);
				    //System.out.printf("Description %s%n",webSiteDescription);
				    //System.out.println("Key Words:");
					//System.out.printf("%s ",tokens[i]);
				    for (int i=2;i<tokens.length; i++)
					   website.addKeyWord(tokens[i]);
				    websites.add(website);
			       }
			 }
		}
	    catch (IOException ioException){
	    	System.out.println("Unable to read test file C:\\TestData\\TestFile.txt");
	    }
		return websites;
		
		
		// keep reading each line of the text file
		/* while (input.hasNext()){
			webSiteURL = input.readLine();
			webSiteDescription = input.next();
			System.out.printf("Site %s%n",webSiteURL);
			System.out.printf("Description %s%n",webSiteDescription);
			// Parse out the Key Words:
			System.out.println("Key Words:");
			StringTokenizer st2 = new StringTokenizer(input.next(), ",");

			while (st2.hasMoreElements()) {
				System.out.println(st2.nextElement());
			}
		} */

	}
}
