package com.menuSlack;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class restfully
 */
@WebServlet(description = "menuSlack", urlPatterns = { "/menutoday" })
// URL = menuSlack/menutoday?
public class MenuSlack extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger LOGGER = Logger.getLogger("InfoLogging");
	private final String USER_AGENT = "Mozilla/5.0";
	  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuSlack() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * SMS inbound from phone to get the menu for today
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] splitURL = null;
		Lunch menu = new Lunch(); 
		
		// TODO Auto-generated method stub
		LOGGER.info("Message recieved..");
		String URLparams = request.getQueryString();
		LOGGER.info("DoGET.." + URLparams);
		//PrintWriter out = response.getWriter();
		//out.println("HelloWorld");
		splitURL = URLparams.split("&");
		
		String[] toAddress = splitURL[0].split("=");
		String[] fromAddress = splitURL[1].split("=");
		//TODO proper answers
		
		int responseCode = 0;
		
		String menulist = menu.LunchRun();
		
		try{
			//Send MT response back to phone with MenuLists
			responseCode = sendGet(toAddress[1], fromAddress[1], menulist);
		}catch(Exception e){
			// do something
		}finally {
			LOGGER.info("MT back..response code: " + responseCode);
		}
	}
	
	/**
	 * @see HttpServlet#sendGet(String toAddress, String fromAddress, String msg)
	 * Send outbound SMS back to enduser phone with menus
	 */
	private int sendGet(String toAddress, String fromAddress, String msg) throws Exception {
		
		LOGGER.info("\nSending MSG: " + msg);
		String urlencMsg = null; 
		
		try {
			urlencMsg = URLEncoder.encode(msg, "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			//TODO
		}
		//send message to SMS gateway
		String url = "http://192.168.1.4:8080/send?from=" + fromAddress + "&to=" + toAddress + "&msg=" + urlencMsg + "&smart=0";
 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
 
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
 
		int responseCode = con.getResponseCode();
		LOGGER.info("\nSending 'GET' request to URL : " + url);
		LOGGER.info("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		//print result
		LOGGER.info(response.toString());
		
		return responseCode;
 
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * TODO: Get menu request from slack bot
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("DoPOST..end");
	}
	
	/**
	 * @see HttpServlet#sendPost()
	 * TODO: MenuList for slack bot
	 */
	private void sendPost() throws Exception {
 
		String url = "https://google.com";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
 
		String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
 
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		//print result
		System.out.println(response.toString());
 
	}
	
	/**
	 * TODO Parser for Slack bot inbound message
	 */
	public static String getPostData(HttpServletRequest req) {
	    
		System.out.println("getPostData..");
		StringBuilder sb = new StringBuilder();
	    
	    try {
	        BufferedReader reader = req.getReader();
	        reader.mark(10000);

	        String line;
	        do {
	            line = reader.readLine();
	            sb.append(line).append("\n");
	        } while (line != null);
	        reader.reset();
	        
	    } catch(IOException e) {
	        LOGGER.log(Level.SEVERE, e.toString(), e);    
	    }

	    return sb.toString();
	}
}

