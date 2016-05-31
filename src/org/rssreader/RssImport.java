package org.rssreader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class RssImport {

	public static void main(String[] args) {
		try{
			RssParser parser = new RssParser("http://tech.uzabase.com/rss", "NewsPicks");
			Channel channel = parser.readRSS();
			System.out.println(channel);
			File file = new File("rss.txt");
			if(!file.exists()){
				file.createNewFile();
			}
			FileWriter fileWrite = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bufferedWrite = new BufferedWriter(fileWrite);
			bufferedWrite.write(channel.toString());
			for(Item item: channel.getItems()){
				System.out.println(item);
				bufferedWrite.write(item.toString());
			}
			bufferedWrite.close();
		} catch (IOException e) {
			System.out.println(e);
		}

	}
}
