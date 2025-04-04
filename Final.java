package finalProject;

import binaryTree.BTNode;
import java.util.*;
import textFile.TextFileReader;

public class Final {
	
	public static void main(String[] args) {
		//Prompt user to input keywords and operator
		System.out.println("Welcome to my Search. Please enter your query (two keywords seperated by an AND or an OR): ");
		Scanner userInput = new Scanner(System.in);
		//Store keywords and operator to use later
		String userString = userInput.nextLine();
		
		//Read the file and store the websites in a list of class Website, which stores a classes URL, Description, and keywords
		ArrayList<Website> websites = createWebsites();
		
		//Create the data structure that will be searched through to find specific websites
		Hashtable<Website, ArrayList<String>> map = createStructure(websites);
		
		//Create a list of websites that fit under the criteria provided by the user
		ArrayList<Website> possibleSites = searchWebsites(userString, map);
		
		//Print out the websites that fit under the users criteria
		System.out.println("Results: " + possibleSites.size());
		for(Website site : possibleSites) {
			System.out.println(site.getURL() + ", " + site.getDescription());
		}
	}
	
	public static Hashtable<Website, ArrayList<String>> createStructure(ArrayList<Website> websites) {
		//Initialize data structure used to store websites
		Hashtable<Website, ArrayList<String>> map = new Hashtable<Website, ArrayList<String>>();
		//Store in a Hashtable with the website object as well as the keywords associated with said website
		//Iterate and place all websites from the file in the data structure
		for(Website website : websites) {
			map.put(website, website.getKeyWords());
		}
		return map;
	}
	
	public static ArrayList<Website> createWebsites(){
		//Create a file reader object
		TextFileReader websiteMaker = new TextFileReader();
		
		//Call the method I altered in the file reader class to create the website objects from reading the file info
		ArrayList<Website> websites = websiteMaker.createWebs();
		
		return websites;
	}
	
	public static ArrayList<Website> searchWebsites(String userInput, Hashtable<Website, ArrayList<String>> map) {
		//Initialize an array to store the websites that fit under the users criteria
		ArrayList<Website> sites = new ArrayList<>();
		//Take out the two key words and the operator from the string the user gave
		String[] words = userInput.split(" ");
		//initialize count variable
		int count = 0;
		
		//Get key words and operator from userString
		String firstKey = words[0].toLowerCase();
		String operator = words[1].toLowerCase();
		String secondKey = words[2].toLowerCase();
		
		//Check whether the operator is and or or
		if(operator.equals("and")) {
			//if its and, look through all the keywords associated with each website
			//if a website has both of the two keywords, which it should because of the and operator,
			//store the website in the website list and iterate through all other websites
			for (Map.Entry<Website, ArrayList<String>> entry : map.entrySet()) {
	            ArrayList<String> keywords = entry.getValue();
	            for(String keyword : keywords) {
	            	if(keyword.equals(firstKey) || keyword.equals(secondKey)) {
	            		count++;
	            	}
	            }
	            if(count == 2) {
	            	sites.add(entry.getKey());
	            }
	            count = 0;
	        }
		}
		else if(operator.equals("or")) {
			//if its or, look through all the keywords associated with each website once again,
			//this time however if a website has at least one of the keywords it will be added
			//to the list of websites, because of the or operator
			for (Map.Entry<Website, ArrayList<String>> entry : map.entrySet()) {
	            ArrayList<String> keywords = entry.getValue();
	            for(String keyword : keywords) {
	            	if(keyword.toLowerCase().equals(firstKey) || keyword.toLowerCase().equals(secondKey)) {
	            		count++;
	            	}
	            }
	            if(count > 0) {
	            	sites.add(entry.getKey());
	            }
	            count = 0;
	        }
		}
		return sites;
	}
}
