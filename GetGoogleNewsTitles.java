package youtube;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetGoogleNewsTitles {
	public static ArrayList<String> getNewsTitles (String channelName)  {
		ArrayList<String> newsTitles = new ArrayList<>();

		/*
		if(shortChannelName.length() > 8) {
			shortChannelName = shortChannelName.substring(0, 8);
		}
		*/
		String line="";
		// TLS v1.2 の有効化 (Java 8 以降では指定不要)
		System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
		//if (args.length != 1) {
		//System.out.println("使用法：java ShowFile1 ファイル");
		//System.out.println("例：java ShowFile1 ShowFile1.java");
		//System.exit(0);
		//}
		//String filename = args[0];
		try {
			// URLオブジェクトを生成
			String newsUrl = "https://news.google.com/search?q="+channelName+"%20youtube&hl=ja&gl=JP&ceid=JP%3Aja";
			//System.out.println("参考にしたgoogleNewsのURL："+newsUrl);
			URL url = new URL(newsUrl);

			// URLオブジェクトから、接続にいくURLConnectionオブジェクトを取得
			URLConnection connection = url.openConnection();
			// 接続
			connection.connect();
			// サーバからやってくるデータをInputStreamとして取得
			InputStream inputStream = connection.getInputStream();
			// 次に inputStream を読み込む InputStreamReader のインスタンス inputStreamReader を生成
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			// さらに inputStreamReader をラップする BufferedReader のインスタンス reader を生成
			BufferedReader reader = new BufferedReader(inputStreamReader);


			//


			Pattern pattern = Pattern.compile("class=\"DY5T1d RZIKme\">");  // グループの指定が1つ(1番目)




			//String endPTag;

			while ((line = reader.readLine()) != null) {		// readLine() が null (=ページ終端) でない間

		        Matcher matcher = pattern.matcher(line);
		        if(matcher.find()) {

		        	int beginIndex = line.indexOf("class=\"DY5T1d RZIKme\">");
					if(beginIndex != -1) {
						beginIndex += "class=\"DY5T1d RZIKme\">".length();
						int endIndex = line.indexOf("</a>", beginIndex);
						line = line.substring(beginIndex, endIndex);
						newsTitles.add(line);
					}
		        }



			}

			reader.close();

		} catch (IOException e) {
				e.printStackTrace();
		}
		return newsTitles;

	}

}


