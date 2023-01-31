package com.example.linebot.replier;

import com.example.linebot.WS0401.FileManager;
import com.example.linebot.Word;
import com.example.linebot.replier.Replier;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordList implements Replier {

    private List<String> wordList = new ArrayList<>();  // txtファイル「wordbook」の記述を格納するためのリスト

    // コンストラクタ
    public WordList() {
        try {
            // ファイルの読み込み(FileManager の getAsListメソッド)
            FileManager fileManager = new FileManager("wordbook.txt");
            this.wordList = fileManager.getAsList();
            // コンストラクタは、ここだとうまくいかないかもしれない
            // リストを編集(「追加」、「削除」)した場合に、
            // もう一度Wordコンストラクタで読み込む必要があるから
        } catch (IllegalArgumentException e1) {
            System.out.println("ファイル名を正しくいれてください");
        } catch (IOException e2) {
            System.out.println("ファイルが読み込めません");
        }
        System.out.println("txtファイルの読み込み終了");
    }

    @Override
    public Message reply() {

        String message = "登録されている単語名一覧\n";
        for (String words : this.wordList) {
            Word word = new Word(words);
            message = message + "「" + word.getWord() + "」\n";
        }
        return new TextMessage(message);
    }

    public Message meanReply(String text) {

        for (String words : this.wordList) {
            Word word = new Word(words);
            if (word.getWord().equals(text)) {
                String message = "「" + word.getWord() + "」\n" + word.getMean();
                return new TextMessage(message);
            }
        }
        return new TextMessage("そのような単語は登録されていませんでした。");
    }
}
