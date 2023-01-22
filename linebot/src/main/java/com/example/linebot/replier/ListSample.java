package com.example.linebot.replier;

import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

// 新機能「リスト」の
// 本来であれば、replier パッケージにあるクラス
public class ListSample implements Replier {

//    private List<String> wordList = new ArrayList<>();  // txtファイル「wordbook」の記述を格納するためのリスト

    // コンストラクタ
    public ListSample() {
        // テキストファイルをここで読み込む
//        try {
//            // ファイルの読み込み(FileManager の getAsListメソッド)
//            FileManager fileManager = new FileManager("wordbook.txt");
//            this.wordList = fileManager.getAsList();
//            // コンストラクタは、ここだとうまくいかないかもしれない
//            // リストを編集(「追加」、「削除」)した場合に、
//            // もう一度Wordコンストラクタで読み込む必要があるから
//
//        } catch (IllegalArgumentException e1) {
//            System.out.println("ファイル名を正しくいれてください");
//
//        } catch (IOException e2) {
//            System.out.println("ファイルが読み込めません");
//        }
//        System.out.println("終了");
    }

    // 「リスト」機能の主な流れはここから
//    @EventMapping
    @Override
    public Message reply() {
        return new TextMessage("""
                「リスト」では以下の機能を利用できます。
                「一覧」・・・登録している単語名の一覧を表示
                「単語名」・・・単語名の意味を表示
                「終了」・・・リスト機能の終了""");
//
////        int count = 0;
//        System.out.println("replyメソッド開始");
//        for (String words : this.wordList) {
//            // Wordクラスを利用
//            Word word = new Word(words);
//            word.wordPrint();
//            word.meanPrint();
//            System.out.println(words);
//        }
//
//        new TextMessage("""
//                「リスト」では以下の機能を利用できます。
//                「一覧」・・・登録している単語名の一覧を表示
//                「単語名」・・・単語名の意味を表示
//                「終了」・・・リスト機能の終了""");
//
////        while(count == 0) {
//
//        // 文章で話しかけられたとき（テキストメッセージのイベント）に対応する
//        // 以下のケース(テキスト)に対応できる
//        TextMessageContent tmc = this.event.getMessage();
//        String text = tmc.getText();
//        switch (text) {
//
//            case "一覧":
//                new TextMessage("これで「リスト」を終了します。");
//
//            case "終了":
//                new TextMessage("これで「リスト」を終了します。");
////                    count = 1;
//
//            default:
//                for (String words : this.wordList) {
//                    // Wordクラスを利用
//                    Word word = new Word(words);
//                    if (word.getWord().equals(text)) {
//                        word.wordPrint();
//                        word.meanPrint();
//                        break;
//                    }
//                }
//                new TextMessage("そのような機能はありません。");
//        }
////        }
//
//        return new TextMessage("これで「リスト」を終了します。");
    }
}
