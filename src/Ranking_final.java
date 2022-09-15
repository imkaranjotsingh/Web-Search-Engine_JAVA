import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ranking_final {
	
	// This function uses regex operation to count the accurrence of searched word in the provided files.
	public static HashMap<String,Integer> Counting(String[] Url,String[] fileName,String strToFind) throws IOException {
		HashMap<String,Integer> countMap = new HashMap<String,Integer>();
		int count = 0;
		for(int i = 0; i<fileName.length;i++) {
			BufferedReader reader = new BufferedReader(new FileReader("/Users/manpreetsingh/Downloads/searchENGranking/Files/Text/"+fileName[i]+".txt"));
	        String currentLine = reader.readLine();
	        reader.close();
	        
	        StopWords stopword = new StopWords();
		    String tokInput = stopword.removeStopWords(currentLine);
		    
		    //System.out.println(tokInput);
	        
	        Matcher matcher = Pattern.compile(strToFind.toLowerCase()).matcher(tokInput);
	       
	        while (matcher.find()) {
	            count++;
	        }
	        
	        String new_fileName = fileName[i]+".txt"+"URL>"+Url[i];
	        String new_filename = new_fileName.replaceAll("\\s",""); 

	        countMap.put(new_filename,count);
	        System.out.println(countMap.keySet());
		}
		return countMap;
	}
	
	public static void sortIndex(HashMap<String,Integer> CMap) {
		HashMap<String,Integer> sortedPages = new HashMap<String,Integer>();
		
		sortedPages = Sorting.sortByValue(CMap);
		
		sortedPages.entrySet().forEach( entry -> {
			String s ="";
			//System.out.println(new_fileName);
	    	for(int i=0; i < entry.getKey().length(); i++){
			    if(entry.getKey().charAt(i) == '>'){
			    	for(int j = i+1;j < entry.getKey().length();j++) {
			    		s = s + entry.getKey().charAt(j); 
			    	}	
			    }
	    	}
		    System.out.println( s  + " => " + entry.getValue() );
		});
		
	}
}
