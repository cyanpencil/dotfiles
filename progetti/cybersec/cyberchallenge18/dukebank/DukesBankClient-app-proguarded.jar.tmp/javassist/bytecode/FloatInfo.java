// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConstPool.java

package javassist.bytecode;

import java.io.*;
import java.util.Map;

// Referenced classes of package javassist.bytecode:
//            ConstInfo, ConstPool

class FloatInfo extends ConstInfo
{

            public FloatInfo(float f, int i)
            {
/*1679*/        super(i);
/*1680*/        value = f;
            }

            public FloatInfo(DataInputStream datainputstream, int i)
                throws IOException
            {
/*1684*/        super(i);
/*1685*/        value = datainputstream.readFloat();
            }

            public int hashCode()
            {
/*1688*/        return Float.floatToIntBits(value);
            }

            public boolean equals(Object obj)
            {
/*1691*/        return (obj instanceof FloatInfo) && ((FloatInfo)obj).value == value;
            }

            public int getTag()
            {
/*1694*/        return 4;
            }

            public int copy(ConstPool constpool, ConstPool constpool1, Map map)
            {
/*1697*/        return constpool1.addFloatInfo(value);
            }

            public void write(DataOutputStream dataoutputstream)
                throws IOException
            {
/*1701*/        dataoutputstream.writeByte(4);
/*1702*/        dataoutputstream.writeFloat(value);
            }

            public void print(PrintWriter printwriter)
            {
/*1706*/        printwriter.print("Float ");
/*1707*/        printwriter.println(value);
            }

            static final int tag = 4;
            float value;
}
