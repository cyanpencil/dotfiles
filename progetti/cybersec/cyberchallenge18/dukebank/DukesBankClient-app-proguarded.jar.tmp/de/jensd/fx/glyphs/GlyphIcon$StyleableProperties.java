// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GlyphIcon.java

package de.jensd.fx.glyphs;

import java.util.*;
import javafx.beans.property.ObjectProperty;
import javafx.css.*;
import javafx.scene.text.Text;

// Referenced classes of package de.jensd.fx.glyphs:
//            GlyphIcon

static class _cls1
{

            private static final CssMetaData GLYPH_NAME;
            private static final CssMetaData GLYPH_SIZE;
            private static final List STYLEABLES;

            static 
            {
/* 172*/        GLYPH_NAME = new CssMetaData("-glyph-name", StyleConverter.getStringConverter(), "BLANK") {

                    public final boolean isSettable(GlyphIcon glyphicon)
                    {
/* 177*/                return GlyphIcon.access$200(glyphicon) == null || !GlyphIcon.access$200(glyphicon).isBound();
                    }

                    public final StyleableProperty getStyleableProperty(GlyphIcon glyphicon)
                    {
/* 182*/                return (StyleableProperty)glyphicon.glyphNameProperty();
                    }

                    public final String getInitialValue(GlyphIcon glyphicon)
                    {
/* 187*/                return "BLANK";
                    }

                    public final volatile Object getInitialValue(Styleable styleable)
                    {
/* 173*/                return getInitialValue((GlyphIcon)styleable);
                    }

                    public final volatile StyleableProperty getStyleableProperty(Styleable styleable)
                    {
/* 173*/                return getStyleableProperty((GlyphIcon)styleable);
                    }

                    public final volatile boolean isSettable(Styleable styleable)
                    {
/* 173*/                return isSettable((GlyphIcon)styleable);
                    }

        };
/* 191*/        GLYPH_SIZE = new CssMetaData("-glyph-size", StyleConverter.getSizeConverter(), GlyphIcon.DEFAULT_ICON_SIZE) {

                    public final boolean isSettable(GlyphIcon glyphicon)
                    {
/* 195*/                return GlyphIcon.access$300(glyphicon) == null || !GlyphIcon.access$300(glyphicon).isBound();
                    }

                    public final StyleableProperty getStyleableProperty(GlyphIcon glyphicon)
                    {
/* 200*/                return (StyleableProperty)glyphicon.glyphSizeProperty();
                    }

                    public final Number getInitialValue(GlyphIcon glyphicon)
                    {
/* 205*/                return GlyphIcon.DEFAULT_ICON_SIZE;
                    }

                    public final volatile Object getInitialValue(Styleable styleable)
                    {
/* 192*/                return getInitialValue((GlyphIcon)styleable);
                    }

                    public final volatile StyleableProperty getStyleableProperty(Styleable styleable)
                    {
/* 192*/                return getStyleableProperty((GlyphIcon)styleable);
                    }

                    public final volatile boolean isSettable(Styleable styleable)
                    {
/* 192*/                return isSettable((GlyphIcon)styleable);
                    }

        };
                ArrayList arraylist;
/* 211*/        Collections.addAll(arraylist = new ArrayList(Text.getClassCssMetaData()), new CssMetaData[] {
/* 212*/            GLYPH_NAME, GLYPH_SIZE
                });
/* 213*/        STYLEABLES = Collections.unmodifiableList(arraylist);
            }




            private _cls1()
            {
            }
}
