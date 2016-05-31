package org.rssreader;

public class Item {
	String title;
	String link;
	String description;
	String pubdate;
	String guid;
	
	String newLine = System.getProperty("line.separator");
	
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getLink(){
		return this.link;
	}
	
	public void setLink(String link){
		this.link = link;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getPubDate(){
		return this.pubdate;
	}
	
	public void setPubDate(String pubdate){
		this.pubdate = pubdate;
	}
	
	public String getGuid(){
		return this.guid;
	}
	
	public void setGuid(String guid){
		this.guid = guid;
	}
	
	public String toString(){
		return "Item: " + newLine + "title: " + title + newLine + "link: " + link + newLine
				+ "description: " + description + newLine + "pubdate: " + pubdate + newLine
				+ "guid: " + guid + newLine;
	}

}
