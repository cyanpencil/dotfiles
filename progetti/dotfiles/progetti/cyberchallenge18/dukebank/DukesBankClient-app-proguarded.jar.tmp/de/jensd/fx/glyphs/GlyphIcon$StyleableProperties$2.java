// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GlyphIcon.java

package de.jensd.fx.glyphs;

import javafx.beans.property.ObjectProperty;
import javafx.css.*;

// Referenced classes of package de.jensd.fx.glyphs:
//            GlyphIcon

static class  extends CssMetaData
{

            public final boolean isSettable(GlyphIcon glyphicon)
            {
/* 195*/        return GlyphIcon.access$300(glyphicon) == null || !GlyphIcon.access$300(glyphicon).isBound();
            }

            public final StyleableProperty getStyleableProperty(GlyphIcon glyphicon)
            {
/* 200*/        return (StyleableProperty)glyphicon.glyphSizeProperty();
            }

            public final Number getInitialValue(GlyphIcon glyphicon)
            {
/* 205*/        return GlyphIcon.DEFAULT_ICON_SIZE;
            }

            public final volatile Object getInitialValue(Styleable styleable)
            {
/* 192*/        return getInitialValue((GlyphIcon)styleable);
            }

            public final volatile StyleableProperty getStyleableProperty(Styleable styleable)
            {
/* 192*/        return getStyleableProperty((GlyphIcon)styleable);
            }

            public final volatile boolean isSettable(Styleable styleable)
            {
/* 192*/        return isSettable((GlyphIcon)styleable);
            }

            (String s, StyleConverter styleconverter, Number number)
            {
/* 192*/        super(s, styleconverter, number);
            }
}
