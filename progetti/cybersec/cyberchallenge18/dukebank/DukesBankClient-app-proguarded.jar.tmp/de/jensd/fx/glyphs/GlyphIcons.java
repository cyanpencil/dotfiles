// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GlyphIcons.java

package de.jensd.fx.glyphs;


public interface GlyphIcons
{

    public abstract String characterToString();

    public abstract String unicodeToString();

    public abstract String name();

    public abstract char getChar();

    public abstract String getFontFamily();
}
