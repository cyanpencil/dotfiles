// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Descriptor.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            Descriptor

public static class param
{

            public boolean hasNext()
            {
/* 816*/        return index < desc.length();
            }

            public boolean isParameter()
            {
/* 822*/        return param;
            }

            public char currentChar()
            {
/* 827*/        return desc.charAt(curPos);
            }

            public boolean is2byte()
            {
                char c;
/* 833*/        return (c = currentChar()) == 'D' || c == 'J';
            }

            public int next()
            {
/* 842*/        int i = index;
                char c;
/* 843*/        if((c = desc.charAt(i)) == '(')
                {
/* 845*/            index++;
/* 846*/            c = desc.charAt(++i);
/* 847*/            param = true;
                }
/* 850*/        if(c == ')')
                {
/* 851*/            index++;
/* 852*/            c = desc.charAt(++i);
/* 853*/            param = false;
                }
/* 856*/        for(; c == '['; c = desc.charAt(++i));
/* 859*/        if(c == 'L')
                {
/* 860*/            if((i = desc.indexOf(';', i) + 1) <= 0)
/* 862*/                throw new IndexOutOfBoundsException("bad descriptor");
                } else
                {
/* 865*/            i++;
                }
/* 867*/        curPos = index;
/* 868*/        index = i;
/* 869*/        return curPos;
            }

            private String desc;
            private int index;
            private int curPos;
            private boolean param;

            public (String s)
            {
/* 807*/        desc = s;
/* 808*/        index = curPos = 0;
/* 809*/        param = false;
            }
}
