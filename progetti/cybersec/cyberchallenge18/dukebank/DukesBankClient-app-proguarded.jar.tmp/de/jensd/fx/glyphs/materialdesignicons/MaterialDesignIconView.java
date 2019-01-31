// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MaterialDesignIconView.java

package de.jensd.fx.glyphs.materialdesignicons;

import de.jensd.fx.glyphs.GlyphIcon;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.text.Font;

// Referenced classes of package de.jensd.fx.glyphs.materialdesignicons:
//            MaterialDesignIcon

public class MaterialDesignIconView extends GlyphIcon
{

            public MaterialDesignIconView(MaterialDesignIcon materialdesignicon)
            {
/*  41*/        super(de/jensd/fx/glyphs/materialdesignicons/MaterialDesignIcon);
/*  42*/        setFont(new Font("Material Design Icons", DEFAULT_ICON_SIZE.doubleValue()));
/*  43*/        setIcon(materialdesignicon);
            }

            public MaterialDesignIconView()
            {
/*  47*/        this(MaterialDesignIcon.ANDROID);
            }

            public MaterialDesignIcon getDefaultGlyph()
            {
/*  52*/        return MaterialDesignIcon.ANDROID;
            }

            public volatile Enum getDefaultGlyph()
            {
/*  27*/        return getDefaultGlyph();
            }

            public static final String TTF_PATH = "/de/jensd/fx/glyphs/materialdesignicons/materialdesignicons-webfont.ttf";

            static 
            {
/*  33*/        try
                {
/*  33*/            Font.loadFont(de/jensd/fx/glyphs/materialdesignicons/MaterialDesignIconView.getResource("/de/jensd/fx/glyphs/materialdesignicons/materialdesignicons-webfont.ttf").openStream(), 10D);
                }
/*  34*/        catch(IOException ioexception)
                {
/*  35*/            Logger.getLogger(de/jensd/fx/glyphs/materialdesignicons/MaterialDesignIconView.getName()).log(Level.SEVERE, null, ioexception);
                }
            }
}
