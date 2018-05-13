// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GlyphsDude.java

package de.jensd.fx.glyphs;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

// Referenced classes of package de.jensd.fx.glyphs:
//            GlyphIcons

public class GlyphsDude
{

            public GlyphsDude()
            {
            }

            public static Text createIcon(GlyphIcons glyphicons)
            {
/*  54*/        return createIcon(glyphicons, "1em");
            }

            public static Text createIcon(GlyphIcons glyphicons, String s)
            {
                Text text;
/*  58*/        (text = new Text(glyphicons.characterToString())).getStyleClass().add("glyph-icon");
/*  60*/        text.setStyle(String.format("-fx-font-family: %s; -fx-font-size: %s;", new Object[] {
/*  60*/            glyphicons.getFontFamily(), s
                }));
/*  61*/        return text;
            }

            public static Label createIconLabel(GlyphIcons glyphicons, String s, String s1, String s2, ContentDisplay contentdisplay)
            {
/*  65*/        glyphicons = createIcon(glyphicons, s1);
/*  66*/        (s = new Label(s)).setStyle((new StringBuilder("-fx-font-size: ")).append(s2).toString());
/*  68*/        s.setGraphic(glyphicons);
/*  69*/        s.setContentDisplay(contentdisplay);
/*  70*/        return s;
            }

            public static Button createIconButton(GlyphIcons glyphicons)
            {
/*  74*/        return createIconButton(glyphicons, "");
            }

            public static Button createIconButton(GlyphIcons glyphicons, String s)
            {
/*  78*/        glyphicons = createIcon(glyphicons, "1em");
/*  79*/        (s = new Button(s)).setGraphic(glyphicons);
/*  81*/        return s;
            }

            public static Button createIconButton(GlyphIcons glyphicons, String s, String s1, String s2, ContentDisplay contentdisplay)
            {
/*  85*/        glyphicons = createIcon(glyphicons, s1);
/*  86*/        (s = new Button(s)).setStyle((new StringBuilder("-fx-font-size: ")).append(s2).toString());
/*  88*/        s.setGraphic(glyphicons);
/*  89*/        s.setContentDisplay(contentdisplay);
/*  90*/        return s;
            }

            public static ToggleButton createIconToggleButton(GlyphIcons glyphicons, String s, String s1, ContentDisplay contentdisplay)
            {
/*  94*/        return createIconToggleButton(glyphicons, s, s1, "1em", contentdisplay);
            }

            public static ToggleButton createIconToggleButton(GlyphIcons glyphicons, String s, String s1, String s2, ContentDisplay contentdisplay)
            {
/*  98*/        glyphicons = createIcon(glyphicons, s1);
/*  99*/        (s = new ToggleButton(s)).setStyle((new StringBuilder("-fx-font-size: ")).append(s2).toString());
/* 101*/        s.setGraphic(glyphicons);
/* 102*/        s.setContentDisplay(contentdisplay);
/* 103*/        return s;
            }

            public static void setIcon(Tab tab, GlyphIcons glyphicons)
            {
/* 107*/        setIcon(tab, glyphicons, "1em");
            }

            public static void setIcon(Tab tab, GlyphIcons glyphicons, String s)
            {
/* 111*/        tab.setGraphic(createIcon(glyphicons, s));
            }

            public static void setIcon(Labeled labeled, GlyphIcons glyphicons)
            {
/* 115*/        setIcon(labeled, glyphicons, "1em");
            }

            public static void setIcon(Labeled labeled, GlyphIcons glyphicons, ContentDisplay contentdisplay)
            {
/* 119*/        setIcon(labeled, glyphicons, "1em", contentdisplay);
            }

            public static void setIcon(Labeled labeled, GlyphIcons glyphicons, String s)
            {
/* 123*/        setIcon(labeled, glyphicons, s, ContentDisplay.LEFT);
            }

            public static void setIcon(Labeled labeled, GlyphIcons glyphicons, String s, ContentDisplay contentdisplay)
            {
/* 127*/        if(labeled == null)
                {
/* 128*/            throw new IllegalArgumentException("The component must not be 'null'!");
                } else
                {
/* 130*/            labeled.setGraphic(createIcon(glyphicons, s));
/* 131*/            labeled.setContentDisplay(contentdisplay);
/* 132*/            return;
                }
            }

            public static void setIcon(MenuItem menuitem, GlyphIcons glyphicons)
            {
/* 135*/        setIcon(menuitem, glyphicons, "1em", "1em");
            }

            public static void setIcon(MenuItem menuitem, GlyphIcons glyphicons, String s)
            {
/* 139*/        setIcon(menuitem, glyphicons, "1em", s);
            }

            public static void setIcon(MenuItem menuitem, GlyphIcons glyphicons, String s, String s1)
            {
/* 143*/        if(menuitem == null)
                {
/* 144*/            throw new IllegalArgumentException("The menu item must not be 'null'!");
                } else
                {
/* 146*/            glyphicons = createIcon(glyphicons, s1);
/* 147*/            menuitem.setStyle((new StringBuilder("-fx-font-size: ")).append(s).toString());
/* 148*/            menuitem.setGraphic(glyphicons);
/* 149*/            return;
                }
            }

            public static void setIcon(TreeItem treeitem, GlyphIcons glyphicons)
            {
/* 152*/        setIcon(treeitem, glyphicons, "1em");
            }

            public static void setIcon(TreeItem treeitem, GlyphIcons glyphicons, String s)
            {
/* 156*/        if(treeitem == null)
                {
/* 157*/            throw new IllegalArgumentException("The tree item must not be 'null'!");
                } else
                {
/* 159*/            glyphicons = createIcon(glyphicons, s);
/* 160*/            treeitem.setGraphic(glyphicons);
/* 161*/            return;
                }
            }

            static 
            {
/*  43*/        try
                {
/*  43*/            Font.loadFont(de/jensd/fx/glyphs/GlyphsDude.getResource("/de/jensd/fx/glyphs/fontawesome/fontawesome-webfont.ttf").openStream(), 10D);
/*  44*/            Font.loadFont(de/jensd/fx/glyphs/GlyphsDude.getResource("/de/jensd/fx/glyphs/weathericons/weathericons-regular-webfont.ttf").openStream(), 10D);
/*  45*/            Font.loadFont(de/jensd/fx/glyphs/GlyphsDude.getResource("/de/jensd/fx/glyphs/materialdesignicons/materialdesignicons-webfont.ttf").openStream(), 10D);
/*  46*/            Font.loadFont(de/jensd/fx/glyphs/GlyphsDude.getResource("/de/jensd/fx/glyphs/materialicons/MaterialIcons-Regular.ttf").openStream(), 10D);
/*  47*/            Font.loadFont(de/jensd/fx/glyphs/GlyphsDude.getResource("/de/jensd/fx/glyphs/octicons/octicons.ttf").openStream(), 10D);
                }
/*  48*/        catch(IOException ioexception)
                {
/*  49*/            Logger.getLogger(de/jensd/fx/glyphs/materialdesignicons/MaterialDesignIconView.getName()).log(Level.SEVERE, null, ioexception);
                }
            }
}
