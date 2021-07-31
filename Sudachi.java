package youtube;

import java.util.ArrayList;
import java.util.List;

import com.worksap.nlp.sudachi.Dictionary;
import com.worksap.nlp.sudachi.DictionaryFactory;
import com.worksap.nlp.sudachi.Morpheme;
import com.worksap.nlp.sudachi.Tokenizer;

public class Sudachi {
	public static ArrayList<String> getWordFreq (ArrayList<String> titleList,ArrayList<String> newsTitleList) {
		ArrayList<String> word = new ArrayList<String>();
		//String descriptionString = "ヒカキンは【ポケモン】が大好きだ";

		// 形態素解析器の用意
		Dictionary dictionary = null;
		try {
			// 設定ファイル sudachi.json を用意した場合にはそれを読み込む
			//dictionary = new DictionaryFactory().create(Files.readString(Paths.get("sudachi.json")));
			dictionary = new DictionaryFactory().create();
		}
		catch(Exception e) {
			System.err.println("辞書が読み込めません: " + e);
			System.exit(-1);
		}
		Tokenizer tokenizer = dictionary.create();


		// 形態素解析
//		System.out.println();
//		System.out.println("【出てきた名詞郡】");
		for(int i=0; i<titleList.size() ;i++){

			for(List<Morpheme> list: tokenizer.tokenizeSentences(Tokenizer.SplitMode.C, titleList.get(i) )) {
				for(Morpheme morpheme: list) {
					if(morpheme.partOfSpeech().contains("名詞")) {
						//System.out.print(morpheme.surface() +" , ");
						word.add(morpheme.surface());
					}
				}
//				System.out.println("EOS");	// 文の終端 (End of Sentence)
			}
			//System.out.println();
		}
		word.add("ここからニュースタイトル");
		for(int i=0; i<newsTitleList.size() ;i++){

			for(List<Morpheme> list: tokenizer.tokenizeSentences(Tokenizer.SplitMode.C, newsTitleList.get(i) )) {
				for(Morpheme morpheme: list) {
					if(morpheme.partOfSpeech().contains("名詞")) {
						//System.out.print(morpheme.surface() +" , ");
						word.add(morpheme.surface());
					}
				}
//				System.out.println("EOS");	// 文の終端 (End of Sentence)
			}
			//System.out.println();
		}


		System.out.println();
		return word;
	}
}
