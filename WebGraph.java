/*
 * Name: Daniel He
 * Solar ID: 114457594
 * Homework #7
 * Email: daniel.he@stonybrook.edu
 * Course: CSE214
 * Recitation #: R01 TA: Ulfeen Ayevan & Wesley Mui  
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 *Initializes a WebGraph from the appropriate text files and allow the user to search for 
 *keywords in the graph. The class should provide functionality to add/remove pages to/from 
 *the graph, as well as alter the hyperlinks between pages in the graph.
 *On start-up, the SearchEngine should construct a new WebGraph using the files indicated 
 *by the static constant Strings PAGES_FILE and LINKS_FILE. The user should then be presented
 *with a menu allowing them to add a page, remove a page, add a link, remove a link, print 
 *the graph, search for a keyword, or quit the program. The appropriate operation should be 
 *handled depending on the selection.
 * 
 * @author Daniel He
 * email: daniel.he@stonybrook.edu
 * 114457594
 */
public class WebGraph {
	public static final int MAX_PAGES = 40;
	private ArrayList<WebPage> pages;
	//check if changing edges is allowed
	private int[][] edges = new int[MAX_PAGES][MAX_PAGES];

	/*
	 * Constructs a WebGraph object using the indicated files as the source for pages and edges.
	 * 
	 * @param pagesFile 
	 *		String of the relative path to the file containing the page information.
	 * @param linksFile
	 * 		String of the relative path to the file containing the link information.
	 * 
	 * <dt> Preconditions:
	 * 	<dd>Both parameters reference text files which exist.
	 * 	<dd>The files follow proper format as outlined in the "Reading Graph from File" section below.
	 * 
	 * <dt> Postconditions:
	 * 	<dd> A WebGraph has been constructed and initialized based on the text files.
	 * 
	 * @returns 
	 * 		The WebGraph constructed from the text files.
	 * 
	 * @throws IllegalArgumentException
	 *		Thrown if either of the files does not reference a valid text file, or if the files are not formatted correctly.
	 */
	public static WebGraph buildFromFiles(String pagesFile, String linksFile) throws IllegalArgumentException {
		WebGraph graph = new WebGraph();
		ArrayList<String> pagesAndKeywords = new ArrayList<String>();
		ArrayList<String> sourceDestination = new ArrayList<String>();
		String data = "";
		String data2 = "";

		//This reads the two files and adds them to the DataKeywords arraylist and SourceDestination Arraylist.
		pagesAndKeywords = readText(pagesFile);
		sourceDestination = readText(linksFile);

		//this part just adds all the possible links to the holder arraylist, initalizes keywords too
		ArrayList<WebPage> holder = new ArrayList<WebPage>();

		for(int i = 0; i<pagesAndKeywords.size(); i++) {
			String[] parts = pagesAndKeywords.get(i).split(" ");
			String name = parts[0];
			ArrayList<String> keywords = new ArrayList<String>();
			if(parts.length > 1) {
				for(int k = 1; k<parts.length; k++) {
					keywords.add(parts[k]);
				}
			}
			WebPage temp = new WebPage(name, keywords);
			temp.setIndex(i);
			holder.add(temp);
		}
		//this part actually puts in the rank of them
		for(int i = 0; i<sourceDestination.size(); i++) {
			String[] parts = sourceDestination.get(i).split(" ", 2);
			String dest = parts[1];
			for(int j = 0; j<holder.size(); j++)
				if(holder.get(j).getURL().trim().equals(dest.trim()))
					holder.get(j).setRank(holder.get(j).getRank()+1);
		}
		//sets the index
		for(int i = 0; i< holder.size(); i++)
			holder.get(i).setIndex(i);
		graph.pages = holder;

		//sets the table
		for(int x = 0; x<sourceDestination.size(); x++) {
			//finds the source and dest
			int sourceIndex = 0;
			int destIndex = 0;
			String[] parts = sourceDestination.get(x).split(" ");
			String source = parts[0];
			String dest = parts[1];

			//finds the index of the two
			for(int y = 0; y<holder.size(); y++) {
				if(holder.get(y).getURL().equals(source))
					sourceIndex = y;
				if(holder.get(y).getURL().equals(dest))
					destIndex = y;
			}
			if(destIndex != sourceIndex)
				graph.edges[sourceIndex][destIndex] = 1;
		}

		//sets the links
		for(int i = 0; i<graph.edges.length; i++) {
			for(int j = 0; j<graph.edges[i].length; j++) {
				if(graph.edges[i][j] != 0)
					graph.pages.get(i).addLinks(j);
			}
		}

		return graph;
	}

