// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   OctIconView.java

package de.jensd.fx.glyphs.octicons;

import de.jensd.fx.glyphs.GlyphIcon;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.text.Font;

// Referenced classes of package de.jensd.fx.glyphs.octicons:
//            OctIcon

public class OctIconView extends GlyphIcon
{

            public OctIconView(OctIcon octicon)
            {
/*  43*/        super(de/jensd/fx/glyphs/octicons/OctIcon);
/*  44*/        setFont(new Font("Octicons", DEFAULT_ICON_SIZE.doubleValue()));
/*  45*/        setIcon(octicon);
            }

            public OctIconView()
            {
/*  49*/        this(OctIcon.MARK_GITHUB);
            }

            public OctIcon getDefaultGlyph()
            {
/*  54*/        return OctIcon.MARK_GITHUB;
            }

            public volatile Enum getDefaultGlyph()
            {
/*  30*/        return getDefaultGlyph();
            }

            public static final String TTF_PATH = "/de/jensd/fx/glyphs/octicons/octicons.ttf";

            static 
            {
/*  36*/        try
                {
/*  36*/            Font.loadFont(de/jensd/fx/glyphs/octicons/OctIconView.getResource("/de/jensd/fx/glyphs/octicons/octicons.ttf").openStream(), 10D);
                }
/*  37*/        catch(IOException ioexception)
                {
/*  38*/            Logger.getLogger(de/jensd/fx/glyphs/octicons/OctIconView.getName()).log(Level.SEVERE, null, ioexception);
                }
            }
}
