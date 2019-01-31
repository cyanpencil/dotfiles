// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SignatureAttribute.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            SignatureAttribute

public static class name extends name
{

            public String getName()
            {
/* 871*/        return name;
            }

            public String toString()
            {
/* 878*/        return name;
            }

            void encode(StringBuffer stringbuffer)
            {
/* 882*/        stringbuffer.append('T').append(name).append(';');
            }

            String name;

            (String s, int i, int j)
            {
/* 855*/        name = s.substring(i, j);
            }

            public name(String s)
            {
/* 864*/        name = s;
            }
}
