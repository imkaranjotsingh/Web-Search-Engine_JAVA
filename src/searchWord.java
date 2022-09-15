import java.util.Scanner;

public class searchWord {
	public static String output_corrected = "";
	public static String Suggested_word = "";
	
	
	public static String search(String input1) {
		

		try {
			// Auto Correction
			Correction ac = new Correction();
			// Dictionary for auto correction
			ac.dictionaryOfWords("src/dictionary.txt");

			Scanner sc = new Scanner(System.in);

			String input = input1.toLowerCase();
			String correct[] = input.split(" ");
		    System.out.println("Input: " + input);
			String string = "";
		    output_corrected = "";
			
			// Auto Correction
			for (String word : correct) {
			//	System.out.println("before suggestwords");
				Suggested_word = ac.suggestWords(word);
					if (!(Suggested_word.equals(word))) {
						//System.out.println("Suggested word for "+correct[i]+" is "+n);
						string += Suggested_word + " ";
						output_corrected += word + " [You mean: " + Suggested_word + "]";
						System.out.println("output_corrected "+ output_corrected);
						
					}
				}
			
		}catch (Exception e) {
			System.out.println(e);
		}
		return Suggested_word;
	}
	
	
	
	
	
	
	/*
	public static void main(String[] args) {
		System.out.println("------------Welcome to our Search Engine------------");
		System.out.println("Press 1 To run Search query");
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		sc.nextLine();
		if (input == 1) {
			while (true) {
				System.out.println("Enter Search String or enter exit to stop: ");

				String s = sc.nextLine();

				if (s.equalsIgnoreCase("exit")) {

					break;
				}
				//System.out.println("Input in main  : " + s);
				String Suggested_word = search(s);
				System.out.println("Suggested word "+ Suggested_word);
			
			}
		} else {
			System.out.println("Enter valid input");
		}
	} 
     
	*/

}
