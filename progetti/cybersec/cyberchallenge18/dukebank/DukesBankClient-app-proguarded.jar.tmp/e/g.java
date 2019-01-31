// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StageStore.java

package e;

import com.airhacks.afterburner.views.FXMLView;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public final class g
{
    static class a
    {

                static g a()
                {
/*  27*/            return a;
                }

                private static final g a = new g((byte)0);

    }


            private g()
            {
            }

            public static g a()
            {
/*  24*/        return a.a();
            }

            public final void a(FXMLView fxmlview)
            {
/*  33*/        fxmlview = new Scene(fxmlview.getView());
/*  34*/        a.setTitle("The Duke's Next Generation Bank Application");
/*  35*/        a.getIcons().add(new Image("/icon.png"));
/*  36*/        a.setScene(fxmlview);
            }

            public final void a(Stage stage)
            {
/*  44*/        a = stage;
            }

            g(byte byte0)
            {
/*  16*/        this();
            }

            private Stage a;
}
