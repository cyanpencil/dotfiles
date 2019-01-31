// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MaterialIconView.java

package de.jensd.fx.glyphs.materialicons;

import de.jensd.fx.glyphs.GlyphIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.text.Font;

// Referenced classes of package de.jensd.fx.glyphs.materialicons:
//            MaterialIcon

public class MaterialIconView extends GlyphIcon
{

            public MaterialIconView(MaterialIcon materialicon)
            {
/*  45*/        super(de/jensd/fx/glyphs/materialicons/MaterialIcon);
/*  46*/        setFont(new Font("", DEFAULT_ICON_SIZE.doubleValue()));
/*  47*/        setIcon(materialicon);
            }

            public MaterialIconView()
            {
/*  51*/        this(MaterialIcon.ANDROID);
            }

            public MaterialIcon getDefaultGlyph()
            {
/*  56*/        return MaterialIcon.ANDROID;
            }

            public volatile Enum getDefaultGlyph()
            {
/*  31*/        return getDefaultGlyph();
            }

            public static final String TTF_PATH = "/de/jensd/fx/glyphs/materialicons/MaterialIcons-Regular.ttf";

            static 
            {
/*  37*/        try
                {
/*  37*/            Font.loadFont(de/jensd/fx/glyphs/materialdesignicons/MaterialDesignIconView.getResource("/de/jensd/fx/glyphs/materialicons/MaterialIcons-Regular.ttf").openStream(), 10D);
                }
/*  38*/        catch(IOException ioexception)
                {
/*  39*/            Logger.getLogger(de/jensd/fx/glyphs/materialdesignicons/MaterialDesignIconView.getName()).log(Level.SEVERE, null, ioexception);
                }
            }
}
