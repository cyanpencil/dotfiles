// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConstPool.java

package javassist.bytecode;

import java.io.*;
import java.util.Map;

// Referenced classes of package javassist.bytecode:
//            ConstInfo, ConstPool

class StringInfo extends ConstInfo
{

            public StringInfo(int i, int j)
            {
/*1605*/        super(j);
/*1606*/        string = i;
            }

            public StringInfo(DataInputStream datainputstream, int i)
                throws IOException
            {
/*1610*/        super(i);
/*1611*/        string = datainputstream.readUnsignedShort();
            }

            public int hashCode()
            {
/*1614*/        return string;
            }

            public boolean equals(Object obj)
            {
/*1617*/        return (obj instanceof StringInfo) && ((StringInfo)obj).string == string;
            }

            public int getTag()
            {
/*1620*/        return 8;
            }

            public int copy(ConstPool constpool, ConstPool constpool1, Map map)
            {
/*1623*/        return constpool1.addStringInfo(constpool.getUtf8Info(string));
            }

            public void write(DataOutputStream dataoutputstream)
                throws IOException
            {
/*1627*/        dataoutputstream.writeByte(8);
/*1628*/        dataoutputstream.writeShort(string);
            }

            public void print(PrintWriter printwriter)
            {
/*1632*/        printwriter.print("String #");
/*1633*/        printwriter.println(string);
            }

            static final int tag = 8;
            int string;
}
