// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 

package com.owlike.genson.internal.asm;


public final class Handle
{

    public Handle(int i, String s, String s1, String s2)
    {
        a = i;
        b = s;
        c = s1;
        d = s2;
    }

    public final int getTag()
    {
        return a;
    }

    public final String getOwner()
    {
        return b;
    }

    public final String getName()
    {
        return c;
    }

    public final String getDesc()
    {
        return d;
    }

    public final boolean equals(Object obj)
    {
        if(obj == this)
            return true;
        if(!(obj instanceof Handle))
            return false;
        obj = (Handle)obj;
        return a == ((Handle) (obj)).a && b.equals(((Handle) (obj)).b) && c.equals(((Handle) (obj)).c) && d.equals(((Handle) (obj)).d);
    }

    public final int hashCode()
    {
        return a + b.hashCode() * c.hashCode() * d.hashCode();
    }

    public final String toString()
    {
        return b + '.' + c + d + " (" + a + ')';
    }

    final int a;
    final String b;
    final String c;
    final String d;
}
