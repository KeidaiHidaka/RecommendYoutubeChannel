package youtube;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GetYouTubeChannelId {
	public static String getChannelId (String channelUrl) {
		//String message = "うまくチャンネルIDを取得できませんでした";
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
			URL url = new URL(channelUrl);  // メープルシロップ

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


			while ((line = reader.readLine()) != null) {		// readLine() が null (=ページ終端) でない間
				int beginIndex = line.indexOf("channelId\" content=\"");
				if(beginIndex != -1) {
					beginIndex += "channelId\" content=\"".length();
					int endIndex = line.indexOf("\">", beginIndex);
					line = line.substring(beginIndex, endIndex);
					if(endIndex != -1) {
						break;
					}
				}

			}
			reader.close();
			//System.out.println("----------------------------------------------------------------");
			//System.out.println(message);///////////////////////////////////
			//System.out.println();//改行

		} catch (IOException e) {
			System.out.println(e);
		}
		return line;

	}

}
