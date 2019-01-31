// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   WeatherIconView.java

package de.jensd.fx.glyphs.weathericons;

import de.jensd.fx.glyphs.GlyphIcon;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.text.Font;

// Referenced classes of package de.jensd.fx.glyphs.weathericons:
//            WeatherIcon

public class WeatherIconView extends GlyphIcon
{

            public WeatherIconView(WeatherIcon weathericon)
            {
/*  43*/        super(de/jensd/fx/glyphs/weathericons/WeatherIcon);
/*  44*/        setFont(new Font("weather icons", DEFAULT_ICON_SIZE.doubleValue()));
/*  45*/        setIcon(weathericon);
            }

            public WeatherIconView()
            {
/*  49*/        this(WeatherIcon.CLOUD);
            }

            public WeatherIcon getDefaultGlyph()
            {
/*  54*/        return WeatherIcon.CLOUD;
            }

            public volatile Enum getDefaultGlyph()
            {
/*  29*/        return getDefaultGlyph();
            }

            public static final String TTF_PATH = "/de/jensd/fx/glyphs/weathericons/weathericons-regular-webfont.ttf";

            static 
            {
/*  35*/        try
                {
/*  35*/            Font.loadFont(de/jensd/fx/glyphs/weathericons/WeatherIconView.getResource("/de/jensd/fx/glyphs/weathericons/weathericons-regular-webfont.ttf").openStream(), 10D);
                }
/*  36*/        catch(IOException ioexception)
                {
/*  37*/            Logger.getLogger(de/jensd/fx/glyphs/weathericons/WeatherIconView.getName()).log(Level.SEVERE, null, ioexception);
                }
            }
}
