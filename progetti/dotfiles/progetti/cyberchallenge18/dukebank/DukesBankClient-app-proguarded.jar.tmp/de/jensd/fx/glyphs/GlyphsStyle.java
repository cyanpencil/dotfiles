// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GlyphsStyle.java

package de.jensd.fx.glyphs;


public final class GlyphsStyle extends Enum
{

            public static GlyphsStyle[] values()
            {
/*  19*/        return (GlyphsStyle[])$VALUES.clone();
            }

            public static GlyphsStyle valueOf(String s)
            {
/*  19*/        return (GlyphsStyle)Enum.valueOf(de/jensd/fx/glyphs/GlyphsStyle, s);
            }

            private GlyphsStyle(String s, int i, String s1)
            {
/*  29*/        super(s, i);
/*  30*/        stylePath = s1;
            }

            public final String getStylePath()
            {
/*  34*/        return stylePath;
            }

            public final String toString()
            {
/*  39*/        return stylePath;
            }

            public static final GlyphsStyle DEFAULT;
            public static final GlyphsStyle DARK;
            public static final GlyphsStyle LIGHT;
            public static final GlyphsStyle BLUE;
            public static final GlyphsStyle RED;
            private final String stylePath;
            private static final GlyphsStyle $VALUES[];

            static 
            {
/*  21*/        DEFAULT = new GlyphsStyle("DEFAULT", 0, "/styles/glyphs.css");
/*  22*/        DARK = new GlyphsStyle("DARK", 1, "/styles/glyphs_dark.css");
/*  23*/        LIGHT = new GlyphsStyle("LIGHT", 2, "/styles/glyphs_light.css");
/*  24*/        BLUE = new GlyphsStyle("BLUE", 3, "/styles/glyphs_blue.css");
/*  25*/        RED = new GlyphsStyle("RED", 4, "/styles/glyphs_red.css");
/*  19*/        $VALUES = (new GlyphsStyle[] {
/*  19*/            DEFAULT, DARK, LIGHT, BLUE, RED
                });
            }
}
