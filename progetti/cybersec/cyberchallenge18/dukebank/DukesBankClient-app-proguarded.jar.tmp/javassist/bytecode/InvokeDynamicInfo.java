// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConstPool.java

package javassist.bytecode;

import java.io.*;
import java.util.Map;

// Referenced classes of package javassist.bytecode:
//            ConstInfo, ConstPool

class InvokeDynamicInfo extends ConstInfo
{

            public InvokeDynamicInfo(int i, int j, int k)
            {
/*1949*/        super(k);
/*1950*/        bootstrap = i;
/*1951*/        nameAndType = j;
            }

            public InvokeDynamicInfo(DataInputStream datainputstream, int i)
                throws IOException
            {
/*1955*/        super(i);
/*1956*/        bootstrap = datainputstream.readUnsignedShort();
/*1957*/        nameAndType = datainputstream.readUnsignedShort();
            }

            public int hashCode()
            {
/*1960*/        return bootstrap << 16 ^ nameAndType;
            }

            public boolean equals(Object obj)
            {
/*1963*/        if(obj instanceof InvokeDynamicInfo)
/*1964*/            return ((InvokeDynamicInfo) (obj = (InvokeDynamicInfo)obj)).bootstrap == bootstrap && ((InvokeDynamicInfo) (obj)).nameAndType == nameAndType;
/*1968*/        else
/*1968*/            return false;
            }

            public int getTag()
            {
/*1971*/        return 18;
            }

            public int copy(ConstPool constpool, ConstPool constpool1, Map map)
            {
/*1974*/        return constpool1.addInvokeDynamicInfo(bootstrap, constpool.getItem(nameAndType).copy(constpool, constpool1, map));
            }

            public void write(DataOutputStream dataoutputstream)
                throws IOException
            {
/*1979*/        dataoutputstream.writeByte(18);
/*1980*/        dataoutputstream.writeShort(bootstrap);
/*1981*/        dataoutputstream.writeShort(nameAndType);
            }

            public void print(PrintWriter printwriter)
            {
/*1985*/        printwriter.print("InvokeDynamic #");
/*1986*/        printwriter.print(bootstrap);
/*1987*/        printwriter.print(", name&type #");
/*1988*/        printwriter.println(nameAndType);
            }

            static final int tag = 18;
            int bootstrap;
            int nameAndType;
}
