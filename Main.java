package youtube;

import java.awt.Desktop;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;





public class Main {

	static ArrayList<String> channelUrlList = new ArrayList<String>();

	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println("あなたの好きなYouTubeチャンネルのURLを下に入力してください！(複数可)");
		System.out.println("入力を終えたら　【　Enter を２回　】押して、数秒お待ち下さい！！");
		System.out.println();
		System.out.println("入力例↓");
		System.out.println("https://www.youtube.com/user/HikakinTV");
		System.out.println("https://www.youtube.com/c/junchannel");
		System.out.println("https://www.youtube.com/c/koyakky-ch");
		System.out.println("https://www.youtube.com/channel/UCFOsYGDAw16cr57cCqdJdVQ");
		System.out.println("(Enter を２回　押す)");

		System.out.println();
		System.out.println();

		System.out.println("↓↓↓ここに入力！！↓↓↓");

		Scanner scan = new Scanner(System.in);

		while(scan.hasNextLine()) {
			String str = scan.nextLine();
			if(str.equals(""))          // 空行なら終了
				break;
			channelUrlList.add(str);
		}
		System.out.println("実行中");

		for(String channelUrl : channelUrlList) {
//			System.out.println();
//			System.out.println();//改行


			//コンソールで入力されたチャンネルURLからチャンネルIDを受け取る
			String channelId =GetYouTubeChannelId.getChannelId(channelUrl);





			String channelName = GetYouTubeChannelName.getChannelName(channelUrl)	;		//String twitterID = GetTwitterID.getTwitterLink(channelUrl);


			String encodedChannelName = URLEncoder.encode(channelName, "Shift_JIS");


			ArrayList<String> newsTitles = GetGoogleNewsTitles.getNewsTitles(encodedChannelName);





			//チャンネルIDを使ってfeedを読み込んで動画タイトル（title）を読み込む
			ArrayList<String> feedTitle = FeedYouTubeChannel.getTitle(channelId);

			//動画タイトルをsudachiで形態素解析

			ArrayList<String> words = Sudachi.getWordFreq(feedTitle,newsTitles);///////////////////

			//System.out.println(words);

			ArrayList<String> freqWordList = FreqWordsCount.counter(words);

			//解析した結果をyou tubeでチャンネル検索
			ArrayList<String> urls = SearchRecommendedYouTubeChannel.urls(freqWordList);

			//ブラウザ自動起動か、またHTML読み取って　txtファイルとかコンソールとかに表示
			Desktop desktop = Desktop.getDesktop();

			for(int i=0; i<urls.size() ;i++){

				try {
					URI uri = new URI(urls.get(i));
					desktop.browse(uri);
				} catch (URISyntaxException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}



		}
		scan.close();

		System.out.println("本プログラムは正常に起動しました");


	}
}
