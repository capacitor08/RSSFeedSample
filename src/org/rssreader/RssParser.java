package org.rssreader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

public class RssParser {
	
	static final String TITLE = "title";
	static final String LINK = "link";
	static final String DESCRIPTION = "description";
	static final String LASTBUILDDATE = "lastbuilddate";
	static final String DOCS = "docs";
	static final String GENERATOR = "generator";
	static final String PUBDATE = "pubdate";
	static final String GUID = "guid";
	static final String ITEM = "item";
	
	URL rssUrl;
	String excludeString = "";
		
	public RssParser(String rssAddress, String stringToExclude){
		try {
			this.rssUrl = new URL(rssAddress);
			this.excludeString = stringToExclude;
		} catch (MalformedURLException e) {
			System.out.println(e.toString());
		}
	}
		
	public Channel readRSS(){	
		Channel channel = null;
		try {
			boolean isHeader = true;
			String title = "";
			String description = "";
			String lastbuilddate = "";
			String link = "";
			String docs = "";
			String generator = "";
			String pubdate = "";
			String guid = "";
			
			XMLInputFactory input = XMLInputFactory.newInstance();
			InputStream stream = rssUrl.openStream();
			
			XMLEventReader eventRead = input.createXMLEventReader(stream);
			
			while(eventRead.hasNext()){
				XMLEvent event = eventRead.nextEvent();
				if(event.isStartElement()){
					String tag = event.asStartElement().getName().getLocalPart();
					switch(tag){
					case ITEM:
						if(isHeader){
							isHeader = false;
							channel = new Channel(title, link, description, lastbuilddate, docs, generator);
						}
						event = eventRead.nextEvent();
						break;
					case TITLE:
						title = extractData(event, eventRead);
						break;
					case LINK:
						link = extractData(event, eventRead);
						break;
					case DESCRIPTION:
						description = extractData(event, eventRead);
						break;
					case LASTBUILDDATE:
						lastbuilddate = extractData(event, eventRead);
						break;
					case DOCS:
						docs = extractData(event, eventRead);
						break;
					case GENERATOR:
						generator = extractData(event, eventRead);
						break;
					case PUBDATE:
						pubdate = extractData(event, eventRead);
						break;
					case GUID:
						guid = extractData(event, eventRead);
						break;

					}
				}else if(event.isEndElement()){
					if(event.asEndElement().getName().getLocalPart().equalsIgnoreCase(ITEM)){
						Item item = new Item();
						item.setTitle(title);
						item.setLink(link);
						item.setDescription(description);
						item.setPubDate(pubdate);
						item.setGuid(guid);
						channel.getItems().add(item);
						event = eventRead.nextEvent();
						continue;
					}
					
				}
			}
		} catch (IOException ioe) {
			System.out.println(ioe.toString());
		} catch (XMLStreamException xmle) {
			System.out.println(xmle.toString());
		}
		
		return channel;
	}
	
	public String extractData(XMLEvent event, XMLEventReader eventReader) throws XMLStreamException{
		String data = "";
		event = eventReader.nextEvent();
		if(event instanceof Characters){
			data = event.asCharacters().getData();
			if(data.contains(this.excludeString)){
				data = data.replace(this.excludeString, "");
			}
		}
		return data;
		
	}
}
