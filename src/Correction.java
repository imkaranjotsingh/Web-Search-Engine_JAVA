
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;


public class Correction {
	EditDistance ed = new EditDistance();
	Map<String, Integer> dictionary = new HashMap<>();
	static List<String> list = Arrays.asList("abcdefghijklmnopqrstuvwxyz"); // to identity single letter word

	/*
	 * This methods reads all the words from the dictionary and adds them in Trie
	 * 
	 * @param dictionaryFileName -> The file path for dictionary file
	 */
	public void dictionaryOfWords(String dictionaryFileName) throws IOException {
		try {
			FileReader fileReader = new FileReader(dictionaryFileName);
			BufferedReader br = new BufferedReader(fileReader);
			String line = null;
			while ((line = br.readLine()) != null) {
				String word = line.toLowerCase();
				if (!line.contains(" ")) {
					dictionary.put(word, dictionary.getOrDefault(word, 0) + 1);
				} else {
					String[] strs = line.split("\\s");
					for (String str : strs) {
						dictionary.put(str, dictionary.getOrDefault(str, 0) + 1);
					}
				}
			}
			fileReader.close();
			br.close();
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	/*
	 * This method suggests word for the given input word
	 * 
	 * @param inputWord -> any user input
	 */
	public String suggestWords(String inputWord) {
		TreeMap<Integer, TreeMap<Integer, TreeSet<String>>> map = new TreeMap<>();
		String str = inputWord.toLowerCase();
	//	System.out.println("str"+str);
		String result = null;
        
	//	System.out.println("inside suggestwords");
		if (inputWord.length() == 0 || inputWord == null || list.contains(str)) {
			return result;
		}
		
		for (String wd : dictionary.keySet()) {
			 //   System.out.println("isnide for "+wd);
				int dist = ed.editDistance(wd, str);
				TreeMap<Integer, TreeSet<String>> similarWords = map.getOrDefault(dist, new TreeMap<>());
				int freq = dictionary.get(wd);
				TreeSet<String> set = similarWords.getOrDefault(freq, new TreeSet<>());
				set.add(wd);
				similarWords.put(freq, set);
				map.put(dist, similarWords);
			}
	    result = map.firstEntry().getValue().lastEntry().getValue().first();
	    System.out.println("results "+result);
        
		return result;
	}

}
