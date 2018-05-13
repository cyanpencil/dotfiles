// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   App.java

package ch.zhaw.dukesbank.client;

import a.b;
import a.d;
import ch.zhaw.dukesbank.client.presentation.license.LicenseView;
import com.airhacks.afterburner.injection.Injector;
import e.g;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application
{

            public App()
            {
            }

            public void start(Stage stage)
                throws Exception
            {
/*  17*/        g.a().a(stage);
/*  18*/        g.a().a(new LicenseView());
/*  19*/        stage.show();
            }

            public void stop()
                throws Exception
            {
/*  24*/        Injector.forgetAll();
/*  25*/        if(e.b.a().b() != null)
/*  26*/            b.a();
/*  28*/        if(e.d.a().b() != null)
/*  29*/            d.a();
            }

            public static void main(String args[])
            {
/*  34*/        launch(args);
            }
}
