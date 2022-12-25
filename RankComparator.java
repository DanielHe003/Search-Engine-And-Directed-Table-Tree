import java.util.Comparator;

/*
 * Compares two WebPages objects by their Rank.
 */
public class RankComparator implements Comparator{
	
	/*
	 * Compares two WebPages objects by their Rank.
	 * 
	 * @returns 
	 * 		1 if o1 is smaller, -1 if it's bigger, 0 if it's the same
	 */
	public int compare(Object e1, Object e2) {
		WebPage o1 = (WebPage) e1;
		WebPage o2 = (WebPage) e2;
		if(o1.getRank() < o2.getRank())
			return 1;
		if(o1.getRank() > o2.getRank())
			return -1;
		return 0;
	}
}
