// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConstPool.java

package javassist.bytecode;

import java.io.*;
import java.util.Map;

// Referenced classes of package javassist.bytecode:
//            ConstInfo, ConstPool

class DoubleInfo extends ConstInfo
{

            public DoubleInfo(double d, int i)
            {
/*1753*/        super(i);
/*1754*/        value = d;
            }

            public DoubleInfo(DataInputStream datainputstream, int i)
                throws IOException
            {
/*1758*/        super(i);
/*1759*/        value = datainputstream.readDouble();
            }

            public int hashCode()
            {
                long l;
/*1763*/        return (int)((l = Double.doubleToLongBits(value)) ^ l >>> 32);
            }

            public boolean equals(Object obj)
            {
/*1768*/        return (obj instanceof DoubleInfo) && ((DoubleInfo)obj).value == value;
            }

            public int getTag()
            {
/*1771*/        return 6;
            }

            public int copy(ConstPool constpool, ConstPool constpool1, Map map)
            {
/*1774*/        return constpool1.addDoubleInfo(value);
            }

            public void write(DataOutputStream dataoutputstream)
                throws IOException
            {
/*1778*/        dataoutputstream.writeByte(6);
/*1779*/        dataoutputstream.writeDouble(value);
            }

            public void print(PrintWriter printwriter)
            {
/*1783*/        printwriter.print("Double ");
/*1784*/        printwriter.println(value);
            }

            static final int tag = 6;
            double value;
}
