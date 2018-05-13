// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Descriptor.java

package javassist.bytecode;

import javassist.CtClass;

// Referenced classes of package javassist.bytecode:
//            Descriptor

static class 
{

            static String toString(String s)
            {
/* 743*/        StringBuffer stringbuffer = new StringBuffer();
/* 744*/        if(s.charAt(0) == '(')
                {
/* 745*/            int i = 1;
/* 746*/            stringbuffer.append('(');
/* 747*/            for(; s.charAt(i) != ')'; i = readType(stringbuffer, i, s))
/* 748*/                if(i > 1)
/* 749*/                    stringbuffer.append(',');

/* 754*/            stringbuffer.append(')');
                } else
                {
/* 757*/            readType(stringbuffer, 0, s);
                }
/* 759*/        return stringbuffer.toString();
            }

            static int readType(StringBuffer stringbuffer, int i, String s)
            {
/* 763*/        char c = s.charAt(i);
/* 764*/        int j = 0;
/* 765*/        for(; c == '['; c = s.charAt(++i))
/* 766*/            j++;

/* 770*/        if(c == 'L')
                {
/* 772*/            while((c = s.charAt(++i)) != ';') 
                    {
/* 776*/                if(c == '/')
/* 777*/                    c = '.';
/* 779*/                stringbuffer.append(c);
                    }
                } else
                {
/* 782*/            s = Descriptor.toPrimitiveClass(c);
/* 783*/            stringbuffer.append(s.getName());
                }
/* 786*/        while(j-- > 0) 
/* 787*/            stringbuffer.append("[]");
/* 789*/        return i + 1;
            }

            ()
            {
            }
}
