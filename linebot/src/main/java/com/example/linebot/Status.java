package com.example.linebot;

// ステータスを保存するクラス(会話の段階を踏むことができる)
public class Status {

    private String context = "0";   //ステータス(文脈の段階)

    public String getContext() {
        return context;
    }
    public void setContext(String context) {
        this.context = context;
    }


    // 一時、記述場所
    //        Status status = new Status();


    // 単語の追加、削除以外は文脈を分ける必要はないんじゃないか?

    // この下に続けてCallbackクラスの受け答え(switch)形式で
    // 記述すれば段階を踏めるのか？

    // ステータスごとにわける文脈を考える↓

    // status = 0; 単語の「一覧」表示    いわゆる初期状態
    // 現在ある単語名の一覧を出力(拡張for文)
//            for (String words : this.wordList) {
//        // Wordクラスを利用
//        Word word = new Word(words);
//        word.wordPrint();
//    }

    // status = 1; 単語の「名前」で意味表示, 可能なら「追加」と「削除」
    // Word クラスのword(単語名)と一致した場合にその(mean)意味を表示

    // 拡張for文で回し、if文で単語名が一致したら、word.meanPrintメソッドを実行


    // status = 2; 「単語を覚えましたか?」と、削除するかどうかを問いかけるのもいい, 可能なら「追加」と「削除」
    // status = 2 を終えたら1に戻る? だったら2は要らないか


}
