// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConstPool.java

package javassist.bytecode;

import java.io.*;
import java.util.Map;

// Referenced classes of package javassist.bytecode:
//            ConstInfo, ConstPool

class Utf8Info extends ConstInfo
{

            public Utf8Info(String s, int i)
            {
/*1793*/        super(i);
/*1794*/        string = s;
            }

            public Utf8Info(DataInputStream datainputstream, int i)
                throws IOException
            {
/*1798*/        super(i);
/*1799*/        string = datainputstream.readUTF();
            }

            public int hashCode()
            {
/*1803*/        return string.hashCode();
            }

            public boolean equals(Object obj)
            {
/*1807*/        return (obj instanceof Utf8Info) && ((Utf8Info)obj).string.equals(string);
            }

            public int getTag()
            {
/*1810*/        return 1;
            }

            public int copy(ConstPool constpool, ConstPool constpool1, Map map)
            {
/*1813*/        return constpool1.addUtf8Info(string);
            }

            public void write(DataOutputStream dataoutputstream)
                throws IOException
            {
/*1817*/        dataoutputstream.writeByte(1);
/*1818*/        dataoutputstream.writeUTF(string);
            }

            public void print(PrintWriter printwriter)
            {
/*1822*/        printwriter.print("UTF8 \"");
/*1823*/        printwriter.print(string);
/*1824*/        printwriter.println("\"");
            }

            static final int tag = 1;
            String string;
}
