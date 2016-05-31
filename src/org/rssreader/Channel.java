package org.rssreader;

import java.util.ArrayList;
import java.util.List;

public class Channel {
	String title;
	String link;
	String description;
	String lastBuildDate;
	String docs;
	String generator;
	
	static String newLine = System.getProperty("line.separator");
	
	final List<Item> rssItem = new ArrayList<Item>();
	
	public Channel(String title, String link, String description, String lastBuildDate, String docs, String generator){
		this.title = title;
		this.link = link;
		this.description = description;
		this.lastBuildDate = lastBuildDate;
		this.docs = docs;
		this.generator = generator;
	}
	
	public List<Item> getItems(){
		return this.rssItem;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public String getLink(){
		return this.link;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public String getLastBuildDate(){
		return this.lastBuildDate;
	}
	
	public String getDocs(){
		return this.docs;
	}
	
	public String getGenerator(){
		return this.generator;
	}
	
	public String toString(){
		return "Channel: " + newLine + "title: " + title + newLine + "link: " + link + newLine
				+ "description: " + description + newLine + "lastBuildDate: " + lastBuildDate + newLine
				+ "docs: " + docs + newLine + "generator: " + generator + newLine;
	}
}

