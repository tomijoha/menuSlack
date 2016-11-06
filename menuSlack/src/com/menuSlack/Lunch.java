package com.menuSlack;

import java.util.*;

public class Lunch {

	public String LunchRun() {
		// TODO Auto-generated method stub
		LunchCollector getLunch = new LunchCollector();
		String SMSlunch = null;
		
		LinkedList<String> lunchSavor = new LinkedList<String>();
		LinkedList<String> lunchAroma = new LinkedList<String>();
		LinkedList<String> lunchElm = new LinkedList<String>();
		LinkedList<String> lunchBistro = new LinkedList<String>();
		
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK);
		
		//Test
		//day_of_week = 5;
		
		if (day_of_week == 7) {
			SMSlunch = "Daiju on lauantai! Koita maanantaina uudestaa.";
		}
		else if (day_of_week == 1) {
			SMSlunch = "Daiju on sunnuntai! Koita huomenna uudestaan.";
		}
		else {
			lunchSavor = getLunch.LunchParserSavor("http://www.savor.fi/lounaslistat/vallila/", day_of_week);
			lunchAroma = getLunch.LunchParserAroma("http://www.compass-group.fi/ravintolat/Helsinki/Ravintola-Aroma/lounaslista/", day_of_week);
			lunchElm = getLunch.LunchParserElm("http://www.palmia.fi/fi/ravintolat/elmstreet/lounas/", day_of_week);
			lunchBistro = getLunch.LunchParserBistro("http://www.ravintolafactory.com/lounasravintolat/ravintolat/bistro/", day_of_week);
			
			SMSlunch = "Onhan sinulla jo mobiilivarmenne käytössä? sonera.fi/mobiilivarmenne \n\n " 
					+ "SAVOR: \n " + lunchSavor.toString() 
					+ "\n\n AROMA: \n " + lunchAroma.toString() 
					+ "\n\n ELMSTREET: \n " + lunchElm.toString() 
					+ "\n\n BISTROFACTORY: \n " + lunchBistro.toString();
		}
		
		return SMSlunch;
		
	}

}
