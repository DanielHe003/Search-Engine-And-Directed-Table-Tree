/*
 * Name: Daniel He
 * Solar ID: 114457594
 * Homework #7
 * Email: daniel.he@stonybrook.edu
 * Course: CSE214
 * Recitation #: R01 TA: Ulfeen Ayevan & Wesley Mui  
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

/*
 *
 *  SearchEngine will initialize a WebGraph from the appropriate text files and allow the user to search for keywords in the graph. 
 *  To keep things interesting, the class should provide functionality to add/remove pages to/from the graph, as well as alter the 
 *  hyperlinks between pages in the graph.
 *  On start-up, the SearchEngine constructs a new WebGraph using the files indicated by the static constant Strings 
 *  PAGES_FILE and LINKS_FILE. The user should then be presented with a menu allowing them to add a page, remove a page, add a 
 *  link, remove a link, print the graph, search for a keyword, or quit the program. The appropriate operation should be handled 
 *  depending on the selection.
 *  
 * @author Daniel He
 * email: daniel.he@stonybrook.edu
 * 114457594
 */
public class SearchEngine {
	public static final String PAGES_FILE = "pages.txt";
	public static final String LINKS_FILE = "links.txt";
	private WebGraph web = new WebGraph();

	/*
	 *  The main menu will print out and allow the user to enter choices from the menu list. The main function will then call those
	 *  functions in the WebGraph and use that to implement those said functions. It will also read form two text files names pages.txt 
	 *  and links.txt to initalize the WebGraph.
	 */
	public static void main(String[] args) {
		WebGraph web = new WebGraph();
		Scanner scanner = new Scanner(System.in);
		String scan = "";

		web = WebGraph.buildFromFiles(PAGES_FILE, LINKS_FILE);

		while(scan != "Q") {
			print();
			System.out.println();
			System.out.print("Please select an option: ");
			scan = scanner.nextLine();

			if(scan.toUpperCase().equals("AP")) {
				System.out.print("Enter a URL: ");
				scan = scanner.nextLine();
				System.out.print("Enter keywords (space-separated): ");
				String key = scanner.nextLine();
				ArrayList<String> e = new ArrayList<String>();
				String[] temp = key.split(" ");
				for(int i = 0; i<temp.length; i++) {
					e.add(temp[i]);
				}
				web.addPage(scan, e);

			} else if(scan.toUpperCase().equals("RP")) {
				System.out.print("Enter a URL: ");
				scan = scanner.nextLine();	
				web.removePage(scan);

			} else if(scan.toUpperCase().equals("AL")) {
				System.out.print("Enter a source URL: ");
				scan = scanner.nextLine();
				System.out.print("Enter a destination URL: ");
				String dest = scanner.nextLine();
				web.addLink(scan, dest);

			} else if(scan.toUpperCase().equals("RL")) {
				System.out.print("Enter a source URL: ");
				scan = scanner.nextLine();
				System.out.print("Enter a destination URL: ");
				String dest = scanner.nextLine();
				web.removeLink(scan, dest);

			} else if(scan.toUpperCase().equals("P")) {
				System.out.printf("\r\n"
						+ "    (I) Sort based on index (ASC)\r\n"
						+ "    (U) Sort based on URL (ASC)\r\n"
						+ "    (R) Sort based on rank (DSC)\n\n"
						+ "Please select an option: ");
				scan = scanner.nextLine();
				if(scan.toUpperCase().equals("I")) {
					System.out.println();
					web.printTable();
				} else if(scan.toUpperCase().equals("U")) {
					System.out.println();
					web.printURL();
				} else if(scan.toUpperCase().equals("R")) {
					System.out.println();
					web.printRank();
				}

			} else if(scan.toUpperCase().equals("S")) {
				System.out.print("Search keyword: ");
				scan = scanner.nextLine();
				System.out.println();
				web.search(scan);

			} else if(scan.toUpperCase().equals("Q")) {
				System.out.println();
				System.out.println("Goodbye.");
				return;
			}
		}
	}

	/*
	 * Prints out the main menu with the listed options.
	 */
	public static void print() {
		System.out.println("\nMenu:\r\n"
				+ "    (AP) - Add a new page to the graph.\r\n"
				+ "    (RP) - Remove a page from the graph.\r\n"
				+ "    (AL) - Add a link between pages in the graph.\r\n"
				+ "    (RL) - Remove a link between pages in the graph.\r\n"
				+ "    (P)  - Print the graph.\r\n"
				+ "    (S)  - Search for pages with a keyword.\r\n"
				+ "    (Q)  - Quit.");
	}
}
