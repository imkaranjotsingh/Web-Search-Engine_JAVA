import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ranking_final {
	static HashMap<String,Integer> countMap = new HashMap<String,Integer>();
	static HashMap<String,Integer> sortedPages = new HashMap<String,Integer>();
	
	
	// This function uses regex operation to count the accurrence of searched word in the provided files.
	public static void Counting(String fileName,String strToFind,String Url) throws IOException {
		int count = 0;
		File in = new File("W3C Web Pages/Text/" + fileName);
    	BufferedReader reader = new BufferedReader(new FileReader(in));
        String currentLine = reader.readLine();
        reader.close();
        
        StopWords stopword = new StopWords();
	    String tokInput = stopword.removeStopWords(currentLine);
        
        Matcher matcher = Pattern.compile(strToFind).matcher(tokInput);
       
        while (matcher.find()) {
            count++;
        }
        
        String new_fileName = fileName+"URL>"+Url;
        String new_filename = new_fileName.replaceAll("\\s",""); 

        countMap.put(new_filename,count);

        
	}
	
	public static void sortIndex() {
		
		sortedPages = Sorting.sortByValue(countMap);
		
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
	
    public static void main(String[] args) {
    	
		File[] filesList = new File("W3C Web Pages/Text").listFiles();             //Getting the name of all text files
		String strToFind = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the string you want to Find:");
		strToFind = sc.nextLine(); 
		//System.out.println("Started converting html files to text");
		
		// Testing By creating Fake Links
		String[] strings = { "i", "me", "my", "myself", "we", "our", "ours", "ourselves", "you", "your", "yours",
				"yourself", "yourselves", "he", "him", "his", "himself", "she", "her", "hers", "herself", "it", "its",
				"itself", "they", "them", "their", "theirs", "themselves", "what", "which", "who", "whom", "this",
				"that", "these", "those", "am", "is", "are", "was", "were", "be", "been", "being", "have", "has", "had",
				"having", "do", "does", "did", "doing", "a", "an", "the", "and", "but", "if", "or", "because", "as",
				"until", "while", "of", "at", "by", "for", "with", "about", "against", "between", "into", "through",
				"during", "before", "after", "above", "below", "to", "from", "up", "down", "in", "out", "on", "off",
				"over", "under", "again", "further", "then", "once", "here", "there", "when", "where", "why", "how",
				"all", "any"}; 
		
		int j = 0;
		try {
			for (File file : filesList) {
			    if (file.isFile()) {
			    	String fileName = file.getName();
			    	Counting(fileName,strToFind,strings[j]);
			    	j++;
			    }
			}
			sortIndex();
			System.out.println("All Files READ!!!");
		} catch(Exception e) {
			System.out.println(e);
		}
	}

}
