import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class main implements Constants {

	public static void main(String []args) throws IOException {
		HashMap<String,String> links = new HashMap<String,String>();
		//System.out.println("Started converting html files to text");


		System.out.println("------------Welcome to our Search Engine------------");
		System.out.println("Press 1 To run Search query");
		Scanner sc = new Scanner(System.in);
		int input;
		do {
			while (!sc.hasNextInt()) {
				String val = sc.next();
				System.out.printf("\"%s\" is not a valid number.%n", val);
			}
			input = sc.nextInt();
			if(input!=1)
				System.out.printf("\"%s\" is not a valid input.%n", input);

		} while (input != 1);
		//input = sc.nextInt();
		//sc.nextLine();

		while (true) {
			
			String s =""; 
			sc.nextLine();
			System.out.println("Enter Search String or enter exit to stop: ");
			s = sc.nextLine();


			if(s.length()!=0)
			{
				String Suggested_word = searchWord.search(s);
				System.out.println("Auto Corrected Word"+ Suggested_word);
				runcrawler.main(args);
				links = SearchCrawlerData.SearchWord(Suggested_word);
				if(links.size()==0)
				{
					System.out.println("Data not present.");
					continue;
				}
				else 
				{
				Object[] key = links.keySet().toArray();
				String[] URL = Arrays.stream(key).toArray(String[]::new);

				Object[] fname = links.values().toArray();
				String[] fileName = Arrays.stream(fname).toArray(String[]::new);
				Linktotext.htmlToTextParser(links);
				HashMap<String,Integer> count = new HashMap<>();
				System.out.println("Debug"+ URL[0] +"***" + fileName[0] + "***"+Suggested_word);
				count = Ranking_final.Counting(URL, fileName, Suggested_word);
				Ranking_final.sortIndex(count);
				}
			}
			else if (s.equalsIgnoreCase("exit")) {

				break;
			}
			}


	} 
}
