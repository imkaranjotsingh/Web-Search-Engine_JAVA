import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

// Runnable interface used for thread execution.
public class crawler implements Runnable{
	// Setting Recursion Depth and content
	public static final int MAX_DEPTH = 1;
	public static final int MAX_CONTENT = 100;
	private Thread thread;
	private String first_link;
	
	private Map<String, String> visitedLinks = new HashMap<String, String>();
	private int ID;

	public crawler(String link, int num){
		// Web Crawler declaration
		first_link = link;
		ID = num;
		// creating thread
		thread = new Thread(this);
		thread.start();
	}
	
	// Overriding runnable interface method
	@Override
	public void run() {
		try {
			clear_file();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		crawl(1,first_link,0);
		
	}
	
	// Function to crawl 
	private void crawl(int level, String url, int total){
		if(level <= MAX_DEPTH){
			Document doc = request(url);
			
			if(doc!= null) {
				while(total<MAX_CONTENT) {
				for(Element link: doc.select("a[href]")) {
					String next_link = link.absUrl("href");
					if(visitedLinks.containsKey(next_link)== false) {
						// Recursion call crawl class again
						total += 1;
						crawl(level++, next_link, total);
					}
				}
			}}
		}
		else {
			writedata(visitedLinks);}
	}

	private Document request(String url){
		try{
			// Jsoup connection 
			Connection con = Jsoup.connect(url);
			Document doc = con.get();
			
			if(con.response().statusCode() == 200) {
				System.out.println("\n Site " + ID + " Received  webpage at " + url);
				String title = doc.title();
				System.out.println(title);
				// Storing Title and url
				visitedLinks.put(url, title);
				return doc;
			}
			return null;
		}
		catch(IOException e) {
			return null;
		}
	}
	
	public void writedata(Map<String, String> visitedLinks2) {
		File file = new File("/Users/manpreetsingh/Downloads/searchENGranking/Data/"+ID+".txt");
		  
        BufferedWriter bf = null;
  
        try {
  
        	FileWriter fstream = new FileWriter(file,true);
        	   
        	bf = new BufferedWriter(fstream);
            // iterate map entries
            for (Map.Entry<String, String> entry :
                 visitedLinks2.entrySet()) {
  
                // put key and value separated by a colon
                bf.write(entry.getKey() + ":"
                         + entry.getValue());
  
                // new line
                bf.newLine();
            }
  
            bf.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
  
            try {
  
                // always close the writer
                bf.close();
            }
            catch (Exception e) {
            }
        }
	}
	public void clear_file() throws FileNotFoundException
	{
		PrintWriter pw = new PrintWriter("/Users/manpreetsingh/Downloads/searchENGranking/Data/"+ID+".txt");
		pw.close();
	}
	
	public Thread getThread(){
		return thread;
	}
}
