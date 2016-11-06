package com.menuSlack;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LunchCollector {
	
	public LinkedList<String> LunchParserSavor(String url, int dayNumber) {
		 
		  LinkedList<String> lunchSavor = new LinkedList<String>();
		  String lunch = null;
		  String day = null;
		  
		  //Sync and map the day number to Day on webpage
		  switch (dayNumber) {
		  
		  case 2 :
			  day = "Maanantai";
			  break;
		  case 3 :
			  day = "Tiistai";
			  break;
		  case 4 :
			  day = "Keskiviikko";
			  break;
		  case 5 :
			  day = "Torstai";
			  break;
		  case 6 :
			  day = "Perjantai";
			  break;
		  
		  }
		  
		try {
	 
			// need http protocol
			Document doc = Jsoup.connect(url).get();
			
			//replacing for tags KKK instead <br>
			String temp = doc.html().replace("<br />", "KKK"); 
			doc = Jsoup.parse(temp); 
			
			String title = doc.title();
			//System.out.println("title : " + title);
			//System.out.println("day : " + day);
			
			Elements contents = doc.select("div.4u");
			for (Element content : contents) {
				
				String value = content.text();
				
				//Take the menu for today
				if (value.contains(day)) {
					lunch = content.text();
					String[] split = lunch.split("KKK");
					
					for (int i = 0; i < split.length; i++){
						lunchSavor.add("\n" + split[i].trim() );
					}
				}	
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lunchSavor;
		
	  }//pageParser
	
	public LinkedList<String> LunchParserAroma(String url, int dayNumber) {
		
		  LinkedList<String> lunchAroma = new LinkedList<String>();
		  String lunch = null;
		  String day = null;
		  
		  switch (dayNumber) {
		  
		  case 2 :
			  day = "Maanantai";
			  break;
		  case 3 :
			  day = "Tiistai";
			  break;
		  case 4 :
			  day = "Keskiviikko";
			  break;
		  case 5 :
			  day = "Torstai";
			  break;
		  case 6 :
			  day = "Perjantai";
			  break;
		  }
		  
		try {
	 
			// need http protocol
			Document doc = Jsoup.connect(url).get();
			
			String title = doc.title();
			//System.out.println("title : " + title);
			
			Elements contents = doc.select("tr");
			
			for (Element content : contents) {
				
				String value = content.text();
				
				//Take the manu from the webpage for today, nasty webpage
				if (value.contains(day)) {
					lunch = content.text();
					Element sibling2 = content.nextElementSibling();
					Element sibling3 = sibling2.nextElementSibling();
					Element sibling4 = sibling3.nextElementSibling();
					Element sibling5 = sibling4.nextElementSibling();
					Element sibling6 = sibling5.nextElementSibling();
					Element sibling7 = sibling6.nextElementSibling();
					Element sibling8 = sibling7.nextElementSibling();
					
					//Formulate the foods properly
					lunchAroma.add("\n" + sibling2.text().trim());
					lunchAroma.add("\n" + sibling3.text().trim());
					lunchAroma.add("\n" + sibling4.text().trim());
					lunchAroma.add("\n" + sibling5.text().trim());
					lunchAroma.add("\n" + sibling6.text().trim());
					lunchAroma.add("\n" + sibling7.text().trim());
				
				}	
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lunchAroma;
		
	  }//pageParser

	public LinkedList<String> LunchParserElm(String url, int dayNumber) {
		
		  LinkedList<String> lunchElm = new LinkedList<String>();
		  String lunch = null;
		  String day = null;
		  
		  //Sync and map the day number to Day on webpage
		  switch (dayNumber) {
		  
		  case 2 :
			  day = " mån";
			  break;
		  case 3 :
			  day = " tis";
			  break;
		  case 4 :
			  day = " ons";
			  break;
		  case 5 :
			  day = " tors";
			  break;
		  case 6 :
			  day = " fre";
			  break;
		  }
		  
		try {
	 
			// need http protocol
			Document doc = Jsoup.connect(url).get();
			//replace tags for KKK instead of </span>
			String temp = doc.html().replace("</span>", "KKK"); 
			doc = Jsoup.parse(temp); 
			
			// get page title
			String title = doc.title();
			//System.out.println("title elm: " + title);
			
			Elements contents = doc.select("div.menu-list-day");
			
			boolean thisok = false;
			
			//Take the manu from the webpage
			for (Element content : contents) {
				
				String value = content.text();
				
				if (value.contains(day) && thisok == false) {
					lunch = content.text();
					String[] split = lunch.split("KKK");
					
					for (int i = 3; i < split.length; i++){
						lunchElm.add("\n" + split[i].trim());
						thisok = true;
					}
				}	
			}	
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lunchElm;
		
	  }//pageParser
	
	public LinkedList<String> LunchParserBistro(String url, int dayNumber) {
		
		  LinkedList<String> lunchBistro = new LinkedList<String>();
		  String lunch = null;
		  String day = null;
		  
		  switch (dayNumber) {
		  
		  case 2 :
			  day = "Maanantai";
			  break;
		  case 3 :
			  day = "Tiistai";
			  break;
		  case 4 :
			  day = "Keskiviikko";
			  break;
		  case 5 :
			  day = "Torstai";
			  break;
		  case 6 :
			  day = "Perjantai";
			  break;
		  }
		  
		try {
	 
			// need http protocol
			Document doc = Jsoup.connect(url).get();
			//replace tags with KKK instead <br>
			String temp = doc.html().replace("<br />", "KKK"); 
			doc = Jsoup.parse(temp); 
			
			// get page title
			String title = doc.title();
			//System.out.println("title : " + title);
			//System.out.println("day : " + day);
			
			Elements contents = doc.select("h2 ~ *");
			
			//Take the menu from the webpage
			for (Element content : contents) {
				
				String value = content.text();
				
				if (value.contains(day)) {
					//lunch = content.text();

					Element sibling2 = content.nextElementSibling();
					String[] split = sibling2.text().split("KKK");
					
					for (int i = 0; i < split.length; i++){
						lunchBistro.add("\n" + split[i].trim() );
					}
				}		
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lunchBistro;
		
	  }//pageParser
}
