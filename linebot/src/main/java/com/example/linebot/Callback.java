package com.example.linebot;

import com.example.linebot.replier.ListSample;
import com.example.linebot.replier.*;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 追加 (Parrotクラス作成)
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.MessageEvent;

// 追加 (Greetクラス作成)

// 追加 (Omikujiクラス作成)

// 追加 (画像のメッセージイベントに対応)
import com.linecorp.bot.client.LineBlobClient;
import com.linecorp.bot.client.MessageContentResponse;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

// ユーザの回答に対応
import com.linecorp.bot.model.event.PostbackEvent;

// BubbleSampleクラスを作成

// CarouselSampleクラスを作成


@LineMessageHandler
public class Callback {

    private static final Logger log = LoggerFactory.getLogger(Callback.class);

    // フォローイベントに対応する
    @EventMapping
    public Message handleFollow(FollowEvent event) {
        // 実際はこのタイミングでフォロワーのユーザIDをデータベースにに格納しておくなど
        Follow follow = new Follow(event);
        return follow.reply();
    }

    // 文章で話しかけられたとき（テキストメッセージのイベント）に対応する
    @EventMapping
    public Message handleMessage(MessageEvent<TextMessageContent> event) {
        TextMessageContent tmc = event.getMessage();
        String text = tmc.getText();
        switch (text) {
            case "やあ":
                Greet greet = new Greet();
                return greet.reply();
            case "おみくじ":
                Omikuji omikuji = new Omikuji();
                return omikuji.reply();
            case "バブル":
                BubbleSample bubbleSample = new BubbleSample();
                return bubbleSample.reply();
            case "カルーセル":
                CarouselSample carouselSample = new CarouselSample();
                return carouselSample.reply();

            // 以下が新機能
            case "リスト":
                ListSample listSample = new ListSample();
                return listSample.reply();

            case "単語一覧":
                WordList wordList = new WordList();
                return wordList.reply();

            default:
                WordList wordList1 = new WordList();
                return wordList1.meanReply(text);

                // オウム返しとの併用は難しい
//                Parrot parrot = new Parrot(event);
//                return parrot.reply();
        }
    }

    // 文字列、位置情報、スタンプなど、
    // バイナリデータが含まれないメッセージイベントと異なり、
    // バイナリデータを含むメッセージでは LineBlobClient のメソッドを利用する
    // 今回は画像
    private LineBlobClient client;

    @Autowired
    public Callback(LineBlobClient client) {
        this.client = client;
    }

    // 画像のメッセージイベントに対応する
    @EventMapping
    public Message handleImg(MessageEvent<ImageMessageContent> event) {
        // ①画像メッセージのidを取得する
        String msgId = event.getMessage().getId();
        Optional<String> opt = Optional.empty();
        try {
            // ②画像メッセージのidを使って MessageContentResponse を取得する
            MessageContentResponse resp = client.getMessageContent(msgId).get();
            log.info("get content{}:", resp);
            // ③ MessageContentResponse からファイルをローカルに保存する
            // ※LINEでは、どの解像度で写真を送っても、サーバ側でjpgファイルに変換される
            opt = makeTmpFile(resp, ".jpg");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        // ④ ファイルが保存できたことが確認できるように、ローカルのファイルパスをコールバックする
        // 運用ではファイルパスを表示することは避けましょう
        String path = opt.orElseGet(() -> "ファイル書き込みNG");
        return new TextMessage(path);
    }

    // MessageContentResponseの中のバイト入力ストリームを、拡張子を指定してファイルに書き込む。
    // また、保存先のファイルパスをOptional型で返す。
    private Optional<String> makeTmpFile(MessageContentResponse resp, String extension) {
        // tmpディレクトリに一時的に格納して、ファイルパスを返す
        try (InputStream is = resp.getStream()) {
            Path tmpFilePath = Files.createTempFile("linebot", extension);
            Files.copy(is, tmpFilePath, StandardCopyOption.REPLACE_EXISTING);
            return Optional.ofNullable(tmpFilePath.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    // PostBackEventに対応する
    @EventMapping
    public Message handlePostBack(PostbackEvent event) {
        DialogAnswer dialogAnswer = new DialogAnswer(event);
        return dialogAnswer.reply();
    }

}
