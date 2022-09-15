
import java.util.ArrayList;

public class runcrawler {

	public static void main(String[] args) {
		// create thread list
		ArrayList<crawler> bots = new ArrayList<>();
		bots.add(new crawler("https://www.javatpoint.com/", 1));
		bots.add(new crawler("https://www.bbc.com/", 2));
		bots.add(new crawler("https://www.cnn.com/", 3));
		bots.add(new crawler("https://en.wikipedia.org/wiki/Main_Page/", 4));
		
		for( crawler cw: bots) {
			try {
				cw.getThread().join();
				
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		

	}

}
