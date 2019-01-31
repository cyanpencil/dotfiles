// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 

package com.owlike.genson.internal.asm;


final class Item
{

    Item()
    {
    }

    Item(int l)
    {
        a = l;
    }

    Item(int l, Item item)
    {
        a = l;
        b = item.b;
        c = item.c;
        d = item.d;
        g = item.g;
        h = item.h;
        i = item.i;
        j = item.j;
    }

    final void a(int l)
    {
        b = 3;
        c = l;
        j = 0x7fffffff & b + l;
    }

    final void a(long l)
    {
        b = 5;
        d = l;
        j = 0x7fffffff & b + (int)l;
    }

    final void a(float f)
    {
        b = 4;
        c = Float.floatToRawIntBits(f);
        j = 0x7fffffff & b + (int)f;
    }

    final void a(double d1)
    {
        b = 6;
        d = Double.doubleToRawLongBits(d1);
        j = 0x7fffffff & b + (int)d1;
    }

    final void a(int l, String s, String s1, String s2)
    {
        b = l;
        g = s;
        h = s1;
        i = s2;
        switch(l)
        {
        case 7: // '\007'
            c = 0;
            // fall through

        case 1: // '\001'
        case 8: // '\b'
        case 16: // '\020'
        case 30: // '\036'
            j = 0x7fffffff & l + s.hashCode();
            return;

        case 12: // '\f'
            j = 0x7fffffff & l + s.hashCode() * s1.hashCode();
            return;

        default:
            j = 0x7fffffff & l + s.hashCode() * s1.hashCode() * s2.hashCode();
            return;
        }
    }

    final void a(String s, String s1, int l)
    {
        b = 18;
        d = l;
        g = s;
        h = s1;
        j = 0x7fffffff & 18 + l * g.hashCode() * h.hashCode();
    }

    final void a(int l, int i1)
    {
        b = 33;
        c = l;
        j = i1;
    }

    final boolean a(Item item)
    {
        switch(b)
        {
        case 1: // '\001'
        case 7: // '\007'
        case 8: // '\b'
        case 16: // '\020'
        case 30: // '\036'
            return item.g.equals(g);

        case 5: // '\005'
        case 6: // '\006'
        case 32: // ' '
            return item.d == d;

        case 3: // '\003'
        case 4: // '\004'
            return item.c == c;

        case 31: // '\037'
            return item.c == c && item.g.equals(g);

        case 12: // '\f'
            return item.g.equals(g) && item.h.equals(h);

        case 18: // '\022'
            return item.d == d && item.g.equals(g) && item.h.equals(h);
        }
        return item.g.equals(g) && item.h.equals(h) && item.i.equals(i);
    }

    int a;
    int b;
    int c;
    long d;
    String g;
    String h;
    String i;
    int j;
    Item k;
}
