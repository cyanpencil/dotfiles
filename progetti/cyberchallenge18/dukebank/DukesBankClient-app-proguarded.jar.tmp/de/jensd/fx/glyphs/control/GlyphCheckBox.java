// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GlyphCheckBox.java

package de.jensd.fx.glyphs.control;

import de.jensd.fx.glyphs.GlyphIcon;
import de.jensd.fx.glyphs.control.skin.GlyphCheckBoxSkin;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Skin;

public class GlyphCheckBox extends CheckBox
{

            public GlyphCheckBox()
            {
/*  22*/        super("");
            }

            public GlyphCheckBox(GlyphIcon glyphicon, GlyphIcon glyphicon1, String s)
            {
/*  26*/        super(s);
/*  27*/        setNotSelectedIcon(glyphicon);
/*  28*/        setSelectedIcon(glyphicon1);
            }

            protected Skin createDefaultSkin()
            {
/*  33*/        return new GlyphCheckBoxSkin(this);
            }

            public ObjectProperty notSelectedIconProperty()
            {
/*  37*/        if(notSelectedIcon == null)
/*  38*/            notSelectedIcon = new SimpleObjectProperty(new FontAwesomeIconView(FontAwesomeIcon.TOGGLE_OFF));
/*  40*/        return notSelectedIcon;
            }

            public GlyphIcon getNotSelectedIcon()
            {
/*  44*/        return (GlyphIcon)notSelectedIconProperty().get();
            }

            public void setNotSelectedIcon(GlyphIcon glyphicon)
            {
/*  48*/        notSelectedIconProperty().set(glyphicon);
            }

            public ObjectProperty selectedIconProperty()
            {
/*  52*/        if(selectedIcon == null)
/*  53*/            selectedIcon = new SimpleObjectProperty(new FontAwesomeIconView(FontAwesomeIcon.TOGGLE_ON));
/*  55*/        return selectedIcon;
            }

            public GlyphIcon getSelectedIcon()
            {
/*  59*/        return (GlyphIcon)selectedIconProperty().get();
            }

            public void setSelectedIcon(GlyphIcon glyphicon)
            {
/*  63*/        selectedIconProperty().set(glyphicon);
            }

            private ObjectProperty notSelectedIcon;
            private ObjectProperty selectedIcon;
}
