import java.util.Comparator;
/*
 * Compares two WebPages objects by their URL.
 */
public class URLComparator implements Comparator{
	/*
	 * Compares two WebPages objects by their URL.
	 * 
	 * @returns 
	 * 		1 if o1 is bigger, -1 if it's smaller, 0 if it's the same
	 */
	public int compare(Object e1, Object e2) {
		WebPage o1 = (WebPage) e1;
		WebPage o2 = (WebPage) e2;
		return o1.getURL().compareTo(o2.getURL());
	}
}
