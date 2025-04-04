package finalProject;

import java.util.*;

public class Website {

	private ArrayList<String> keywords = new ArrayList<>();;
	private String description;
	private String url;
	
	public Website() {	
	}

	public String getURL() {
		return this.url;
	}
	
	public void setURL(String url) {
		this.url = url;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public ArrayList<String> getKeyWords() {
		return this.keywords;
	}
	
	public void addKeyWord(String keyword) {
		keywords.add(keyword);
	}
}
