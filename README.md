# RecommendYoutubehannel





<h2>タイトル</h2>

<p>おすすめYouTubeチャンネル　紹介君</p>

<h2>19FI095　日髙継大</h2>


<h2>1. はじめに</h2>

<p>
この授業で扱った情報アクセスと知的処理に関する手法を活用したマッシュアップを考えたところ、
授業で利用したsudachiの形態素解析を使ってなにかを作りたいと思った。<br><br>

私にとって役立つプログラムを作りたかったため、私がよく利用する、YouTubeと形態素解析を関連付けた。<br><br>


プログラムの大まかな流れとしては、コンソールにYouTubeチャンネルのURLを入力(複数可)し、Enterを押すと、デフォルトのブラウザが開き、おすすめのYouTubeチャンネルが表示されるといったものだ。<br><br>






</p>

<h2>2. 情報アクセス技術/知的処理技術の動向</h2>

<p>
今回作成したプログラムに関連する技術の動向について述べる。

今回、sudachiを利用した形態素解析を行った。Sudachiの近年の動向としては、<br>
2020年10月9日より、<strong>国内最大規模の日本語言語処理資源「SudachiDict」および「chiVe」をOpen Data on AWSで公開開始</strong>
されたそうだ。

</p>

<h2>3. システムの設計と実装</h2>

<h3>3.1 実現した機能</h3>

<p>


コンソールに入力した任意のYouTubeチャンネルのURLから、おすすめYouTubeチャンネルが表示されるシステム<br><br>

任意のYouTubeチャンネルのURLをコンソールに入力後、Enterを２回押すと起動する。数秒後に各デバイスのデフォルトのブラウザでおすすめチャンネルが表示される。<br><br>


YouTubeチャンネルのURL以外をコンソールに入力すると、エラーを吐く

</p>


<h3>3.2 システムが利用するリソース</h3>

<dl>
<dt>名称:YouTube</dt>
<dt>概要:YouTubeチャンネル</dt>
<dt>利用目的:htmlからYouTubeチャンネルのチャンネルIDとチャンネル名を取得</dt>
<dt>URL例:https://www.youtube.com/channel/UCKnPsmCuIyXioKofQa217JQ</dt>
<dt>形式:HTML</dt>
</dl>
<br>
<dl>
<dt>名称:YouTubeチャンネルの最新動画情報</dt>
<dt>概要:YouTubeチャンネルの最新の動画１５本の情報</dt>
<dt>利用目的:YouTubeチャンネルの最新動画のタイトルを取得</dt>
<dt>URL例:https://www.youtube.com/feeds/videos.xml?channel_id=UCKnPsmCuIyXioKofQa217JQ</dt>
<dt>形式:Atom</dt>
</dl>
<br>

<dl>
<dt>名称:GoogleNews</dt>
<dt>概要:世の中のニュース</dt>
<dt>利用目的:該当YouTubeチャンネルのGoogleNewsを取得</dt>
<dt>URL:https://news.google.com/topstories?hl=ja&gl=JP&ceid=JP:ja</dt>
<dt>形式:HTML</dt>

</dl>

<br>
<h3>3.3 プログラムの構成</h3>



<p>
まずこのプログラムは	</p>

<ul>
  <li>Main.java</li>
  <li>GetYouTubeChannelId.java</li>
  <li>FeedYouTubeChannel.java</li>
  <li>GetYouTubeChannelName.java</li>
  <li>GetGoogleNewsTitle.java</li>
  <li>Sudachi.java</li>
  <li>FreqWordsCount.java</li>
  <li>SearchRecommendedYouTubeChannel.java</li>
</ul>

<p>の８つのプログラムからなる。<br><br>

YouTubeチャンネルリンク＞チャンネルID & チャンネル名＞Feedから最新動画15件のタイトル & GoogelNewsででてきたNewsタイトル ＞
形態素解析で登場頻度の高い名詞TOP3を選択＞名詞からYouTubeチャンネルをブラウザ起動して検索<br><br>

というのが大まかな流れだ。<br><br>

<li>Main.javaを実行して、コンソールに任意のYouTubeチャンネルのURL(複数可)を入力し実行。文字列配列にURLが入る</li><br>
<li>GetYouTubeChannelId.javaにURLの配列を渡す。各URLにアクセスし、HTMLからチャンネルIDを取得する<br><br>

<li>同様にGetYouTubeChannelName.javaにURLの配列を渡す。各URLにアクセスし、HTMLからチャンネル名を取得する<br><br>
<li>GetGoogleNewsTitle.javaにチャンネル名を渡し、出てきたニュースのタイトルを取得する</li><br><br>


