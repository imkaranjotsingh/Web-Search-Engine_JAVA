import java.util.*;
/**
 * @author Team ACC (Karanjot Singh)
 * This class removes stopwords from text 
 *
 */
public class StopWords {

	Set<String> set;

	public StopWords() {
		String stopwords[] = { "i", "me", "my", "myself", "we", "our", "ours", "ourselves", "you", "your", "yours",
				"yourself", "yourselves", "he", "him", "his", "himself", "she", "her", "hers", "herself", "it", "its",
				"itself", "they", "them", "their", "theirs", "themselves", "what", "which", "who", "whom", "this",
				"that", "these", "those", "am", "is", "are", "was", "were", "be", "been", "being", "have", "has", "had",
				"having", "do", "does", "did", "doing", "a", "an", "the", "and", "but", "if", "or", "because", "as",
				"until", "while", "of", "at", "by", "for", "with", "about", "against", "between", "into", "through",
				"during", "before", "after", "above", "below", "to", "from", "up", "down", "in", "out", "on", "off",
				"over", "under", "again", "further", "then", "once", "here", "there", "when", "where", "why", "how",
				"all", "any", "both", "each", "few", "more", "most", "other", "some", "such", "no", "nor", "not",
				"only", "own", "same", "so", "than", "too", "very", "s", "t", "can", "will", "just", "don", "should",
				"now" };

		set = new HashSet<>(Arrays.asList(stopwords));
	}

	public boolean isStopWord(String word) {
		if (set.contains(word)) {
			return true;
		} else if (word.length() <= 1) {
			return true;
		}
		return false;
	}
     /**
      * 
      * @param text input text
      * @return string after removing stopwords
      */
	public String removeStopWords(String text) {
		// tokenizing the string
		String tokenzinedText = "";
		StringTokenizer stoken = new StringTokenizer(text, " -=,.#%&*<>\"\t\n\r\f,.:;?![](){}'\\/+-_?&");
		// for each token add it to
		while (stoken.hasMoreTokens()) {
			String word = stoken.nextToken().trim().toLowerCase();
			if (!isStopWord(word))
				tokenzinedText += " " + word;
		}
		return tokenzinedText;
	}
}
