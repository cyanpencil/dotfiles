// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LineNumberAttribute.java

package javassist.bytecode;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

// Referenced classes of package javassist.bytecode:
//            AttributeInfo, ByteArray, ConstPool

public class LineNumberAttribute extends AttributeInfo
{
    public static class Pc
    {

                public int index;
                public int line;

                public Pc()
                {
                }
    }


            LineNumberAttribute(ConstPool constpool, int i, DataInputStream datainputstream)
                throws IOException
            {
/*  35*/        super(constpool, i, datainputstream);
            }

            private LineNumberAttribute(ConstPool constpool, byte abyte0[])
            {
/*  39*/        super(constpool, "LineNumberTable", abyte0);
            }

            public int tableLength()
            {
/*  47*/        return ByteArray.readU16bit(info, 0);
            }

            public int startPc(int i)
            {
/*  58*/        return ByteArray.readU16bit(info, (i << 2) + 2);
            }

            public int lineNumber(int i)
            {
/*  69*/        return ByteArray.readU16bit(info, (i << 2) + 4);
            }

            public int toLineNumber(int i)
            {
/*  78*/        int j = tableLength();
/*  79*/        int k = 0;
/*  80*/        do
                {
/*  80*/            if(k >= j)
/*  81*/                break;
/*  81*/            if(i < startPc(k))
                    {
/*  82*/                if(k == 0)
/*  83*/                    return lineNumber(0);
/*  80*/                break;
                    }
/*  80*/            k++;
                } while(true);
/*  87*/        return lineNumber(k - 1);
            }

            public int toStartPc(int i)
            {
/*  98*/        int j = tableLength();
/*  99*/        for(int k = 0; k < j; k++)
/* 100*/            if(i == lineNumber(k))
/* 101*/                return startPc(k);

/* 103*/        return -1;
            }

            public Pc toNearPc(int i)
            {
/* 130*/        int j = tableLength();
/* 131*/        int k = 0;
/* 132*/        int l = 0;
/* 133*/        if(j > 0)
                {
/* 134*/            l = lineNumber(0) - i;
/* 135*/            k = startPc(0);
                }
/* 138*/        for(int i1 = 1; i1 < j; i1++)
                {
                    int j1;
/* 139*/            if((j1 = lineNumber(i1) - i) < 0 && j1 > l || j1 >= 0 && (j1 < l || l < 0))
                    {
/* 142*/                l = j1;
/* 143*/                k = startPc(i1);
                    }
                }

                Pc pc;
/* 147*/        (pc = new Pc()).index = k;
/* 149*/        pc.line = i + l;
/* 150*/        return pc;
            }

            public AttributeInfo copy(ConstPool constpool, Map map)
            {
                int i;
/* 160*/        byte abyte0[] = new byte[i = (map = info).length];
/* 163*/        for(int j = 0; j < i; j++)
/* 164*/            abyte0[j] = map[j];

                LineNumberAttribute linenumberattribute;
/* 166*/        return linenumberattribute = new LineNumberAttribute(constpool, abyte0);
            }

            void shiftPc(int i, int j, boolean flag)
            {
/* 174*/        int k = tableLength();
/* 175*/        for(int l = 0; l < k; l++)
                {
/* 176*/            int i1 = (l << 2) + 2;
                    int j1;
/* 177*/            if((j1 = ByteArray.readU16bit(info, i1)) > i || flag && j1 == i)
/* 179*/                ByteArray.write16bit(j1 + j, info, i1);
                }

            }

            public static final String tag = "LineNumberTable";
}
