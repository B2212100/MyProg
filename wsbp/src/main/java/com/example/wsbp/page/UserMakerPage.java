package com.example.wsbp.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.wicketstuff.annotation.mount.MountPath;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;

import org.apache.wicket.model.Model;

@MountPath("UserMaker")
public class UserMakerPage extends WebPage {

    public UserMakerPage() {
        /**
         * 動作確認3. WebPage間の移動
         */
        var toHomeLink = new BookmarkablePageLink<>("toHome", HomePage.class);
        add(toHomeLink);

        /**
         * ユーザ追加フォームの作成
         */
        var userNameModel = Model.of("");
        var userPassModel = Model.of("");

        // 配置したFormコンポーネントを匿名クラス化して処理を上書きする
        var userInfoForm = new Form<>("userInfo") {
            @Override
            protected void onSubmit() {
                var userName = userNameModel.getObject();
                var userPass = userPassModel.getObject();
                var msg = "送信データ："
                        + userName
                        + ","
                        + userPass;
                System.out.println(msg);
                // この1行を追加
                setResponsePage(new UserMakerCompPage(userNameModel));
            }
        };
        add(userInfoForm);

        var userNameField = new TextField<>("userName", userNameModel);
        userInfoForm.add(userNameField);

        var userPassField = new PasswordTextField("userPass", userPassModel);
        userInfoForm.add(userPassField);
    }

}
