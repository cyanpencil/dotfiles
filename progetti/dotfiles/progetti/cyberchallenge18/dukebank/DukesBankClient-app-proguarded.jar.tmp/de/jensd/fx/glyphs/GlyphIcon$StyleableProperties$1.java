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
/* 177*/        return GlyphIcon.access$200(glyphicon) == null || !GlyphIcon.access$200(glyphicon).isBound();
            }

            public final StyleableProperty getStyleableProperty(GlyphIcon glyphicon)
            {
/* 182*/        return (StyleableProperty)glyphicon.glyphNameProperty();
            }

            public final String getInitialValue(GlyphIcon glyphicon)
            {
/* 187*/        return "BLANK";
            }

            public final volatile Object getInitialValue(Styleable styleable)
            {
/* 173*/        return getInitialValue((GlyphIcon)styleable);
            }

            public final volatile StyleableProperty getStyleableProperty(Styleable styleable)
            {
/* 173*/        return getStyleableProperty((GlyphIcon)styleable);
            }

            public final volatile boolean isSettable(Styleable styleable)
            {
/* 173*/        return isSettable((GlyphIcon)styleable);
            }

            (String s, StyleConverter styleconverter, String s1)
            {
/* 173*/        super(s, styleconverter, s1);
            }
}
