package com.example.linebot;

import com.linecorp.bot.model.message.TextMessage;

// テキストファイルから、単語の名前と意味に切り分けるクラス
// また、それらを格納    List?   →   それはやめるかも
public class Word {
    private String word;
    private String mean;

    // コンストラクタ
    public Word(String arg) {
        if (arg == null) {
            throw new RuntimeException();
        }
        // splitメソッドを利用して、文字列を分離
        String[] splitted = arg.split(",");
        this.word = splitted[0];
        this.mean = splitted[1];
    }

    public String getWord() {
        return this.word;
    }

    // 単語名を表示するメソッド
    public void wordPrint() {
        String message = "「 " + this.word + " 」";   // 単語名であることを明記するため「」形式
        new TextMessage(message);
        return;
    }

    // 単語の意味(mean)を表示するメソッド
    public void meanPrint() {
        new TextMessage(this.mean);
        return;
    }
}
