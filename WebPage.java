/*
 * Name: Daniel He
 * Solar ID: 114457594
 * Homework #7
 * Email: daniel.he@stonybrook.edu
 * Course: CSE214
 * Recitation #: R01 TA: Ulfeen Ayevan & Wesley Mui  
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 *The WebPage class should contain a String member variable for its URL, an int to 
 *represent its position in the adjacency matrix, an int to represent the rank 
 *of this WebPage, as well as a Collection of Strings containing the keywords 
 *describing this this page.
 * 
 * @author Daniel He
 * email: daniel.he@stonybrook.edu
 * 114457594
 */
public class WebPage {
	private String url;
	private int index;
	private int rank;
	private ArrayList<Integer> links = new ArrayList<Integer>();
	private ArrayList<String> keywords;

	/*
	 * Constructs an WebPage with null or 0 fields.
	 *
	 * <dt> Postconditions:
	 * 	<dd> WebPage was constructed.
	 * 
	 */
	public WebPage() {
	}

	/*
	 * Constructs an WebPage objects with user inputted fields
	 * 
	 * @param url
	 * 		url of the WebPage.
	 *  
	 * <dt> Postconditions:
	 * 	<dd> WebPage was constructed.
	 * 
	 */
	public WebPage(String url) {
		this.url = url;
	}

	/*
	 * Constructs an WebPage objects with user inputted fields
	 * 
	 * @param url
	 * 		url of the WebPage.
	 *  
	 * <dt> Postconditions:
	 * 	<dd> WebPage was constructed.
	 * 
	 */
	public WebPage(String url, int index) {
		this.url = url;
		this.index = index;
	}

	/*
	 * Constructs an WebPage objects with user inputted fields
	 * 
	 * @param url
	 * 		url of the WebPage.
	 * @param keywords
	 * 		keywords of the WebPage.
	 *  
	 * <dt> Postconditions:
	 * 	<dd> WebPage was constructed.
	 * 
	 */
	public WebPage(String url, ArrayList<String> keywords) {
		this.url = url;
		this.keywords = keywords;
	}

	/*
	 * Constructs an WebPage objects with user inputted fields
	 * 
	 * @param url
	 * 		url of the WebPage.
	 * @param keywords
	 * 		keywords of the WebPage.
	 * @param index
	 * 		index of the WebPage.
	 * @param rank
	 * 		rank of the WebPage.
	 * @param keywords
	 * 		keywords of the WebPage.
	 *  
	 * <dt> Postconditions:
	 * 	<dd> WebPage was constructed.
	 * 
	 */
	public WebPage(String url, int index, int rank, ArrayList<String> keywords) {
		this.url = url;
		this.index = index;
		this.rank = rank;
		this.keywords = keywords;
	}
	/*
	 * returns the int rank
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This WebPage object has been instantiated.
	 * 
	 * @return
	 * 	The WebPage sellerName
	 * 
	 */
	public int getRank() {
		return rank;
	}

	/*
	 * returns the int index
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This WebPage object has been instantiated.
	 * 
	 * @return
	 * 	The WebPage index
	 * 
	 */
	public int getIndex() {
		return index;
	}

	/*
	 * returns the String url
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This WebPage object has been instantiated.
	 * 
	 * @return
	 * 	The WebPage url
	 * 
	 */
	public String getURL() {
		return url;
	}

	/*
	 * returns the ArrayList<Integer> links
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This WebPage object has been instantiated.
	 * 
	 * @return
	 * 	The WebPage links
	 * 
	 */
	public ArrayList<Integer> getLinks(){
		return links;
	}

	/*
	 * returns the ArrayList<Integer> keywords
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This WebPage object has been instantiated.
	 * 
	 * @return
	 * 	The WebPage keywords
	 * 
	 */
	public ArrayList<String> getKeywords(){
		return keywords;
	}

	/*
	 * Adds a int to a arraylist of ints
	 * 
	 * @param connection
	 * 		int to add to the arraylist.
	 * 
	 * <dt> Preconditions:
	 * 	<dd> The WebPage object was created.
	 * 
	 * <dt> Postconditions:
	 * 	<dd>The new WebPage links now has a new int field with the parameter connection.
	 * 
	 */
	public void addLinks(int connection) {
		links.add(connection);
	}

	/*
	 * Sets the ArrayList<String> keywords of the WebPage.
	 * 
	 * @param keywords
	 * 		New ArrayList<String> keywords for the WebPage
	 * 
	 * <dt> Preconditions:
	 * 	<dd> The WebPage object was created.
	 * 
	 * <dt> Postconditions:
	 * 	<dd>The new WebPage now has a new ArrayList<String> keywords.
	 * 
	 */
	public void setKeywords(ArrayList<String> keywords){
		this.keywords = keywords;
	}

	/*
	 * Sets the String url of the WebPage.
	 * 
	 * @param url
	 * 		New String url for the WebPage
	 * 
	 * <dt> Preconditions:
	 * 	<dd> The WebPage object was created.
	 * 
	 * <dt> Postconditions:
	 * 	<dd>The new WebPage now has a new String url.
	 * 
	 */
	public void setUrl(String url){
		this.url = url;
	}

	/*
	 * Sets the int rank of the WebPage.
	 * 
	 * @param url
	 * 		New int rank for the WebPage
	 * 
	 * <dt> Preconditions:
	 * 	<dd> The WebPage object was created.
	 * 
	 * <dt> Postconditions:
	 * 	<dd>The new WebPage now has a new int rank.
	 * 
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}

	/*
	 * Sets the int index of the WebPage.
	 * 
	 * @param url
	 * 		New int index for the WebPage
	 * 
	 * <dt> Preconditions:
	 * 	<dd> The WebPage object was created.
	 * 
	 * <dt> Postconditions:
	 * 	<dd>The new WebPage now has a new int index.
	 * 
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/*
	 * Sets the ArrayList<Integer> links of the WebPage.
	 * 
	 * @param keywords
	 * 		New ArrayList<Integer> links for the WebPage
	 * 
	 * <dt> Preconditions:
	 * 	<dd> The WebPage object was created.
	 * 
	 * <dt> Postconditions:
	 * 	<dd>The new WebPage now has a new ArrayList<Integer> links.
	 * 
	 */
	public void setLinks(ArrayList<Integer> links) {
		this.links = links;
	}

	/*
	 * returns string of data members in tabular form.
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This WebPage object has been instantiated.
	 * 
	 * @return
	 * 	The string of data members in tabular form.
	 */
	public String toString() {
		StringBuilder str = new StringBuilder("");
		StringBuilder temp = new StringBuilder("");
		for(int i = 0; i<keywords.size(); i++) {
			str.append(keywords.get(i));
			str.append(", ");
		}
		Collections.sort(links, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				if(o1 > o2)
					return 1;
				if(o2 < o1)
					return -1;
				return 0;
			}
		});
		Collections.sort(links);  
		for(int i = 0; i<links.size(); i++) {
			temp.append(links.get(i));
			temp.append(", ");
		}
		String a = "";
		if(temp != null && !(temp.length() < 2))
			a = temp.substring(0, temp.length()-2);
		String s = str.substring(0, str.length()-2);
		s = String.format("  %d   | %-19s|    %d    | %-18s| %s", index, url, rank, a, s);
		return s;
	}
}
