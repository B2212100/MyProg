package com.example.wsbp.page;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.annotation.mount.MountPath;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

@WicketHomePage
@MountPath("Home")
public class HomePage extends WebPage {

    public HomePage() {
        var youModel = Model.of("Wicket-Spring-Boot");
        var youLabel = new Label("you", youModel);
        add(youLabel);

        /* 動作確認1. WebPageの表示 */
        var gakusekiModel = Model.of("B2212100");
        var gakusekiLabel = new Label("gakuseki", gakusekiModel);
        add(gakusekiLabel);

        var nameModel = Model.of("細越 拓");
        var nameLabel = new Label("name", nameModel);
        add(nameLabel);
    }

}