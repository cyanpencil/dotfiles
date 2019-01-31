// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FontAwesomeIconView.java

package de.jensd.fx.glyphs.fontawesome;

import de.jensd.fx.glyphs.GlyphIcon;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.text.Font;

// Referenced classes of package de.jensd.fx.glyphs.fontawesome:
//            FontAwesomeIcon

public class FontAwesomeIconView extends GlyphIcon
{

            public FontAwesomeIconView(FontAwesomeIcon fontawesomeicon)
            {
/*  39*/        super(de/jensd/fx/glyphs/fontawesome/FontAwesomeIcon);
/*  40*/        setFont(new Font("FontAwesome", DEFAULT_ICON_SIZE.doubleValue()));
/*  41*/        setIcon(fontawesomeicon);
            }

            public FontAwesomeIconView()
            {
/*  45*/        this(FontAwesomeIcon.ANCHOR);
            }

            public FontAwesomeIcon getDefaultGlyph()
            {
/*  50*/        return FontAwesomeIcon.ANCHOR;
            }

            public volatile Enum getDefaultGlyph()
            {
/*  26*/        return getDefaultGlyph();
            }

            public static final String TTF_PATH = "/de/jensd/fx/glyphs/fontawesome/fontawesome-webfont.ttf";

            static 
            {
/*  32*/        try
                {
/*  32*/            Font.loadFont(de/jensd/fx/glyphs/fontawesome/FontAwesomeIconView.getResource("/de/jensd/fx/glyphs/fontawesome/fontawesome-webfont.ttf").openStream(), 10D);
                }
/*  33*/        catch(IOException ioexception)
                {
/*  34*/            Logger.getLogger(de/jensd/fx/glyphs/fontawesome/FontAwesomeIconView.getName()).log(Level.SEVERE, null, ioexception);
                }
            }
}
