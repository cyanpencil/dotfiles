// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GlyphsStack.java

package de.jensd.fx.glyphs;

import java.util.Collection;
import javafx.collections.ObservableList;
import javafx.scene.layout.StackPane;

// Referenced classes of package de.jensd.fx.glyphs:
//            GlyphIcon

public class GlyphsStack extends StackPane
{

            public GlyphsStack()
            {
            }

            public static GlyphsStack create()
            {
/*  27*/        return new GlyphsStack();
            }

            public GlyphsStack add(GlyphIcon glyphicon)
            {
/*  32*/        getChildren().add(glyphicon);
/*  33*/        return this;
            }

            public transient GlyphsStack addAll(GlyphIcon aglyphicon[])
            {
/*  43*/        if(aglyphicon != null && aglyphicon.length > 0)
/*  44*/            getChildren().addAll(aglyphicon);
/*  46*/        return this;
            }

            public GlyphsStack addAll(Collection collection)
            {
/*  56*/        if(collection != null && !collection.isEmpty())
/*  57*/            getChildren().addAll(collection);
/*  59*/        return this;
            }

            public GlyphsStack addAll(int i, Collection collection)
            {
/*  70*/        if(collection != null && !collection.isEmpty())
/*  71*/            getChildren().addAll(i, collection);
/*  73*/        return this;
            }

            public GlyphsStack remove(GlyphIcon glyphicon)
            {
/*  77*/        getChildren().remove(glyphicon);
/*  78*/        return this;
            }
}
