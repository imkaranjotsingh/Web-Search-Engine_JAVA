
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/**
 * @author Team ACC (Karanjot Singh)
 * In this class we have used in-built sort function in collections class from java.util package. 
 * In built sort uses the dual-pivot sorting method.
 * For comparison, comparator class is used. 
 */
public class Sorting {

	// function to sort hashmap by values
	public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hmap) {
		// Create a list from elements of HashMap
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(hmap.entrySet());

		// Sort the list
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> obj1, Map.Entry<String, Integer> obj2) {
				return (obj2.getValue()).compareTo(obj1.getValue());
			}
		});
		
		// Sort the list
		//Collections.sort(list,Collections.reverseOrder());

		// put data from sorted list to hashmap
		HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> a1 : list) {
			temp.put(a1.getKey(), a1.getValue());
		}
		return temp;
	}
}
