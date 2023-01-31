package com.example.linebot.replier;

import com.example.linebot.replier.Replier;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

// 新機能「リスト」の概要
// 本来であれば、replier パッケージにあるクラス
public class ListSample implements Replier {

    @Override
    public Message reply() {
        return new TextMessage("""
                「リスト」では以下の機能を利用できます。
                「単語一覧」・・・登録している単語名の一覧を表示
                「単語名」・・・単語名の意味を表示""");
    }
}
