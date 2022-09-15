import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SearchCrawlerData {

	public static HashMap SearchWord(String Word) {

		HashMap<String,String> fileslist = new HashMap<String,String>();
		HashMap<String,String> returnlist = new HashMap<String,String>();
		ArrayList<String> filesnames = new ArrayList<String>();
		// Root folder location
		final File folder = new File("/Users/manpreetsingh/Downloads/searchENGranking/Data");
		filesnames = listFilesForFolder(folder);
		fileslist = (HashMap<String, String>) HashMapFromTextFile(filesnames);
		for (Map.Entry<String, String> entry : fileslist.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			if(value.toLowerCase().contains(Word.toLowerCase())) {
				System.out.println(key+" --- "+ value);
				returnlist.put(key, value);

			}

		}


		return returnlist;

	}

	public static ArrayList listFilesForFolder(final File folder) {
		ArrayList<String> files= new ArrayList<String>();
		// iterating inside folder
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				// Adding the Folder
				files.add("/Users/manpreetsingh/Downloads/searchENGranking/Data/"+fileEntry.getName());
			}

		}
		return files;
	}

	public static Map<String, String> HashMapFromTextFile(ArrayList<String> filesnames)
	{

		Map<String, String> map
		= new HashMap<String, String>();
		BufferedReader br = null;

		for(String filePath:filesnames)
		{

			try {

				// create file object
				File file = new File(filePath);

				// create BufferedReader object from the File
				br = new BufferedReader(new FileReader(file));

				String line = null;

				// read file line by line
				while ((line = br.readLine()) != null) {

					// split the line by :
					String[] parts = line.split(":");
					String name;
					String number;

					// first part is name, second is number
					if(parts.length >= 3) {
						name = parts[0].trim() + ":" + parts[1].trim();
						number = parts[2].trim();
					}
					else {
						name = parts[0].trim() + ":" + parts[1].trim();
						number = "";
					}

					// put name, number in HashMap if they are
					// not empty
					if (!name.equals("") && !number.equals(""))
						map.put(name, number);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {

				// Always close the BufferedReader
				if (br != null) {
					try {
						br.close();
					}
					catch (Exception e) {
					};
				}
			}
		}

		return map;
	}
}