	/*
	 * Reads the text files and inputs the correct information.
	 * 
	 * @param name
	 * 		the text file's name that information is needed from.
	 * 
	 *<dt> Postconditions:
	 * 	<dd> WebGraph was initalized with the information provided in the text files.
	 */
	public static ArrayList<String> readText(String name) {
		ArrayList<String> temp = new ArrayList<String>();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(name);
		} catch (FileNotFoundException e) {
			System.out.println("File is not there.");
		}
		InputStreamReader inStream = new InputStreamReader(fis);
		BufferedReader reader = new BufferedReader(inStream);
		try {
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				temp.add(line.trim());
			}
		} catch (IOException e) {
			System.out.println("Data reader.readline has a error 1");
		}
		return temp;
	}

	/*
	 * Adds a page to the WebGraph.
	 * 
	 * @param url
	 * 		The URL of the webpage (must not already exist in the WebGraph).
	 * @param keywords
	 * 		The keywords associated with the WebPage.
	 * 
	 * <dt> Preconditions:
	 * 	<dd>url is unique and does not exist as the URL of a WebPage already in the graph.
	 * 	<dd>url and keywords are not null.
	 * 
	 * <dt> Postconditions:
	 * 	<dd> The page has been added to pages at index 'i' and links has been logically extended to 
	 * 		 include the new row and column indexed by 'i'.
	 * 
	 * @throws IllegalArgumentException
	 *		 If url is not unique and already exists in the graph, or if either argument is null.
	 */
	public void addPage(String url, ArrayList<String> keywords) throws IllegalArgumentException{
		try {
			if(url.equals(null) || keywords.equals(null)) {
				throw new IllegalArgumentException("URL is null or Keywords is null.");
			}
			for(int i = 0; i<pages.size(); i++) {
				if(url.equals(pages.get(i).getURL())) {
					throw new IllegalArgumentException("URL is already in the WebGraph.");
				}
			}
			WebPage page = new WebPage(url, keywords);
			page.setIndex(pages.get(pages.size()-1).getIndex());
			page.setRank(0);
			page.setIndex(pages.get(pages.size()-1).getIndex()+1);
			pages.add(page);

			System.out.println();
			System.out.println(url+" sucessfully added to the WebGraph!");
		} catch(IllegalArgumentException e) {
			System.out.println();
			System.out.println("Error: "+ url + " already exists in the WebGraph. Could not add new WepPage.");
		}
	}

	/*
	 * Adds a link from the WebPage with the URL indicated by source to the WebPage with the URL indicated by destination
	 * 
	 * @param source 
	 * 		the URL of the page which contains the hyperlink to destination.
	 * @param destination 
	 * 		the URL of the page which the hyperlink points to.
	 * 
	 * <dt> Preconditions:
	 * 	<dd> Both parameters reference WebPages which exist in the graph.
	 * 
	 * @throws IllegalArgumentException
	 *		 If either of the URLs are null or could not be found in pages.
	 */
	public void addLink(String source, String destination) throws IllegalArgumentException {
		int sourceInd = 0;
		int destInd = 0;

		int count = 0;
		int count2 = 0;
		for(int i = 0; i<pages.size(); i++) {
			if(source.equals(pages.get(i).getURL()))
				count = 1;
			if(source.equals(pages.get(i).getURL()))
				count2 = 1;
		}

		if(count != 0 && count2 != 0) {
			for(int i = 0; i<pages.size(); i++) {
				if(source.equals(pages.get(i).getURL())) {
					sourceInd = i;
				}
				if(destination.equals(pages.get(i).getURL())) {
					destInd = i;
				}
			}
			pages.get(destInd).setRank(pages.get(destInd).getRank()+1);
			edges[sourceInd][destInd] = 1;
			pages.get(sourceInd).addLinks(destInd);
			System.out.println();
			System.out.println("Link sucessfully added from "+source+" to "+destination+"!");
		} else {
			System.out.println();
			int p = 0;
			int j = 0;
			for(int i = 0; i<pages.size(); i++) {
				if(pages.get(i).getURL().equals(source))
					p++;
				if(pages.get(i).getURL().equals(destination))
					j++;
			}
			if(p == 0)
				System.out.println("Error: "+source+" could not be found in the WebGraph.");
			if(j == 0)
				System.out.println("Error: "+destination+" could not be found in the WebGraph.");
		}
	}

	/*
	 * Removes the WebPage from the graph with the given URL.
	 * 
	 * @param url 
	 * 		The URL of the page to remove from the graph.
	 * 
	 * <dt> Postconditions:
	 * 	<dd> The WebPage with the indicated URL has been removed from the graph, and it's corresponding row and 
	 * 			column has been removed from the adjacency matrix.
	 *	<dd> All pages that has an index greater than the index that was removed should decrease their index value by 1.
	 * 	<dd> If url is null or could not be found in pages, the method ignores the input and does nothing.
	 * 
	 * @note
	 * When the page is removed, it's corresponding row and column must be removed from the adjacency matrix. This can 
	 * be accomplished by copying links[k][j+1] to links[k][j] and links[j+1][k] to links[j][k] for 0 <= k < size(pages) 
	 * and i <= j < size(pages)-1, where i is the index of the WebPage being removed.
	 */
	public void removePage(String url) {
		if(url.equals(null))
			return;

		int destInd = 0;
		for(int i = 0; i<pages.size(); i++) {
			if(pages.get(i).getURL().equals(url)) {
				destInd = i;
			}
		}
		int p = -1;
		int in = 0;
		ArrayList<Integer> b = new ArrayList<Integer>();
		for(int i = 0; i<pages.size(); i++) {
			if(pages.get(i).getURL().equals(url)) {
				b = pages.get(i).getLinks();
				for(int j = 0; j<b.size(); j++) {
					pages.get(b.get(j)).setRank(pages.get(b.get(j)).getRank()-1);
				}
				p = 1;
				pages.remove(i);
				in = i;
			}
		}

		if(p != -1) {
			for(int k = 0; k<pages.size(); k++) {
				for(int j = p; j < pages.size()-1; j++) {
					edges[k][j] = edges[k][j+1];
					edges[k][j] = edges[j+1][k];
				}
			}
			System.out.println(url + "has been removed from the graph!");	
		}
		for(int i = 0; i < pages.size(); i++) {
			ArrayList<Integer> temp = pages.get(i).getLinks();
			ArrayList<Integer> a = new ArrayList<Integer>();
			for(int j = 0; j<temp.size(); j++) {
				if(temp.get(j) < destInd) {
					a.add(temp.get(j));
				} if(temp.get(j) > destInd) {
					a.add(temp.get(j)-1);
				}
				pages.get(i).setLinks(a);
			}
		}
		for(int i = 0; i<pages.size(); i++)
			if(pages.get(i).getIndex() > in) {
				pages.get(i).setIndex(pages.get(i).getIndex() - 1);
			}
	}

	/*
	 * Removes the link from WebPage with the URL indicated by source to the WebPage with the URL indicated by destination.
	 * 
	 * @param source 
	 * 		The URL of the WebPage to remove the link.
	 * @param destination 
	 * 		The URL of the link to be removed.
	 * 
	 * <dt> Postconditions:
	 * 	<dd> The entry in links for the specified hyperlink has been set to 0 (no link).
	 *	<dd> If either of the URLs could not be found, the input is ignored and the method does nothing.
	 * 
	 */
	public void removeLink(String source, String destination) {
		try {
			int sourceInd = 0;
			int destInd = 0;
			int a = 0;
			for(int i = 0; i<pages.size(); i++) {
				if(pages.get(i).getURL().equals(source)) {
					sourceInd = i;
					a++;
				}
				if(pages.get(i).getURL().equals(destination)) {
					destInd = i;
					a++;
				}
			}
			if(a != 0) {
				edges[sourceInd][destInd] = 0;
				System.out.println();
				System.out.println("Link removed from "+source+" to "+destination+"!");
				pages.get(destInd).setRank(pages.get(destInd).getRank()-1);

				pages.get(sourceInd).getLinks().remove(pages.get(sourceInd).getLinks().indexOf(destInd));
			}
			if(a == 0) {
				System.out.println("There is no link between these two.");
			}
		} catch (Exception e) {
			System.out.println("There is no link between those two URLs or input was wrong.");
		}
	}

	/*
	 * Calculates and assigns the PageRank for every page in the WebGraph (see the PageRank Algorithm section for further detail).
	 * This operation should be performed after ANY alteration of the graph structure (adding/removing a link, adding/removing a page).
	 * 
	 * <dt> Postconditions:
	 * 	<dd> All WebPages in the graph have been assigned their proper PageRank.
	 * 
	 */
	public void updatePageRanks() {

		for(int i = 0; i<edges.length; i++) {
			for(int j = 0; j<edges[i].length; j++)
				System.out.print(edges[i][j]);
			System.out.println();
		}

		int count = 0;
		for(int i = 0; i < edges.length; i++) {
			for(int j = 0; j < edges[i].length; j++) {
				if(edges[j][i] == 1)
					count++;
			}
			pages.get(i).setRank(count);
			count = 0;
		}
	}

	/*
	 * Searches and prints out the WebGraph for the String name if it is found.
	 * 
	 * @param s
	 * 		String name that the WebGraph needs to search for.
	 * 
	 * <dt> Preconditions:
	 * 	<dd>WebGraph is intialized.
	 * 
	 * <dt> Postconditions:
	 * 	<dd> Prints out the WebPage of the matching string name if it has one.
	 */
	public void search(String s) {
		try {
			ArrayList<WebPage> hold = new ArrayList<WebPage>();
			int p = 0;
			for(int i = 0; i<pages.size(); i++) {
				if(pages.get(i).getKeywords().contains(s)) {
					p++;
					hold.add(pages.get(i));
				}
			}
			if(p == 0) {
				System.out.println("No search results found for the keyword dogs.");
			}
			if(p != 0) {
				System.out.println("Rank   PageRank    URL\r\n"
						+ "---------------------------------------------");
				Collections.sort(hold, new Comparator<WebPage>() {
					public int compare(WebPage o1, WebPage o2) {
						if(o1.getRank() < o2.getRank())
							return 1;
						if(o1.getRank() > o2.getRank())
							return -1;
						return 0;
					}
				});
			}
			for(int i = 0; i<hold.size(); i++) {
				WebPage temp = hold.get(i);
				System.out.printf("%3d%3s%5d%6s %s\n",i+1, "|", temp.getRank(), "|", temp.getURL());
			}
		} catch (NullPointerException e){
			System.out.println("The graph was not initalized.");
		} catch (Exception e) {
			System.out.println("There was a error in searching for keywords.");
		}
	}

	/*
	 * Compares the rank of two WebPages.
	 * 
	 * @param o1
	 * 		WebPage 1 that needs to be compared
	 * @param o2
	 * 		WebPage 2 that needs to be compared
	 * 
	 * <dt> Preconditions:
	 * 	<dd> WebGraph is intialized and it has two not null webpages.
	 * 
	 *	@return
	 *		returns 0 if the ranks are equal, -1 if o1<o2 and 1 if vice versa.
	 */
	public int compare(WebPage o1, WebPage o2){
		if(o1.getRank() == o2.getRank())
			return 0;
		return o1.getRank() < o2.getRank() ? -1 : 1;
	};

	/*
	 * Prints the WebGraph in tabular form.
	 */
	public void printTable() {
		System.out.println("Index     URL               PageRank  Links               Keywords\r\n"
				+ "------------------------------------------------------------------------------------------------------");
		ArrayList<WebPage> temp = pages;
		Collections.sort(temp, new Comparator<WebPage>() {
			public int compare(WebPage o1, WebPage o2) {
				if(o1.getIndex() > o2.getIndex())
					return 1;
				if(o1.getIndex() < o2.getIndex())
					return -1;
				return 0;
			}
		});

		for(int i = 0; i<pages.size(); i++) {
			System.out.println(pages.get(i).toString());
		}
	}
	/*
	 * Prints the WebGraph in tabular form by Url.
	 */
	public void printURL() {
		System.out.println("Index     URL               PageRank  Links               Keywords\r\n"
				+ "------------------------------------------------------------------------------------------------------");
		ArrayList<WebPage> temp = pages;
		Collections.sort(temp, new Comparator<WebPage>() {
			public int compare(WebPage o1, WebPage o2) {
				return o1.getURL().compareTo(o2.getURL());
			}
		});
		for(int i = 0; i<temp.size(); i++) {
			System.out.println(temp.get(i).toString());
		}
	}

	/*
	 * Prints the WebGraph in tabular form by Rank.
	 */
	public void printRank() {
		System.out.println("Index     URL               PageRank  Links               Keywords\r\n"
				+ "------------------------------------------------------------------------------------------------------");
		ArrayList<WebPage> temp = pages;
		Collections.sort(temp, new Comparator<WebPage>() {
			public int compare(WebPage o1, WebPage o2) {
				if(o1.getRank() < o2.getRank())
					return 1;
				if(o1.getRank() > o2.getRank())
					return -1;
				return 0;
			}
		});
		for(int i = 0; i<temp.size(); i++) {
			System.out.println(temp.get(i).toString());
		}
	}
}