<li>FeedYouTubeChannel.javaにチャンネルIDの配列を渡す。各Feedにアクセスし、最新動画１５件のタイトルを取得する<br><br>
<li>Sudachi.javaに動画タイトルとニュースタイトルの配列を渡す。形態素解析をし、名詞のみ取得<br><br>
<li>FreqWordsCount.javaに名詞の配列を渡す。頻度の高い単語TOP3を取得<br><br>
<li>SearchRecommendedYouTubeChannel.javaに頻度の高い単語TOP3の配列を渡す。デバイスのデフォルトのブラウザを起動。頻度の高い単語を検索欄に入れた状態でYouTubeチャンネル検索をブラウザで開く



</p>


<h3>3.4 データ構造とアルゴリズム</h3>

<p>
FreqWordsCount.javaにて動画タイトルに使われたすべての名詞が格納された文字配列を、HashMapを利用して、頻度の高い順に並び替え、上位３名詞を配列に格納した。
</p>


<h3>3.5 その他の工夫点</h3>

<p>
	YouTubeチャンネルのURLは主に、<br>
https://www.youtube.com/channel/チャンネルID<br>
https://www.youtube.com/user/ユーザー名<br>
https://www.youtube.com/c/チャンネル名？<br>
<br>
の３種類からなっている。<br>
１種類目だけであればチャンネルIDは一目瞭然なのだが、残りの二種類はチャンネルIDがわからない。<br>
調べてもチャンネルIDを取得できるツールがなかったため、HTMLから自力で探した。<br>
(苦労した)<br>
</p>

<h2>4. 実験</h2>


<h3>4.1 実験条件</h3>

<p>プログラムの入力について説明すること。
   データを入力とする場合には、データの件数、データの時間範囲など、
   データの持つ性質に即した説明をすること。
   ライブラリや外部ツールの version についても記載すること。</p>
<p>
	Main.javaを実行後、コンソールの指示通りに、任意のYouTubeチャンネルのURLを入力しEnterを２回押す。<br>
	URLは基本的に何行でも入力可能。<br><br>

	sudachiのバージョンは0.5.2

</p>
<h3>4.2 実験結果</h3>


<p>
	入力URL:	<br>
	https://www.youtube.com/user/HikakinTV<br>
https://www.youtube.com/c/junchannel<br>
https://www.youtube.com/c/koyakky-ch<br><br>

結果画像：準備中<br>
<img src="image/p1.png" width="900" height="600">
<img src="image/p2.png" width="900" height="600">
<img src="image/p3.png" width="900" height="600">

</p>

<h2>5. 考察</h2>



<h3>5.1 機能</h3>

<p>改良点としては　動画タイトルの頻出名詞、GoogleNewsタイトル以外からも、おすすめYouTubeチャンネルを検索する要素を取り出すべきだった</p>

<h3>5.2 実現方法</h3>

<p>大量の名詞が格納された配列から、頻度の高い順に並べるアルゴリズムは
ハッシュマップを利用して効率よくできた</p>

<p>リソースは最大限有効に活用できた。
</p>

<h3>5.3 その他</h3>

<p><strong>今回YouTube(最新動画タイトル)とGoogleNewsのマッシュアップをしたわけだが、もっと効率的にチャンネルの特徴を表す要素を引き出す方法を考えたかった</strong></p>


<h2>6. おわりに</h2>

<p>今後の展開として、YouTube以外にもTwitterなどのSNSからおすすめYouTubeチャンネルを探す機能などもつけてみたい</p>

<h2>感想</h2>

<p>そこそこ実用的なプログラムが書けたのが嬉しい。<br>
3.5 その他の工夫点で記述したが、
YouTubeチャンネルのURLが３種類（もっとあるかも）あるせいで、
チャンネルIDをHTMLから自力で探すはめになった<br>

もとから１種類であってくれ、、、、、(AtomのためにチャンネルIDが必要だった上、チャンネルIDの取得が意外と大変だった)
</p>

<h2>参考文献</h2>

<p>

<br>
国内最大規模の日本語言語処理資源「SudachiDict」および「chiVe」をOpen Data on AWSで公開開始
ー自然言語処理技術で日本語の曖昧さを吸収し、さらに便利でオープンなシステムへー
株式会社ワークスアプリケーションズ2020年10月9日 13時20分<br>
https://prtimes.jp/main/html/rd/p/000000178.000011485.html<br>

<br>
情報アクセスと知的処理ー形態素解析ーSudachi<br>
https://www.mlab.im.dendai.ac.jp/~yamada/ir/MorphologicalAnalyzer/Sudachi.html<br>



</p>

</div>


</body>
</html>
