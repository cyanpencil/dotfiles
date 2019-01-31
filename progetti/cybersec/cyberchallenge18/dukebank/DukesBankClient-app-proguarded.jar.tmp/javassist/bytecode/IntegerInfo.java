// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConstPool.java

package javassist.bytecode;

import java.io.*;
import java.util.Map;

// Referenced classes of package javassist.bytecode:
//            ConstInfo, ConstPool

class IntegerInfo extends ConstInfo
{

            public IntegerInfo(int i, int j)
            {
/*1642*/        super(j);
/*1643*/        value = i;
            }

            public IntegerInfo(DataInputStream datainputstream, int i)
                throws IOException
            {
/*1647*/        super(i);
/*1648*/        value = datainputstream.readInt();
            }

            public int hashCode()
            {
/*1651*/        return value;
            }

            public boolean equals(Object obj)
            {
/*1654*/        return (obj instanceof IntegerInfo) && ((IntegerInfo)obj).value == value;
            }

            public int getTag()
            {
/*1657*/        return 3;
            }

            public int copy(ConstPool constpool, ConstPool constpool1, Map map)
            {
/*1660*/        return constpool1.addIntegerInfo(value);
            }

            public void write(DataOutputStream dataoutputstream)
                throws IOException
            {
/*1664*/        dataoutputstream.writeByte(3);
/*1665*/        dataoutputstream.writeInt(value);
            }

            public void print(PrintWriter printwriter)
            {
/*1669*/        printwriter.print("Integer ");
/*1670*/        printwriter.println(value);
            }

            static final int tag = 3;
            int value;
}
