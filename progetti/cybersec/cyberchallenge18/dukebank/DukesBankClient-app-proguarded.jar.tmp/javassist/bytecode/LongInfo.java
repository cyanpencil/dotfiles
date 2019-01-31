// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConstPool.java

package javassist.bytecode;

import java.io.*;
import java.util.Map;

// Referenced classes of package javassist.bytecode:
//            ConstInfo, ConstPool

class LongInfo extends ConstInfo
{

            public LongInfo(long l, int i)
            {
/*1716*/        super(i);
/*1717*/        value = l;
            }

            public LongInfo(DataInputStream datainputstream, int i)
                throws IOException
            {
/*1721*/        super(i);
/*1722*/        value = datainputstream.readLong();
            }

            public int hashCode()
            {
/*1725*/        return (int)(value ^ value >>> 32);
            }

            public boolean equals(Object obj)
            {
/*1728*/        return (obj instanceof LongInfo) && ((LongInfo)obj).value == value;
            }

            public int getTag()
            {
/*1731*/        return 5;
            }

            public int copy(ConstPool constpool, ConstPool constpool1, Map map)
            {
/*1734*/        return constpool1.addLongInfo(value);
            }

            public void write(DataOutputStream dataoutputstream)
                throws IOException
            {
/*1738*/        dataoutputstream.writeByte(5);
/*1739*/        dataoutputstream.writeLong(value);
            }

            public void print(PrintWriter printwriter)
            {
/*1743*/        printwriter.print("Long ");
/*1744*/        printwriter.println(value);
            }

            static final int tag = 5;
            long value;
}
