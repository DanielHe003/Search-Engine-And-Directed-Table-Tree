import java.util.Comparator;

/*
 * Compares two WebPages objects by their Index.
 */
public class IndexComparator implements Comparator{
	
	/*
	 * Compares two WebPages objects by their Index.
	 * 
	 * @returns 
	 * 		1 if o1 is bigger, -1 if it's smaller, 0 if it's the same
	 */
	@Override
	public int compare(Object e1, Object e2) {
		WebPage o1 = (WebPage) e1;
		WebPage o2 = (WebPage) e2;
		if(o1.getIndex() > o2.getIndex())
			return 1;
		if(o1.getIndex() < o2.getIndex())
			return -1;
		return 0;
	}
	
}