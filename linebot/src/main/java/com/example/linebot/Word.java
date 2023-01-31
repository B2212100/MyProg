package com.example.linebot;

import com.linecorp.bot.model.message.TextMessage;

// テキストファイルから、単語の名前と意味に切り分けるクラス
public class Word {
    private String word;
    private String mean;

    // コンストラクタ  テキストファイルの切り分け(単語の名前と意味)を行う
    public Word(String arg) {
        if (arg == null) {
            throw new RuntimeException();
        }
        // splitメソッドを利用して、文字列を分離
        String[] splitted = arg.split(",");
        this.word = splitted[0];
        this.mean = splitted[1];
    }

    // ゲッター     単語と意味を返す
    public String getWord() {
        return this.word;
    }

    public String getMean() {
        return this.mean;
    }

}
