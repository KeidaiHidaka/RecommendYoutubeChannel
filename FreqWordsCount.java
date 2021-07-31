package youtube;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;



public class FreqWordsCount {
	public static ArrayList<String> counter (ArrayList<String> wordList){
		ArrayList<String> freqWordList = new ArrayList<String>();
		Map<String, Integer> map = new HashMap<>();
            for (String word : wordList) {
            	if (!word.isEmpty()) {
            		if (map.containsKey(word)) {
            			int count = map.get(word) + 1;
                         map.put(word, count);
                    } else {
                    	map.put(word, 1);
                    }
                }
            }



        ArrayList<String> list = new ArrayList<>();
        int maxLengthOfSpelling = 0;
        for (String key : map.keySet()) {
            list.add(key);

            if (maxLengthOfSpelling < key.length()) {
                maxLengthOfSpelling = key.length();
            }
        }
        Collections.sort(list, (o1, o2) -> {
            return - map.get(o1) + map.get(o2);
        });

        // 3回以上出た名詞　上位から
//        System.out.println("２回以上出た名詞"
//        		+ "");
//        String format = "%-" + maxLengthOfSpelling + "s: %3d";
        for (String word : list) {
            int count = map.get(word);
            if (3 <= count && freqWordList.size()<3) {
                //System.out.printf(format, word, count);
                //System.out.println();
            	
            	freqWordList.add(word);
            	
            }
        }


		return  freqWordList;

	}
}
