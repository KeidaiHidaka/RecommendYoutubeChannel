package youtube;

import java.util.ArrayList;

public class SearchRecommendedYouTubeChannel {
	public static ArrayList<String> urls (ArrayList<String> freqWordList) {

		String searchWord = "";
		for (String a : freqWordList) {
			searchWord += a;
			searchWord += "%E3%80%80";
		}
		ArrayList <String> urls = new ArrayList<String>();

		urls.add("https://www.youtube.com/results?search_query="+ searchWord +"&sp=EgIQAg%253D%253D");




		return urls;

	}
}
