// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConstPool.java

package javassist.bytecode;

import java.io.*;
import java.util.Map;

// Referenced classes of package javassist.bytecode:
//            ConstInfo, ConstPool

class MethodHandleInfo extends ConstInfo
{

            public MethodHandleInfo(int i, int j, int k)
            {
/*1833*/        super(k);
/*1834*/        refKind = i;
/*1835*/        refIndex = j;
            }

            public MethodHandleInfo(DataInputStream datainputstream, int i)
                throws IOException
            {
/*1839*/        super(i);
/*1840*/        refKind = datainputstream.readUnsignedByte();
/*1841*/        refIndex = datainputstream.readUnsignedShort();
            }

            public int hashCode()
            {
/*1844*/        return refKind << 16 ^ refIndex;
            }

            public boolean equals(Object obj)
            {
/*1847*/        if(obj instanceof MethodHandleInfo)
/*1848*/            return ((MethodHandleInfo) (obj = (MethodHandleInfo)obj)).refKind == refKind && ((MethodHandleInfo) (obj)).refIndex == refIndex;
/*1852*/        else
/*1852*/            return false;
            }

            public int getTag()
            {
/*1855*/        return 15;
            }

            public int copy(ConstPool constpool, ConstPool constpool1, Map map)
            {
/*1858*/        return constpool1.addMethodHandleInfo(refKind, constpool.getItem(refIndex).copy(constpool, constpool1, map));
            }

            public void write(DataOutputStream dataoutputstream)
                throws IOException
            {
/*1863*/        dataoutputstream.writeByte(15);
/*1864*/        dataoutputstream.writeByte(refKind);
/*1865*/        dataoutputstream.writeShort(refIndex);
            }

            public void print(PrintWriter printwriter)
            {
/*1869*/        printwriter.print("MethodHandle #");
/*1870*/        printwriter.print(refKind);
/*1871*/        printwriter.print(", index #");
/*1872*/        printwriter.println(refIndex);
            }

            static final int tag = 15;
            int refKind;
            int refIndex;
}
